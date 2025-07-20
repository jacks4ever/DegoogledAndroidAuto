package com.degoogled.androidauto.service

import android.content.Context
import com.degoogled.androidauto.data.model.Media
import com.degoogled.androidauto.data.model.MediaType
import com.degoogled.androidauto.data.repository.MediaRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class MediaPlaybackServiceTest {

    private val testDispatcher = TestCoroutineDispatcher()
    
    private lateinit var service: MediaPlaybackService
    private lateinit var context: Context
    private lateinit var mediaRepository: MediaRepository
    
    private val mockMediaList = listOf(
        Media(
            id = "1",
            title = "Song 1",
            artist = "Artist 1",
            album = "Album 1",
            uri = "file:///music/song1.mp3",
            type = MediaType.AUDIO,
            duration = 180000, // 3 minutes
            artworkUri = null
        ),
        Media(
            id = "2",
            title = "Song 2",
            artist = "Artist 2",
            album = "Album 2",
            uri = "file:///music/song2.mp3",
            type = MediaType.AUDIO,
            duration = 240000, // 4 minutes
            artworkUri = null
        ),
        Media(
            id = "3",
            title = "Song 3",
            artist = "Artist 3",
            album = "Album 3",
            uri = "file:///music/song3.mp3",
            type = MediaType.AUDIO,
            duration = 300000, // 5 minutes
            artworkUri = null
        )
    )
    
    @Before
    fun setup() {
        context = mock()
        mediaRepository = mock()
        
        // Set up mock repository responses
        val mediaFlow = MutableStateFlow(mockMediaList)
        whenever(mediaRepository.getAllMedia()).thenReturn(mediaFlow)
        
        service = MediaPlaybackService()
    }
    
    @Test
    fun `playMedia should update playback state`() = testDispatcher.runBlockingTest {
        // Given
        val media = mockMediaList[0]
        
        // When
        service.playMedia(media)
        
        // Then
        assert(service.playbackState.value.currentMedia == media)
        assert(service.playbackState.value.isPlaying)
    }
    
    @Test
    fun `pauseMedia should update playback state`() = testDispatcher.runBlockingTest {
        // Given
        val media = mockMediaList[0]
        service.playMedia(media)
        
        // When
        service.pauseMedia()
        
        // Then
        assert(service.playbackState.value.currentMedia == media)
        assert(!service.playbackState.value.isPlaying)
    }
    
    @Test
    fun `resumeMedia should update playback state`() = testDispatcher.runBlockingTest {
        // Given
        val media = mockMediaList[0]
        service.playMedia(media)
        service.pauseMedia()
        
        // When
        service.resumeMedia()
        
        // Then
        assert(service.playbackState.value.currentMedia == media)
        assert(service.playbackState.value.isPlaying)
    }
    
    @Test
    fun `nextTrack should play the next track`() = testDispatcher.runBlockingTest {
        // Given
        val media = mockMediaList[0]
        service.setPlaylist(mockMediaList)
        service.playMedia(media)
        
        // When
        service.nextTrack()
        
        // Then
        assert(service.playbackState.value.currentMedia == mockMediaList[1])
        assert(service.playbackState.value.isPlaying)
    }
    
    @Test
    fun `previousTrack should play the previous track`() = testDispatcher.runBlockingTest {
        // Given
        val media = mockMediaList[1]
        service.setPlaylist(mockMediaList)
        service.playMedia(media)
        
        // When
        service.previousTrack()
        
        // Then
        assert(service.playbackState.value.currentMedia == mockMediaList[0])
        assert(service.playbackState.value.isPlaying)
    }
    
    @Test
    fun `seekTo should update playback position`() = testDispatcher.runBlockingTest {
        // Given
        val media = mockMediaList[0]
        service.playMedia(media)
        
        // When
        val position = 60000L // 1 minute
        service.seekTo(position)
        
        // Then
        assert(service.playbackState.value.currentPosition == position)
    }
    
    @Test
    fun `setPlaylist should update the playlist`() = testDispatcher.runBlockingTest {
        // Given
        val playlist = mockMediaList
        
        // When
        service.setPlaylist(playlist)
        
        // Then
        assert(service.playlist.value == playlist)
    }
}