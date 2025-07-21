package com.degoogled.androidauto.data.voice;

import java.lang.System;

/**
 * Speech recognizer implementation using Vosk for offline speech recognition.
 */
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0011\u0010\f\u001a\u00020\rH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000eJ\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010H\u0002J\u0011\u0010\u0012\u001a\u00020\u0010H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000eJ\u0006\u0010\u0013\u001a\u00020\u0014R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\tR\u0010\u0010\n\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\tR\u000e\u0010\u000b\u001a\u00020\u0006X\u0082D\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0016"}, d2 = {"Lcom/degoogled/androidauto/data/voice/VoskSpeechRecognizer;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "bufferSize", "", "model", "error/NonExistentClass", "Lerror/NonExistentClass;", "recognizer", "sampleRate", "initialize", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "parseResult", "", "jsonResult", "recognize", "release", "", "Companion", "app_release"})
public final class VoskSpeechRecognizer {
    private final android.content.Context context = null;
    private error.NonExistentClass model;
    private error.NonExistentClass recognizer;
    private final int sampleRate = 16000;
    private final int bufferSize = 4096;
    @org.jetbrains.annotations.NotNull
    public static final com.degoogled.androidauto.data.voice.VoskSpeechRecognizer.Companion Companion = null;
    private static final java.lang.String TAG = "VoskSpeechRecognizer";
    private static final double SILENCE_THRESHOLD = 200.0;
    private static final int SILENCE_DURATION = 30;
    private static final int MAX_DURATION = 300;
    
    public VoskSpeechRecognizer(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object initialize(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Boolean> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object recognize(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.String> continuation) {
        return null;
    }
    
    private final java.lang.String parseResult(java.lang.String jsonResult) {
        return null;
    }
    
    public final void release() {
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\n"}, d2 = {"Lcom/degoogled/androidauto/data/voice/VoskSpeechRecognizer$Companion;", "", "()V", "MAX_DURATION", "", "SILENCE_DURATION", "SILENCE_THRESHOLD", "", "TAG", "", "app_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}