package com.degoogled.androidauto.data.model;

import java.lang.System;

/**
 * Data class representing a conversation.
 */
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u00a2\u0006\u0002\u0010\u000bJ\t\u0010\u0014\u001a\u00020\u0003H\u00c6\u0003J\u000f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\bH\u00c6\u0003J\t\u0010\u0017\u001a\u00020\nH\u00c6\u0003J9\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\b\u0002\u0010\t\u001a\u00020\nH\u00c6\u0001J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001c\u001a\u00020\nH\u00d6\u0001J\t\u0010\u001d\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013\u00a8\u0006\u001e"}, d2 = {"Lcom/degoogled/androidauto/data/model/Conversation;", "", "id", "", "participants", "", "Lcom/degoogled/androidauto/data/model/Contact;", "lastMessage", "Lcom/degoogled/androidauto/data/model/Message;", "unreadCount", "", "(Ljava/lang/String;Ljava/util/List;Lcom/degoogled/androidauto/data/model/Message;I)V", "getId", "()Ljava/lang/String;", "getLastMessage", "()Lcom/degoogled/androidauto/data/model/Message;", "getParticipants", "()Ljava/util/List;", "getUnreadCount", "()I", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "app_release"})
public final class Conversation {
    @org.jetbrains.annotations.NotNull
    private final java.lang.String id = null;
    @org.jetbrains.annotations.NotNull
    private final java.util.List<com.degoogled.androidauto.data.model.Contact> participants = null;
    @org.jetbrains.annotations.Nullable
    private final com.degoogled.androidauto.data.model.Message lastMessage = null;
    private final int unreadCount = 0;
    
    /**
     * Data class representing a conversation.
     */
    @org.jetbrains.annotations.NotNull
    public final com.degoogled.androidauto.data.model.Conversation copy(@org.jetbrains.annotations.NotNull
    java.lang.String id, @org.jetbrains.annotations.NotNull
    java.util.List<com.degoogled.androidauto.data.model.Contact> participants, @org.jetbrains.annotations.Nullable
    com.degoogled.androidauto.data.model.Message lastMessage, int unreadCount) {
        return null;
    }
    
    /**
     * Data class representing a conversation.
     */
    @java.lang.Override
    public boolean equals(@org.jetbrains.annotations.Nullable
    java.lang.Object other) {
        return false;
    }
    
    /**
     * Data class representing a conversation.
     */
    @java.lang.Override
    public int hashCode() {
        return 0;
    }
    
    /**
     * Data class representing a conversation.
     */
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public java.lang.String toString() {
        return null;
    }
    
    public Conversation(@org.jetbrains.annotations.NotNull
    java.lang.String id, @org.jetbrains.annotations.NotNull
    java.util.List<com.degoogled.androidauto.data.model.Contact> participants, @org.jetbrains.annotations.Nullable
    com.degoogled.androidauto.data.model.Message lastMessage, int unreadCount) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.degoogled.androidauto.data.model.Contact> component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.degoogled.androidauto.data.model.Contact> getParticipants() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.degoogled.androidauto.data.model.Message component3() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.degoogled.androidauto.data.model.Message getLastMessage() {
        return null;
    }
    
    public final int component4() {
        return 0;
    }
    
    public final int getUnreadCount() {
        return 0;
    }
}