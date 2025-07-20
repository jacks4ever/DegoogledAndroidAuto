package com.degoogled.androidauto

import android.app.Application
import com.degoogled.androidauto.di.repositoryModule
import com.degoogled.androidauto.di.serviceModule
import com.degoogled.androidauto.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

/**
 * Main application class for the Degoogled Android Auto app.
 * Initializes dependency injection and other app-wide configurations.
 */
class DegoogledAutoApp : Application() {

    override fun onCreate() {
        super.onCreate()
        
        // Initialize Koin for dependency injection
        startKoin {
            androidLogger(Level.ERROR) // Use ERROR level to avoid Koin-related crashes
            androidContext(this@DegoogledAutoApp)
            modules(listOf(
                repositoryModule,
                serviceModule,
                viewModelModule
            ))
        }
        
        // Initialize offline map data
        initializeMapData()
        
        // Initialize voice recognition models
        initializeVoiceRecognition()
    }
    
    private fun initializeMapData() {
        // Check if offline map data exists, if not, prompt user to download
        val mapDir = getExternalFilesDir("maps")
        if (mapDir?.exists() != true || mapDir.listFiles()?.isEmpty() != false) {
            // In a real implementation, this would trigger a download prompt
            // For now, just create the directory
            mapDir?.mkdirs()
        }
    }
    
    private fun initializeVoiceRecognition() {
        // Check if voice recognition models exist, if not, prompt user to download
        val voiceDir = getExternalFilesDir("vosk-model")
        if (voiceDir?.exists() != true || voiceDir.listFiles()?.isEmpty() != false) {
            // In a real implementation, this would trigger a download prompt
            // For now, just create the directory
            voiceDir?.mkdirs()
        }
    }
}