package com.degoogled.androidauto.data;

import java.lang.System;

/**
 * Main database for the application.
 */
@androidx.room.Database(entities = {com.degoogled.androidauto.data.model.Setting.class}, version = 1, exportSchema = false)
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\'\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&\u00a8\u0006\u0005"}, d2 = {"Lcom/degoogled/androidauto/data/AppDatabase;", "Landroidx/room/RoomDatabase;", "()V", "settingsDao", "Lcom/degoogled/androidauto/data/dao/SettingsDao;", "app_release"})
public abstract class AppDatabase extends androidx.room.RoomDatabase {
    
    public AppDatabase() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public abstract com.degoogled.androidauto.data.dao.SettingsDao settingsDao();
}