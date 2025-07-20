package com.degoogled.androidauto.data.model;

import java.lang.System;

/**
 * Data class representing a navigation instruction.
 */
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u00a2\u0006\u0002\u0010\fJ\t\u0010\u0017\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0018\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0019\u001a\u00020\u0007H\u00c6\u0003J\t\u0010\u001a\u001a\u00020\tH\u00c6\u0003J\t\u0010\u001b\u001a\u00020\u000bH\u00c6\u0003J;\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000bH\u00c6\u0001J\u0013\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010 \u001a\u00020\u0007H\u00d6\u0001J\t\u0010!\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\n\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016\u00a8\u0006\""}, d2 = {"Lcom/degoogled/androidauto/data/model/Instruction;", "", "text", "", "distance", "", "time", "", "type", "Lcom/degoogled/androidauto/data/model/InstructionType;", "location", "Lcom/degoogled/androidauto/data/model/Location;", "(Ljava/lang/String;DILcom/degoogled/androidauto/data/model/InstructionType;Lcom/degoogled/androidauto/data/model/Location;)V", "getDistance", "()D", "getLocation", "()Lcom/degoogled/androidauto/data/model/Location;", "getText", "()Ljava/lang/String;", "getTime", "()I", "getType", "()Lcom/degoogled/androidauto/data/model/InstructionType;", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "app_release"})
public final class Instruction {
    @org.jetbrains.annotations.NotNull
    private final java.lang.String text = null;
    private final double distance = 0.0;
    private final int time = 0;
    @org.jetbrains.annotations.NotNull
    private final com.degoogled.androidauto.data.model.InstructionType type = null;
    @org.jetbrains.annotations.NotNull
    private final com.degoogled.androidauto.data.model.Location location = null;
    
    /**
     * Data class representing a navigation instruction.
     */
    @org.jetbrains.annotations.NotNull
    public final com.degoogled.androidauto.data.model.Instruction copy(@org.jetbrains.annotations.NotNull
    java.lang.String text, double distance, int time, @org.jetbrains.annotations.NotNull
    com.degoogled.androidauto.data.model.InstructionType type, @org.jetbrains.annotations.NotNull
    com.degoogled.androidauto.data.model.Location location) {
        return null;
    }
    
    /**
     * Data class representing a navigation instruction.
     */
    @java.lang.Override
    public boolean equals(@org.jetbrains.annotations.Nullable
    java.lang.Object other) {
        return false;
    }
    
    /**
     * Data class representing a navigation instruction.
     */
    @java.lang.Override
    public int hashCode() {
        return 0;
    }
    
    /**
     * Data class representing a navigation instruction.
     */
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public java.lang.String toString() {
        return null;
    }
    
    public Instruction(@org.jetbrains.annotations.NotNull
    java.lang.String text, double distance, int time, @org.jetbrains.annotations.NotNull
    com.degoogled.androidauto.data.model.InstructionType type, @org.jetbrains.annotations.NotNull
    com.degoogled.androidauto.data.model.Location location) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getText() {
        return null;
    }
    
    public final double component2() {
        return 0.0;
    }
    
    public final double getDistance() {
        return 0.0;
    }
    
    public final int component3() {
        return 0;
    }
    
    public final int getTime() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.degoogled.androidauto.data.model.InstructionType component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.degoogled.androidauto.data.model.InstructionType getType() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.degoogled.androidauto.data.model.Location component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.degoogled.androidauto.data.model.Location getLocation() {
        return null;
    }
}