package com.degoogled.androidauto.data.repository;

import java.lang.System;

/**
 * Implementation of the navigation repository using GraphHopper for routing.
 */
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J#\u0010\n\u001a\u0004\u0018\u00010\t2\u0006\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0007H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\rJ\u0010\u0010\u000e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u000fH\u0016J\u0010\u0010\u0010\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\u000fH\u0016J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\tH\u0016J\b\u0010\u0014\u001a\u00020\u0012H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0015"}, d2 = {"Lcom/degoogled/androidauto/data/repository/NavigationRepositoryImpl;", "Lcom/degoogled/androidauto/data/repository/NavigationRepository;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "currentLocationFlow", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/degoogled/androidauto/data/model/Location;", "currentRouteFlow", "Lcom/degoogled/androidauto/data/model/Route;", "calculateRoute", "start", "destination", "(Lcom/degoogled/androidauto/data/model/Location;Lcom/degoogled/androidauto/data/model/Location;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getCurrentLocation", "Lkotlinx/coroutines/flow/Flow;", "getCurrentRoute", "startNavigation", "", "route", "stopNavigation", "app_release"})
public final class NavigationRepositoryImpl implements com.degoogled.androidauto.data.repository.NavigationRepository {
    private final android.content.Context context = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<com.degoogled.androidauto.data.model.Route> currentRouteFlow = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<com.degoogled.androidauto.data.model.Location> currentLocationFlow = null;
    
    public NavigationRepositoryImpl(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    @java.lang.Override
    public java.lang.Object calculateRoute(@org.jetbrains.annotations.NotNull
    com.degoogled.androidauto.data.model.Location start, @org.jetbrains.annotations.NotNull
    com.degoogled.androidauto.data.model.Location destination, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.degoogled.androidauto.data.model.Route> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public kotlinx.coroutines.flow.Flow<com.degoogled.androidauto.data.model.Route> getCurrentRoute() {
        return null;
    }
    
    @java.lang.Override
    public void startNavigation(@org.jetbrains.annotations.NotNull
    com.degoogled.androidauto.data.model.Route route) {
    }
    
    @java.lang.Override
    public void stopNavigation() {
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public kotlinx.coroutines.flow.Flow<com.degoogled.androidauto.data.model.Location> getCurrentLocation() {
        return null;
    }
}