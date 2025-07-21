package com.degoogled.androidauto.data.repository;

import java.lang.System;

/**
 * Implementation of the voice repository using Vosk for offline voice recognition.
 */
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0002J\u0010\u0010\u000b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0002J\u0010\u0010\f\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0002J\u0010\u0010\r\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH\u0002J\u000e\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00070\u000fH\u0016J\u0019\u0010\u0010\u001a\u00020\u00112\u0006\u0010\n\u001a\u00020\tH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0012J\u0011\u0010\u0013\u001a\u00020\u0014H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0015J\u0011\u0010\u0016\u001a\u00020\u0014H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0015R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0017"}, d2 = {"Lcom/degoogled/androidauto/data/repository/VoiceRepositoryImpl;", "Lcom/degoogled/androidauto/data/repository/VoiceRepository;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "recognitionStateFlow", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/degoogled/androidauto/data/repository/RecognitionState;", "extractDestination", "", "text", "extractMessage", "extractRecipient", "extractSong", "getRecognitionState", "Lkotlinx/coroutines/flow/Flow;", "processCommand", "Lcom/degoogled/androidauto/data/model/VoiceCommand;", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "startListening", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "stopListening", "app_release"})
public final class VoiceRepositoryImpl implements com.degoogled.androidauto.data.repository.VoiceRepository {
    private final android.content.Context context = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<com.degoogled.androidauto.data.repository.RecognitionState> recognitionStateFlow = null;
    
    public VoiceRepositoryImpl(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public kotlinx.coroutines.flow.Flow<com.degoogled.androidauto.data.repository.RecognitionState> getRecognitionState() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    @java.lang.Override
    public java.lang.Object startListening(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    @java.lang.Override
    public java.lang.Object stopListening(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    @java.lang.Override
    public java.lang.Object processCommand(@org.jetbrains.annotations.NotNull
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
}