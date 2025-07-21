package com.degoogled.minimalandroidauto.network

import java.util.concurrent.ConcurrentHashMap

/**
 * Network filter for third-party app integrations
 */
object AppNetworkFilter {

    // Mapping of app package names to their blocked domains
    private val APP_BLOCKED_DOMAINS = ConcurrentHashMap<String, List<String>>()
    
    // Mapping of app package names to their essential domains
    private val APP_ESSENTIAL_DOMAINS = ConcurrentHashMap<String, List<String>>()
    
    init {
        // Initialize WhatsApp domains
        APP_BLOCKED_DOMAINS["com.whatsapp"] = listOf(
            "crashlyticsreports-pa.googleapis.com",
            "firebaselogging-pa.googleapis.com",
            "app-measurement.com",
            "graph.facebook.com",
            "analytics.facebook.com"
        )
        
        APP_ESSENTIAL_DOMAINS["com.whatsapp"] = listOf(
            "whatsapp.com",
            "whatsapp.net",
            "wa.me"
        )
        
        // Initialize Microsoft Teams domains
        APP_BLOCKED_DOMAINS["com.microsoft.teams"] = listOf(
            "browser.pipe.aria.microsoft.com",
            "mobile.pipe.aria.microsoft.com",
            "telemetry.microsoft.com",
            "telemetry.urs.microsoft.com",
            "vortex.data.microsoft.com"
        )
        
        APP_ESSENTIAL_DOMAINS["com.microsoft.teams"] = listOf(
            "teams.microsoft.com",
            "teams.live.com",
            "outlook.office.com",
            "outlook.office365.com",
            "login.microsoftonline.com"
        )
        
        // Initialize Handcent SMS domains
        APP_BLOCKED_DOMAINS["com.handcent.nextsms"] = listOf(
            "analytics.handcent.com",
            "metrics.handcent.com",
            "ads.handcent.com"
        )
        
        APP_ESSENTIAL_DOMAINS["com.handcent.nextsms"] = listOf(
            "api.handcent.com",
            "sync.handcent.com",
            "cloud.handcent.com"
        )
        
        // Initialize August Lock domains
        APP_BLOCKED_DOMAINS["com.august.luna"] = listOf(
            "analytics.august.com",
            "metrics.august.com",
            "crashlytics.august.com"
        )
        
        APP_ESSENTIAL_DOMAINS["com.august.luna"] = listOf(
            "api.august.com",
            "connect.august.com",
            "auth.august.com"
        )
    }
    
    /**
     * Get blocked domains for a specific app
     */
    fun getBlockedDomains(packageName: String): List<String> {
        return APP_BLOCKED_DOMAINS[packageName] ?: emptyList()
    }
    
    /**
     * Get essential domains for a specific app
     */
    fun getEssentialDomains(packageName: String): List<String> {
        return APP_ESSENTIAL_DOMAINS[packageName] ?: emptyList()
    }
    
    /**
     * Get all blocked domains across all apps
     */
    fun getAllBlockedDomains(): List<String> {
        return APP_BLOCKED_DOMAINS.values.flatten()
    }
    
    /**
     * Get all essential domains across all apps
     */
    fun getAllEssentialDomains(): List<String> {
        return APP_ESSENTIAL_DOMAINS.values.flatten()
    }
}