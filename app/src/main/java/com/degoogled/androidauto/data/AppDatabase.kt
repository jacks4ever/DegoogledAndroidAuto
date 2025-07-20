package com.degoogled.androidauto.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.degoogled.androidauto.data.dao.SettingsDao
import com.degoogled.androidauto.data.model.Setting

/**
 * Main database for the application.
 */
@Database(
    entities = [Setting::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun settingsDao(): SettingsDao
}