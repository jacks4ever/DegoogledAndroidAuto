package com.degoogled.androidauto.data.messaging;

import java.lang.System;

/**
 * Client for Matrix messaging protocol.
 */
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u0000 /2\u00020\u0001:\u0001/B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\f0\u00072\u0006\u0010\u0018\u001a\u00020\u000bH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0019J)\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u000b2\u0006\u0010\u001d\u001a\u00020\u000b2\u0006\u0010\u001e\u001a\u00020\u000bH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001fJ\b\u0010 \u001a\u00020!H\u0002J\b\u0010\"\u001a\u00020!H\u0002J\u0006\u0010#\u001a\u00020!J*\u0010$\u001a\b\u0012\u0004\u0012\u00020!0%2\u0006\u0010&\u001a\u00020\u000bH\u0086@\u00f8\u0001\u0001\u00f8\u0001\u0002\u00f8\u0001\u0000\u00f8\u0001\u0000\u00a2\u0006\u0004\b\'\u0010\u0019J2\u0010(\u001a\b\u0012\u0004\u0012\u00020\f0%2\u0006\u0010)\u001a\u00020\u000b2\u0006\u0010*\u001a\u00020\u000bH\u0086@\u00f8\u0001\u0001\u00f8\u0001\u0002\u00f8\u0001\u0000\u00f8\u0001\u0000\u00a2\u0006\u0004\b+\u0010,J\u0010\u0010-\u001a\u00020!2\u0006\u0010.\u001a\u00020\u000bH\u0002R\u001a\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R&\u0010\t\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u00070\n0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0010\u0010\u0011\u001a\u00020\u0012X\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\u0013R)\u0010\u0014\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u00070\n0\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0010R\u0010\u0010\u0016\u001a\u00020\u0012X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u0013R\u0010\u0010\u0017\u001a\u00020\u0012X\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\u0013\u0082\u0002\u000f\n\u0002\b\u0019\n\u0002\b!\n\u0005\b\u00a1\u001e0\u0001\u00a8\u00060"}, d2 = {"Lcom/degoogled/androidauto/data/messaging/MatrixClient;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "_conversations", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "Lcom/degoogled/androidauto/data/model/Conversation;", "_messages", "", "", "Lcom/degoogled/androidauto/data/model/Message;", "conversations", "Lkotlinx/coroutines/flow/StateFlow;", "getConversations", "()Lkotlinx/coroutines/flow/StateFlow;", "matrix", "error/NonExistentClass", "Lerror/NonExistentClass;", "messages", "getMessages", "roomTimelines", "session", "conversationId", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "initialize", "", "homeServerUrl", "username", "password", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "listenForRoomUpdates", "", "loadConversations", "logout", "markAsRead", "Lkotlin/Result;", "messageId", "markAsRead-gIAlu-s", "sendMessage", "recipientId", "content", "sendMessage-0E7RQCE", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateMessagesForRoom", "roomId", "Companion", "app_release"})
public final class MatrixClient {
    private final android.content.Context context = null;
    private error.NonExistentClass matrix;
    private error.NonExistentClass session;
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.degoogled.androidauto.data.model.Conversation>> _conversations = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.degoogled.androidauto.data.model.Conversation>> conversations = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.Map<java.lang.String, java.util.List<com.degoogled.androidauto.data.model.Message>>> _messages = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.util.Map<java.lang.String, java.util.List<com.degoogled.androidauto.data.model.Message>>> messages = null;
    private final error.NonExistentClass roomTimelines = null;
    @org.jetbrains.annotations.NotNull
    public static final com.degoogled.androidauto.data.messaging.MatrixClient.Companion Companion = null;
    private static final java.lang.String TAG = "MatrixClient";
    
    public MatrixClient(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.degoogled.androidauto.data.model.Conversation>> getConversations() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.util.Map<java.lang.String, java.util.List<com.degoogled.androidauto.data.model.Message>>> getMessages() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object initialize(@org.jetbrains.annotations.NotNull
    java.lang.String homeServerUrl, @org.jetbrains.annotations.NotNull
    java.lang.String username, @org.jetbrains.annotations.NotNull
    java.lang.String password, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Boolean> continuation) {
        return null;
    }
    
    private final void listenForRoomUpdates() {
    }
    
    private final void loadConversations() {
    }
    
    private final void updateMessagesForRoom(java.lang.String roomId) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getMessages(@org.jetbrains.annotations.NotNull
    java.lang.String conversationId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.degoogled.androidauto.data.model.Message>> continuation) {
        return null;
    }
    
    public final void logout() {
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/degoogled/androidauto/data/messaging/MatrixClient$Companion;", "", "()V", "TAG", "", "app_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}