package com.degoogled.androidauto.domain.usecase;

import java.lang.System;

/**
 * Use case for recognizing speech.
 */
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006J\u0011\u0010\b\u001a\u00020\tH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nJ\u0011\u0010\u000b\u001a\u00020\tH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\f"}, d2 = {"Lcom/degoogled/androidauto/domain/usecase/RecognizeSpeechUseCase;", "", "voiceRepository", "Lcom/degoogled/androidauto/data/repository/VoiceRepository;", "(Lcom/degoogled/androidauto/data/repository/VoiceRepository;)V", "getRecognitionState", "Lkotlinx/coroutines/flow/Flow;", "Lcom/degoogled/androidauto/data/repository/RecognitionState;", "startListening", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "stopListening", "app_release"})
public final class RecognizeSpeechUseCase {
    private final com.degoogled.androidauto.data.repository.VoiceRepository voiceRepository = null;
    
    public RecognizeSpeechUseCase(@org.jetbrains.annotations.NotNull
    com.degoogled.androidauto.data.repository.VoiceRepository voiceRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.Flow<com.degoogled.androidauto.data.repository.RecognitionState> getRecognitionState() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object startListening(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object stopListening(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
}