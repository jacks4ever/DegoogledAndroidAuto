package com.degoogled.androidauto.di

import com.degoogled.androidauto.data.repository.MediaRepository
import com.degoogled.androidauto.data.repository.MediaRepositoryImpl
import com.degoogled.androidauto.domain.usecase.GetMediaLibraryUseCase
import com.degoogled.androidauto.domain.usecase.PlayMediaUseCase
import com.degoogled.androidauto.service.MediaPlaybackService
import org.koin.dsl.module

/**
 * Module for media playback related dependencies.
 */
val mediaModule = module {
    // Repositories
    single<MediaRepository> { MediaRepositoryImpl(get()) }
    
    // Use Cases
    factory { GetMediaLibraryUseCase(get()) }
    factory { PlayMediaUseCase(get()) }
    
    // Services
    single { MediaPlaybackService() }
    
    // ViewModels are defined in their respective UI modules
}