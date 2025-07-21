package com.degoogled.androidauto.ui.messaging;

import java.lang.System;

/**
 * ViewModel for the messaging screen.
 */
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00070\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\r\u00a8\u0006\u0010"}, d2 = {"Lcom/degoogled/androidauto/ui/messaging/MessagingViewModel;", "Landroidx/lifecycle/ViewModel;", "getConversationsUseCase", "Lcom/degoogled/androidauto/domain/usecase/GetConversationsUseCase;", "(Lcom/degoogled/androidauto/domain/usecase/GetConversationsUseCase;)V", "_errorMessage", "Landroidx/lifecycle/MutableLiveData;", "", "conversations", "Landroidx/lifecycle/LiveData;", "", "Lcom/degoogled/androidauto/data/model/Conversation;", "getConversations", "()Landroidx/lifecycle/LiveData;", "errorMessage", "getErrorMessage", "app_release"})
public final class MessagingViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.LiveData<java.util.List<com.degoogled.androidauto.data.model.Conversation>> conversations = null;
    private final androidx.lifecycle.MutableLiveData<java.lang.String> _errorMessage = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.LiveData<java.lang.String> errorMessage = null;
    
    public MessagingViewModel(@org.jetbrains.annotations.NotNull
    com.degoogled.androidauto.domain.usecase.GetConversationsUseCase getConversationsUseCase) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<java.util.List<com.degoogled.androidauto.data.model.Conversation>> getConversations() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<java.lang.String> getErrorMessage() {
        return null;
    }
}