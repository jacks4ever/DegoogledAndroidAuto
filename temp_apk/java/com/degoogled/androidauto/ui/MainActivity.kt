package com.degoogled.androidauto.ui

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.degoogled.androidauto.R
import com.degoogled.androidauto.databinding.ActivityMainBinding
import com.degoogled.androidauto.domain.usecase.ProcessVoiceCommandUseCase
import com.degoogled.androidauto.domain.usecase.RecognizeSpeechUseCase
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

/**
 * Main activity for the Degoogled Android Auto app.
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val recognizeSpeechUseCase: RecognizeSpeechUseCase by inject()
    private val processVoiceCommandUseCase: ProcessVoiceCommandUseCase by inject()
    
    private val requiredPermissions = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.RECORD_AUDIO,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.BLUETOOTH
    )
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setupNavigation()
        setupVoiceAssistant()
        checkPermissions()
    }
    
    private fun setupNavigation() {
        val navController = findNavController(R.id.nav_host_fragment)
        binding.bottomNavigation.setupWithNavController(navController)
    }
    
    private fun setupVoiceAssistant() {
        binding.fabVoiceCommand.setOnClickListener {
            // Start voice recognition
            CoroutineScope(Dispatchers.Main).launch {
                try {
                    recognizeSpeechUseCase.startListening()
                    // In a real app, this would show a voice recognition UI
                    // and process the recognized speech
                    
                    // For now, simulate a voice command
                    val command = "navigate to San Francisco"
                    processVoiceCommandUseCase(command)
                    
                    Snackbar.make(
                        binding.root,
                        "Processed command: $command",
                        Snackbar.LENGTH_SHORT
                    ).show()
                } catch (e: Exception) {
                    Snackbar.make(
                        binding.root,
                        "Error: ${e.message}",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
    
    private fun checkPermissions() {
        val permissionsToRequest = requiredPermissions.filter {
            ContextCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED
        }.toTypedArray()
        
        if (permissionsToRequest.isNotEmpty()) {
            ActivityCompat.requestPermissions(this, permissionsToRequest, PERMISSION_REQUEST_CODE)
        }
    }
    
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        
        if (requestCode == PERMISSION_REQUEST_CODE) {
            val deniedPermissions = permissions.filterIndexed { index, _ ->
                grantResults[index] != PackageManager.PERMISSION_GRANTED
            }
            
            if (deniedPermissions.isNotEmpty()) {
                // Show a message about missing permissions
                Snackbar.make(
                    binding.root,
                    "Some features may not work without required permissions",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }
    }
    
    companion object {
        private const val PERMISSION_REQUEST_CODE = 100
    }
}