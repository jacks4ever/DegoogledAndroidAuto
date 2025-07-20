package com.degoogled.androidauto.ui.messaging;

import java.lang.System;

/**
 * ViewModel for the conversation screen.
 */
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u000e\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0003R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001d\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000eR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0016"}, d2 = {"Lcom/degoogled/androidauto/ui/messaging/ConversationViewModel;", "Landroidx/lifecycle/ViewModel;", "conversationId", "", "messagingRepository", "Lcom/degoogled/androidauto/data/repository/MessagingRepository;", "sendMessageUseCase", "Lcom/degoogled/androidauto/domain/usecase/SendMessageUseCase;", "(Ljava/lang/String;Lcom/degoogled/androidauto/data/repository/MessagingRepository;Lcom/degoogled/androidauto/domain/usecase/SendMessageUseCase;)V", "_errorMessage", "Landroidx/lifecycle/MutableLiveData;", "errorMessage", "Landroidx/lifecycle/LiveData;", "getErrorMessage", "()Landroidx/lifecycle/LiveData;", "messages", "", "Lcom/degoogled/androidauto/data/model/Message;", "getMessages", "sendMessage", "", "content", "app_release"})
public final class ConversationViewModel extends androidx.lifecycle.ViewModel {
    private final java.lang.String conversationId = null;
    private final com.degoogled.androidauto.data.repository.MessagingRepository messagingRepository = null;
    private final com.degoogled.androidauto.domain.usecase.SendMessageUseCase sendMessageUseCase = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.LiveData<java.util.List<com.degoogled.androidauto.data.model.Message>> messages = null;
    private final androidx.lifecycle.MutableLiveData<java.lang.String> _errorMessage = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.LiveData<java.lang.String> errorMessage = null;
    
    public ConversationViewModel(@org.jetbrains.annotations.NotNull
    java.lang.String conversationId, @org.jetbrains.annotations.NotNull
    com.degoogled.androidauto.data.repository.MessagingRepository messagingRepository, @org.jetbrains.annotations.NotNull
    com.degoogled.androidauto.domain.usecase.SendMessageUseCase sendMessageUseCase) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<java.util.List<com.degoogled.androidauto.data.model.Message>> getMessages() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<java.lang.String> getErrorMessage() {
        return null;
    }
    
    public final void sendMessage(@org.jetbrains.annotations.NotNull
    java.lang.String content) {
    }
}