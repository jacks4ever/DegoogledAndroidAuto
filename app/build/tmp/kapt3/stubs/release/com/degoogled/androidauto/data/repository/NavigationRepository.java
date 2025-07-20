package com.degoogled.androidauto.data.repository;

import java.lang.System;

/**
 * Repository interface for navigation-related operations.
 */
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J#\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0007J\u0010\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\tH&J\u0010\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\tH&J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0003H&J\b\u0010\u000e\u001a\u00020\fH&\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u000f"}, d2 = {"Lcom/degoogled/androidauto/data/repository/NavigationRepository;", "", "calculateRoute", "Lcom/degoogled/androidauto/data/model/Route;", "start", "Lcom/degoogled/androidauto/data/model/Location;", "destination", "(Lcom/degoogled/androidauto/data/model/Location;Lcom/degoogled/androidauto/data/model/Location;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getCurrentLocation", "Lkotlinx/coroutines/flow/Flow;", "getCurrentRoute", "startNavigation", "", "route", "stopNavigation", "app_release"})
public abstract interface NavigationRepository {
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object calculateRoute(@org.jetbrains.annotations.NotNull
    com.degoogled.androidauto.data.model.Location start, @org.jetbrains.annotations.NotNull
    com.degoogled.androidauto.data.model.Location destination, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.degoogled.androidauto.data.model.Route> continuation);
    
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<com.degoogled.androidauto.data.model.Route> getCurrentRoute();
    
    public abstract void startNavigation(@org.jetbrains.annotations.NotNull
    com.degoogled.androidauto.data.model.Route route);
    
    public abstract void stopNavigation();
    
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<com.degoogled.androidauto.data.model.Location> getCurrentLocation();
}