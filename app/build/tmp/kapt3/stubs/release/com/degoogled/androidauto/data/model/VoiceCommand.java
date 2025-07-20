package com.degoogled.androidauto.data.model;

import java.lang.System;

/**
 * Represents a voice command processed by the voice assistant.
 */
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0014\b\u0002\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0012\u001a\u00020\u0005H\u00c6\u0003J\u0015\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0007H\u00c6\u0003J\t\u0010\u0014\u001a\u00020\u0005H\u00c6\u0003J=\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u0014\b\u0002\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u00072\b\b\u0002\u0010\b\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0019\u001a\u00020\u001aH\u00d6\u0001J\t\u0010\u001b\u001a\u00020\u0005H\u00d6\u0001R\u001d\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\b\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010\u00a8\u0006\u001c"}, d2 = {"Lcom/degoogled/androidauto/data/model/VoiceCommand;", "", "type", "Lcom/degoogled/androidauto/data/model/VoiceCommandType;", "text", "", "parameters", "", "response", "(Lcom/degoogled/androidauto/data/model/VoiceCommandType;Ljava/lang/String;Ljava/util/Map;Ljava/lang/String;)V", "getParameters", "()Ljava/util/Map;", "getResponse", "()Ljava/lang/String;", "getText", "getType", "()Lcom/degoogled/androidauto/data/model/VoiceCommandType;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"})
public final class VoiceCommand {
    @org.jetbrains.annotations.NotNull
    private final com.degoogled.androidauto.data.model.VoiceCommandType type = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String text = null;
    @org.jetbrains.annotations.NotNull
    private final java.util.Map<java.lang.String, java.lang.String> parameters = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String response = null;
    
    /**
     * Represents a voice command processed by the voice assistant.
     */
    @org.jetbrains.annotations.NotNull
    public final com.degoogled.androidauto.data.model.VoiceCommand copy(@org.jetbrains.annotations.NotNull
    com.degoogled.androidauto.data.model.VoiceCommandType type, @org.jetbrains.annotations.NotNull
    java.lang.String text, @org.jetbrains.annotations.NotNull
    java.util.Map<java.lang.String, java.lang.String> parameters, @org.jetbrains.annotations.NotNull
    java.lang.String response) {
        return null;
    }
    
    /**
     * Represents a voice command processed by the voice assistant.
     */
    @java.lang.Override
    public boolean equals(@org.jetbrains.annotations.Nullable
    java.lang.Object other) {
        return false;
    }
    
    /**
     * Represents a voice command processed by the voice assistant.
     */
    @java.lang.Override
    public int hashCode() {
        return 0;
    }
    
    /**
     * Represents a voice command processed by the voice assistant.
     */
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public java.lang.String toString() {
        return null;
    }
    
    public VoiceCommand(@org.jetbrains.annotations.NotNull
    com.degoogled.androidauto.data.model.VoiceCommandType type, @org.jetbrains.annotations.NotNull
    java.lang.String text, @org.jetbrains.annotations.NotNull
    java.util.Map<java.lang.String, java.lang.String> parameters, @org.jetbrains.annotations.NotNull
    java.lang.String response) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.degoogled.androidauto.data.model.VoiceCommandType component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.degoogled.androidauto.data.model.VoiceCommandType getType() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getText() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.Map<java.lang.String, java.lang.String> component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.Map<java.lang.String, java.lang.String> getParameters() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getResponse() {
        return null;
    }
}