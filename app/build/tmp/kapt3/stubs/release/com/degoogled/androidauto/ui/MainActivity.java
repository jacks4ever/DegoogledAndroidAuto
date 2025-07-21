package com.degoogled.androidauto.ui;

import java.lang.System;

/**
 * Main activity for the Degoogled Android Auto app.
 */
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u0005\u0018\u0000 \"2\u00020\u0001:\u0001\"B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0014\u001a\u00020\u0015H\u0002J\u0012\u0010\u0016\u001a\u00020\u00152\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0014J-\u0010\u0019\u001a\u00020\u00152\u0006\u0010\u001a\u001a\u00020\u001b2\u000e\u0010\u001c\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00120\u00112\u0006\u0010\u001d\u001a\u00020\u001eH\u0016\u00a2\u0006\u0002\u0010\u001fJ\b\u0010 \u001a\u00020\u0015H\u0002J\b\u0010!\u001a\u00020\u0015H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u001b\u0010\u000b\u001a\u00020\f8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000f\u0010\n\u001a\u0004\b\r\u0010\u000eR\u0016\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u0013\u00a8\u0006#"}, d2 = {"Lcom/degoogled/androidauto/ui/MainActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lcom/degoogled/androidauto/databinding/ActivityMainBinding;", "processVoiceCommandUseCase", "Lcom/degoogled/androidauto/domain/usecase/ProcessVoiceCommandUseCase;", "getProcessVoiceCommandUseCase", "()Lcom/degoogled/androidauto/domain/usecase/ProcessVoiceCommandUseCase;", "processVoiceCommandUseCase$delegate", "Lkotlin/Lazy;", "recognizeSpeechUseCase", "Lcom/degoogled/androidauto/domain/usecase/RecognizeSpeechUseCase;", "getRecognizeSpeechUseCase", "()Lcom/degoogled/androidauto/domain/usecase/RecognizeSpeechUseCase;", "recognizeSpeechUseCase$delegate", "requiredPermissions", "", "", "[Ljava/lang/String;", "checkPermissions", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onRequestPermissionsResult", "requestCode", "", "permissions", "grantResults", "", "(I[Ljava/lang/String;[I)V", "setupNavigation", "setupVoiceAssistant", "Companion", "app_release"})
public final class MainActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.degoogled.androidauto.databinding.ActivityMainBinding binding;
    private final kotlin.Lazy recognizeSpeechUseCase$delegate = null;
    private final kotlin.Lazy processVoiceCommandUseCase$delegate = null;
    private final java.lang.String[] requiredPermissions = {"android.permission.ACCESS_FINE_LOCATION", "android.permission.RECORD_AUDIO", "android.permission.READ_EXTERNAL_STORAGE", "android.permission.BLUETOOTH"};
    @org.jetbrains.annotations.NotNull
    public static final com.degoogled.androidauto.ui.MainActivity.Companion Companion = null;
    private static final int PERMISSION_REQUEST_CODE = 100;
    
    public MainActivity() {
        super();
    }
    
    private final com.degoogled.androidauto.domain.usecase.RecognizeSpeechUseCase getRecognizeSpeechUseCase() {
        return null;
    }
    
    private final com.degoogled.androidauto.domain.usecase.ProcessVoiceCommandUseCase getProcessVoiceCommandUseCase() {
        return null;
    }
    
    @java.lang.Override
    protected void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setupNavigation() {
    }
    
    private final void setupVoiceAssistant() {
    }
    
    private final void checkPermissions() {
    }
    
    @java.lang.Override
    public void onRequestPermissionsResult(int requestCode, @org.jetbrains.annotations.NotNull
    java.lang.String[] permissions, @org.jetbrains.annotations.NotNull
    int[] grantResults) {
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/degoogled/androidauto/ui/MainActivity$Companion;", "", "()V", "PERMISSION_REQUEST_CODE", "", "app_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}