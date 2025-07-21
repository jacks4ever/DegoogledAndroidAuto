package com.degoogled.androidauto.data.repository;

import java.lang.System;

/**
 * Repository interface for media-related operations.
 */
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0010\t\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003H&J\u0014\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00070\u0006H&J\u000e\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0003H&J\u0014\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u00070\u0006H&J\u0011\u0010\f\u001a\u00020\rH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000eJ\u0011\u0010\u000f\u001a\u00020\rH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000eJ\u0019\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u0004H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0012J\u0011\u0010\u0013\u001a\u00020\rH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000eJ\u0011\u0010\u0014\u001a\u00020\rH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000eJ\u0019\u0010\u0015\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\u0017H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0018J\u0011\u0010\u0019\u001a\u00020\rH\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000e\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u001a"}, d2 = {"Lcom/degoogled/androidauto/data/repository/MediaRepository;", "", "getCurrentMedia", "Lkotlinx/coroutines/flow/StateFlow;", "Lcom/degoogled/androidauto/data/model/Media;", "getMediaLibrary", "Lkotlinx/coroutines/flow/Flow;", "", "getPlaybackState", "Lcom/degoogled/androidauto/data/repository/PlaybackState;", "getPlaylists", "Lcom/degoogled/androidauto/data/model/Playlist;", "nextTrack", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "pauseMedia", "playMedia", "media", "(Lcom/degoogled/androidauto/data/model/Media;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "previousTrack", "resumeMedia", "seekTo", "position", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "stopMedia", "app_release"})
public abstract interface MediaRepository {
    
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.degoogled.androidauto.data.model.Media>> getMediaLibrary();
    
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.degoogled.androidauto.data.model.Playlist>> getPlaylists();
    
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.StateFlow<com.degoogled.androidauto.data.model.Media> getCurrentMedia();
    
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.StateFlow<com.degoogled.androidauto.data.repository.PlaybackState> getPlaybackState();
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object playMedia(@org.jetbrains.annotations.NotNull
    com.degoogled.androidauto.data.model.Media media, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object pauseMedia(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object resumeMedia(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object stopMedia(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object nextTrack(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object previousTrack(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object seekTo(long position, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
}