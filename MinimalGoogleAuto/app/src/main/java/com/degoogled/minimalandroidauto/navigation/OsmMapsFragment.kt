package com.degoogled.minimalandroidauto.navigation

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.degoogled.minimalandroidauto.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay
import java.io.File

/**
 * Fragment for OpenStreetMap navigation
 */
class OsmMapsFragment : Fragment(), LocationListener {

    companion object {
        private const val TAG = "OsmMapsFragment"
        private const val REQUEST_PERMISSIONS_REQUEST_CODE = 1
        private const val DEFAULT_ZOOM = 15.0
    }

    private lateinit var mapView: MapView
    private lateinit var fabMyLocation: FloatingActionButton
    private lateinit var fabStartNavigation: FloatingActionButton
    
    private var locationManager: LocationManager? = null
    private var myLocationOverlay: MyLocationNewOverlay? = null
    private var destinationMarker: Marker? = null
    private var currentLocation: GeoPoint? = null
    private var isNavigating = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_osm_maps, container, false)
        
        // Initialize OSMDroid configuration
        val ctx = requireActivity().applicationContext
        Configuration.getInstance().load(ctx, androidx.preference.PreferenceManager.getDefaultSharedPreferences(ctx))
        Configuration.getInstance().userAgentValue = requireActivity().packageName
        
        // Initialize views
        mapView = view.findViewById(R.id.mapView)
        fabMyLocation = view.findViewById(R.id.fabMyLocation)
        fabStartNavigation = view.findViewById(R.id.fabStartNavigation)
        
        // Configure map
        mapView.setTileSource(TileSourceFactory.MAPNIK)
        mapView.setMultiTouchControls(true)
        mapView.controller.setZoom(DEFAULT_ZOOM)
        
        // Set up location overlay
        val locationProvider = GpsMyLocationProvider(requireContext())
        myLocationOverlay = MyLocationNewOverlay(locationProvider, mapView)
        myLocationOverlay?.enableMyLocation()
        mapView.overlays.add(myLocationOverlay)
        
        // Set up buttons
        fabMyLocation.setOnClickListener {
            centerMapOnMyLocation()
        }
        
        fabStartNavigation.setOnClickListener {
            if (destinationMarker != null) {
                startNavigation()
            }
        }
        
        // Request location permissions if needed
        requestPermissionsIfNecessary()
        
        return view
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
        startLocationUpdates()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
        stopLocationUpdates()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isNavigating) {
            stopNavigation()
        }
    }

    /**
     * Request location permissions if necessary
     */
    private fun requestPermissionsIfNecessary() {
        val permissions = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        
        val permissionsToRequest = permissions.filter {
            ContextCompat.checkSelfPermission(requireContext(), it) != PackageManager.PERMISSION_GRANTED
        }.toTypedArray()
        
        if (permissionsToRequest.isNotEmpty()) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                permissionsToRequest,
                REQUEST_PERMISSIONS_REQUEST_CODE
            )
        } else {
            // Permissions already granted, start location updates
            startLocationUpdates()
        }
    }

    /**
     * Start location updates
     */
    private fun startLocationUpdates() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            locationManager = requireContext().getSystemService(Context.LOCATION_SERVICE) as LocationManager
            locationManager?.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                1000, // 1 second
                10f, // 10 meters
                this
            )
        }
    }

    /**
     * Stop location updates
     */
    private fun stopLocationUpdates() {
        locationManager?.removeUpdates(this)
    }

    /**
     * Center map on current location
     */
    private fun centerMapOnMyLocation() {
        currentLocation?.let {
            mapView.controller.animateTo(it)
            mapView.controller.setZoom(DEFAULT_ZOOM)
        } ?: run {
            // If current location is not available, try to get it from the overlay
            myLocationOverlay?.myLocation?.let {
                val geoPoint = GeoPoint(it)
                mapView.controller.animateTo(geoPoint)
                mapView.controller.setZoom(DEFAULT_ZOOM)
                currentLocation = geoPoint
            }
        }
    }

    /**
     * Search for a location
     */
    fun searchLocation(query: String) {
        // This would normally use a geocoder to search for the location
        // For now, just log the search query
        Log.d(TAG, "Searching for location: $query")
        
        // TODO: Implement geocoding to search for the location
    }

    /**
     * Navigate to coordinates
     */
    fun navigateToCoordinates(latitude: Double, longitude: Double) {
        val destination = GeoPoint(latitude, longitude)
        
        // Add a marker at the destination
        if (destinationMarker == null) {
            destinationMarker = Marker(mapView)
        }
        
        destinationMarker?.position = destination
        destinationMarker?.title = "Destination"
        destinationMarker?.snippet = "Lat: $latitude, Lon: $longitude"
        destinationMarker?.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
        
        if (!mapView.overlays.contains(destinationMarker)) {
            mapView.overlays.add(destinationMarker)
        }
        
        // Center map on destination
        mapView.controller.animateTo(destination)
        mapView.controller.setZoom(DEFAULT_ZOOM)
        
        // Refresh map
        mapView.invalidate()
    }

    /**
     * Start navigation
     */
    private fun startNavigation() {
        if (isNavigating) {
            return
        }
        
        isNavigating = true
        
        // This would normally start the actual navigation
        // For now, just log that navigation has started
        Log.d(TAG, "Starting navigation to ${destinationMarker?.position}")
        
        // Change FAB to stop navigation
        fabStartNavigation.setImageResource(android.R.drawable.ic_menu_close_clear_cancel)
    }

    /**
     * Stop navigation
     */
    private fun stopNavigation() {
        if (!isNavigating) {
            return
        }
        
        isNavigating = false
        
        // This would normally stop the actual navigation
        // For now, just log that navigation has stopped
        Log.d(TAG, "Stopping navigation")
        
        // Change FAB back to start navigation
        fabStartNavigation.setImageResource(android.R.drawable.ic_menu_directions)
    }

    // LocationListener methods
    override fun onLocationChanged(location: Location) {
        currentLocation = GeoPoint(location.latitude, location.longitude)
        
        // If we're navigating, update the route
        if (isNavigating) {
            // This would normally update the navigation route
            // For now, just log the location update
            Log.d(TAG, "Location updated: ${location.latitude}, ${location.longitude}")
        }
    }
}