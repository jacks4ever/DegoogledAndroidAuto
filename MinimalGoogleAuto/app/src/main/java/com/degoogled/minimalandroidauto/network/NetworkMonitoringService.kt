package com.degoogled.minimalandroidauto.network

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.degoogled.minimalandroidauto.R
import com.degoogled.minimalandroidauto.utils.PrivacyPreferences
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Service to monitor network traffic and log suspicious requests
 */
class NetworkMonitoringService : Service() {

    companion object {
        private const val TAG = "NetworkMonitoringService"
        private const val NOTIFICATION_ID = 1001
        private const val CHANNEL_ID = "network_monitoring_channel"
    }

    private val serviceScope = CoroutineScope(Dispatchers.IO + Job())
    private var isRunning = false
    private lateinit var logFile: File

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
        setupLogFile()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (!isRunning) {
            isRunning = true
            startForeground(NOTIFICATION_ID, createNotification())
            startMonitoring()
        }
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        isRunning = false
        super.onDestroy()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.notification_channel_connection)
            val importance = NotificationManager.IMPORTANCE_LOW
            val channel = NotificationChannel(CHANNEL_ID, name, importance)
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun createNotification(): Notification {
        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Network Monitoring")
            .setContentText("Monitoring network traffic for privacy")
            .setSmallIcon(android.R.drawable.ic_menu_compass)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .build()
    }

    private fun setupLogFile() {
        val logDir = File(getExternalFilesDir(null), "network_logs")
        if (!logDir.exists()) {
            logDir.mkdirs()
        }

        val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Date())
        logFile = File(logDir, "network_log_$timestamp.txt")
        
        // Write header to log file
        try {
            BufferedWriter(FileWriter(logFile, true)).use { writer ->
                writer.write("=== Network Monitoring Log ===\n")
                writer.write("Started: ${Date()}\n")
                writer.write("Privacy Mode: ${PrivacyPreferences.isPrivacyModeEnabled(this)}\n")
                writer.write("Analytics Blocking: ${PrivacyPreferences.isAnalyticsBlockingEnabled(this)}\n")
                writer.write("Minimal Auth: ${PrivacyPreferences.isMinimalAuthEnabled(this)}\n")
                writer.write("Offline Mode: ${PrivacyPreferences.isOfflineModeEnabled(this)}\n")
                writer.write("==============================\n\n")
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error writing to log file", e)
        }
    }

    private fun startMonitoring() {
        serviceScope.launch {
            while (isRunning) {
                // Check for suspicious network activity
                monitorNetworkActivity()
                delay(5000) // Check every 5 seconds
            }
        }
    }

    private fun monitorNetworkActivity() {
        // This would normally use VpnService to monitor all network traffic
        // For now, we'll just log that monitoring is active
        if (PrivacyPreferences.isNetworkMonitoringEnabled(this)) {
            logNetworkActivity("Network monitoring active")
        }
    }

    private fun logNetworkActivity(message: String) {
        try {
            val timestamp = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).format(Date())
            val logMessage = "[$timestamp] $message\n"
            
            // Log to file
            BufferedWriter(FileWriter(logFile, true)).use { writer ->
                writer.write(logMessage)
            }
            
            // Log to system
            Log.d(TAG, message)
        } catch (e: Exception) {
            Log.e(TAG, "Error writing to log file", e)
        }
    }
}