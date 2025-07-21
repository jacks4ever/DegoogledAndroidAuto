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
        Log.d(TAG, "Starting Degoogled Android Auto service")
        isRunning = true
        
        // Use a try-catch block to handle any exceptions
        try {
            // Start as a foreground service with notification
            startForeground(NOTIFICATION_ID, createNotification())
            
            // Check if this is a restart after crash
            val isRestart = intent?.getBooleanExtra("isRestart", false) ?: false
            if (isRestart) {
                Log.d(TAG, "Service is restarting after crash")
                
                // Show a toast about the restart
                val handler = android.os.Handler(android.os.Looper.getMainLooper())
                handler.post {
                    android.widget.Toast.makeText(
                        applicationContext,
                        "Reconnecting to car after interruption...",
                        android.widget.Toast.LENGTH_LONG
                    ).show()
                }
                
                // Add a small delay before initialization on restart
                handler.postDelayed({
                    initializeAfterDelay()
                }, 2000)
            } else {
                // Normal start - initialize immediately
                initializeAfterDelay()
            }
        } catch (e: SecurityException) {
            // Handle security exceptions (usually permission issues)
            Log.e(TAG, "Security exception starting service", e)
            
            // Show a toast with the error
            val message = "Permission error: ${e.message}. Please grant all permissions in settings."
            val handler = android.os.Handler(android.os.Looper.getMainLooper())
            handler.post {
                android.widget.Toast.makeText(
                    applicationContext,
                    message,
                    android.widget.Toast.LENGTH_LONG
                ).show()
            }
            
            // Stop the service
            stopSelf()
            isRunning = false
            return START_NOT_STICKY
        } catch (e: Exception) {
            // Handle any other exceptions
            Log.e(TAG, "Error starting service", e)
            
            // Show a toast with the error
            val message = "Error starting service: ${e.message}"
            val handler = android.os.Handler(android.os.Looper.getMainLooper())
            handler.post {
                android.widget.Toast.makeText(
                    applicationContext,
                    message,
                    android.widget.Toast.LENGTH_LONG
                ).show()
            }
            
            // Try to restart the service
            val restartIntent = Intent(this, MinimalAutoService::class.java)
            restartIntent.putExtra("isRestart", true)
            handler.postDelayed({
                try {
                    startService(restartIntent)
                } catch (e2: Exception) {
                    Log.e(TAG, "Failed to restart service", e2)
                }
            }, 3000)
            
            // Stop the current instance
            stopSelf()
            isRunning = false
            return START_NOT_STICKY
        }
        
        // Return START_STICKY to ensure the service restarts if killed
        return START_STICKY
    }
    
    /**
     * Initialize the service after a short delay
     */
    private fun initializeAfterDelay() {
        // Add a small delay before initialization to ensure system is ready
        val handler = android.os.Handler(android.os.Looper.getMainLooper())
        handler.postDelayed({
            // Initialize the Android Auto proxy in a coroutine
            serviceScope.launch {
                try {
                    initializeAutoProxy()
                } catch (e: Exception) {
                    Log.e(TAG, "Error initializing Android Auto proxy", e)
                    
                    // Show a toast with the error
                    val message = "Error connecting to car: ${e.message}"
                    val handler = android.os.Handler(android.os.Looper.getMainLooper())
                    handler.post {
                        android.widget.Toast.makeText(
                            applicationContext,
                            message,
                            android.widget.Toast.LENGTH_LONG
                        ).show()
                    }
                    
                    // Try to restart the service
                    val restartIntent = Intent(applicationContext, MinimalAutoService::class.java)
                    restartIntent.putExtra("isRestart", true)
                    handler.postDelayed({
                        try {
                            startService(restartIntent)
                        } catch (e2: Exception) {
                            Log.e(TAG, "Failed to restart service", e2)
                        }
                    }, 3000)
                    
                    // Stop the current instance
                    stopSelf()
                }
            }
        }, 1000)
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
            .setOngoing(true)
            .setCategory(NotificationCompat.CATEGORY_SERVICE)
            .build()
    }

    private suspend fun initializeAutoProxy() {
        try {
            // Log the initialization process
            Log.d(TAG, "Initializing Android Auto proxy service")
            
            // Add a small delay before initialization to ensure system is ready
            // This helps prevent the "Android Auto stopped unexpectedly" error
            try {
                Log.d(TAG, "Adding pre-initialization delay for stability")
                kotlinx.coroutines.delay(1000)
            } catch (e: Exception) {
                Log.w(TAG, "Delay interrupted", e)
            }
            
            // Only use minimal Google authentication if enabled
            if (PrivacyPreferences.isMinimalAuthEnabled(this)) {
                Log.d(TAG, "Minimal Google authentication is enabled")
                
                try {
                    // Minimal Google Sign-In for Android Auto authentication
                    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestEmail()
                        .build()
                    
                    val googleSignInClient = GoogleSignIn.getClient(this, gso)
                    val account = GoogleSignIn.getLastSignedInAccount(this)
                    
                    if (account != null) {
                        // Use account for minimal authentication
                        Log.d(TAG, "Using Google account for minimal authentication")
                        autoProxy.initialize(this, account)
                    } else {
                        // No account available, try to connect without authentication
                        Log.d(TAG, "No Google account available, connecting without authentication")
                        autoProxy.initialize(this, null)
                    }
                } catch (e: Exception) {
                    Log.e(TAG, "Error with Google authentication, falling back to no authentication", e)
                    // Fall back to no authentication
                    autoProxy.initialize(this, null)
                }
            } else {
                // Skip Google authentication entirely
                Log.d(TAG, "Google authentication disabled, connecting without authentication")
                autoProxy.initialize(this, null)
            }
            
            // Add a post-initialization delay to ensure stability
            try {
                Log.d(TAG, "Adding post-initialization delay for stability")
                kotlinx.coroutines.delay(500)
            } catch (e: Exception) {
                Log.w(TAG, "Post-initialization delay interrupted", e)
            }
            
            Log.d(TAG, "Android Auto proxy initialization completed successfully")
        } catch (e: Exception) {
            Log.e(TAG, "Error initializing Android Auto proxy", e)
            
            // Show a toast with the error
            val handler = android.os.Handler(android.os.Looper.getMainLooper())
            handler.post {
                android.widget.Toast.makeText(
                    this,
                    "Error initializing Android Auto: ${e.message}",
                    android.widget.Toast.LENGTH_LONG
                ).show()
            }
            
            // Try to recover by restarting the service
            try {
                Log.d(TAG, "Attempting to recover from initialization error")
                stopSelf()
                handler.postDelayed({
                    startService(Intent(this, MinimalAutoService::class.java))
                }, 2000)
            } catch (e2: Exception) {
                Log.e(TAG, "Recovery attempt failed", e2)
            }
        }
    }
}