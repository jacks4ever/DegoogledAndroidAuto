package com.degoogled.androidauto.di

import com.degoogled.androidauto.data.repository.MapRepository
import com.degoogled.androidauto.data.repository.MapRepositoryImpl
import com.degoogled.androidauto.data.repository.MediaRepository
import com.degoogled.androidauto.data.repository.MediaRepositoryImpl
import com.degoogled.androidauto.data.repository.MessagingRepository
import com.degoogled.androidauto.data.repository.MessagingRepositoryImpl
import com.degoogled.androidauto.data.repository.SettingsRepository
import com.degoogled.androidauto.data.repository.SettingsRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * Koin module for repositories.
 */
val repositoryModule = module {
    single<MapRepository> { MapRepositoryImpl(androidContext(), get(), get()) }
    single<MediaRepository> { MediaRepositoryImpl(androidContext()) }
    single<MessagingRepository> { MessagingRepositoryImpl(get()) }
    single<SettingsRepository> { SettingsRepositoryImpl(androidContext()) }
}