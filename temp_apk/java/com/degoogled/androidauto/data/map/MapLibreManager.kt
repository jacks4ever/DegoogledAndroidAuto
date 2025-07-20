package com.degoogled.androidauto.data.map

import android.content.Context
import android.util.Log
import com.degoogled.androidauto.data.model.Location
import com.degoogled.androidauto.data.model.Route
import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.camera.CameraPosition
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.maps.MapView
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.maps.Style
import com.mapbox.mapboxsdk.style.layers.LineLayer
import com.mapbox.mapboxsdk.style.layers.Property
import com.mapbox.mapboxsdk.style.layers.PropertyFactory
import com.mapbox.mapboxsdk.style.sources.GeoJsonSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.io.File
import java.io.IOException

/**
 * Manager for MapLibre map operations.
 */
class MapLibreManager(private val context: Context) {

    private var mapboxMap: MapboxMap? = null
    private var mapView: MapView? = null
    
    private val styleUrl = "asset://osm_style.json"
    
    suspend fun initialize(mapView: MapView): Boolean = withContext(Dispatchers.Main) {
        this@MapLibreManager.mapView = mapView
        
        try {
            // Initialize MapLibre
            Mapbox.getInstance(context)
            
            var initialized = false
            mapView.getMapAsync { map ->
                mapboxMap = map
                map.setStyle(styleUrl) { style ->
                    // Map is ready
                    initialized = true
                }
            }
            
            // Wait for initialization
            while (!initialized) {
                kotlinx.coroutines.delay(100)
            }
            
            true
        } catch (e: Exception) {
            Log.e(TAG, "Failed to initialize MapLibre", e)
            false
        }
    }
    
    fun centerOnLocation(location: Location) {
        mapboxMap?.let { map ->
            val position = CameraPosition.Builder()
                .target(LatLng(location.latitude, location.longitude))
                .zoom(15.0)
                .build()
            
            map.animateCamera(CameraUpdateFactory.newCameraPosition(position))
        }
    }
    
    fun drawRoute(route: Route) {
        mapboxMap?.let { map ->
            map.getStyle { style ->
                // Create a GeoJSON source with the route
                val routeGeoJson = createRouteGeoJson(route)
                
                // Remove existing route if any
                if (style.getSource(ROUTE_SOURCE_ID) != null) {
                    style.removeLayer(ROUTE_LAYER_ID)
                    style.removeSource(ROUTE_SOURCE_ID)
                }
                
                // Add the source and layer
                style.addSource(GeoJsonSource(ROUTE_SOURCE_ID, routeGeoJson))
                
                // Add a line layer to display the route
                val routeLayer = LineLayer(ROUTE_LAYER_ID, ROUTE_SOURCE_ID)
                routeLayer.setProperties(
                    PropertyFactory.lineColor("#4882c5"),
                    PropertyFactory.lineWidth(5f),
                    PropertyFactory.lineCap(Property.LINE_CAP_ROUND),
                    PropertyFactory.lineJoin(Property.LINE_JOIN_ROUND)
                )
                
                style.addLayer(routeLayer)
                
                // Zoom to show the entire route
                zoomToRoute(route)
            }
        }
    }
    
    private fun createRouteGeoJson(route: Route): String {
        // In a real implementation, this would create a GeoJSON representation of the route
        // For now, create a simple LineString with the route points
        val coordinates = StringBuilder()
        
        route.points.forEachIndexed { index, point ->
            coordinates.append("[${point.longitude}, ${point.latitude}]")
            if (index < route.points.size - 1) {
                coordinates.append(",")
            }
        }
        
        return """
            {
                "type": "Feature",
                "properties": {},
                "geometry": {
                    "type": "LineString",
                    "coordinates": [$coordinates]
                }
            }
        """.trimIndent()
    }
    
    private fun zoomToRoute(route: Route) {
        if (route.points.isEmpty()) return
        
        // In a real implementation, this would calculate the bounds of the route
        // For now, just zoom to the first point
        centerOnLocation(route.points.first())
    }
    
    fun clearRoute() {
        mapboxMap?.let { map ->
            map.getStyle { style ->
                if (style.getSource(ROUTE_SOURCE_ID) != null) {
                    style.removeLayer(ROUTE_LAYER_ID)
                    style.removeSource(ROUTE_SOURCE_ID)
                }
            }
        }
    }
    
    fun onDestroy() {
        mapView?.onDestroy()
        mapView = null
        mapboxMap = null
    }
    
    companion object {
        private const val TAG = "MapLibreManager"
        private const val ROUTE_SOURCE_ID = "route-source"
        private const val ROUTE_LAYER_ID = "route-layer"
    }
}