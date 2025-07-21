package com.degoogled.minimalandroidauto

import android.app.Application
import android.content.Context
import android.os.StrictMode
import com.degoogled.minimalandroidauto.network.NetworkBlocker
import com.degoogled.minimalandroidauto.utils.PrivacyPreferences
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.osmdroid.config.Configuration

class MinimalAutoApplication : Application() {

    companion object {
        lateinit var instance: MinimalAutoApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        
        // Initialize StrictMode for development builds
        if (BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(
                StrictMode.ThreadPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                    .build()
            )
            StrictMode.setVmPolicy(
                StrictMode.VmPolicy.Builder()
                    .detectLeakedSqlLiteObjects()
                    .detectLeakedClosableObjects()
                    .penaltyLog()
                    .build()
            )
        }
        
        // Initialize Koin for dependency injection
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@MinimalAutoApplication)
            modules(appModule)
        }
        
        // Initialize OSMDroid
        Configuration.getInstance().load(
            applicationContext,
            PrivacyPreferences.getPreferences(applicationContext)
        )
        
        // Initialize network blocker
        NetworkBlocker.init(this)
    }

    override fun attachBaseContext(base: Context) {
        // Attach base context with potential locale changes
        super.attachBaseContext(base)
    }
}