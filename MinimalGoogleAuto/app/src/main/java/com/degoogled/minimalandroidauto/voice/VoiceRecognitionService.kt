package com.degoogled.minimalandroidauto.voice

import android.app.Service
import android.content.Intent
import android.media.AudioFormat
import android.media.AudioRecord
import android.media.MediaRecorder
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.vosk.Model
import org.vosk.Recognizer
import org.vosk.android.RecognitionListener
import org.vosk.android.SpeechService
import java.io.File
import java.io.IOException

/**
 * Service for offline voice recognition using Vosk
 */
class VoiceRecognitionService : Service(), RecognitionListener {

    companion object {
        private const val TAG = "VoiceRecognitionService"
        private const val SAMPLE_RATE = 16000
    }

    private val serviceScope = CoroutineScope(Dispatchers.IO + Job())
    private var speechService: SpeechService? = null
    private var model: Model? = null
    private var isListening = false

    override fun onCreate() {
        super.onCreate()
        initializeVosk()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.let {
            when (it.action) {
                ACTION_START_LISTENING -> startListening()
                ACTION_STOP_LISTENING -> stopListening()
            }
        }
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        stopListening()
        model?.close()
        super.onDestroy()
    }

    private fun initializeVosk() {
        serviceScope.launch {
            try {
                // Check if model exists
                val modelDir = File(getExternalFilesDir(null), "vosk-model")
                if (!modelDir.exists()) {
                    Log.e(TAG, "Vosk model not found. Please download the model first.")
                    return@launch
                }

                // Load the model
                model = Model(modelDir.absolutePath)
                Log.d(TAG, "Vosk model loaded")
            } catch (e: IOException) {
                Log.e(TAG, "Failed to initialize Vosk", e)
            }
        }
    }

    private fun startListening() {
        if (isListening) {
            return
        }

        if (model == null) {
            Log.e(TAG, "Vosk model not loaded")
            return
        }

        isListening = true
        
        try {
            val recognizer = Recognizer(model, SAMPLE_RATE.toFloat())
            val audioSource = MediaRecorder.AudioSource.MIC
            val channelConfig = AudioFormat.CHANNEL_IN_MONO
            val audioFormat = AudioFormat.ENCODING_PCM_16BIT
            
            val bufferSize = AudioRecord.getMinBufferSize(
                SAMPLE_RATE, channelConfig, audioFormat
            )
            
            speechService = SpeechService(recognizer, SAMPLE_RATE.toFloat())
            speechService?.startListening(this)
            
            Log.d(TAG, "Started listening")
            
            // Broadcast that we're listening
            val intent = Intent(ACTION_LISTENING_STARTED)
            sendBroadcast(intent)
        } catch (e: Exception) {
            Log.e(TAG, "Failed to start listening", e)
            isListening = false
        }
    }

    private fun stopListening() {
        if (!isListening) {
            return
        }

        isListening = false
        speechService?.stop()
        speechService = null
        
        Log.d(TAG, "Stopped listening")
        
        // Broadcast that we've stopped listening
        val intent = Intent(ACTION_LISTENING_STOPPED)
        sendBroadcast(intent)
    }

    override fun onPartialResult(hypothesis: String?) {
        hypothesis?.let {
            Log.d(TAG, "Partial result: $it")
            
            // Broadcast partial result
            val intent = Intent(ACTION_PARTIAL_RESULT)
            intent.putExtra(EXTRA_RESULT, it)
            sendBroadcast(intent)
        }
    }

    override fun onResult(hypothesis: String?) {
        hypothesis?.let {
            Log.d(TAG, "Final result: $it")
            
            // Process the command
            processVoiceCommand(it)
            
            // Broadcast final result
            val intent = Intent(ACTION_FINAL_RESULT)
            intent.putExtra(EXTRA_RESULT, it)
            sendBroadcast(intent)
        }
    }

    override fun onError(exception: Exception?) {
        exception?.let {
            Log.e(TAG, "Recognition error", it)
            
            // Broadcast error
            val intent = Intent(ACTION_ERROR)
            intent.putExtra(EXTRA_ERROR, it.message)
            sendBroadcast(intent)
        }
    }

    override fun onTimeout() {
        Log.d(TAG, "Recognition timeout")
        
        // Broadcast timeout
        val intent = Intent(ACTION_TIMEOUT)
        sendBroadcast(intent)
    }

    private fun processVoiceCommand(command: String) {
        // Simple command processing
        when {
            command.contains("navigate") || command.contains("go to") -> {
                // Extract destination
                val destination = extractDestination(command)
                if (destination.isNotEmpty()) {
                    // Start navigation
                    val intent = Intent(this, NavigationCommandHandler::class.java)
                    intent.putExtra(NavigationCommandHandler.EXTRA_DESTINATION, destination)
                    startService(intent)
                }
            }
            command.contains("play") -> {
                // Extract media
                val media = extractMedia(command)
                if (media.isNotEmpty()) {
                    // Start media playback
                    val intent = Intent(this, MediaCommandHandler::class.java)
                    intent.putExtra(MediaCommandHandler.EXTRA_MEDIA, media)
                    startService(intent)
                }
            }
            command.contains("message") || command.contains("text") -> {
                // Extract contact and message
                val contact = extractContact(command)
                val message = extractMessage(command)
                if (contact.isNotEmpty() && message.isNotEmpty()) {
                    // Send message
                    val intent = Intent(this, MessagingCommandHandler::class.java)
                    intent.putExtra(MessagingCommandHandler.EXTRA_CONTACT, contact)
                    intent.putExtra(MessagingCommandHandler.EXTRA_MESSAGE, message)
                    startService(intent)
                }
            }
        }
    }

