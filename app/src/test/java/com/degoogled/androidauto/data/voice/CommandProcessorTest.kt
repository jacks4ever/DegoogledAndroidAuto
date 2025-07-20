package com.degoogled.androidauto.data.voice

import android.content.Context
import com.degoogled.androidauto.data.model.VoiceCommandType
import com.degoogled.androidauto.data.repository.MediaRepository
import com.degoogled.androidauto.data.repository.MessagingRepository
import com.degoogled.androidauto.data.repository.NavigationRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock

@ExperimentalCoroutinesApi
class CommandProcessorTest {

    private val testDispatcher = TestCoroutineDispatcher()
    
    private lateinit var commandProcessor: CommandProcessor
    private lateinit var context: Context
    private lateinit var navigationRepository: NavigationRepository
    private lateinit var mediaRepository: MediaRepository
    private lateinit var messagingRepository: MessagingRepository
    
    @Before
    fun setup() {
        context = mock()
        navigationRepository = mock()
        mediaRepository = mock()
        messagingRepository = mock()
        
        commandProcessor = CommandProcessor(
            context,
            navigationRepository,
            mediaRepository,
            messagingRepository
        )
    }
    
    @Test
    fun `processCommand should recognize navigation commands`() = testDispatcher.runBlockingTest {
        // Given
        val text = "navigate to San Francisco"
        
        // When
        val command = commandProcessor.processCommand(text)
        
        // Then
        assert(command.type == VoiceCommandType.NAVIGATION)
        assert(command.parameters["destination"] == "San Francisco")
    }
    
    @Test
    fun `processCommand should recognize media play commands`() = testDispatcher.runBlockingTest {
        // Given
        val text = "play Bohemian Rhapsody"
        
        // When
        val command = commandProcessor.processCommand(text)
        
        // Then
        assert(command.type == VoiceCommandType.MEDIA_PLAY)
        assert(command.parameters["song"] == "Bohemian Rhapsody")
    }
    
    @Test
    fun `processCommand should recognize media pause commands`() = testDispatcher.runBlockingTest {
        // Given
        val text = "pause music"
        
        // When
        val command = commandProcessor.processCommand(text)
        
        // Then
        assert(command.type == VoiceCommandType.MEDIA_PAUSE)
    }
    
    @Test
    fun `processCommand should recognize media next commands`() = testDispatcher.runBlockingTest {
        // Given
        val text = "next track"
        
        // When
        val command = commandProcessor.processCommand(text)
        
        // Then
        assert(command.type == VoiceCommandType.MEDIA_NEXT)
    }
    
    @Test
    fun `processCommand should recognize media previous commands`() = testDispatcher.runBlockingTest {
        // Given
        val text = "previous track"
        
        // When
        val command = commandProcessor.processCommand(text)
        
        // Then
        assert(command.type == VoiceCommandType.MEDIA_PREVIOUS)
    }
    
    @Test
    fun `processCommand should recognize messaging send commands`() = testDispatcher.runBlockingTest {
        // Given
        val text = "send message to John saying Hello there"
        
        // When
        val command = commandProcessor.processCommand(text)
        
        // Then
        assert(command.type == VoiceCommandType.MESSAGING_SEND)
        assert(command.parameters["recipient"] == "John")
        assert(command.parameters["message"] == "Hello there")
    }
    
    @Test
    fun `processCommand should recognize messaging read commands`() = testDispatcher.runBlockingTest {
        // Given
        val text = "read messages"
        
        // When
        val command = commandProcessor.processCommand(text)
        
        // Then
        assert(command.type == VoiceCommandType.MESSAGING_READ)
    }
    
    @Test
    fun `processCommand should return unknown for unrecognized commands`() = testDispatcher.runBlockingTest {
        // Given
        val text = "do something completely random"
        
        // When
        val command = commandProcessor.processCommand(text)
        
        // Then
        assert(command.type == VoiceCommandType.UNKNOWN)
    }
}