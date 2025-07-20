package com.degoogled.androidauto.ui.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.degoogled.androidauto.data.model.Setting
import com.degoogled.androidauto.data.repository.SettingsRepository
import com.degoogled.androidauto.domain.usecase.DownloadMapUseCase
import kotlinx.coroutines.launch

/**
 * ViewModel for the settings screen.
 */
class SettingsViewModel(
    private val settingsRepository: SettingsRepository,
    private val downloadMapUseCase: DownloadMapUseCase
) : ViewModel() {

    val settings: LiveData<List<Setting>> = settingsRepository.getSettings().asLiveData()
    
    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message
    
    fun updateSetting(id: String, value: Boolean) {
        viewModelScope.launch {
            try {
                settingsRepository.updateSetting(id, value)
                _message.value = "Setting updated"
            } catch (e: Exception) {
                _message.value = "Failed to update setting: ${e.message}"
            }
        }
    }
    
    fun downloadMaps() {
        viewModelScope.launch {
            try {
                _message.value = "Downloading maps..."
                downloadMapUseCase()
                    .onSuccess {
                        _message.value = "Maps downloaded successfully"
                    }
                    .onFailure { error ->
                        _message.value = "Failed to download maps: ${error.message}"
                    }
            } catch (e: Exception) {
                _message.value = "Failed to download maps: ${e.message}"
            }
        }
    }
    
    fun clearCache() {
        viewModelScope.launch {
            try {
                settingsRepository.clearCache()
                _message.value = "Cache cleared"
            } catch (e: Exception) {
                _message.value = "Failed to clear cache: ${e.message}"
            }
        }
    }
}