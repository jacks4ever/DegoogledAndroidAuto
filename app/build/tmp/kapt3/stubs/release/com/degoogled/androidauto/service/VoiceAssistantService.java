package com.degoogled.androidauto.service;

import java.lang.System;

/**
 * Service for voice assistant functionality.
 * Uses Vosk for offline speech recognition.
 */
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0007\u0018\u0000 /2\u00020\u0001:\u0003/01B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010!\u001a\u00020\"H\u0002J\u0012\u0010#\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010&H\u0016J\b\u0010\'\u001a\u00020\"H\u0016J\b\u0010(\u001a\u00020\"H\u0016J\u0010\u0010)\u001a\u00020\"2\u0006\u0010*\u001a\u00020+H\u0002J\u000e\u0010,\u001a\u00020\"2\u0006\u0010*\u001a\u00020+J\u0006\u0010-\u001a\u00020\"J\u0006\u0010.\u001a\u00020\"R\u0016\u0010\u0003\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0012\u0010\b\u001a\u00060\tR\u00020\u0000X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0019\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001b\u0010\u0010\u001a\u00020\u00118BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0012\u0010\u0013R\u001b\u0010\u0016\u001a\u00020\u00178BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u001a\u0010\u0015\u001a\u0004\b\u0018\u0010\u0019R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00070\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u000fR\u0010\u0010\u001f\u001a\u0004\u0018\u00010 X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u00062"}, d2 = {"Lcom/degoogled/androidauto/service/VoiceAssistantService;", "Landroid/app/Service;", "()V", "_lastCommand", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/degoogled/androidauto/data/model/VoiceCommand;", "_state", "Lcom/degoogled/androidauto/service/VoiceAssistantService$VoiceAssistantState;", "binder", "Lcom/degoogled/androidauto/service/VoiceAssistantService$VoiceAssistantBinder;", "isTtsReady", "", "lastCommand", "Lkotlinx/coroutines/flow/StateFlow;", "getLastCommand", "()Lkotlinx/coroutines/flow/StateFlow;", "processVoiceCommandUseCase", "Lcom/degoogled/androidauto/domain/usecase/ProcessVoiceCommandUseCase;", "getProcessVoiceCommandUseCase", "()Lcom/degoogled/androidauto/domain/usecase/ProcessVoiceCommandUseCase;", "processVoiceCommandUseCase$delegate", "Lkotlin/Lazy;", "recognizeSpeechUseCase", "Lcom/degoogled/androidauto/domain/usecase/RecognizeSpeechUseCase;", "getRecognizeSpeechUseCase", "()Lcom/degoogled/androidauto/domain/usecase/RecognizeSpeechUseCase;", "recognizeSpeechUseCase$delegate", "serviceScope", "Lkotlinx/coroutines/CoroutineScope;", "state", "getState", "textToSpeech", "Landroid/speech/tts/TextToSpeech;", "initTextToSpeech", "", "onBind", "Landroid/os/IBinder;", "intent", "Landroid/content/Intent;", "onCreate", "onDestroy", "processCommand", "text", "", "speak", "startListening", "stopListening", "Companion", "VoiceAssistantBinder", "VoiceAssistantState", "app_release"})
public final class VoiceAssistantService extends android.app.Service {
    private final kotlin.Lazy recognizeSpeechUseCase$delegate = null;
    private final kotlin.Lazy processVoiceCommandUseCase$delegate = null;
    private final kotlinx.coroutines.CoroutineScope serviceScope = null;
    private final com.degoogled.androidauto.service.VoiceAssistantService.VoiceAssistantBinder binder = null;
    private android.speech.tts.TextToSpeech textToSpeech;
    private boolean isTtsReady = false;
    private final kotlinx.coroutines.flow.MutableStateFlow<com.degoogled.androidauto.service.VoiceAssistantService.VoiceAssistantState> _state = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<com.degoogled.androidauto.service.VoiceAssistantService.VoiceAssistantState> state = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<com.degoogled.androidauto.data.model.VoiceCommand> _lastCommand = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<com.degoogled.androidauto.data.model.VoiceCommand> lastCommand = null;
    @org.jetbrains.annotations.NotNull
    public static final com.degoogled.androidauto.service.VoiceAssistantService.Companion Companion = null;
    private static final java.lang.String TAG = "VoiceAssistantService";
    
    public VoiceAssistantService() {
        super();
    }
    
    private final com.degoogled.androidauto.domain.usecase.RecognizeSpeechUseCase getRecognizeSpeechUseCase() {
        return null;
    }
    
    private final com.degoogled.androidauto.domain.usecase.ProcessVoiceCommandUseCase getProcessVoiceCommandUseCase() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<com.degoogled.androidauto.service.VoiceAssistantService.VoiceAssistantState> getState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<com.degoogled.androidauto.data.model.VoiceCommand> getLastCommand() {
        return null;
    }
    
    @java.lang.Override
    public void onCreate() {
    }
    
    private final void initTextToSpeech() {
    }
    
    public final void startListening() {
    }
    
    private final void processCommand(java.lang.String text) {
    }
    
    public final void speak(@org.jetbrains.annotations.NotNull
    java.lang.String text) {
    }
    
    public final void stopListening() {
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public android.os.IBinder onBind(@org.jetbrains.annotations.Nullable
    android.content.Intent intent) {
        return null;
    }
    
    @java.lang.Override
    public void onDestroy() {
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004\u00a8\u0006\u0005"}, d2 = {"Lcom/degoogled/androidauto/service/VoiceAssistantService$VoiceAssistantBinder;", "Landroid/os/Binder;", "(Lcom/degoogled/androidauto/service/VoiceAssistantService;)V", "getService", "Lcom/degoogled/androidauto/service/VoiceAssistantService;", "app_release"})
    public final class VoiceAssistantBinder extends android.os.Binder {
        
        public VoiceAssistantBinder() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.degoogled.androidauto.service.VoiceAssistantService getService() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/degoogled/androidauto/service/VoiceAssistantService$VoiceAssistantState;", "", "(Ljava/lang/String;I)V", "IDLE", "LISTENING", "PROCESSING", "SPEAKING", "app_release"})
    public static enum VoiceAssistantState {
        /*public static final*/ IDLE /* = new IDLE() */,
        /*public static final*/ LISTENING /* = new LISTENING() */,
        /*public static final*/ PROCESSING /* = new PROCESSING() */,
        /*public static final*/ SPEAKING /* = new SPEAKING() */;
        
        VoiceAssistantState() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/degoogled/androidauto/service/VoiceAssistantService$Companion;", "", "()V", "TAG", "", "app_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}