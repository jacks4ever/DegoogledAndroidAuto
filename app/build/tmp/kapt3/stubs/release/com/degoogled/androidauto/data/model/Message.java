package com.degoogled.androidauto.data.model;

import java.lang.System;

/**
 * Data class representing a message.
 */
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\b\u0016\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BG\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\f\u00a2\u0006\u0002\u0010\rJ\t\u0010\u0018\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0019\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001a\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001c\u001a\u00020\bH\u00c6\u0003J\t\u0010\u001d\u001a\u00020\nH\u00c6\u0003J\u000f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00030\fH\u00c6\u0003JU\u0010\u001f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\fH\u00c6\u0001J\u0013\u0010 \u001a\u00020\n2\b\u0010!\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\"\u001a\u00020#H\u00d6\u0001J\t\u0010$\u001a\u00020\u0003H\u00d6\u0001R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0013R\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0011R\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017\u00a8\u0006%"}, d2 = {"Lcom/degoogled/androidauto/data/model/Message;", "", "id", "", "senderId", "recipientId", "content", "timestamp", "Ljava/util/Date;", "isRead", "", "attachments", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Date;ZLjava/util/List;)V", "getAttachments", "()Ljava/util/List;", "getContent", "()Ljava/lang/String;", "getId", "()Z", "getRecipientId", "getSenderId", "getTimestamp", "()Ljava/util/Date;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "other", "hashCode", "", "toString", "app_release"})
public final class Message {
    @org.jetbrains.annotations.NotNull
    private final java.lang.String id = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String senderId = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String recipientId = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String content = null;
    @org.jetbrains.annotations.NotNull
    private final java.util.Date timestamp = null;
    private final boolean isRead = false;
    @org.jetbrains.annotations.NotNull
    private final java.util.List<java.lang.String> attachments = null;
    
    /**
     * Data class representing a message.
     */
    @org.jetbrains.annotations.NotNull
    public final com.degoogled.androidauto.data.model.Message copy(@org.jetbrains.annotations.NotNull
    java.lang.String id, @org.jetbrains.annotations.NotNull
    java.lang.String senderId, @org.jetbrains.annotations.NotNull
    java.lang.String recipientId, @org.jetbrains.annotations.NotNull
    java.lang.String content, @org.jetbrains.annotations.NotNull
    java.util.Date timestamp, boolean isRead, @org.jetbrains.annotations.NotNull
    java.util.List<java.lang.String> attachments) {
        return null;
    }
    
    /**
     * Data class representing a message.
     */
    @java.lang.Override
    public boolean equals(@org.jetbrains.annotations.Nullable
    java.lang.Object other) {
        return false;
    }
    
    /**
     * Data class representing a message.
     */
    @java.lang.Override
    public int hashCode() {
        return 0;
    }
    
    /**
     * Data class representing a message.
     */
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public java.lang.String toString() {
        return null;
    }
    
    public Message(@org.jetbrains.annotations.NotNull
    java.lang.String id, @org.jetbrains.annotations.NotNull
    java.lang.String senderId, @org.jetbrains.annotations.NotNull
    java.lang.String recipientId, @org.jetbrains.annotations.NotNull
    java.lang.String content, @org.jetbrains.annotations.NotNull
    java.util.Date timestamp, boolean isRead, @org.jetbrains.annotations.NotNull
    java.util.List<java.lang.String> attachments) {
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
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getSenderId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getRecipientId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getContent() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.Date component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.Date getTimestamp() {
        return null;
    }
    
    public final boolean component6() {
        return false;
    }
    
    public final boolean isRead() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<java.lang.String> component7() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<java.lang.String> getAttachments() {
        return null;
    }
}