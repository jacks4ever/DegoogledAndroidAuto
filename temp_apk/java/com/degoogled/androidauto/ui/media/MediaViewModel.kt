package com.degoogled.androidauto.ui.media

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.degoogled.androidauto.data.model.Media
import com.degoogled.androidauto.data.model.Playlist
import com.degoogled.androidauto.data.repository.MediaRepository
import com.degoogled.androidauto.data.repository.PlaybackState
import com.degoogled.androidauto.domain.usecase.GetMediaLibraryUseCase
import com.degoogled.androidauto.domain.usecase.PlayMediaUseCase
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

/**
 * ViewModel for the media screen.
 */
class MediaViewModel(
    private val mediaRepository: MediaRepository,
    private val getMediaLibraryUseCase: GetMediaLibraryUseCase,
    private val playMediaUseCase: PlayMediaUseCase
) : ViewModel() {

    val mediaLibrary: LiveData<List<Media>> = getMediaLibraryUseCase().asLiveData()
    
    val playlists: LiveData<List<Playlist>> = mediaRepository.getPlaylists().asLiveData()
    
    val currentMedia: LiveData<Media?> = mediaRepository.getCurrentMedia().asLiveData()
    
    val playbackState: LiveData<PlaybackState> = mediaRepository.getPlaybackState().asLiveData()
    
    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage
    
    fun playMedia(media: Media) {
        viewModelScope.launch {
            try {
                playMediaUseCase(media)
            } catch (e: Exception) {
                _errorMessage.value = "Failed to play media: ${e.message}"
            }
        }
    }
    
    fun togglePlayPause() {
        viewModelScope.launch {
            try {
                when (mediaRepository.getPlaybackState().value) {
                    PlaybackState.PLAYING -> mediaRepository.pauseMedia()
                    PlaybackState.PAUSED -> mediaRepository.resumeMedia()
                    else -> {
                        // If no media is playing, play the first item in the library
                        val media = mediaRepository.getMediaLibrary().value.firstOrNull()
                        if (media != null) {
                            mediaRepository.playMedia(media)
                        }
                    }
                }
            } catch (e: Exception) {
                _errorMessage.value = "Playback control failed: ${e.message}"
            }
        }
    }
    
    fun nextTrack() {
        viewModelScope.launch {
            try {
                mediaRepository.nextTrack()
            } catch (e: Exception) {
                _errorMessage.value = "Failed to play next track: ${e.message}"
            }
        }
    }
    
    fun previousTrack() {
        viewModelScope.launch {
            try {
                mediaRepository.previousTrack()
            } catch (e: Exception) {
                _errorMessage.value = "Failed to play previous track: ${e.message}"
            }
        }
    }
}