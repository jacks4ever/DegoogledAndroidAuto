package com.degoogled.androidauto.data.map

import android.content.Context
import android.util.Log
import com.degoogled.androidauto.data.model.Instruction
import com.degoogled.androidauto.data.model.Location
import com.degoogled.androidauto.data.model.Route
import com.graphhopper.GHRequest
import com.graphhopper.GHResponse
import com.graphhopper.GraphHopper
import com.graphhopper.config.CHProfile
import com.graphhopper.config.Profile
import com.graphhopper.util.Parameters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.IOException

/**
 * Router implementation using GraphHopper for offline routing.
 */
class GraphHopperRouter(private val context: Context) {

    private var graphHopper: GraphHopper? = null
    
    suspend fun initialize(): Boolean = withContext(Dispatchers.IO) {
        try {
            if (graphHopper == null) {
                val osmFile = File(context.filesDir, "maps/map.osm.pbf")
                if (!osmFile.exists()) {
                    // In a real implementation, this would download the map if not present
                    // For now, assume the map is already downloaded
                    return@withContext false
                }
                
                val graphFolder = File(context.filesDir, "maps/graph-cache")
                if (!graphFolder.exists()) {
                    graphFolder.mkdirs()
                }
                
                graphHopper = GraphHopper().apply {
                    osmFile = osmFile.absolutePath
                    graphHopperLocation = graphFolder.absolutePath
                    
                    // Create profiles for different vehicle types
                    setProfiles(
                        Profile("car").setVehicle("car").setWeighting("fastest"),
                        Profile("bike").setVehicle("bike").setWeighting("fastest"),
                        Profile("foot").setVehicle("foot").setWeighting("fastest")
                    )
                    
                    // Enable contraction hierarchies for faster routing
                    setCHProfiles(
                        CHProfile("car"),
                        CHProfile("bike"),
                        CHProfile("foot")
                    )
                    
                    importOrLoad()
                }
            }
            
            true
        } catch (e: IOException) {
            Log.e(TAG, "Failed to initialize GraphHopper", e)
            false
        }
    }
    
    suspend fun calculateRoute(start: Location, end: Location, profile: String = "car"): Result<Route> = withContext(Dispatchers.IO) {
        try {
            if (graphHopper == null) {
                if (!initialize()) {
                    return@withContext Result.failure(IOException("GraphHopper not initialized"))
                }
            }
            
            val request = GHRequest(
                start.latitude, start.longitude,
                end.latitude, end.longitude
            ).apply {
                setProfile(profile)
                setLocale(java.util.Locale.US)
                
                // Request turn instructions
                hints.put(Parameters.Details.PATH_DETAILS, listOf("time", "distance"))
                hints.put("instructions", true)
            }
            
            val response: GHResponse = graphHopper!!.route(request)
            
            if (response.hasErrors()) {
                return@withContext Result.failure(IOException("Routing failed: ${response.errors.joinToString()}"))
            }
            
            val path = response.best
            
            // Extract route points
            val points = mutableListOf<Location>()
            val pointList = path.points
            for (i in 0 until pointList.size()) {
                points.add(Location(
                    pointList.getLat(i),
                    pointList.getLon(i),
                    "Point $i"
                ))
            }
            
            // Extract instructions
            val instructions = mutableListOf<Instruction>()
            path.instructions.forEachIndexed { index, instr ->
                val startPoint = if (instr.points.size() > 0) {
                    Location(
                        instr.points.getLat(0),
                        instr.points.getLon(0),
                        "Instruction Start $index"
                    )
                } else null
                
                val endPoint = if (instr.points.size() > 1) {
                    Location(
                        instr.points.getLat(instr.points.size() - 1),
                        instr.points.getLon(instr.points.size() - 1),
                        "Instruction End $index"
                    )
                } else null
                
                instructions.add(
                    Instruction(
                        text = instr.getTurnDescription(java.util.Locale.US),
                        distance = instr.distance.toInt(),
                        startLocation = startPoint,
                        endLocation = endPoint
                    )
                )
            }
            
            val route = Route(
                points = points,
                instructions = instructions,
                distance = path.distance.toInt(),
                duration = path.time.toInt() / 1000 // Convert from milliseconds to seconds
            )
            
            Result.success(route)
        } catch (e: Exception) {
            Log.e(TAG, "Route calculation failed", e)
            Result.failure(e)
        }
    }
    
    fun release() {
        graphHopper?.close()
        graphHopper = null
    }
    
    companion object {
        private const val TAG = "GraphHopperRouter"
    }
}