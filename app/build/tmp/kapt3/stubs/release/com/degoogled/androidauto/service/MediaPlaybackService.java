package com.degoogled.androidauto.service;

import java.lang.System;

/**
 * Service for media playback.
 * Uses libVLC for media playback.
 */
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 \u001f2\u00020\u0001:\u0002\u001f B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0013\u001a\u00020\u0014H\u0002J\b\u0010\u0015\u001a\u00020\u0014H\u0002J\u0012\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016J\b\u0010\u001a\u001a\u00020\u0014H\u0016J\b\u0010\u001b\u001a\u00020\u0014H\u0016J\u0010\u0010\u001c\u001a\u00020\u00142\u0006\u0010\u001d\u001a\u00020\u0006H\u0002J\b\u0010\u001e\u001a\u00020\u0014H\u0002R\u0012\u0010\u0003\u001a\u00060\u0004R\u00020\u0000X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\tR\u0010\u0010\n\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\tR\u001b\u0010\u000b\u001a\u00020\f8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006!"}, d2 = {"Lcom/degoogled/androidauto/service/MediaPlaybackService;", "Landroid/app/Service;", "()V", "binder", "Lcom/degoogled/androidauto/service/MediaPlaybackService$MediaPlaybackBinder;", "currentMedia", "Lcom/degoogled/androidauto/data/model/Media;", "libVlc", "error/NonExistentClass", "Lerror/NonExistentClass;", "mediaPlayer", "mediaRepository", "Lcom/degoogled/androidauto/data/repository/MediaRepository;", "getMediaRepository", "()Lcom/degoogled/androidauto/data/repository/MediaRepository;", "mediaRepository$delegate", "Lkotlin/Lazy;", "serviceScope", "Lkotlinx/coroutines/CoroutineScope;", "initVlc", "", "observeMediaRepository", "onBind", "Landroid/os/IBinder;", "intent", "Landroid/content/Intent;", "onCreate", "onDestroy", "playMedia", "media", "updateNotification", "Companion", "MediaPlaybackBinder", "app_release"})
public final class MediaPlaybackService extends android.app.Service {
    private final kotlin.Lazy mediaRepository$delegate = null;
    private final kotlinx.coroutines.CoroutineScope serviceScope = null;
    private final com.degoogled.androidauto.service.MediaPlaybackService.MediaPlaybackBinder binder = null;
    private error.NonExistentClass libVlc;
    private error.NonExistentClass mediaPlayer;
    private com.degoogled.androidauto.data.model.Media currentMedia;
    @org.jetbrains.annotations.NotNull
    public static final com.degoogled.androidauto.service.MediaPlaybackService.Companion Companion = null;
    private static final java.lang.String TAG = "MediaPlaybackService";
    private static final int NOTIFICATION_ID = 1;
    
    public MediaPlaybackService() {
        super();
    }
    
    private final com.degoogled.androidauto.data.repository.MediaRepository getMediaRepository() {
        return null;
    }
    
    @java.lang.Override
    public void onCreate() {
    }
    
    private final void initVlc() {
    }
    
    private final void observeMediaRepository() {
    }
    
    private final void playMedia(com.degoogled.androidauto.data.model.Media media) {
    }
    
    private final void updateNotification() {
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
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004\u00a8\u0006\u0005"}, d2 = {"Lcom/degoogled/androidauto/service/MediaPlaybackService$MediaPlaybackBinder;", "Landroid/os/Binder;", "(Lcom/degoogled/androidauto/service/MediaPlaybackService;)V", "getService", "Lcom/degoogled/androidauto/service/MediaPlaybackService;", "app_release"})
    public final class MediaPlaybackBinder extends android.os.Binder {
        
        public MediaPlaybackBinder() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.degoogled.androidauto.service.MediaPlaybackService getService() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0007"}, d2 = {"Lcom/degoogled/androidauto/service/MediaPlaybackService$Companion;", "", "()V", "NOTIFICATION_ID", "", "TAG", "", "app_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}