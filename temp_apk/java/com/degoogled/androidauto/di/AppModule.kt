package com.degoogled.androidauto.di

import android.content.Context
import androidx.room.Room
import com.degoogled.androidauto.data.AppDatabase
import com.degoogled.androidauto.data.repository.SettingsRepository
import com.degoogled.androidauto.data.repository.SettingsRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * Main application module for dependency injection.
 * Contains core app-wide dependencies.
 */
val appModule = module {
    // Database
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "degoogled-auto-db"
        ).build()
    }
    
    // DAOs
    single { get<AppDatabase>().settingsDao() }
    
    // Repositories
    single<SettingsRepository> { SettingsRepositoryImpl(get()) }
    
    // Shared Preferences
    single { 
        androidContext().getSharedPreferences("degoogled_auto_prefs", Context.MODE_PRIVATE) 
    }
}