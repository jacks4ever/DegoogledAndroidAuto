package com.degoogled.androidauto.service

import android.content.Context
import android.speech.tts.TextToSpeech
import com.degoogled.androidauto.data.model.VoiceCommand
import com.degoogled.androidauto.data.model.VoiceCommandType
import com.degoogled.androidauto.domain.usecase.ProcessVoiceCommandUseCase
import com.degoogled.androidauto.domain.usecase.RecognizeSpeechUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import org.powermock.api.mockito.PowerMockito
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner
import org.junit.runner.RunWith
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.test.AutoCloseKoinTest
import org.koin.test.inject

@ExperimentalCoroutinesApi
@RunWith(PowerMockRunner::class)
@PrepareForTest(TextToSpeech::class, VoiceAssistantService::class)
class VoiceAssistantServiceTest : AutoCloseKoinTest() {

    private val testDispatcher = TestCoroutineDispatcher()
    
    private lateinit var service: VoiceAssistantService
    private lateinit var recognizeSpeechUseCase: RecognizeSpeechUseCase
    private lateinit var processVoiceCommandUseCase: ProcessVoiceCommandUseCase
    private lateinit var textToSpeech: TextToSpeech
    private lateinit var context: Context
    
    @Before
    fun setup() {
        context = mock()
        recognizeSpeechUseCase = mock()
        processVoiceCommandUseCase = mock()
        textToSpeech = mock()
        
        PowerMockito.whenNew(TextToSpeech::class.java).withAnyArguments().thenReturn(textToSpeech)
        
        // Set up Koin for dependency injection
        startKoin {
            modules(
                module {
                    single { recognizeSpeechUseCase }
                    single { processVoiceCommandUseCase }
                }
            )
        }
        
        service = VoiceAssistantService()
    }
    
    @Test
    fun `startListening should call recognizeSpeechUseCase`() = testDispatcher.runBlockingTest {
        // Given
        val recognizedText = "navigate to San Francisco"
        whenever(recognizeSpeechUseCase()).thenReturn(Result.success(recognizedText))
        
        val command = VoiceCommand(
            type = VoiceCommandType.NAVIGATION,
            text = recognizedText,
            parameters = mapOf("destination" to "San Francisco"),
            response = "Navigating to San Francisco"
        )
        whenever(processVoiceCommandUseCase(recognizedText)).thenReturn(Result.success(command))
        
        // When
        service.startListening()
        
        // Then
        verify(recognizeSpeechUseCase).invoke()
        verify(processVoiceCommandUseCase).invoke(recognizedText)
    }
    
    @Test
    fun `speak should use TextToSpeech`() {
        // Given
        val text = "Hello, how can I help you?"
        whenever(textToSpeech.speak(any(), any(), any(), any())).thenReturn(TextToSpeech.SUCCESS)
        
        // Set TTS as ready
        val ttsListener = PowerMockito.mockStatic(TextToSpeech.OnInitListener::class.java)
        ttsListener.`when`<Any> { TextToSpeech.OnInitListener::onInit }.thenReturn(TextToSpeech.SUCCESS)
        
        // When
        service.speak(text)
        
        // Then
        verify(textToSpeech).speak(any(), any(), any(), any())
    }
    
    @Test
    fun `stopListening should update state`() {
        // Given
        service.startListening() // Set state to LISTENING
        
        // When
        service.stopListening()
        
        // Then
        assert(service.state.value == VoiceAssistantService.VoiceAssistantState.IDLE)
    }
}