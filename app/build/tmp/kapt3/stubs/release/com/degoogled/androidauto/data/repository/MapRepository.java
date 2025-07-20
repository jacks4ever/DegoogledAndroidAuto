package com.degoogled.androidauto.data.repository;

import java.lang.System;

/**
 * Repository interface for map-related operations.
 */
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J\u0019\u0010\u0007\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J\u0014\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\n0\tH&J\u001f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\n2\u0006\u0010\r\u001a\u00020\u0005H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u000e"}, d2 = {"Lcom/degoogled/androidauto/data/repository/MapRepository;", "", "deleteMap", "", "region", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "downloadMapForRegion", "getDownloadedMaps", "Lkotlinx/coroutines/flow/Flow;", "", "searchLocation", "Lcom/degoogled/androidauto/data/model/Location;", "query", "app_release"})
public abstract interface MapRepository {
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object searchLocation(@org.jetbrains.annotations.NotNull
    java.lang.String query, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.degoogled.androidauto.data.model.Location>> continuation);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object downloadMapForRegion(@org.jetbrains.annotations.NotNull
    java.lang.String region, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Boolean> continuation);
    
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<java.lang.String>> getDownloadedMaps();
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteMap(@org.jetbrains.annotations.NotNull
    java.lang.String region, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Boolean> continuation);
}