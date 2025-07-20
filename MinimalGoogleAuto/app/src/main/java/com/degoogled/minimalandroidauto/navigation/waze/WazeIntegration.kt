package com.degoogled.minimalandroidauto.navigation.waze

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.util.Log
import com.degoogled.minimalandroidauto.network.NetworkBlocker
import com.degoogled.minimalandroidauto.utils.PrivacyPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.URLEncoder
import java.util.Locale

/**
 * Integration with Waze for navigation while maintaining privacy
 */
class WazeIntegration(private val context: Context) {

    companion object {
        private const val TAG = "WazeIntegration"
        private const val WAZE_PACKAGE = "com.waze"
        
        // Waze deep link formats
        private const val WAZE_URI_FORMAT = "waze://?ll=%f,%f&navigate=yes"
        private const val WAZE_SEARCH_URI_FORMAT = "waze://?q=%s&navigate=yes"
        private const val WAZE_FALLBACK_URI_FORMAT = "https://waze.com/ul?ll=%f,%f&navigate=yes"
        private const val WAZE_FALLBACK_SEARCH_URI_FORMAT = "https://waze.com/ul?q=%s&navigate=yes"
    }

    private val networkBlocker = NetworkBlocker.getInstance(context)
    
    /**
     * Check if Waze is installed
     */
    fun isWazeInstalled(): Boolean {
        return try {
            context.packageManager.getPackageInfo(WAZE_PACKAGE, 0)
            true
        } catch (e: PackageManager.NameNotFoundException) {
            false
        }
    }
    
    /**
     * Navigate to coordinates using Waze
     */
    suspend fun navigateToCoordinates(latitude: Double, longitude: Double): Boolean = withContext(Dispatchers.IO) {
        try {
            // Apply privacy protections before launching Waze
            applyPrivacyProtections()
            
            // Create intent to launch Waze
            val wazeUri = if (isWazeInstalled()) {
                // Use deep link if Waze is installed
                Uri.parse(String.format(Locale.US, WAZE_URI_FORMAT, latitude, longitude))
            } else {
                // Use web fallback if Waze is not installed
                Uri.parse(String.format(Locale.US, WAZE_FALLBACK_URI_FORMAT, latitude, longitude))
            }
            
            val intent = Intent(Intent.ACTION_VIEW, wazeUri)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            
            // Launch Waze
            context.startActivity(intent)
            
            Log.d(TAG, "Launched Waze navigation to $latitude, $longitude")
            true
        } catch (e: Exception) {
            Log.e(TAG, "Error launching Waze", e)
            false
        }
    }
    
    /**
     * Navigate to address or place using Waze
     */
    suspend fun navigateToAddress(address: String): Boolean = withContext(Dispatchers.IO) {
        try {
            // Apply privacy protections before launching Waze
            applyPrivacyProtections()
            
            // Encode the address
            val encodedAddress = URLEncoder.encode(address, "UTF-8")
            
            // Create intent to launch Waze
            val wazeUri = if (isWazeInstalled()) {
                // Use deep link if Waze is installed
                Uri.parse(String.format(Locale.US, WAZE_SEARCH_URI_FORMAT, encodedAddress))
            } else {
                // Use web fallback if Waze is not installed
                Uri.parse(String.format(Locale.US, WAZE_FALLBACK_SEARCH_URI_FORMAT, encodedAddress))
            }
            
            val intent = Intent(Intent.ACTION_VIEW, wazeUri)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            
            // Launch Waze
            context.startActivity(intent)
            
            Log.d(TAG, "Launched Waze navigation to $address")
            true
        } catch (e: Exception) {
            Log.e(TAG, "Error launching Waze", e)
            false
        }
    }
    
    /**
     * Apply privacy protections before launching Waze
     */
    private fun applyPrivacyProtections() {
        if (PrivacyPreferences.isPrivacyModeEnabled(context)) {
            // Enable network blocking for Waze
            WazeNetworkFilter.enableFiltering(context)
            
            // Log that we're launching Waze with privacy protections
            Log.d(TAG, "Launching Waze with privacy protections enabled")
        }
    }
}