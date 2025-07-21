package com.degoogled.androidauto.domain.usecase;

import java.lang.System;

/**
 * Use case for processing voice commands.
 */
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ*\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u000e\u001a\u00020\u000fH\u0086B\u00f8\u0001\u0000\u00f8\u0001\u0001\u00f8\u0001\u0002\u00f8\u0001\u0002\u00a2\u0006\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u000f\n\u0002\b!\n\u0005\b\u00a1\u001e0\u0001\n\u0002\b\u0019\u00a8\u0006\u0012"}, d2 = {"Lcom/degoogled/androidauto/domain/usecase/ProcessVoiceCommandUseCase;", "", "commandProcessor", "Lcom/degoogled/androidauto/data/voice/CommandProcessor;", "mapRepository", "Lcom/degoogled/androidauto/data/repository/MapRepository;", "mediaRepository", "Lcom/degoogled/androidauto/data/repository/MediaRepository;", "messagingRepository", "Lcom/degoogled/androidauto/data/repository/MessagingRepository;", "(Lcom/degoogled/androidauto/data/voice/CommandProcessor;Lcom/degoogled/androidauto/data/repository/MapRepository;Lcom/degoogled/androidauto/data/repository/MediaRepository;Lcom/degoogled/androidauto/data/repository/MessagingRepository;)V", "invoke", "Lkotlin/Result;", "Lcom/degoogled/androidauto/data/model/VoiceCommand;", "text", "", "invoke-gIAlu-s", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_release"})
public final class ProcessVoiceCommandUseCase {
    private final com.degoogled.androidauto.data.voice.CommandProcessor commandProcessor = null;
    private final com.degoogled.androidauto.data.repository.MapRepository mapRepository = null;
    private final com.degoogled.androidauto.data.repository.MediaRepository mediaRepository = null;
    private final com.degoogled.androidauto.data.repository.MessagingRepository messagingRepository = null;
    
    public ProcessVoiceCommandUseCase(@org.jetbrains.annotations.NotNull
    com.degoogled.androidauto.data.voice.CommandProcessor commandProcessor, @org.jetbrains.annotations.NotNull
    com.degoogled.androidauto.data.repository.MapRepository mapRepository, @org.jetbrains.annotations.NotNull
    com.degoogled.androidauto.data.repository.MediaRepository mediaRepository, @org.jetbrains.annotations.NotNull
    com.degoogled.androidauto.data.repository.MessagingRepository messagingRepository) {
        super();
    }
}