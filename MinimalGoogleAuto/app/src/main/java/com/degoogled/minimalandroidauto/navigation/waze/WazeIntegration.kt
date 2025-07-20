package com.degoogled.minimalandroidauto.navigation.waze

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.util.Log
import com.degoogled.minimalandroidauto.network.NetworkBlocker
import com.degoogled.minimalandroidauto.utils.PackageUtils
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
        private const val WAZE_PLAY_STORE_URL = "https://play.google.com/store/apps/details?id=com.waze"
        private const val WAZE_AURORA_URL = "market://details?id=com.waze"
        
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
        return PackageUtils.isPackageInstalled(context, WAZE_PACKAGE)
    }
    
    /**
     * Get the intent to install Waze (tries Aurora Store first, then Play Store)
     */
    fun getWazeInstallIntent(): Intent {
        // Check if Aurora Store is installed
        if (PackageUtils.isPackageInstalled(context, "com.aurora.store")) {
            // If Aurora Store is installed, open it to the Waze page
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setPackage("com.aurora.store")
            intent.data = Uri.parse(WAZE_AURORA_URL)
            return intent
        } else {
            // Fallback to Play Store or direct download
            return Intent(Intent.ACTION_VIEW, Uri.parse(WAZE_PLAY_STORE_URL))
        }
    }
    
    /**
     * Navigate to coordinates using Waze
     */
    suspend fun navigateToCoordinates(latitude: Double, longitude: Double): Boolean = withContext(Dispatchers.IO) {
        try {
            // Apply privacy protections before launching Waze
            applyPrivacyProtections()
            
            if (isWazeInstalled()) {
                // Use deep link if Waze is installed
                val wazeUri = Uri.parse(String.format(Locale.US, WAZE_URI_FORMAT, latitude, longitude))
                val intent = Intent(Intent.ACTION_VIEW, wazeUri)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                
                // Launch Waze
                context.startActivity(intent)
            } else {
                // Prompt to install Waze
                Log.d(TAG, "Waze is not installed, prompting to install")
                val installIntent = getWazeInstallIntent()
                installIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(installIntent)
                
                // Return false since navigation didn't start
                return@withContext false
            }
            
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
            
            if (isWazeInstalled()) {
                // Use deep link if Waze is installed
                val wazeUri = Uri.parse(String.format(Locale.US, WAZE_SEARCH_URI_FORMAT, encodedAddress))
                val intent = Intent(Intent.ACTION_VIEW, wazeUri)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                
                // Launch Waze
                context.startActivity(intent)
            } else {
                // Prompt to install Waze
                Log.d(TAG, "Waze is not installed, prompting to install")
                val installIntent = getWazeInstallIntent()
                installIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(installIntent)
                
                // Return false since navigation didn't start
                return@withContext false
            }
            
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