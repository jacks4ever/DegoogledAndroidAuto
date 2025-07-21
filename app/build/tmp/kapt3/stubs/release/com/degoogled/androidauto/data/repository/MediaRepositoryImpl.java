package com.degoogled.androidauto.data.repository;

import java.lang.System;

/**
 * Implementation of the media repository using VLC for media playback.
 */
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0010\t\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\u000e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u000fH\u0016J\u0014\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\t0\u0011H\u0016J\u000e\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000b0\u000fH\u0016J\u0014\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\t0\u0011H\u0016J\u0011\u0010\u0014\u001a\u00020\u0015H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0016J\u0011\u0010\u0017\u001a\u00020\u0015H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0016J\u0019\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\u0007H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001aJ\u0011\u0010\u001b\u001a\u00020\u0015H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0016J\u0011\u0010\u001c\u001a\u00020\u0015H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0016J\u0019\u0010\u001d\u001a\u00020\u00152\u0006\u0010\u001e\u001a\u00020\u001fH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010 J\u0011\u0010!\u001a\u00020\u0015H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\t0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\t0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\""}, d2 = {"Lcom/degoogled/androidauto/data/repository/MediaRepositoryImpl;", "Lcom/degoogled/androidauto/data/repository/MediaRepository;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "currentMediaFlow", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/degoogled/androidauto/data/model/Media;", "mediaLibraryFlow", "", "playbackStateFlow", "Lcom/degoogled/androidauto/data/repository/PlaybackState;", "playlistsFlow", "Lcom/degoogled/androidauto/data/model/Playlist;", "getCurrentMedia", "Lkotlinx/coroutines/flow/StateFlow;", "getMediaLibrary", "Lkotlinx/coroutines/flow/Flow;", "getPlaybackState", "getPlaylists", "nextTrack", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "pauseMedia", "playMedia", "media", "(Lcom/degoogled/androidauto/data/model/Media;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "previousTrack", "resumeMedia", "seekTo", "position", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "stopMedia", "app_release"})
public final class MediaRepositoryImpl implements com.degoogled.androidauto.data.repository.MediaRepository {
    private final android.content.Context context = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.degoogled.androidauto.data.model.Media>> mediaLibraryFlow = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.degoogled.androidauto.data.model.Playlist>> playlistsFlow = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<com.degoogled.androidauto.data.model.Media> currentMediaFlow = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<com.degoogled.androidauto.data.repository.PlaybackState> playbackStateFlow = null;
    
    public MediaRepositoryImpl(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public kotlinx.coroutines.flow.Flow<java.util.List<com.degoogled.androidauto.data.model.Media>> getMediaLibrary() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public kotlinx.coroutines.flow.Flow<java.util.List<com.degoogled.androidauto.data.model.Playlist>> getPlaylists() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public kotlinx.coroutines.flow.StateFlow<com.degoogled.androidauto.data.model.Media> getCurrentMedia() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    @java.lang.Override
    public kotlinx.coroutines.flow.StateFlow<com.degoogled.androidauto.data.repository.PlaybackState> getPlaybackState() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    @java.lang.Override
    public java.lang.Object playMedia(@org.jetbrains.annotations.NotNull
    com.degoogled.androidauto.data.model.Media media, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    @java.lang.Override
    public java.lang.Object pauseMedia(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    @java.lang.Override
    public java.lang.Object resumeMedia(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    @java.lang.Override
    public java.lang.Object stopMedia(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    @java.lang.Override
    public java.lang.Object nextTrack(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    @java.lang.Override
    public java.lang.Object previousTrack(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    @java.lang.Override
    public java.lang.Object seekTo(long position, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
}