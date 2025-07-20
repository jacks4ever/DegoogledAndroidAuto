package com.degoogled.androidauto.data.model;

import java.lang.System;

/**
 * Data class representing a navigation route.
 */
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001BQ\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006\u0012\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u0006\u00a2\u0006\u0002\u0010\u000eJ\t\u0010\u001a\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001b\u001a\u00020\u0003H\u00c6\u0003J\u000f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006H\u00c6\u0003J\t\u0010\u001d\u001a\u00020\bH\u00c6\u0003J\t\u0010\u001e\u001a\u00020\nH\u00c6\u0003J\u000f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006H\u00c6\u0003J\u000f\u0010 \u001a\b\u0012\u0004\u0012\u00020\r0\u0006H\u00c6\u0003Ja\u0010!\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\u00062\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u0006H\u00c6\u0001J\u0013\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010%\u001a\u00020\nH\u00d6\u0001J\t\u0010&\u001a\u00020\'H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0016R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0010R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0016\u00a8\u0006("}, d2 = {"Lcom/degoogled/androidauto/data/model/Route;", "", "start", "Lcom/degoogled/androidauto/data/model/Location;", "destination", "waypoints", "", "distance", "", "duration", "", "polyline", "instructions", "Lcom/degoogled/androidauto/data/model/Instruction;", "(Lcom/degoogled/androidauto/data/model/Location;Lcom/degoogled/androidauto/data/model/Location;Ljava/util/List;DILjava/util/List;Ljava/util/List;)V", "getDestination", "()Lcom/degoogled/androidauto/data/model/Location;", "getDistance", "()D", "getDuration", "()I", "getInstructions", "()Ljava/util/List;", "getPolyline", "getStart", "getWaypoints", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "toString", "", "app_release"})
public final class Route {
    @org.jetbrains.annotations.NotNull
    private final com.degoogled.androidauto.data.model.Location start = null;
    @org.jetbrains.annotations.NotNull
    private final com.degoogled.androidauto.data.model.Location destination = null;
    @org.jetbrains.annotations.NotNull
    private final java.util.List<com.degoogled.androidauto.data.model.Location> waypoints = null;
    private final double distance = 0.0;
    private final int duration = 0;
    @org.jetbrains.annotations.NotNull
    private final java.util.List<com.degoogled.androidauto.data.model.Location> polyline = null;
    @org.jetbrains.annotations.NotNull
    private final java.util.List<com.degoogled.androidauto.data.model.Instruction> instructions = null;
    
    /**
     * Data class representing a navigation route.
     */
    @org.jetbrains.annotations.NotNull
    public final com.degoogled.androidauto.data.model.Route copy(@org.jetbrains.annotations.NotNull
    com.degoogled.androidauto.data.model.Location start, @org.jetbrains.annotations.NotNull
    com.degoogled.androidauto.data.model.Location destination, @org.jetbrains.annotations.NotNull
    java.util.List<com.degoogled.androidauto.data.model.Location> waypoints, double distance, int duration, @org.jetbrains.annotations.NotNull
    java.util.List<com.degoogled.androidauto.data.model.Location> polyline, @org.jetbrains.annotations.NotNull
    java.util.List<com.degoogled.androidauto.data.model.Instruction> instructions) {
        return null;
    }
    
    /**
     * Data class representing a navigation route.
     */
    @java.lang.Override
    public boolean equals(@org.jetbrains.annotations.Nullable
    java.lang.Object other) {
        return false;
    }
    
    /**
     * Data class representing a navigation route.
     */
    @java.lang.Override
    public int hashCode() {
        return 0;
    }
    
    /**
     * Data class representing a navigation route.
     */
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public java.lang.String toString() {
        return null;
    }
    
    public Route(@org.jetbrains.annotations.NotNull
    com.degoogled.androidauto.data.model.Location start, @org.jetbrains.annotations.NotNull
    com.degoogled.androidauto.data.model.Location destination, @org.jetbrains.annotations.NotNull
    java.util.List<com.degoogled.androidauto.data.model.Location> waypoints, double distance, int duration, @org.jetbrains.annotations.NotNull
    java.util.List<com.degoogled.androidauto.data.model.Location> polyline, @org.jetbrains.annotations.NotNull
    java.util.List<com.degoogled.androidauto.data.model.Instruction> instructions) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.degoogled.androidauto.data.model.Location component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.degoogled.androidauto.data.model.Location getStart() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.degoogled.androidauto.data.model.Location component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.degoogled.androidauto.data.model.Location getDestination() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.degoogled.androidauto.data.model.Location> component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.degoogled.androidauto.data.model.Location> getWaypoints() {
        return null;
    }
    
    public final double component4() {
        return 0.0;
    }
    
    public final double getDistance() {
        return 0.0;
    }
    
    public final int component5() {
        return 0;
    }
    
    public final int getDuration() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.degoogled.androidauto.data.model.Location> component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.degoogled.androidauto.data.model.Location> getPolyline() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.degoogled.androidauto.data.model.Instruction> component7() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.degoogled.androidauto.data.model.Instruction> getInstructions() {
        return null;
    }
}