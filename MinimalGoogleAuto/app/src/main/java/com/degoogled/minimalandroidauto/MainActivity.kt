package com.degoogled.minimalandroidauto

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.hardware.usb.UsbAccessory
import android.hardware.usb.UsbManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.degoogled.minimalandroidauto.databinding.ActivityMainBinding
import com.degoogled.minimalandroidauto.home.HomeActivity
import com.degoogled.minimalandroidauto.messaging.MessagingActivity
import com.degoogled.minimalandroidauto.navigation.NavigationActivity
import com.degoogled.minimalandroidauto.network.NetworkMonitoringService
import com.degoogled.minimalandroidauto.proxy.MinimalAutoService
import com.degoogled.minimalandroidauto.utils.PrivacyPreferences

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var isConnected = false

    companion object {
        private const val TAG = "MainActivity"
        private const val PERMISSIONS_REQUEST_CODE = 100
        private val REQUIRED_PERMISSIONS = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.BLUETOOTH_CONNECT,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.READ_CONTACTS,
                Manifest.permission.INTERNET,
                Manifest.permission.MODIFY_AUDIO_SETTINGS
            )
        } else {
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.BLUETOOTH,
                Manifest.permission.BLUETOOTH_ADMIN,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.READ_CONTACTS,
                Manifest.permission.INTERNET,
                Manifest.permission.MODIFY_AUDIO_SETTINGS
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Check and request permissions
        if (!hasRequiredPermissions()) {
            Log.d(TAG, "Requesting permissions on startup")
            requestPermissions()
            
            // Show a message about permissions
            Toast.makeText(
                this,
                "Please grant ALL permissions for Android Auto to work properly",
                Toast.LENGTH_LONG
            ).show()
        } else {
            Log.d(TAG, "All permissions already granted")
        }
        
        // Check if started from USB accessory attachment
        if (intent?.action == UsbManager.ACTION_USB_ACCESSORY_ATTACHED) {
            handleUsbAccessoryAttached(intent)
        }

        // Set up privacy switch
        binding.switchPrivacy.isChecked = PrivacyPreferences.isPrivacyModeEnabled(this)
        binding.switchPrivacy.setOnCheckedChangeListener { _, isChecked ->
            PrivacyPreferences.setPrivacyModeEnabled(this, isChecked)
            updatePrivacyStatus(isChecked)
        }

        // Set up connect button
        binding.buttonConnect.setOnClickListener {
            if (isConnected) {
                disconnectFromCar()
            } else {
                connectToCar()
            }
        }

        // Set up settings button
        binding.buttonSettings.setOnClickListener {
            // TODO: Navigate to settings screen
            Toast.makeText(this, "Settings not implemented yet", Toast.LENGTH_SHORT).show()
        }
        
        // Set up navigation buttons
        binding.buttonOpenStreetMap.setOnClickListener {
            val intent = Intent(this, NavigationActivity::class.java)
            intent.putExtra(NavigationActivity.EXTRA_NAVIGATION_TAB, NavigationActivity.TAB_OSM)
            startActivity(intent)
        }
        
        binding.buttonWaze.setOnClickListener {
            val intent = Intent(this, NavigationActivity::class.java)
            intent.putExtra(NavigationActivity.EXTRA_NAVIGATION_TAB, NavigationActivity.TAB_WAZE)
            startActivity(intent)
        }
        
        // Set up messaging and home automation buttons
        binding.buttonMessaging.setOnClickListener {
            val intent = Intent(this, MessagingActivity::class.java)
            startActivity(intent)
        }
        
        binding.buttonHome.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        // Start network monitoring service
        startService(Intent(this, NetworkMonitoringService::class.java))
    }

    override fun onResume() {
        super.onResume()
        
        // Check permissions again in case they were granted in settings
        if (hasRequiredPermissions()) {
            Log.d(TAG, "All permissions granted in onResume")
            binding.buttonConnect.isEnabled = true
        } else {
            Log.d(TAG, "Permissions still missing in onResume")
            binding.buttonConnect.isEnabled = false
            
            // Show a message if the user returned from settings
            if (isReturningFromSettings) {
                Toast.makeText(
                    this,
                    "Please grant ALL permissions in settings for Android Auto to work",
                    Toast.LENGTH_LONG
                ).show()
                isReturningFromSettings = false
            }
        }
        
        updateConnectionStatus()
        updatePrivacyStatus(PrivacyPreferences.isPrivacyModeEnabled(this))
    }
    
    // Track if we're returning from settings
    private var isReturningFromSettings = false
    
    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        
        // Check if started from USB accessory attachment
        if (intent?.action == UsbManager.ACTION_USB_ACCESSORY_ATTACHED) {
            handleUsbAccessoryAttached(intent)
        }
    }
    
    /**
     * Handle USB accessory attachment
     */
    private fun handleUsbAccessoryAttached(intent: Intent) {
        val accessory = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(UsbManager.EXTRA_ACCESSORY, UsbAccessory::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(UsbManager.EXTRA_ACCESSORY)
        }
        
        if (accessory != null) {
            Log.d(TAG, "USB accessory attached: ${accessory.manufacturer} ${accessory.model}")
            Toast.makeText(
                this,
                "USB accessory connected: ${accessory.manufacturer} ${accessory.model}",
                Toast.LENGTH_LONG
            ).show()
            
            // Check permissions before connecting
            if (hasRequiredPermissions()) {
                // Start the Android Auto service
                connectToCar()
            } else {
                Toast.makeText(
                    this,
                    "Cannot connect to car - permissions required. Please grant all permissions.",
                    Toast.LENGTH_LONG
                ).show()
                
                // Request permissions
                requestPermissions()
            }
        } else {
            Log.e(TAG, "USB accessory is null")
            Toast.makeText(
                this,
                "USB accessory not detected properly. Please disconnect and reconnect.",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun hasRequiredPermissions(): Boolean {
        return REQUIRED_PERMISSIONS.all {
            ContextCompat.checkSelfPermission(this, it) == PackageManager.PERMISSION_GRANTED
        }
    }

    private fun requestPermissions() {
        ActivityCompat.requestPermissions(this, REQUIRED_PERMISSIONS, PERMISSIONS_REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSIONS_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
                // All permissions granted
                updateConnectionStatus()
                Toast.makeText(
                    this,
                    "All permissions granted. You can now connect to your car.",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                // Some permissions denied
                val deniedPermissions = permissions.filterIndexed { index, _ -> 
                    grantResults.getOrNull(index) != PackageManager.PERMISSION_GRANTED 
                }
                
                Log.e(TAG, "Denied permissions: ${deniedPermissions.joinToString()}")
                
                // Check if we should show rationale for any permission
                val shouldShowRationale = deniedPermissions.any {
                    ActivityCompat.shouldShowRequestPermissionRationale(this, it)
                }
                
                if (shouldShowRationale) {
                    // User denied but didn't check "Don't ask again"
                    Toast.makeText(
                        this,
                        "These permissions are required for Android Auto to work properly. Please grant all permissions.",
                        Toast.LENGTH_LONG
                    ).show()
                    
                    // Request again after a short delay
                    binding.root.postDelayed({
                        requestPermissions()
                    }, 2000)
                } else {
                    // User denied and checked "Don't ask again"
                    Toast.makeText(
                        this,
                        "Required permissions denied. Please enable them in app settings.",
                        Toast.LENGTH_LONG
                    ).show()
                    
                    // Open app settings
                    val intent = Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                    val uri = android.net.Uri.fromParts("package", packageName, null)
                    intent.data = uri
                    isReturningFromSettings = true
                    startActivity(intent)
                }
            }
        }
    }

    private fun connectToCar() {
        if (!hasRequiredPermissions()) {
            // Show a more detailed message about permissions
            Toast.makeText(
                this,
                "Required permissions not granted. Please grant ALL permissions in the app settings.",
                Toast.LENGTH_LONG
            ).show()
            
            // Open app settings directly
            val intent = Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            val uri = android.net.Uri.fromParts("package", packageName, null)
            intent.data = uri
            isReturningFromSettings = true
            startActivity(intent)
            return
        }

        try {
            // Start the Android Auto service
            val intent = Intent(this, MinimalAutoService::class.java)
            startService(intent)

            // Update UI
            isConnected = true
            updateConnectionStatus()
            
            Toast.makeText(
                this,
                "Connected to car. If your car display doesn't show the app, please disconnect and reconnect the USB cable.",
                Toast.LENGTH_LONG
            ).show()
        } catch (e: Exception) {
            Log.e(TAG, "Error connecting to car", e)
            Toast.makeText(
                this,
                "Error connecting to car: ${e.message}",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun disconnectFromCar() {
        // Stop the Android Auto service
        val intent = Intent(this, MinimalAutoService::class.java)
        stopService(intent)

        // Update UI
        isConnected = false
        updateConnectionStatus()
    }

    private fun updateConnectionStatus() {
        // Check if service is running
        isConnected = MinimalAutoService.isRunning

        if (isConnected) {
            binding.textConnectionStatus.text = getString(R.string.connected)
            binding.textConnectionStatus.setTextColor(
                ContextCompat.getColor(this, R.color.success)
            )
            binding.buttonConnect.text = getString(R.string.disconnect_from_car)
        } else {
            binding.textConnectionStatus.text = getString(R.string.not_connected)
            binding.textConnectionStatus.setTextColor(
                ContextCompat.getColor(this, R.color.error)
            )
            binding.buttonConnect.text = getString(R.string.connect_to_car)
        }
    }

    private fun updatePrivacyStatus(isEnabled: Boolean) {
        if (isEnabled) {
            binding.textNetworkStatus.text = "Network Monitoring Active"
            binding.textNetworkStatus.setTextColor(
                ContextCompat.getColor(this, R.color.success)
            )
        } else {
            binding.textNetworkStatus.text = "Network Monitoring Disabled"
            binding.textNetworkStatus.setTextColor(
                ContextCompat.getColor(this, R.color.error)
            )
        }
    }
}