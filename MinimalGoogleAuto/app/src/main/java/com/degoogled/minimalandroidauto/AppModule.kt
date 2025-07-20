package com.degoogled.minimalandroidauto

import com.degoogled.minimalandroidauto.media.MediaPlaybackService
import com.degoogled.minimalandroidauto.messaging.MessagingService
import com.degoogled.minimalandroidauto.navigation.NavigationService
import com.degoogled.minimalandroidauto.network.NetworkBlocker
import com.degoogled.minimalandroidauto.network.NetworkMonitoringService
import com.degoogled.minimalandroidauto.proxy.MinimalAutoService
import com.degoogled.minimalandroidauto.voice.VoiceRecognitionService
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * Koin dependency injection module for the application
 */
val appModule = module {
    // Network components
    single { NetworkBlocker.getInstance(androidContext()) }
    single { NetworkMonitoringService() }
    
    // Android Auto proxy components
    single { MinimalAutoService() }
    
    // Feature services
    single { NavigationService() }
    single { MediaPlaybackService() }
    single { VoiceRecognitionService() }
    single { MessagingService() }
}