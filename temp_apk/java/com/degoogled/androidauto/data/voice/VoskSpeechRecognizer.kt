package com.degoogled.androidauto.data.voice

import android.content.Context
import android.media.AudioFormat
import android.media.AudioRecord
import android.media.MediaRecorder
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.vosk.LibVosk
import org.vosk.LogLevel
import org.vosk.Model
import org.vosk.Recognizer
import org.vosk.android.StorageService
import java.io.File
import java.io.IOException

/**
 * Speech recognizer implementation using Vosk for offline speech recognition.
 */
class VoskSpeechRecognizer(private val context: Context) {

    private var model: Model? = null
    private var recognizer: Recognizer? = null
    
    private val sampleRate = 16000
    private val bufferSize = 4096
    
    init {
        LibVosk.setLogLevel(LogLevel.INFO)
    }
    
    suspend fun initialize(): Boolean = withContext(Dispatchers.IO) {
        try {
            if (model == null) {
                val modelDir = File(context.filesDir, "vosk-model")
                if (!modelDir.exists()) {
                    // In a real implementation, this would download the model if not present
                    // For now, assume the model is already downloaded
                    return@withContext false
                }
                
                model = Model(modelDir.absolutePath)
                recognizer = Recognizer(model, sampleRate.toFloat())
            }
            true
        } catch (e: IOException) {
            Log.e(TAG, "Failed to initialize Vosk", e)
            false
        }
    }
    
    suspend fun recognize(): String = withContext(Dispatchers.IO) {
        if (model == null || recognizer == null) {
            if (!initialize()) {
                return@withContext ""
            }
        }
        
        var result = ""
        
        val audioFormat = AudioFormat.Builder()
            .setSampleRate(sampleRate)
            .setEncoding(AudioFormat.ENCODING_PCM_16BIT)
            .setChannelMask(AudioFormat.CHANNEL_IN_MONO)
            .build()
        
        val recorder = AudioRecord.Builder()
            .setAudioSource(MediaRecorder.AudioSource.VOICE_RECOGNITION)
            .setAudioFormat(audioFormat)
            .setBufferSizeInBytes(bufferSize * 2)
            .build()
        
        try {
            recorder.startRecording()
            
            val buffer = ShortArray(bufferSize)
            var isListening = true
            var silenceCounter = 0
            
            while (isListening) {
                val nread = recorder.read(buffer, 0, buffer.size)
                
                if (nread > 0) {
                    // Check if this is silence
                    var sum = 0.0
                    for (i in 0 until nread) {
                        sum += buffer[i] * buffer[i]
                    }
                    val rms = Math.sqrt(sum / nread)
                    
                    if (rms < SILENCE_THRESHOLD) {
                        silenceCounter++
                        if (silenceCounter > SILENCE_DURATION) {
                            // Stop listening after silence
                            isListening = false
                        }
                    } else {
                        silenceCounter = 0
                    }
                    
                    if (recognizer?.acceptWaveForm(buffer, nread) == true) {
                        val jsonResult = recognizer?.result ?: "{}"
                        val text = parseResult(jsonResult)
                        if (text.isNotEmpty()) {
                            result = text
                            isListening = false
                        }
                    }
                }
                
                // Stop after MAX_DURATION seconds
                if (silenceCounter > MAX_DURATION) {
                    isListening = false
                }
            }
            
            // Get final result
            if (result.isEmpty()) {
                val jsonResult = recognizer?.finalResult ?: "{}"
                result = parseResult(jsonResult)
            }
            
        } finally {
            recorder.stop()
            recorder.release()
        }
        
        result
    }
    
    private fun parseResult(jsonResult: String): String {
        // In a real implementation, this would parse the JSON result from Vosk
        // For now, just extract the text from a simple JSON structure
        val textRegex = "\"text\"\\s*:\\s*\"([^\"]+)\"".toRegex()
        val match = textRegex.find(jsonResult)
        return match?.groupValues?.getOrNull(1) ?: ""
    }
    
    fun release() {
        recognizer?.close()
        model?.close()
        recognizer = null
        model = null
    }
    
    companion object {
        private const val TAG = "VoskSpeechRecognizer"
        private const val SILENCE_THRESHOLD = 200.0 // Threshold for silence detection
        private const val SILENCE_DURATION = 30 // Number of silent frames to stop recording
        private const val MAX_DURATION = 300 // Maximum recording duration in frames
    }
}