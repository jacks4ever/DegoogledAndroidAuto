package com.degoogled.androidauto.data.dao;

import java.lang.System;

/**
 * Data Access Object for the settings table.
 */
@androidx.room.Dao
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\bg\u0018\u00002\u00020\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J\u0014\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\bH\'J\u0018\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\b2\u0006\u0010\u0004\u001a\u00020\u0005H\'J\u0019\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\nH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000e\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u000f"}, d2 = {"Lcom/degoogled/androidauto/data/dao/SettingsDao;", "", "deleteSetting", "", "key", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllSettings", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/degoogled/androidauto/data/model/Setting;", "getSetting", "insertSetting", "setting", "(Lcom/degoogled/androidauto/data/model/Setting;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_release"})
public abstract interface SettingsDao {
    
    @org.jetbrains.annotations.NotNull
    @androidx.room.Query(value = "SELECT * FROM settings WHERE `key` = :key")
    public abstract kotlinx.coroutines.flow.Flow<com.degoogled.androidauto.data.model.Setting> getSetting(@org.jetbrains.annotations.NotNull
    java.lang.String key);
    
    @org.jetbrains.annotations.NotNull
    @androidx.room.Query(value = "SELECT * FROM settings")
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.degoogled.androidauto.data.model.Setting>> getAllSettings();
    
    @org.jetbrains.annotations.Nullable
    @androidx.room.Insert(onConflict = 1)
    public abstract java.lang.Object insertSetting(@org.jetbrains.annotations.NotNull
    com.degoogled.androidauto.data.model.Setting setting, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
    
    @org.jetbrains.annotations.Nullable
    @androidx.room.Query(value = "DELETE FROM settings WHERE `key` = :key")
    public abstract java.lang.Object deleteSetting(@org.jetbrains.annotations.NotNull
    java.lang.String key, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
}