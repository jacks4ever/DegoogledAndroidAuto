package com.degoogled.androidauto.data.map

import android.content.Context
import android.util.Log
import com.degoogled.androidauto.data.model.Location
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder

/**
 * Geocoder implementation using Nominatim for offline geocoding.
 */
class NominatimGeocoder(private val context: Context) {

    /**
     * Search for locations by name.
     */
    suspend fun search(query: String, limit: Int = 5): Result<List<Location>> = withContext(Dispatchers.IO) {
        try {
            // In a real implementation, this would use a local Nominatim instance
            // For now, use the public Nominatim API
            val encodedQuery = URLEncoder.encode(query, "UTF-8")
            val url = URL("https://nominatim.openstreetmap.org/search?q=$encodedQuery&format=json&limit=$limit")
            
            val connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "GET"
            connection.setRequestProperty("User-Agent", "DegoogledAndroidAuto")
            
            val responseCode = connection.responseCode
            if (responseCode == HttpURLConnection.HTTP_OK) {
                val reader = BufferedReader(InputStreamReader(connection.inputStream))
                val response = StringBuilder()
                var line: String?
                while (reader.readLine().also { line = it } != null) {
                    response.append(line)
                }
                reader.close()
                
                val jsonArray = JSONArray(response.toString())
                val locations = mutableListOf<Location>()
                
                for (i in 0 until jsonArray.length()) {
                    val jsonObject = jsonArray.getJSONObject(i)
                    val lat = jsonObject.getDouble("lat")
                    val lon = jsonObject.getDouble("lon")
                    val name = jsonObject.getString("display_name")
                    
                    locations.add(Location(lat, lon, name))
                }
                
                Result.success(locations)
            } else {
                Result.failure(Exception("HTTP error: $responseCode"))
            }
        } catch (e: Exception) {
            Log.e(TAG, "Search failed", e)
            Result.failure(e)
        }
    }
    
    /**
     * Get the address for a location.
     */
    suspend fun reverseGeocode(latitude: Double, longitude: Double): Result<String> = withContext(Dispatchers.IO) {
        try {
            // In a real implementation, this would use a local Nominatim instance
            // For now, use the public Nominatim API
            val url = URL("https://nominatim.openstreetmap.org/reverse?lat=$latitude&lon=$longitude&format=json")
            
            val connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "GET"
            connection.setRequestProperty("User-Agent", "DegoogledAndroidAuto")
            
            val responseCode = connection.responseCode
            if (responseCode == HttpURLConnection.HTTP_OK) {
                val reader = BufferedReader(InputStreamReader(connection.inputStream))
                val response = StringBuilder()
                var line: String?
                while (reader.readLine().also { line = it } != null) {
                    response.append(line)
                }
                reader.close()
                
                val jsonObject = JSONObject(response.toString())
                val address = jsonObject.getString("display_name")
                
                Result.success(address)
            } else {
                Result.failure(Exception("HTTP error: $responseCode"))
            }
        } catch (e: Exception) {
            Log.e(TAG, "Reverse geocoding failed", e)
            Result.failure(e)
        }
    }
    
    companion object {
        private const val TAG = "NominatimGeocoder"
    }
}