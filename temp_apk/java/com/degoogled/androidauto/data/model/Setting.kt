package com.degoogled.androidauto.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity representing a setting in the database.
 */
@Entity(tableName = "settings")
data class Setting(
    @PrimaryKey
    val key: String,
    val value: String
)