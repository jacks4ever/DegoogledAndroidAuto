package com.degoogled.androidauto.data.repository

import com.degoogled.androidauto.data.dao.SettingsDao
import com.degoogled.androidauto.data.model.Setting
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for settings.
 */
interface SettingsRepository {
    fun getSetting(key: String): Flow<Setting?>
    fun getAllSettings(): Flow<List<Setting>>
    suspend fun saveSetting(key: String, value: String)
    suspend fun deleteSetting(key: String)
}

/**
 * Implementation of the settings repository.
 */
class SettingsRepositoryImpl(
    private val settingsDao: SettingsDao
) : SettingsRepository {
    
    override fun getSetting(key: String): Flow<Setting?> {
        return settingsDao.getSetting(key)
    }
    
    override fun getAllSettings(): Flow<List<Setting>> {
        return settingsDao.getAllSettings()
    }
    
    override suspend fun saveSetting(key: String, value: String) {
        settingsDao.insertSetting(Setting(key, value))
    }
    
    override suspend fun deleteSetting(key: String) {
        settingsDao.deleteSetting(key)
    }
}