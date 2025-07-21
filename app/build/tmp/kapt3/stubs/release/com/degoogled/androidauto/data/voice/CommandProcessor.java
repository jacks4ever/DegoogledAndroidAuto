package com.degoogled.androidauto.data.voice;

import java.lang.System;

/**
 * Processes voice commands and determines the appropriate action.
 */
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0002J\u0010\u0010\u000e\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0002J\u0010\u0010\u000f\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0002J\u0010\u0010\u0010\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0002J\b\u0010\u0011\u001a\u00020\fH\u0002J\u0019\u0010\u0012\u001a\u00020\u00132\u0006\u0010\r\u001a\u00020\fH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0014R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0015"}, d2 = {"Lcom/degoogled/androidauto/data/voice/CommandProcessor;", "", "context", "Landroid/content/Context;", "navigationRepository", "Lcom/degoogled/androidauto/data/repository/NavigationRepository;", "mediaRepository", "Lcom/degoogled/androidauto/data/repository/MediaRepository;", "messagingRepository", "Lcom/degoogled/androidauto/data/repository/MessagingRepository;", "(Landroid/content/Context;Lcom/degoogled/androidauto/data/repository/NavigationRepository;Lcom/degoogled/androidauto/data/repository/MediaRepository;Lcom/degoogled/androidauto/data/repository/MessagingRepository;)V", "extractDestination", "", "text", "extractMessage", "extractRecipient", "extractSong", "getCurrentTime", "processCommand", "Lcom/degoogled/androidauto/data/model/VoiceCommand;", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_release"})
public final class CommandProcessor {
    private final android.content.Context context = null;
    private final com.degoogled.androidauto.data.repository.NavigationRepository navigationRepository = null;
    private final com.degoogled.androidauto.data.repository.MediaRepository mediaRepository = null;
    private final com.degoogled.androidauto.data.repository.MessagingRepository messagingRepository = null;
    
    public CommandProcessor(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.NotNull
    com.degoogled.androidauto.data.repository.NavigationRepository navigationRepository, @org.jetbrains.annotations.NotNull
    com.degoogled.androidauto.data.repository.MediaRepository mediaRepository, @org.jetbrains.annotations.NotNull
    com.degoogled.androidauto.data.repository.MessagingRepository messagingRepository) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object processCommand(@org.jetbrains.annotations.NotNull
    java.lang.String text, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.degoogled.androidauto.data.model.VoiceCommand> continuation) {
        return null;
    }
    
    private final java.lang.String extractDestination(java.lang.String text) {
        return null;
    }
    
    private final java.lang.String extractSong(java.lang.String text) {
        return null;
    }
    
    private final java.lang.String extractRecipient(java.lang.String text) {
        return null;
    }
    
    private final java.lang.String extractMessage(java.lang.String text) {
        return null;
    }
    
    private final java.lang.String getCurrentTime() {
        return null;
    }
}