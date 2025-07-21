package com.degoogled.minimalandroidauto.navigation

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.degoogled.minimalandroidauto.utils.logDebug
import org.osmdroid.util.GeoPoint

/**
 * Service for handling navigation using OpenStreetMap
 */
class NavigationService : Service() {

    companion object {
        private const val TAG = "NavigationService"
        
        // Actions
        const val ACTION_START_NAVIGATION = "com.degoogled.minimalandroidauto.action.START_NAVIGATION"
        const val ACTION_STOP_NAVIGATION = "com.degoogled.minimalandroidauto.action.STOP_NAVIGATION"
        
        // Extras
        const val EXTRA_LATITUDE = "com.degoogled.minimalandroidauto.extra.LATITUDE"
        const val EXTRA_LONGITUDE = "com.degoogled.minimalandroidauto.extra.LONGITUDE"
    }

    private var isNavigating = false
    private var currentDestination: GeoPoint? = null

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.let {
            when (it.action) {
                ACTION_START_NAVIGATION -> {
                    val latitude = it.getDoubleExtra(EXTRA_LATITUDE, 0.0)
                    val longitude = it.getDoubleExtra(EXTRA_LONGITUDE, 0.0)
                    startNavigation(GeoPoint(latitude, longitude))
                }
                ACTION_STOP_NAVIGATION -> {
                    stopNavigation()
                }
            }
        }
        return START_STICKY
    }

    /**
     * Start navigation to a destination
     */
    private fun startNavigation(destination: GeoPoint) {
        isNavigating = true
        currentDestination = destination
        logDebug("Starting navigation to $destination")
        
        // This would normally start the actual navigation
        // For now, just log that navigation has started
    }

    /**
     * Stop navigation
     */
    private fun stopNavigation() {
        isNavigating = false
        currentDestination = null
        logDebug("Stopping navigation")
        
        // This would normally stop the actual navigation
        // For now, just log that navigation has stopped
    }


}