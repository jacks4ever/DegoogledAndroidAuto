package com.degoogled.androidauto.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import android.util.Log
import com.degoogled.androidauto.data.model.VoiceCommand
import com.degoogled.androidauto.domain.usecase.ProcessVoiceCommandUseCase
import com.degoogled.androidauto.domain.usecase.RecognizeSpeechUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import java.util.Locale
import java.util.UUID

/**
 * Service for voice assistant functionality.
 * Uses Vosk for offline speech recognition.
 */
class VoiceAssistantService : Service() {

    private val recognizeSpeechUseCase: RecognizeSpeechUseCase by inject()
    private val processVoiceCommandUseCase: ProcessVoiceCommandUseCase by inject()
    
    private val serviceScope = CoroutineScope(Dispatchers.Main + Job())
    
    private val binder = VoiceAssistantBinder()
    
    private var textToSpeech: TextToSpeech? = null
    private var isTtsReady = false
    
    private val _state = MutableStateFlow(VoiceAssistantState.IDLE)
    val state: StateFlow<VoiceAssistantState> = _state
    
    private val _lastCommand = MutableStateFlow<VoiceCommand?>(null)
    val lastCommand: StateFlow<VoiceCommand?> = _lastCommand
    
    override fun onCreate() {
        super.onCreate()
        initTextToSpeech()
    }
    
    private fun initTextToSpeech() {
        textToSpeech = TextToSpeech(applicationContext) { status ->
            isTtsReady = status == TextToSpeech.SUCCESS
            
            if (isTtsReady) {
                textToSpeech?.language = Locale.US
                textToSpeech?.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
                    override fun onStart(utteranceId: String?) {
                        _state.value = VoiceAssistantState.SPEAKING
                    }
                    
                    override fun onDone(utteranceId: String?) {
                        _state.value = VoiceAssistantState.IDLE
                    }
                    
                    @Deprecated("Deprecated in Java")
                    override fun onError(utteranceId: String?) {
                        _state.value = VoiceAssistantState.IDLE
                    }
                })
            } else {
                Log.e(TAG, "Failed to initialize TTS")
            }
        }
    }
    
    fun startListening() {
        if (_state.value == VoiceAssistantState.IDLE) {
            _state.value = VoiceAssistantState.LISTENING
            
            serviceScope.launch {
                try {
                    val result = recognizeSpeechUseCase()
                    result.onSuccess { text ->
                        if (text.isNotEmpty()) {
                            _state.value = VoiceAssistantState.PROCESSING
                            processCommand(text)
                        } else {
                            _state.value = VoiceAssistantState.IDLE
                        }
                    }.onFailure { error ->
                        Log.e(TAG, "Speech recognition failed", error)
                        _state.value = VoiceAssistantState.IDLE
                    }
                } catch (e: Exception) {
                    Log.e(TAG, "Speech recognition failed", e)
                    _state.value = VoiceAssistantState.IDLE
                }
            }
        }
    }
    
    private fun processCommand(text: String) {
        serviceScope.launch {
            try {
                val result = processVoiceCommandUseCase(text)
                result.onSuccess { command ->
                    _lastCommand.value = command
                    speak(command.response)
                }.onFailure { error ->
                    Log.e(TAG, "Command processing failed", error)
                    speak("I'm sorry, I couldn't understand that command.")
                }
            } catch (e: Exception) {
                Log.e(TAG, "Command processing failed", e)
                speak("I'm sorry, I couldn't understand that command.")
            }
        }
    }
    
    fun speak(text: String) {
        if (isTtsReady && text.isNotEmpty()) {
            val utteranceId = UUID.randomUUID().toString()
            textToSpeech?.speak(text, TextToSpeech.QUEUE_FLUSH, null, utteranceId)
        }
    }
    
    fun stopListening() {
        if (_state.value == VoiceAssistantState.LISTENING) {
            // In a real implementation, this would stop the speech recognizer
            _state.value = VoiceAssistantState.IDLE
        }
    }
    
    override fun onBind(intent: Intent?): IBinder {
        return binder
    }
    
    override fun onDestroy() {
        textToSpeech?.stop()
        textToSpeech?.shutdown()
        super.onDestroy()
    }
    
    inner class VoiceAssistantBinder : Binder() {
        fun getService(): VoiceAssistantService = this@VoiceAssistantService
    }
    
    enum class VoiceAssistantState {
        IDLE,
        LISTENING,
        PROCESSING,
        SPEAKING
    }
    
    companion object {
        private const val TAG = "VoiceAssistantService"
    }
}