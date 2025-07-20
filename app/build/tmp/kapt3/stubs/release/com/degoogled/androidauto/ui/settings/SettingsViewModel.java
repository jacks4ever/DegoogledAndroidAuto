package com.degoogled.androidauto.ui.settings;

import java.lang.System;

/**
 * ViewModel for the settings screen.
 */
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0006\u0010\u0012\u001a\u00020\u0013J\u0006\u0010\u0014\u001a\u00020\u0013J\u0016\u0010\u0015\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\t2\u0006\u0010\u0017\u001a\u00020\u0018R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001d\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f0\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\rR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0019"}, d2 = {"Lcom/degoogled/androidauto/ui/settings/SettingsViewModel;", "Landroidx/lifecycle/ViewModel;", "settingsRepository", "Lcom/degoogled/androidauto/data/repository/SettingsRepository;", "downloadMapUseCase", "Lcom/degoogled/androidauto/domain/usecase/DownloadMapUseCase;", "(Lcom/degoogled/androidauto/data/repository/SettingsRepository;Lcom/degoogled/androidauto/domain/usecase/DownloadMapUseCase;)V", "_message", "Landroidx/lifecycle/MutableLiveData;", "", "message", "Landroidx/lifecycle/LiveData;", "getMessage", "()Landroidx/lifecycle/LiveData;", "settings", "", "Lcom/degoogled/androidauto/data/model/Setting;", "getSettings", "clearCache", "", "downloadMaps", "updateSetting", "id", "value", "", "app_release"})
public final class SettingsViewModel extends androidx.lifecycle.ViewModel {
    private final com.degoogled.androidauto.data.repository.SettingsRepository settingsRepository = null;
    private final com.degoogled.androidauto.domain.usecase.DownloadMapUseCase downloadMapUseCase = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.LiveData<java.util.List<com.degoogled.androidauto.data.model.Setting>> settings = null;
    private final androidx.lifecycle.MutableLiveData<java.lang.String> _message = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.LiveData<java.lang.String> message = null;
    
    public SettingsViewModel(@org.jetbrains.annotations.NotNull
    com.degoogled.androidauto.data.repository.SettingsRepository settingsRepository, @org.jetbrains.annotations.NotNull
    com.degoogled.androidauto.domain.usecase.DownloadMapUseCase downloadMapUseCase) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<java.util.List<com.degoogled.androidauto.data.model.Setting>> getSettings() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<java.lang.String> getMessage() {
        return null;
    }
    
    public final void updateSetting(@org.jetbrains.annotations.NotNull
    java.lang.String id, boolean value) {
    }
    
    public final void downloadMaps() {
    }
    
    public final void clearCache() {
    }
}