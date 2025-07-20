package com.degoogled.androidauto.ui.media

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.degoogled.androidauto.data.model.Media
import com.degoogled.androidauto.data.model.MediaType
import com.degoogled.androidauto.data.repository.MediaRepository
import com.degoogled.androidauto.data.repository.PlaybackState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class MediaViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    
    private val testDispatcher = TestCoroutineDispatcher()
    
    private lateinit var viewModel: MediaViewModel
    private lateinit var mediaRepository: MediaRepository
    
    private val mediaList = listOf(
        Media(
            id = "1",
            title = "Song 1",
            artist = "Artist 1",
            album = "Album 1",
            uri = "file:///music/song1.mp3",
            type = MediaType.AUDIO,
            duration = 180,
            artworkUri = null
        ),
        Media(
            id = "2",
            title = "Song 2",
            artist = "Artist 2",
            album = "Album 1",
            uri = "file:///music/song2.mp3",
            type = MediaType.AUDIO,
            duration = 240,
            artworkUri = null
        )
    )
    
    private val _mediaItems = MutableStateFlow<List<Media>>(mediaList)
    private val _currentMedia = MutableStateFlow<Media?>(null)
    private val _playbackState = MutableStateFlow(PlaybackState.STOPPED)
    
    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        
        mediaRepository = mock()
        
        whenever(mediaRepository.getAllMedia()).thenReturn(_mediaItems as StateFlow<List<Media>>)
        whenever(mediaRepository.getCurrentMedia()).thenReturn(_currentMedia as StateFlow<Media?>)
        whenever(mediaRepository.getPlaybackState()).thenReturn(_playbackState as StateFlow<PlaybackState>)
        
        viewModel = MediaViewModel(mediaRepository)
    }
    
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }
    
    @Test
    fun `initial state should be correct`() {
        assert(viewModel.mediaItems.value == mediaList)
        assert(viewModel.currentMedia.value == null)
        assert(viewModel.playbackState.value == PlaybackState.STOPPED)
    }
    
    @Test
    fun `playMedia should call repository`() = testDispatcher.runBlockingTest {
        // Given
        val media = mediaList[0]
        
        // When
        viewModel.playMedia(media)
        
        // Then
        verify(mediaRepository).playMedia(media)
    }
    
    @Test
    fun `pauseMedia should call repository`() = testDispatcher.runBlockingTest {
        // When
        viewModel.pauseMedia()
        
        // Then
        verify(mediaRepository).pauseMedia()
    }
    
    @Test
    fun `resumeMedia should call repository`() = testDispatcher.runBlockingTest {
        // When
        viewModel.resumeMedia()
        
        // Then
        verify(mediaRepository).resumeMedia()
    }
    
    @Test
    fun `nextTrack should call repository`() = testDispatcher.runBlockingTest {
        // When
        viewModel.nextTrack()
        
        // Then
        verify(mediaRepository).nextTrack()
    }
    
    @Test
    fun `previousTrack should call repository`() = testDispatcher.runBlockingTest {
        // When
        viewModel.previousTrack()
        
        // Then
        verify(mediaRepository).previousTrack()
    }
    
    @Test
    fun `searchMedia should filter media items`() {
        // Given
        val query = "Song 1"
        
        // When
        viewModel.searchMedia(query)
        
        // Then
        assert(viewModel.filteredMediaItems.value?.size == 1)
        assert(viewModel.filteredMediaItems.value?.get(0)?.title == "Song 1")
    }
    
    @Test
    fun `searchMedia with empty query should show all items`() {
        // Given
        val query = ""
        
        // When
        viewModel.searchMedia(query)
        
        // Then
        assert(viewModel.filteredMediaItems.value?.size == 2)
    }
    
    @Test
    fun `repository updates should be reflected in view model`() {
        // Given
        val newMedia = Media(
            id = "3",
            title = "Song 3",
            artist = "Artist 3",
            album = "Album 2",
            uri = "file:///music/song3.mp3",
            type = MediaType.AUDIO,
            duration = 300,
            artworkUri = null
        )
        
        // When
        _mediaItems.value = mediaList + newMedia
        _currentMedia.value = newMedia
        _playbackState.value = PlaybackState.PLAYING
        
        // Then
        assert(viewModel.mediaItems.value.size == 3)
        assert(viewModel.currentMedia.value == newMedia)
        assert(viewModel.playbackState.value == PlaybackState.PLAYING)
    }
}