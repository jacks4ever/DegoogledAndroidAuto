package com.degoogled.androidauto.di

import com.degoogled.androidauto.data.repository.VoiceRepository
import com.degoogled.androidauto.data.repository.VoiceRepositoryImpl
import com.degoogled.androidauto.domain.usecase.ProcessVoiceCommandUseCase
import com.degoogled.androidauto.domain.usecase.RecognizeSpeechUseCase
import com.degoogled.androidauto.service.VoiceRecognitionService
import org.koin.dsl.module

/**
 * Module for voice recognition related dependencies.
 */
val voiceModule = module {
    // Repositories
    single<VoiceRepository> { VoiceRepositoryImpl(get()) }
    
    // Use Cases
    factory { ProcessVoiceCommandUseCase(get(), get(), get(), get()) }
    factory { RecognizeSpeechUseCase(get()) }
    
    // Services
    single { VoiceRecognitionService() }
    
    // ViewModels are defined in their respective UI modules
}