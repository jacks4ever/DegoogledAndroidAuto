package com.degoogled.androidauto.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.location.Location
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.degoogled.androidauto.R
import com.degoogled.androidauto.data.model.Instruction
import com.degoogled.androidauto.data.model.Route
import com.degoogled.androidauto.ui.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import kotlin.math.min

/**
 * Service for navigation.
 */
class NavigationService : Service() {

    private val serviceScope = CoroutineScope(Dispatchers.Main + Job())
    
    private val binder = NavigationBinder()
    
    private val _isNavigating = MutableStateFlow(false)
    val isNavigating: StateFlow<Boolean> = _isNavigating
    
    private val _currentRoute = MutableStateFlow<Route?>(null)
    val currentRoute: StateFlow<Route?> = _currentRoute
    
    private val _currentInstruction = MutableStateFlow<Instruction?>(null)
    val currentInstruction: StateFlow<Instruction?> = _currentInstruction
    
    private val _distanceToNextInstruction = MutableStateFlow(0)
    val distanceToNextInstruction: StateFlow<Int> = _distanceToNextInstruction
    
    private val _currentLocation = MutableStateFlow<com.degoogled.androidauto.data.model.Location?>(null)
    val currentLocation: StateFlow<com.degoogled.androidauto.data.model.Location?> = _currentLocation
    
    private var currentInstructionIndex = 0
    private var navigationJob: Job? = null
    
    override fun onCreate() {
        super.onCreate()
    }
    
    fun startNavigation(route: Route) {
        _currentRoute.value = route
        _isNavigating.value = true
        
        if (route.instructions.isNotEmpty()) {
            currentInstructionIndex = 0
            _currentInstruction.value = route.instructions[currentInstructionIndex]
            _distanceToNextInstruction.value = route.instructions[currentInstructionIndex].distance
            
            startForeground(NOTIFICATION_ID, createNotification())
            
            // Simulate navigation updates
            navigationJob = serviceScope.launch {
                while (_isNavigating.value) {
                    // Simulate location updates
                    updateLocation()
                    
                    // Update distance to next instruction
                    _distanceToNextInstruction.value = max(0, _distanceToNextInstruction.value - 10)
                    
                    // Check if we've reached the next instruction
                    if (_distanceToNextInstruction.value <= 0) {
                        currentInstructionIndex++
                        
                        if (currentInstructionIndex < route.instructions.size) {
                            _currentInstruction.value = route.instructions[currentInstructionIndex]
                            _distanceToNextInstruction.value = route.instructions[currentInstructionIndex].distance
                            updateNotification()
                        } else {
                            // Reached destination
                            stopNavigation()
                        }
                    }
                    
                    delay(1000) // Update every second
                }
            }
        }
    }
    
    private fun updateLocation() {
        // In a real implementation, this would use the device's location
        // For now, just simulate movement along the route
        val route = _currentRoute.value ?: return
        
        // Simulate movement along the route
        val progress = 1.0 - (_distanceToNextInstruction.value.toDouble() / route.instructions[currentInstructionIndex].distance.toDouble())
        val instruction = route.instructions[currentInstructionIndex]
        
        if (instruction.startLocation != null && instruction.endLocation != null) {
            val startLat = instruction.startLocation.latitude
            val startLng = instruction.startLocation.longitude
            val endLat = instruction.endLocation.latitude
            val endLng = instruction.endLocation.longitude
            
            val lat = startLat + (endLat - startLat) * progress
            val lng = startLng + (endLng - startLng) * progress
            
            _currentLocation.value = com.degoogled.androidauto.data.model.Location(lat, lng, "Current Location")
        }
    }
    
    fun stopNavigation() {
        navigationJob?.cancel()
        _isNavigating.value = false
        _currentRoute.value = null
        _currentInstruction.value = null
        _distanceToNextInstruction.value = 0
        stopForeground(true)
    }
    
    private fun createNotification(): Notification {
        val channelId = "navigation_channel"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Navigation",
                NotificationManager.IMPORTANCE_LOW
            )
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
        
        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
        }
        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        
        val instruction = _currentInstruction.value
        val distance = _distanceToNextInstruction.value
        
        return NotificationCompat.Builder(this, channelId)
            .setContentTitle("Navigation")
            .setContentText(instruction?.text ?: "Navigating")
            .setSubText("In $distance meters")
            .setSmallIcon(R.drawable.ic_navigation)
            .setContentIntent(pendingIntent)
            .setOngoing(true)
            .build()
    }
    
    private fun updateNotification() {
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(NOTIFICATION_ID, createNotification())
    }
    
    override fun onBind(intent: Intent?): IBinder {
        return binder
    }
    
    override fun onDestroy() {
        navigationJob?.cancel()
        super.onDestroy()
    }
    
    inner class NavigationBinder : Binder() {
        fun getService(): NavigationService = this@NavigationService
    }
    
    companion object {
        private const val TAG = "NavigationService"
        private const val NOTIFICATION_ID = 2
        
        private fun max(a: Int, b: Int): Int = if (a > b) a else b
    }
}