package com.degoogled.minimalandroidauto

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.degoogled.minimalandroidauto.databinding.ActivityMainBinding
import com.degoogled.minimalandroidauto.network.NetworkMonitoringService
import com.degoogled.minimalandroidauto.proxy.MinimalAutoService
import com.degoogled.minimalandroidauto.utils.PrivacyPreferences

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var isConnected = false

    companion object {
        private const val PERMISSIONS_REQUEST_CODE = 100
        private val REQUIRED_PERMISSIONS = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.BLUETOOTH_CONNECT,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.INTERNET
            )
        } else {
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.BLUETOOTH,
                Manifest.permission.BLUETOOTH_ADMIN,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.INTERNET
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Check and request permissions
        if (!hasRequiredPermissions()) {
            requestPermissions()
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

        // Start network monitoring service
        startService(Intent(this, NetworkMonitoringService::class.java))
    }

    override fun onResume() {
        super.onResume()
        updateConnectionStatus()
        updatePrivacyStatus(PrivacyPreferences.isPrivacyModeEnabled(this))
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
            if (grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
                // All permissions granted
                updateConnectionStatus()
            } else {
                // Some permissions denied
                Toast.makeText(
                    this,
                    R.string.error_permission_required,
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun connectToCar() {
        if (!hasRequiredPermissions()) {
            requestPermissions()
            return
        }

        // Start the Android Auto service
        val intent = Intent(this, MinimalAutoService::class.java)
        startService(intent)

        // Update UI
        isConnected = true
        updateConnectionStatus()
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