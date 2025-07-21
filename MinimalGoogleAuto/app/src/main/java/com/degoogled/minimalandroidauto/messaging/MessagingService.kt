package com.degoogled.minimalandroidauto.messaging

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

/**
 * Service for handling messaging using Matrix protocol
 */
class MessagingService : Service() {

    companion object {
        private const val TAG = "MessagingService"
        
        // Actions
        const val ACTION_SEND_MESSAGE = "com.degoogled.minimalandroidauto.action.SEND_MESSAGE"
        const val ACTION_RECEIVE_MESSAGES = "com.degoogled.minimalandroidauto.action.RECEIVE_MESSAGES"
        const val ACTION_MESSAGE_SENT = "com.degoogled.minimalandroidauto.action.MESSAGE_SENT"
        const val ACTION_MESSAGE_RECEIVED = "com.degoogled.minimalandroidauto.action.MESSAGE_RECEIVED"
        const val ACTION_MESSAGE_ERROR = "com.degoogled.minimalandroidauto.action.MESSAGE_ERROR"
        
        // WhatsApp actions
        const val ACTION_LAUNCH_WHATSAPP = "com.degoogled.minimalandroidauto.action.LAUNCH_WHATSAPP"
        const val ACTION_WHATSAPP_CHAT = "com.degoogled.minimalandroidauto.action.WHATSAPP_CHAT"
        
        // Teams actions
        const val ACTION_LAUNCH_TEAMS = "com.degoogled.minimalandroidauto.action.LAUNCH_TEAMS"
        const val ACTION_TEAMS_CHAT = "com.degoogled.minimalandroidauto.action.TEAMS_CHAT"
        
        // Handcent actions
        const val ACTION_LAUNCH_HANDCENT = "com.degoogled.minimalandroidauto.action.LAUNCH_HANDCENT"
        const val ACTION_HANDCENT_COMPOSE = "com.degoogled.minimalandroidauto.action.HANDCENT_COMPOSE"
        
        // Common extras
        const val EXTRA_RECIPIENT = "com.degoogled.minimalandroidauto.extra.RECIPIENT"
        const val EXTRA_SENDER = "com.degoogled.minimalandroidauto.extra.SENDER"
        const val EXTRA_MESSAGE = "com.degoogled.minimalandroidauto.extra.MESSAGE"
        const val EXTRA_ERROR = "com.degoogled.minimalandroidauto.extra.ERROR"
        const val EXTRA_PLATFORM = "com.degoogled.minimalandroidauto.extra.PLATFORM"
        
        // Platform-specific extras
        const val EXTRA_PHONE_NUMBER = "com.degoogled.minimalandroidauto.extra.PHONE_NUMBER"
        const val EXTRA_USER_ID = "com.degoogled.minimalandroidauto.extra.USER_ID"
        const val EXTRA_ADDRESS = "com.degoogled.minimalandroidauto.extra.ADDRESS"
        
        // Platform identifiers
        const val PLATFORM_MATRIX = "matrix"
        const val PLATFORM_WHATSAPP = "whatsapp"
        const val PLATFORM_TEAMS = "teams"
        const val PLATFORM_HANDCENT = "handcent"
    }

    private val serviceScope = CoroutineScope(Dispatchers.IO + Job())
    private var isInitialized = false
    
    // Messaging integrations
    private val whatsAppIntegration: WhatsAppIntegration by inject()
    private val teamsIntegration: TeamsIntegration by inject()
    private val handcentIntegration: HandcentIntegration by inject()

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
                    val platform = it.getStringExtra(EXTRA_PLATFORM) ?: PLATFORM_MATRIX
                    if (recipient != null && message != null) {
                        sendMessage(recipient, message, platform)
                    }
                }
                ACTION_RECEIVE_MESSAGES -> {
                    receiveMessages()
                }
                ACTION_LAUNCH_WHATSAPP -> {
                    serviceScope.launch {
                        whatsAppIntegration.launchWhatsApp()
                    }
                }
                ACTION_LAUNCH_TEAMS -> {
                    serviceScope.launch {
                        teamsIntegration.launchTeams()
                    }
                }
                ACTION_LAUNCH_HANDCENT -> {
                    serviceScope.launch {
                        handcentIntegration.launchHandcent()
                    }
                }
                ACTION_WHATSAPP_CHAT -> {
                    val phoneNumber = it.getStringExtra(EXTRA_PHONE_NUMBER)
                    val chatMessage = it.getStringExtra(EXTRA_MESSAGE)
                    if (phoneNumber != null) {
                        serviceScope.launch {
                            whatsAppIntegration.openChat(phoneNumber, chatMessage)
                        }
                    }
                }
                ACTION_TEAMS_CHAT -> {
                    val userId = it.getStringExtra(EXTRA_USER_ID)
                    if (userId != null) {
                        serviceScope.launch {
                            teamsIntegration.openChat(userId)
                        }
                    }
                }
                ACTION_HANDCENT_COMPOSE -> {
                    val address = it.getStringExtra(EXTRA_ADDRESS)
                    val smsMessage = it.getStringExtra(EXTRA_MESSAGE)
                    if (address != null) {
                        serviceScope.launch {
                            handcentIntegration.composeMessage(address, smsMessage)
                        }
                    }
                }
                else -> {
                    Log.w(TAG, "Unknown action: ${it.action}")
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

    private fun sendMessage(recipient: String, message: String, platform: String = PLATFORM_MATRIX) {
        if (platform == PLATFORM_MATRIX && !isInitialized) {
            Log.e(TAG, "Matrix not initialized")
            return
        }

        serviceScope.launch {
            try {
                when (platform) {
                    PLATFORM_MATRIX -> {
                        // This would normally send a message using the Matrix SDK
                        Log.d(TAG, "Sending Matrix message to $recipient: $message")
                    }
                    PLATFORM_WHATSAPP -> {
                        // Send message via WhatsApp
                        Log.d(TAG, "Sending WhatsApp message to $recipient: $message")
                        whatsAppIntegration.openChat(recipient, message)
                    }
                    PLATFORM_TEAMS -> {
                        // Send message via Teams
                        Log.d(TAG, "Sending Teams message to $recipient: $message")
                        teamsIntegration.openChat(recipient)
                    }
                    PLATFORM_HANDCENT -> {
                        // Send message via Handcent
                        Log.d(TAG, "Sending Handcent message to $recipient: $message")
                        handcentIntegration.composeMessage(recipient, message)
                    }
                    else -> {
                        Log.e(TAG, "Unknown platform: $platform")
                        throw IllegalArgumentException("Unknown platform: $platform")
                    }
                }
                
                // Broadcast that message was sent
                val intent = Intent(ACTION_MESSAGE_SENT)
                intent.putExtra(EXTRA_RECIPIENT, recipient)
                intent.putExtra(EXTRA_PLATFORM, platform)
                sendBroadcast(intent)
            } catch (e: Exception) {
                Log.e(TAG, "Error sending message on platform $platform", e)
                
                // Broadcast error
                val intent = Intent(ACTION_MESSAGE_ERROR)
                intent.putExtra(EXTRA_ERROR, e.message)
                intent.putExtra(EXTRA_PLATFORM, platform)
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


}