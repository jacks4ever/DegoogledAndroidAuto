package com.degoogled.androidauto.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.degoogled.androidauto.data.model.Setting
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for the settings table.
 */
@Dao
interface SettingsDao {
    @Query("SELECT * FROM settings WHERE `key` = :key")
    fun getSetting(key: String): Flow<Setting?>
    
    @Query("SELECT * FROM settings")
    fun getAllSettings(): Flow<List<Setting>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSetting(setting: Setting)
    
    @Query("DELETE FROM settings WHERE `key` = :key")
    suspend fun deleteSetting(key: String)
}