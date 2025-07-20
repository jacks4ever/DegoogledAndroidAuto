package com.degoogled.minimalandroidauto.proxy

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.degoogled.minimalandroidauto.R
import com.degoogled.minimalandroidauto.utils.PrivacyPreferences
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * Service that handles the Android Auto connection with minimal Google dependencies
 */
class MinimalAutoService : Service() {

    companion object {
        private const val TAG = "MinimalAutoService"
        private const val NOTIFICATION_ID = 1000
        private const val CHANNEL_ID = "auto_connection_channel"
        
        // Track if the service is running
        var isRunning = false
            private set
    }

    private val serviceScope = CoroutineScope(Dispatchers.IO + Job())
    private val autoProxy = AutoProxy()

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        isRunning = true
        startForeground(NOTIFICATION_ID, createNotification())
        
        // Initialize the Android Auto proxy
        serviceScope.launch {
            initializeAutoProxy()
        }
        
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        isRunning = false
        autoProxy.disconnect()
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
            .setContentTitle(getString(R.string.app_name))
            .setContentText(getString(R.string.connected))
            .setSmallIcon(android.R.drawable.ic_menu_compass)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .build()
    }

    private suspend fun initializeAutoProxy() {
        try {
            // Only use minimal Google authentication if enabled
            if (PrivacyPreferences.isMinimalAuthEnabled(this)) {
                // Minimal Google Sign-In for Android Auto authentication
                val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestEmail()
                    .build()
                
                val googleSignInClient = GoogleSignIn.getClient(this, gso)
                val account = GoogleSignIn.getLastSignedInAccount(this)
                
                if (account != null) {
                    // Use account for minimal authentication
                    autoProxy.initialize(this, account)
                } else {
                    // No account available, try to connect without authentication
                    autoProxy.initialize(this, null)
                }
            } else {
                // Skip Google authentication entirely
                autoProxy.initialize(this, null)
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error initializing Android Auto proxy", e)
        }
    }
}