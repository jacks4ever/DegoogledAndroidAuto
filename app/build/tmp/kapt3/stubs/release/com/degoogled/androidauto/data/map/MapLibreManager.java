package com.degoogled.androidauto.data.map;

import java.lang.System;

/**
 * Manager for MapLibre map operations.
 */
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eJ\u0006\u0010\u000f\u001a\u00020\fJ\u0010\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J\u000e\u0010\u0013\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u0012J\u0019\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0005\u001a\u00020\u0006H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0016J\u0006\u0010\u0017\u001a\u00020\fJ\u0010\u0010\u0018\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u0012H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082D\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u001a"}, d2 = {"Lcom/degoogled/androidauto/data/map/MapLibreManager;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "mapView", "Lcom/mapbox/mapboxsdk/maps/MapView;", "mapboxMap", "Lcom/mapbox/mapboxsdk/maps/MapboxMap;", "styleUrl", "", "centerOnLocation", "", "location", "Lcom/degoogled/androidauto/data/model/Location;", "clearRoute", "createRouteGeoJson", "route", "Lcom/degoogled/androidauto/data/model/Route;", "drawRoute", "initialize", "", "(Lcom/mapbox/mapboxsdk/maps/MapView;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onDestroy", "zoomToRoute", "Companion", "app_release"})
public final class MapLibreManager {
    private final android.content.Context context = null;
    private com.mapbox.mapboxsdk.maps.MapboxMap mapboxMap;
    private com.mapbox.mapboxsdk.maps.MapView mapView;
    private final java.lang.String styleUrl = "asset://osm_style.json";
    @org.jetbrains.annotations.NotNull
    public static final com.degoogled.androidauto.data.map.MapLibreManager.Companion Companion = null;
    private static final java.lang.String TAG = "MapLibreManager";
    private static final java.lang.String ROUTE_SOURCE_ID = "route-source";
    private static final java.lang.String ROUTE_LAYER_ID = "route-layer";
    
    public MapLibreManager(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object initialize(@org.jetbrains.annotations.NotNull
    com.mapbox.mapboxsdk.maps.MapView mapView, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Boolean> continuation) {
        return null;
    }
    
    public final void centerOnLocation(@org.jetbrains.annotations.NotNull
    com.degoogled.androidauto.data.model.Location location) {
    }
    
    public final void drawRoute(@org.jetbrains.annotations.NotNull
    com.degoogled.androidauto.data.model.Route route) {
    }
    
    private final java.lang.String createRouteGeoJson(com.degoogled.androidauto.data.model.Route route) {
        return null;
    }
    
    private final void zoomToRoute(com.degoogled.androidauto.data.model.Route route) {
    }
    
    public final void clearRoute() {
    }
    
    public final void onDestroy() {
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0007"}, d2 = {"Lcom/degoogled/androidauto/data/map/MapLibreManager$Companion;", "", "()V", "ROUTE_LAYER_ID", "", "ROUTE_SOURCE_ID", "TAG", "app_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}