    private fun extractDestination(command: String): String {
        // Simple extraction logic
        val keywords = listOf("navigate to", "go to", "directions to")
        for (keyword in keywords) {
            if (command.contains(keyword)) {
                return command.substringAfter(keyword).trim()
            }
        }
        return ""
    }

    private fun extractMedia(command: String): String {
        // Simple extraction logic
        val keywords = listOf("play", "listen to")
        for (keyword in keywords) {
            if (command.contains(keyword)) {
                return command.substringAfter(keyword).trim()
            }
        }
        return ""
    }

    private fun extractContact(command: String): String {
        // Simple extraction logic
        val keywords = listOf("message to", "text to", "send message to", "send text to")
        for (keyword in keywords) {
            if (command.contains(keyword)) {
                val afterKeyword = command.substringAfter(keyword).trim()
                if (afterKeyword.contains("saying")) {
                    return afterKeyword.substringBefore("saying").trim()
                }
                return afterKeyword
            }
        }
        return ""
    }

    private fun extractMessage(command: String): String {
        // Simple extraction logic
        if (command.contains("saying")) {
            return command.substringAfter("saying").trim()
        }
        return ""
    }

    companion object {
        const val ACTION_START_LISTENING = "com.degoogled.minimalandroidauto.action.START_LISTENING"
        const val ACTION_STOP_LISTENING = "com.degoogled.minimalandroidauto.action.STOP_LISTENING"
        const val ACTION_LISTENING_STARTED = "com.degoogled.minimalandroidauto.action.LISTENING_STARTED"
        const val ACTION_LISTENING_STOPPED = "com.degoogled.minimalandroidauto.action.LISTENING_STOPPED"
        const val ACTION_PARTIAL_RESULT = "com.degoogled.minimalandroidauto.action.PARTIAL_RESULT"
        const val ACTION_FINAL_RESULT = "com.degoogled.minimalandroidauto.action.FINAL_RESULT"
        const val ACTION_ERROR = "com.degoogled.minimalandroidauto.action.ERROR"
        const val ACTION_TIMEOUT = "com.degoogled.minimalandroidauto.action.TIMEOUT"
        const val EXTRA_RESULT = "com.degoogled.minimalandroidauto.extra.RESULT"
        const val EXTRA_ERROR = "com.degoogled.minimalandroidauto.extra.ERROR"
    }

    /**
     * Handler for navigation commands
     */
    class NavigationCommandHandler : Service() {
        override fun onBind(intent: Intent?): IBinder? = null
        
        override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
            intent?.let {
                val destination = it.getStringExtra(EXTRA_DESTINATION) ?: ""
                if (destination.isNotEmpty()) {
                    // Process navigation command
                    Log.d(TAG, "Navigation command: $destination")
                    
                    // This would normally start navigation to the destination
                    // For now, just log the command
                }
            }
            return START_NOT_STICKY
        }
        
        companion object {
            private const val TAG = "NavigationCommandHandler"
            const val EXTRA_DESTINATION = "com.degoogled.minimalandroidauto.extra.DESTINATION"
        }
    }

    /**
     * Handler for media commands
     */
    class MediaCommandHandler : Service() {
        override fun onBind(intent: Intent?): IBinder? = null
        
        override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
            intent?.let {
                val media = it.getStringExtra(EXTRA_MEDIA) ?: ""
                if (media.isNotEmpty()) {
                    // Process media command
                    Log.d(TAG, "Media command: $media")
                    
                    // This would normally start media playback
                    // For now, just log the command
                }
            }
            return START_NOT_STICKY
        }
        
        companion object {
            private const val TAG = "MediaCommandHandler"
            const val EXTRA_MEDIA = "com.degoogled.minimalandroidauto.extra.MEDIA"
        }
    }

    /**
     * Handler for messaging commands
     */
    class MessagingCommandHandler : Service() {
        override fun onBind(intent: Intent?): IBinder? = null
        
        override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
            intent?.let {
                val contact = it.getStringExtra(EXTRA_CONTACT) ?: ""
                val message = it.getStringExtra(EXTRA_MESSAGE) ?: ""
                if (contact.isNotEmpty() && message.isNotEmpty()) {
                    // Process messaging command
                    Log.d(TAG, "Messaging command: To $contact: $message")
                    
                    // This would normally send a message
                    // For now, just log the command
                }
            }
            return START_NOT_STICKY
        }
        
        companion object {
            private const val TAG = "MessagingCommandHandler"
            const val EXTRA_CONTACT = "com.degoogled.minimalandroidauto.extra.CONTACT"
            const val EXTRA_MESSAGE = "com.degoogled.minimalandroidauto.extra.MESSAGE"
        }
    }
}