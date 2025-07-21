package com.degoogled.androidauto.data.repository;

import java.lang.System;

/**
 * Implementation of the messaging repository using Matrix protocol.
 */
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0014\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0010H\u0016J\u0014\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u00070\u0010H\u0016J\u001c\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u00070\u00102\u0006\u0010\u0013\u001a\u00020\rH\u0016J\u0019\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\rH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0017J2\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00192\u0006\u0010\u001a\u001a\u00020\r2\u0006\u0010\u001b\u001a\u00020\rH\u0096@\u00f8\u0001\u0001\u00f8\u0001\u0002\u00f8\u0001\u0000\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u001c\u0010\u001dR\u001a\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R&\u0010\u000b\u001a\u001a\u0012\u0004\u0012\u00020\r\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u00070\u00060\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u000f\n\u0002\b\u0019\n\u0002\b!\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006\u001e"}, d2 = {"Lcom/degoogled/androidauto/data/repository/MessagingRepositoryImpl;", "Lcom/degoogled/androidauto/data/repository/MessagingRepository;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "contactsFlow", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "Lcom/degoogled/androidauto/data/model/Contact;", "conversationsFlow", "Lcom/degoogled/androidauto/data/model/Conversation;", "messagesMap", "", "", "Lcom/degoogled/androidauto/data/model/Message;", "getContacts", "Lkotlinx/coroutines/flow/Flow;", "getConversations", "getMessages", "conversationId", "markAsRead", "", "messageId", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sendMessage", "Lkotlin/Result;", "recipientId", "content", "sendMessage-0E7RQCE", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_release"})
public final class MessagingRepositoryImpl implements com.degoogled.androidauto.data.repository.MessagingRepository {
    private final android.content.Context context = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.degoogled.androidauto.data.model.Conversation>> conversationsFlow = null;
    private final java.util.Map<java.lang.String, kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.degoogled.androidauto.data.model.Message>>> messagesMap = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.degoogled.androidauto.data.model.Contact>> contactsFlow = null;
    
    public MessagingRepositoryImpl(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public kotlinx.coroutines.flow.Flow<java.util.List<com.degoogled.androidauto.data.model.Conversation>> getConversations() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public kotlinx.coroutines.flow.Flow<java.util.List<com.degoogled.androidauto.data.model.Message>> getMessages(@org.jetbrains.annotations.NotNull
    java.lang.String conversationId) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public kotlinx.coroutines.flow.Flow<java.util.List<com.degoogled.androidauto.data.model.Contact>> getContacts() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    @java.lang.Override
    public java.lang.Object markAsRead(@org.jetbrains.annotations.NotNull
    java.lang.String messageId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Boolean> continuation) {
        return null;
    }
}