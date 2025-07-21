package com.degoogled.minimalandroidauto.utils

import android.content.Context
import android.content.SharedPreferences

/**
 * Utility class for managing privacy preferences
 */
object PrivacyPreferences {
    private const val PREFS_NAME = "privacy_preferences"
    private const val KEY_PRIVACY_MODE = "privacy_mode_enabled"
    private const val KEY_BLOCK_ANALYTICS = "block_analytics"
    private const val KEY_MINIMAL_AUTH = "minimal_auth"
    private const val KEY_OFFLINE_MODE = "offline_mode"
    private const val KEY_NETWORK_MONITORING = "network_monitoring"
    private const val KEY_WAZE_PRIVACY = "waze_privacy"
    private const val KEY_WHATSAPP_PRIVACY = "whatsapp_privacy"
    private const val KEY_TEAMS_PRIVACY = "teams_privacy"
    private const val KEY_HANDCENT_PRIVACY = "handcent_privacy"
    private const val KEY_AUGUST_PRIVACY = "august_privacy"

    /**
     * Get the shared preferences instance
     */
    fun getPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    /**
     * Check if privacy mode is enabled
     */
    fun isPrivacyModeEnabled(context: Context): Boolean {
        return getPreferences(context).getBoolean(KEY_PRIVACY_MODE, true)
    }

    /**
     * Set privacy mode enabled/disabled
     */
    fun setPrivacyModeEnabled(context: Context, enabled: Boolean) {
        getPreferences(context).edit().putBoolean(KEY_PRIVACY_MODE, enabled).apply()
    }

    /**
     * Check if analytics blocking is enabled
     */
    fun isAnalyticsBlockingEnabled(context: Context): Boolean {
        return getPreferences(context).getBoolean(KEY_BLOCK_ANALYTICS, true)
    }

    /**
     * Set analytics blocking enabled/disabled
     */
    fun setAnalyticsBlockingEnabled(context: Context, enabled: Boolean) {
        getPreferences(context).edit().putBoolean(KEY_BLOCK_ANALYTICS, enabled).apply()
    }

    /**
     * Check if minimal authentication is enabled
     */
    fun isMinimalAuthEnabled(context: Context): Boolean {
        return getPreferences(context).getBoolean(KEY_MINIMAL_AUTH, true)
    }

    /**
     * Set minimal authentication enabled/disabled
     */
    fun setMinimalAuthEnabled(context: Context, enabled: Boolean) {
        getPreferences(context).edit().putBoolean(KEY_MINIMAL_AUTH, enabled).apply()
    }

    /**
     * Check if offline mode is enabled
     */
    fun isOfflineModeEnabled(context: Context): Boolean {
        return getPreferences(context).getBoolean(KEY_OFFLINE_MODE, false)
    }

    /**
     * Set offline mode enabled/disabled
     */
    fun setOfflineModeEnabled(context: Context, enabled: Boolean) {
        getPreferences(context).edit().putBoolean(KEY_OFFLINE_MODE, enabled).apply()
    }

    /**
     * Check if network monitoring is enabled
     */
    fun isNetworkMonitoringEnabled(context: Context): Boolean {
        return getPreferences(context).getBoolean(KEY_NETWORK_MONITORING, true)
    }

    /**
     * Set network monitoring enabled/disabled
     */
    fun setNetworkMonitoringEnabled(context: Context, enabled: Boolean) {
        getPreferences(context).edit().putBoolean(KEY_NETWORK_MONITORING, enabled).apply()
    }
    
    /**
     * Check if Waze privacy protection is enabled
     */
    fun isWazePrivacyEnabled(context: Context): Boolean {
        return getPreferences(context).getBoolean(KEY_WAZE_PRIVACY, true)
    }
    
    /**
     * Set Waze privacy protection enabled/disabled
     */
    fun setWazePrivacyEnabled(context: Context, enabled: Boolean) {
        getPreferences(context).edit().putBoolean(KEY_WAZE_PRIVACY, enabled).apply()
    }
    
    /**
     * Check if WhatsApp privacy protection is enabled
     */
    fun isWhatsAppPrivacyEnabled(context: Context): Boolean {
        return getPreferences(context).getBoolean(KEY_WHATSAPP_PRIVACY, true)
    }
    
    /**
     * Set WhatsApp privacy protection enabled/disabled
     */
    fun setWhatsAppPrivacyEnabled(context: Context, enabled: Boolean) {
        getPreferences(context).edit().putBoolean(KEY_WHATSAPP_PRIVACY, enabled).apply()
    }
    
    /**
     * Check if Microsoft Teams privacy protection is enabled
     */
    fun isTeamsPrivacyEnabled(context: Context): Boolean {
        return getPreferences(context).getBoolean(KEY_TEAMS_PRIVACY, true)
    }
    
    /**
     * Set Microsoft Teams privacy protection enabled/disabled
     */
    fun setTeamsPrivacyEnabled(context: Context, enabled: Boolean) {
        getPreferences(context).edit().putBoolean(KEY_TEAMS_PRIVACY, enabled).apply()
    }
    
    /**
     * Check if Handcent SMS privacy protection is enabled
     */
    fun isHandcentPrivacyEnabled(context: Context): Boolean {
        return getPreferences(context).getBoolean(KEY_HANDCENT_PRIVACY, true)
    }
    
    /**
     * Set Handcent SMS privacy protection enabled/disabled
     */
    fun setHandcentPrivacyEnabled(context: Context, enabled: Boolean) {
        getPreferences(context).edit().putBoolean(KEY_HANDCENT_PRIVACY, enabled).apply()
    }
    
    /**
     * Check if August Lock privacy protection is enabled
     */
    fun isAugustPrivacyEnabled(context: Context): Boolean {
        return getPreferences(context).getBoolean(KEY_AUGUST_PRIVACY, true)
    }
    
    /**
     * Set August Lock privacy protection enabled/disabled
     */
    fun setAugustPrivacyEnabled(context: Context, enabled: Boolean) {
        getPreferences(context).edit().putBoolean(KEY_AUGUST_PRIVACY, enabled).apply()
    }
}