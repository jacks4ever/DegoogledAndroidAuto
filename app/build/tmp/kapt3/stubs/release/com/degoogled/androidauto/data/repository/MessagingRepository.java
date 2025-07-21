package com.degoogled.androidauto.data.repository;

import java.lang.System;

/**
 * Repository interface for messaging-related operations.
 */
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J\u0014\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0003H&J\u0014\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00040\u0003H&J\u001c\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u00040\u00032\u0006\u0010\n\u001a\u00020\u000bH&J\u0019\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000bH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000fJ2\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\t0\u00112\u0006\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\u000bH\u00a6@\u00f8\u0001\u0001\u00f8\u0001\u0002\u00f8\u0001\u0000\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0014\u0010\u0015\u0082\u0002\u000f\n\u0002\b\u0019\n\u0002\b!\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006\u0016"}, d2 = {"Lcom/degoogled/androidauto/data/repository/MessagingRepository;", "", "getContacts", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/degoogled/androidauto/data/model/Contact;", "getConversations", "Lcom/degoogled/androidauto/data/model/Conversation;", "getMessages", "Lcom/degoogled/androidauto/data/model/Message;", "conversationId", "", "markAsRead", "", "messageId", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sendMessage", "Lkotlin/Result;", "recipientId", "content", "sendMessage-0E7RQCE", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_release"})
public abstract interface MessagingRepository {
    
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.degoogled.androidauto.data.model.Conversation>> getConversations();
    
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.degoogled.androidauto.data.model.Message>> getMessages(@org.jetbrains.annotations.NotNull
    java.lang.String conversationId);
    
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.degoogled.androidauto.data.model.Contact>> getContacts();
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object markAsRead(@org.jetbrains.annotations.NotNull
    java.lang.String messageId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Boolean> continuation);
}