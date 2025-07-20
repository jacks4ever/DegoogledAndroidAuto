package com.degoogled.minimalandroidauto.messaging

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * Service for handling messaging using Matrix protocol
 */
class MessagingService : Service() {

    companion object {
        private const val TAG = "MessagingService"
    }

    private val serviceScope = CoroutineScope(Dispatchers.IO + Job())
    private var isInitialized = false

    override fun onCreate() {
        super.onCreate()
        initializeMatrix()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.let {
            when (it.action) {
                ACTION_SEND_MESSAGE -> {
                    val recipient = it.getStringExtra(EXTRA_RECIPIENT)
                    val message = it.getStringExtra(EXTRA_MESSAGE)
                    if (recipient != null && message != null) {
                        sendMessage(recipient, message)
                    }
                }
                ACTION_RECEIVE_MESSAGES -> {
                    receiveMessages()
                }
            }
        }
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun initializeMatrix() {
        serviceScope.launch {
            try {
                // This would normally initialize the Matrix SDK
                // For now, just log that initialization is complete
                isInitialized = true
                Log.d(TAG, "Matrix initialized")
            } catch (e: Exception) {
                Log.e(TAG, "Error initializing Matrix", e)
            }
        }
    }

    private fun sendMessage(recipient: String, message: String) {
        if (!isInitialized) {
            Log.e(TAG, "Matrix not initialized")
            return
        }

        serviceScope.launch {
            try {
                // This would normally send a message using the Matrix SDK
                Log.d(TAG, "Sending message to $recipient: $message")
                
                // Broadcast that message was sent
                val intent = Intent(ACTION_MESSAGE_SENT)
                intent.putExtra(EXTRA_RECIPIENT, recipient)
                sendBroadcast(intent)
            } catch (e: Exception) {
                Log.e(TAG, "Error sending message", e)
                
                // Broadcast error
                val intent = Intent(ACTION_MESSAGE_ERROR)
                intent.putExtra(EXTRA_ERROR, e.message)
                sendBroadcast(intent)
            }
        }
    }

    private fun receiveMessages() {
        if (!isInitialized) {
            Log.e(TAG, "Matrix not initialized")
            return
        }

        serviceScope.launch {
            try {
                // This would normally receive messages using the Matrix SDK
                Log.d(TAG, "Receiving messages")
                
                // For demonstration, simulate receiving a message
                val sender = "example@matrix.org"
                val message = "This is a test message"
                
                // Broadcast received message
                val intent = Intent(ACTION_MESSAGE_RECEIVED)
                intent.putExtra(EXTRA_SENDER, sender)
                intent.putExtra(EXTRA_MESSAGE, message)
                sendBroadcast(intent)
            } catch (e: Exception) {
                Log.e(TAG, "Error receiving messages", e)
                
                // Broadcast error
                val intent = Intent(ACTION_MESSAGE_ERROR)
                intent.putExtra(EXTRA_ERROR, e.message)
                sendBroadcast(intent)
            }
        }
    }

    companion object {
        const val ACTION_SEND_MESSAGE = "com.degoogled.minimalandroidauto.action.SEND_MESSAGE"
        const val ACTION_RECEIVE_MESSAGES = "com.degoogled.minimalandroidauto.action.RECEIVE_MESSAGES"
        const val ACTION_MESSAGE_SENT = "com.degoogled.minimalandroidauto.action.MESSAGE_SENT"
        const val ACTION_MESSAGE_RECEIVED = "com.degoogled.minimalandroidauto.action.MESSAGE_RECEIVED"
        const val ACTION_MESSAGE_ERROR = "com.degoogled.minimalandroidauto.action.MESSAGE_ERROR"
        const val EXTRA_RECIPIENT = "com.degoogled.minimalandroidauto.extra.RECIPIENT"
        const val EXTRA_SENDER = "com.degoogled.minimalandroidauto.extra.SENDER"
        const val EXTRA_MESSAGE = "com.degoogled.minimalandroidauto.extra.MESSAGE"
        const val EXTRA_ERROR = "com.degoogled.minimalandroidauto.extra.ERROR"
    }
}