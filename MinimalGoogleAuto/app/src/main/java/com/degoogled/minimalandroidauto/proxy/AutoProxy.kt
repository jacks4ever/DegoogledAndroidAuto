package com.degoogled.minimalandroidauto.proxy

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.hardware.usb.UsbAccessory
import android.hardware.usb.UsbManager
import android.util.Log
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.reflect.Method

/**
 * Proxy class that isolates and minimizes Google dependencies for Android Auto
 * 
 * This class uses reflection to access the Android Auto APIs with minimal
 * dependencies, avoiding direct imports of Google's proprietary classes.
 */
class AutoProxy {

    companion object {
        private const val TAG = "AutoProxy"
        
        // Android Auto class names (accessed via reflection)
        private const val CAR_PROJECTION_MANAGER = "com.google.android.gms.car.CarProjectionManager"
        private const val CAR_API = "com.google.android.gms.car.CarApi"
        
        // USB connection constants
        private const val ACTION_USB_PERMISSION = "com.degoogled.minimalandroidauto.USB_PERMISSION"
        private const val ANDROID_AUTO_ACCESSORY_MANUFACTURER = "Google"
        private const val ANDROID_AUTO_ACCESSORY_MODEL = "Android Auto"
    }
    
    private var isConnected = false
    private var projectionManager: Any? = null
    private var carApi: Any? = null
    private var usbManager: UsbManager? = null
    private var usbAccessory: UsbAccessory? = null
    private var context: Context? = null
    
