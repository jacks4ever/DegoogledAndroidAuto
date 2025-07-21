package com.degoogled.androidauto.di

import com.degoogled.androidauto.data.repository.MapRepository
import com.degoogled.androidauto.data.repository.MapRepositoryImpl
import com.degoogled.androidauto.data.repository.NavigationRepository
import com.degoogled.androidauto.data.repository.NavigationRepositoryImpl
import com.degoogled.androidauto.domain.usecase.CalculateRouteUseCase
import com.degoogled.androidauto.domain.usecase.DownloadMapUseCase
import com.degoogled.androidauto.domain.usecase.SearchLocationUseCase
import org.koin.dsl.module

/**
 * Module for map and navigation related dependencies.
 */
val mapModule = module {
    // Repositories
    single<MapRepository> { MapRepositoryImpl(get()) }
    single<NavigationRepository> { NavigationRepositoryImpl(get()) }
    
    // Use Cases
    factory { CalculateRouteUseCase(get()) }
    factory { DownloadMapUseCase(get()) }
    factory { SearchLocationUseCase(get()) }
    
    // ViewModels are defined in their respective UI modules
}