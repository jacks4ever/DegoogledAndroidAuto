package com.degoogled.androidauto.di

import com.degoogled.androidauto.data.map.GraphHopperRouter
import com.degoogled.androidauto.data.map.MapLibreManager
import com.degoogled.androidauto.data.map.NominatimGeocoder
import com.degoogled.androidauto.data.messaging.MatrixClient
import com.degoogled.androidauto.data.voice.CommandProcessor
import com.degoogled.androidauto.data.voice.VoskSpeechRecognizer
import com.degoogled.androidauto.domain.usecase.ProcessVoiceCommandUseCase
import com.degoogled.androidauto.domain.usecase.RecognizeSpeechUseCase
import com.degoogled.androidauto.service.MediaPlaybackService
import com.degoogled.androidauto.service.NavigationService
import com.degoogled.androidauto.service.VoiceAssistantService
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * Koin module for services.
 */
val serviceModule = module {
    // Voice services
    single { VoskSpeechRecognizer(androidContext()) }
    single { CommandProcessor(androidContext(), get(), get(), get()) }
    single { RecognizeSpeechUseCase(get()) }
    single { ProcessVoiceCommandUseCase(get(), get(), get(), get()) }
    
    // Map services
    single { MapLibreManager(androidContext()) }
    single { GraphHopperRouter(androidContext()) }
    single { NominatimGeocoder(androidContext()) }
    
    // Messaging services
    single { MatrixClient(androidContext()) }
    
    // System services
    factory { VoiceAssistantService() }
    factory { MediaPlaybackService() }
    factory { NavigationService() }
}