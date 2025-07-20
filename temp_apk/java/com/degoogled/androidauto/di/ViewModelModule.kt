package com.degoogled.androidauto.di

import com.degoogled.androidauto.ui.home.HomeViewModel
import com.degoogled.androidauto.ui.media.MediaViewModel
import com.degoogled.androidauto.ui.messaging.MessagingViewModel
import com.degoogled.androidauto.ui.navigation.NavigationViewModel
import com.degoogled.androidauto.ui.settings.SettingsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Koin module for view models.
 */
val viewModelModule = module {
    viewModel { HomeViewModel() }
    viewModel { NavigationViewModel(get(), get()) }
    viewModel { MediaViewModel(get()) }
    viewModel { MessagingViewModel(get()) }
    viewModel { SettingsViewModel(get()) }
}