package com.degoogled.androidauto.di

import com.degoogled.androidauto.data.repository.MessagingRepository
import com.degoogled.androidauto.data.repository.MessagingRepositoryImpl
import com.degoogled.androidauto.domain.usecase.GetConversationsUseCase
import com.degoogled.androidauto.domain.usecase.SendMessageUseCase
import com.degoogled.androidauto.service.MessagingService
import org.koin.dsl.module

/**
 * Module for messaging related dependencies.
 */
val messagingModule = module {
    // Repositories
    single<MessagingRepository> { MessagingRepositoryImpl(get()) }
    
    // Use Cases
    factory { GetConversationsUseCase(get()) }
    factory { SendMessageUseCase(get()) }
    
    // Services
    single { MessagingService() }
    
    // ViewModels are defined in their respective UI modules
}