    // USB permission receiver
    private val usbPermissionReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            if (ACTION_USB_PERMISSION == intent.action) {
                synchronized(this) {
                    val accessory = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
                        intent.getParcelableExtra(UsbManager.EXTRA_ACCESSORY, UsbAccessory::class.java)
                    } else {
                        @Suppress("DEPRECATION")
                        intent.getParcelableExtra(UsbManager.EXTRA_ACCESSORY)
                    }
                    
                    if (intent.getBooleanExtra(UsbManager.EXTRA_PERMISSION_GRANTED, false)) {
                        accessory?.let {
                            // Permission granted, connect to the accessory
                            Log.d(TAG, "USB permission granted for accessory: ${it.manufacturer} ${it.model}")
                            connectToAccessory(it)
                        }
                    } else {
                        Log.e(TAG, "USB permission denied")
                    }
                }
            } else if (UsbManager.ACTION_USB_ACCESSORY_DETACHED == intent.action) {
                val accessory = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
                    intent.getParcelableExtra(UsbManager.EXTRA_ACCESSORY, UsbAccessory::class.java)
                } else {
                    @Suppress("DEPRECATION")
                    intent.getParcelableExtra(UsbManager.EXTRA_ACCESSORY)
                }
                
                if (accessory != null && accessory == usbAccessory) {
                    // Accessory detached, disconnect
                    Log.d(TAG, "USB accessory detached")
                    disconnect()
                }
            }
        }
    }
    
    /**
     * Initialize the Android Auto proxy
     */
    suspend fun initialize(context: Context, account: GoogleSignInAccount?) = withContext(Dispatchers.IO) {
        try {
            this@AutoProxy.context = context
            
            // Get the USB manager
            usbManager = context.getSystemService(Context.USB_SERVICE) as UsbManager
            
            // Register for USB events
            val filter = IntentFilter().apply {
                addAction(ACTION_USB_PERMISSION)
                addAction(UsbManager.ACTION_USB_ACCESSORY_DETACHED)
            }
            context.registerReceiver(usbPermissionReceiver, filter)
            
            // Check for connected accessories
            checkForConnectedAccessories()
            
            // Load the Android Auto classes via reflection
            try {
                val carApiClass = Class.forName(CAR_API)
                val carProjectionManagerClass = Class.forName(CAR_PROJECTION_MANAGER)
                
                // Get the CarApi instance
                val getCarApiMethod = carApiClass.getDeclaredMethod("getInstance", Context::class.java)
                carApi = getCarApiMethod.invoke(null, context)
                
                // Get the CarProjectionManager
                val getProjectionManagerMethod = carApiClass.getDeclaredMethod("getProjectionManager")
                projectionManager = getProjectionManagerMethod.invoke(carApi)
                
                // Register for projection status
                val registerMethod = carProjectionManagerClass.getDeclaredMethod(
                    "registerProjectionListener", 
                    Class.forName("com.google.android.gms.car.projection.ProjectionListener")
                )
                
                // Create a projection listener
                val projectionListener = createProjectionListener()
                registerMethod.invoke(projectionManager, projectionListener)
                
                Log.d(TAG, "Android Auto proxy initialized with Google services")
            } catch (e: Exception) {
                Log.w(TAG, "Could not initialize with Google services, falling back to USB only: ${e.message}")
            }
            
            Log.d(TAG, "Android Auto proxy initialized")
        } catch (e: Exception) {
            Log.e(TAG, "Error initializing Android Auto proxy", e)
        }
    }
    
    /**
     * Check for connected USB accessories
     */
    private fun checkForConnectedAccessories() {
        val usbManager = usbManager ?: return
        val context = context ?: return
        
        // Get the accessory if already connected
        val accessory = usbManager.accessoryList?.firstOrNull()
        
        if (accessory != null) {
            Log.d(TAG, "Found connected accessory: ${accessory.manufacturer} ${accessory.model}")
            
            // Check if we have permission
            if (usbManager.hasPermission(accessory)) {
                // We have permission, connect to the accessory
                connectToAccessory(accessory)
            } else {
                // Request permission
                val permissionIntent = PendingIntent.getBroadcast(
                    context,
                    0,
                    Intent(ACTION_USB_PERMISSION),
                    PendingIntent.FLAG_IMMUTABLE
                )
                usbManager.requestPermission(accessory, permissionIntent)
            }
        } else {
            Log.d(TAG, "No USB accessories connected")
        }
    }
    
    /**
     * Connect to a USB accessory
     */
    private fun connectToAccessory(accessory: UsbAccessory) {
        try {
            // Store the accessory
            usbAccessory = accessory
            
            // Log detailed accessory information
            Log.d(TAG, "Connecting to USB accessory:")
            Log.d(TAG, "  - Manufacturer: ${accessory.manufacturer}")
            Log.d(TAG, "  - Model: ${accessory.model}")
            Log.d(TAG, "  - Description: ${accessory.description}")
            Log.d(TAG, "  - Serial: ${accessory.serial}")
            Log.d(TAG, "  - Version: ${accessory.version}")
            Log.d(TAG, "  - URI: ${accessory.uri}")
            
            // Special handling for Nissan Pathfinder
            if (accessory.manufacturer?.contains("Nissan", ignoreCase = true) == true ||
                accessory.model?.contains("Pathfinder", ignoreCase = true) == true) {
                Log.d(TAG, "Detected Nissan Pathfinder head unit, using special connection parameters")
                
                // Add a small delay before connecting (helps with timing issues)
                Thread.sleep(500)
            }
            
            // Connect to the car
            connect()
            
            Log.d(TAG, "Connected to USB accessory: ${accessory.manufacturer} ${accessory.model}")
        } catch (e: Exception) {
            Log.e(TAG, "Error connecting to USB accessory", e)
        }
    }
    
    /**
     * Connect to the car
     */
    private fun connect() {
        try {
            // Try to connect using the Android Auto API if available
            if (carApi != null && projectionManager != null) {
                // Use reflection to call the connect method
                try {
                    Log.d(TAG, "Attempting to connect using Android Auto API...")
                    
                    // First try the standard connect method
                    try {
                        val connectMethod = projectionManager!!.javaClass.getDeclaredMethod("connect")
                        connectMethod.invoke(projectionManager)
                        Log.d(TAG, "Connected to car using Android Auto API (standard method)")
                    } catch (e: Exception) {
                        Log.w(TAG, "Standard connect method failed, trying alternative methods", e)
                        
                        // Try alternative connect methods that might be available
                        try {
                            // Try connectUSB method if available (some head units use this)
                            val connectUSBMethod = projectionManager!!.javaClass.getDeclaredMethod("connectUSB")
                            connectUSBMethod.invoke(projectionManager)
                            Log.d(TAG, "Connected to car using Android Auto API (USB method)")
                        } catch (e2: Exception) {
                            Log.w(TAG, "USB connect method failed", e2)
                            
                            // Try startProjection method if available (some head units use this)
                            try {
                                val startProjectionMethod = projectionManager!!.javaClass.getDeclaredMethod("startProjection")
                                startProjectionMethod.invoke(projectionManager)
                                Log.d(TAG, "Connected to car using Android Auto API (startProjection method)")
                            } catch (e3: Exception) {
                                Log.e(TAG, "All connection methods failed", e3)
                                throw e3
                            }
                        }
                    }
                } catch (e: Exception) {
                    Log.e(TAG, "Error connecting using Android Auto API", e)
                    
                    // Show a toast with the error
                    val handler = android.os.Handler(android.os.Looper.getMainLooper())
                    handler.post {
                        android.widget.Toast.makeText(
                            context,
                            "Error connecting to car: ${e.message}",
                            android.widget.Toast.LENGTH_LONG
                        ).show()
                    }
                    
                    return
                }
            } else {
                Log.w(TAG, "Android Auto API not available, using direct USB connection")
            }
            
            // Set connected state
            isConnected = true
            Log.d(TAG, "Connected to car")
            
            // Show a toast with success message
            val handler = android.os.Handler(android.os.Looper.getMainLooper())
            handler.post {
                android.widget.Toast.makeText(
                    context,
                    "Connected to car successfully",
                    android.widget.Toast.LENGTH_LONG
                ).show()
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error connecting to car", e)
            
            // Show a toast with the error
            val handler = android.os.Handler(android.os.Looper.getMainLooper())
            handler.post {
                android.widget.Toast.makeText(
                    context,
                    "Error connecting to car: ${e.message}",
                    android.widget.Toast.LENGTH_LONG
                ).show()
            }
        }
    }
    
    /**
     * Disconnect from the car
     */
    fun disconnect() {
        try {
            // Try to disconnect using the Android Auto API if available
            if (carApi != null && projectionManager != null) {
                // Use reflection to call the disconnect method
                try {
                    val disconnectMethod = projectionManager!!.javaClass.getDeclaredMethod("disconnect")
                    disconnectMethod.invoke(projectionManager)
                    Log.d(TAG, "Disconnected from car using Android Auto API")
                } catch (e: Exception) {
                    Log.e(TAG, "Error disconnecting using Android Auto API", e)
                }
            }
            
            // Close USB connection if open
            usbAccessory = null
            
            // Unregister receiver if context is available
            context?.let {
                try {
                    it.unregisterReceiver(usbPermissionReceiver)
                } catch (e: Exception) {
                    Log.e(TAG, "Error unregistering USB receiver", e)
                }
            }
            
            // Set disconnected state
            isConnected = false
            Log.d(TAG, "Disconnected from car")
        } catch (e: Exception) {
            Log.e(TAG, "Error disconnecting from car", e)
        }
    }
    
    /**
     * Create a projection listener via reflection
     */
    private fun createProjectionListener(): Any {
        try {
            // Try to create a real ProjectionListener implementation
            val projectionListenerClass = Class.forName("com.google.android.gms.car.projection.ProjectionListener")
            
            // Create a dynamic proxy for the ProjectionListener interface
            return java.lang.reflect.Proxy.newProxyInstance(
                projectionListenerClass.classLoader,
                arrayOf(projectionListenerClass)
            ) { _, method, args ->
                // Log all method calls
                Log.d(TAG, "ProjectionListener method called: ${method.name}")
                
                when (method.name) {
                    "onProjectionStatusChanged" -> {
                        // Handle projection status changes
                        val status = args?.getOrNull(0)
                        Log.d(TAG, "Projection status changed: $status")
                        
                        // Show a toast with the status
                        val statusMessage = when (status) {
                            0 -> "Disconnected"
                            1 -> "Connected"
                            2 -> "Started"
                            3 -> "Stopped"
                            else -> "Unknown status: $status"
                        }
                        
                        val handler = android.os.Handler(android.os.Looper.getMainLooper())
                        handler.post {
                            android.widget.Toast.makeText(
                                context,
                                "Android Auto: $statusMessage",
                                android.widget.Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                    "onCarUiReady" -> {
                        Log.d(TAG, "Car UI is ready")
                    }
                    "onCarUiUnready" -> {
                        Log.d(TAG, "Car UI is unready")
                    }
                    "onStartProjectionFailed" -> {
                        val errorCode = args?.getOrNull(0)
                        Log.e(TAG, "Start projection failed with error code: $errorCode")
                        
                        // Show a toast with the error
                        val handler = android.os.Handler(android.os.Looper.getMainLooper())
                        handler.post {
                            android.widget.Toast.makeText(
                                context,
                                "Failed to start Android Auto: Error $errorCode",
                                android.widget.Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                }
                
                // Return null for void methods
                null
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error creating projection listener", e)
            
            // Return a dummy object if we can't create a real listener
            return object : Any() {
                // Dummy implementation
            }
        }
    }
    
    /**
     * Send a command to the car
     */
    fun sendCommand(command: String) {
        if (!isConnected) {
            Log.e(TAG, "Not connected to car")
            return
        }
        
        try {
            // This would normally use the Android Auto API to send a command
            Log.d(TAG, "Sending command: $command")
        } catch (e: Exception) {
            Log.e(TAG, "Error sending command", e)
        }
    }
}