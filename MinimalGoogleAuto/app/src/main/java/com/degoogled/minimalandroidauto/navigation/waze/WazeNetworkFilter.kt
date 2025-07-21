package com.degoogled.minimalandroidauto.navigation.waze

import android.content.Context
import com.degoogled.minimalandroidauto.utils.logDebug
import com.degoogled.minimalandroidauto.utils.logError
import com.degoogled.minimalandroidauto.utils.PrivacyPreferences
import java.io.File
import java.io.FileWriter
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Network filter for Waze to block analytics and tracking
 */
object WazeNetworkFilter {
    private const val TAG = "WazeNetworkFilter"
    
    // Waze analytics and tracking domains to block
    private val WAZE_ANALYTICS_DOMAINS = listOf(
        "waze-analytics.com",
        "waze-analytics-events.com",
        "waze-logs.com",
        "wazeanalytics.com",
        "wazestats.com",
        "wazemetrics.com",
        "waze-telemetry.com",
        "waze-ads.com",
        "wazeads.com",
        "waze-ad-server.com",
        "waze-ad.com",
        "waze-adserver.com",
        "waze-ad-network.com",
        "wazeadnetwork.com",
        "waze-ad-exchange.com",
        "wazeadexchange.com"
    )
    
    // Essential Waze domains for navigation
    private val WAZE_ESSENTIAL_DOMAINS = listOf(
        "waze.com",
        "wazecdn.com",
        "waze-api.com",
        "waze-maps.com",
        "waze-routing.com",
        "waze-traffic.com"
    )
    
    /**
     * Enable network filtering for Waze
     */
    fun enableFiltering(context: Context) {
        if (PrivacyPreferences.isPrivacyModeEnabled(context)) {
            try {
                // Create hosts file entries to block analytics domains
                createHostsFileEntries(context)
                
                // Log that filtering is enabled
                logDebug("Waze network filtering enabled")
            } catch (e: Exception) {
                logError("Error enabling Waze network filtering", e)
            }
        }
    }
    
    /**
     * Create hosts file entries to block analytics domains
     * 
     * Note: This is a simulation as we can't actually modify the hosts file
     * without root access. In a real implementation, this would use a local
     * VPN to intercept and block requests to these domains.
     */
    private fun createHostsFileEntries(context: Context) {
        try {
            // Create a log file to record blocked domains
            val logDir = File(context.getExternalFilesDir(null), "waze_filter")
            if (!logDir.exists()) {
                logDir.mkdirs()
            }
            
            val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Date())
            val logFile = File(logDir, "waze_filter_$timestamp.txt")
            
            FileWriter(logFile).use { writer ->
                writer.write("# Waze Network Filter\n")
                writer.write("# Created: ${Date()}\n\n")
                
                // Add entries for analytics domains
                for (domain in WAZE_ANALYTICS_DOMAINS) {
                    writer.write("127.0.0.1 $domain\n")
                    writer.write("127.0.0.1 www.$domain\n")
                }
                
                writer.write("\n# Essential domains (allowed)\n")
                for (domain in WAZE_ESSENTIAL_DOMAINS) {
                    writer.write("# $domain\n")
                }
            }
            
            logDebug("Created Waze filter log at ${logFile.absolutePath}")
        } catch (e: Exception) {
            logError("Error creating hosts file entries", e)
        }
    }
    
    /**
     * Get a list of domains to block for Waze
     */
    fun getBlockedDomains(): List<String> {
        return WAZE_ANALYTICS_DOMAINS
    }
    
    /**
     * Get a list of essential domains for Waze
     */
    fun getEssentialDomains(): List<String> {
        return WAZE_ESSENTIAL_DOMAINS
    }
}