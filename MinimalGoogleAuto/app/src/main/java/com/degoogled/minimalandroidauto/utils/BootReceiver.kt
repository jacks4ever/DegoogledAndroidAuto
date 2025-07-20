package com.degoogled.minimalandroidauto.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.degoogled.minimalandroidauto.network.NetworkMonitoringService

/**
 * Broadcast receiver for device boot to start necessary services
 */
class BootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_BOOT_COMPLETED) {
            // Start network monitoring service on boot if enabled
            if (PrivacyPreferences.isNetworkMonitoringEnabled(context)) {
                val serviceIntent = Intent(context, NetworkMonitoringService::class.java)
                context.startService(serviceIntent)
            }
        }
    }
}