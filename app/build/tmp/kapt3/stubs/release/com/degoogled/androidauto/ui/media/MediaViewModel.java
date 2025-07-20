package com.degoogled.androidauto.ui.media;

import java.lang.System;

/**
 * ViewModel for the media screen.
 */
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0006\u0010\u001c\u001a\u00020\u001dJ\u000e\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020\u000eJ\u0006\u0010 \u001a\u00020\u001dJ\u0006\u0010!\u001a\u00020\u001dR\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u000b0\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u00140\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0010R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0010R\u001d\u0010\u0019\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a0\u00140\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0010\u00a8\u0006\""}, d2 = {"Lcom/degoogled/androidauto/ui/media/MediaViewModel;", "Landroidx/lifecycle/ViewModel;", "mediaRepository", "Lcom/degoogled/androidauto/data/repository/MediaRepository;", "getMediaLibraryUseCase", "Lcom/degoogled/androidauto/domain/usecase/GetMediaLibraryUseCase;", "playMediaUseCase", "Lcom/degoogled/androidauto/domain/usecase/PlayMediaUseCase;", "(Lcom/degoogled/androidauto/data/repository/MediaRepository;Lcom/degoogled/androidauto/domain/usecase/GetMediaLibraryUseCase;Lcom/degoogled/androidauto/domain/usecase/PlayMediaUseCase;)V", "_errorMessage", "Landroidx/lifecycle/MutableLiveData;", "", "currentMedia", "Landroidx/lifecycle/LiveData;", "Lcom/degoogled/androidauto/data/model/Media;", "getCurrentMedia", "()Landroidx/lifecycle/LiveData;", "errorMessage", "getErrorMessage", "mediaLibrary", "", "getMediaLibrary", "playbackState", "Lcom/degoogled/androidauto/data/repository/PlaybackState;", "getPlaybackState", "playlists", "Lcom/degoogled/androidauto/data/model/Playlist;", "getPlaylists", "nextTrack", "", "playMedia", "media", "previousTrack", "togglePlayPause", "app_release"})
public final class MediaViewModel extends androidx.lifecycle.ViewModel {
    private final com.degoogled.androidauto.data.repository.MediaRepository mediaRepository = null;
    private final com.degoogled.androidauto.domain.usecase.GetMediaLibraryUseCase getMediaLibraryUseCase = null;
    private final com.degoogled.androidauto.domain.usecase.PlayMediaUseCase playMediaUseCase = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.LiveData<java.util.List<com.degoogled.androidauto.data.model.Media>> mediaLibrary = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.LiveData<java.util.List<com.degoogled.androidauto.data.model.Playlist>> playlists = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.LiveData<com.degoogled.androidauto.data.model.Media> currentMedia = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.LiveData<com.degoogled.androidauto.data.repository.PlaybackState> playbackState = null;
    private final androidx.lifecycle.MutableLiveData<java.lang.String> _errorMessage = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.LiveData<java.lang.String> errorMessage = null;
    
    public MediaViewModel(@org.jetbrains.annotations.NotNull
    com.degoogled.androidauto.data.repository.MediaRepository mediaRepository, @org.jetbrains.annotations.NotNull
    com.degoogled.androidauto.domain.usecase.GetMediaLibraryUseCase getMediaLibraryUseCase, @org.jetbrains.annotations.NotNull
    com.degoogled.androidauto.domain.usecase.PlayMediaUseCase playMediaUseCase) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<java.util.List<com.degoogled.androidauto.data.model.Media>> getMediaLibrary() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<java.util.List<com.degoogled.androidauto.data.model.Playlist>> getPlaylists() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<com.degoogled.androidauto.data.model.Media> getCurrentMedia() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<com.degoogled.androidauto.data.repository.PlaybackState> getPlaybackState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<java.lang.String> getErrorMessage() {
        return null;
    }
    
    public final void playMedia(@org.jetbrains.annotations.NotNull
    com.degoogled.androidauto.data.model.Media media) {
    }
    
    public final void togglePlayPause() {
    }
    
    public final void nextTrack() {
    }
    
    public final void previousTrack() {
    }
}