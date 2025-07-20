package com.degoogled.androidauto.service;

import java.lang.System;

/**
 * Service for navigation.
 */
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\t\u0018\u0000 .2\u00020\u0001:\u0002./B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010 \u001a\u00020!H\u0002J\u0012\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010%H\u0016J\b\u0010&\u001a\u00020\'H\u0016J\b\u0010(\u001a\u00020\'H\u0016J\u000e\u0010)\u001a\u00020\'2\u0006\u0010*\u001a\u00020\tJ\u0006\u0010+\u001a\u00020\'J\b\u0010,\u001a\u00020\'H\u0002J\b\u0010-\u001a\u00020\'H\u0002R\u0016\u0010\u0003\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0012\u0010\u000e\u001a\u00060\u000fR\u00020\u0000X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\u0010\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0014\u001a\u00020\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0019\u0010\u0015\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0013R\u0019\u0010\u0017\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0013R\u0017\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0013R\u0017\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\r0\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0013R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001fX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u00060"}, d2 = {"Lcom/degoogled/androidauto/service/NavigationService;", "Landroid/app/Service;", "()V", "_currentInstruction", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/degoogled/androidauto/data/model/Instruction;", "_currentLocation", "Lcom/degoogled/androidauto/data/model/Location;", "_currentRoute", "Lcom/degoogled/androidauto/data/model/Route;", "_distanceToNextInstruction", "", "_isNavigating", "", "binder", "Lcom/degoogled/androidauto/service/NavigationService$NavigationBinder;", "currentInstruction", "Lkotlinx/coroutines/flow/StateFlow;", "getCurrentInstruction", "()Lkotlinx/coroutines/flow/StateFlow;", "currentInstructionIndex", "currentLocation", "getCurrentLocation", "currentRoute", "getCurrentRoute", "distanceToNextInstruction", "getDistanceToNextInstruction", "isNavigating", "navigationJob", "Lkotlinx/coroutines/Job;", "serviceScope", "Lkotlinx/coroutines/CoroutineScope;", "createNotification", "Landroid/app/Notification;", "onBind", "Landroid/os/IBinder;", "intent", "Landroid/content/Intent;", "onCreate", "", "onDestroy", "startNavigation", "route", "stopNavigation", "updateLocation", "updateNotification", "Companion", "NavigationBinder", "app_release"})
public final class NavigationService extends android.app.Service {
    private final kotlinx.coroutines.CoroutineScope serviceScope = null;
    private final com.degoogled.androidauto.service.NavigationService.NavigationBinder binder = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _isNavigating = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isNavigating = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<com.degoogled.androidauto.data.model.Route> _currentRoute = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<com.degoogled.androidauto.data.model.Route> currentRoute = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<com.degoogled.androidauto.data.model.Instruction> _currentInstruction = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<com.degoogled.androidauto.data.model.Instruction> currentInstruction = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Integer> _distanceToNextInstruction = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> distanceToNextInstruction = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<com.degoogled.androidauto.data.model.Location> _currentLocation = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<com.degoogled.androidauto.data.model.Location> currentLocation = null;
    private int currentInstructionIndex = 0;
    private kotlinx.coroutines.Job navigationJob;
    @org.jetbrains.annotations.NotNull
    public static final com.degoogled.androidauto.service.NavigationService.Companion Companion = null;
    private static final java.lang.String TAG = "NavigationService";
    private static final int NOTIFICATION_ID = 2;
    
    public NavigationService() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isNavigating() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<com.degoogled.androidauto.data.model.Route> getCurrentRoute() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<com.degoogled.androidauto.data.model.Instruction> getCurrentInstruction() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> getDistanceToNextInstruction() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<com.degoogled.androidauto.data.model.Location> getCurrentLocation() {
        return null;
    }
    
    @java.lang.Override
    public void onCreate() {
    }
    
    public final void startNavigation(@org.jetbrains.annotations.NotNull
    com.degoogled.androidauto.data.model.Route route) {
    }
    
    private final void updateLocation() {
    }
    
    public final void stopNavigation() {
    }
    
    private final android.app.Notification createNotification() {
        return null;
    }
    
    private final void updateNotification() {
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public android.os.IBinder onBind(@org.jetbrains.annotations.Nullable
    android.content.Intent intent) {
        return null;
    }
    
    @java.lang.Override
    public void onDestroy() {
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004\u00a8\u0006\u0005"}, d2 = {"Lcom/degoogled/androidauto/service/NavigationService$NavigationBinder;", "Landroid/os/Binder;", "(Lcom/degoogled/androidauto/service/NavigationService;)V", "getService", "Lcom/degoogled/androidauto/service/NavigationService;", "app_release"})
    public final class NavigationBinder extends android.os.Binder {
        
        public NavigationBinder() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.degoogled.androidauto.service.NavigationService getService() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u0004H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\n"}, d2 = {"Lcom/degoogled/androidauto/service/NavigationService$Companion;", "", "()V", "NOTIFICATION_ID", "", "TAG", "", "max", "a", "b", "app_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        private final int max(int a, int b) {
            return 0;
        }
    }
}