package com.degoogled.androidauto.data.voice

import android.content.Context
import com.degoogled.androidauto.data.model.VoiceCommand
import com.degoogled.androidauto.data.model.VoiceCommandType
import com.degoogled.androidauto.data.repository.MediaRepository
import com.degoogled.androidauto.data.repository.MessagingRepository
import com.degoogled.androidauto.data.repository.NavigationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Processes voice commands and determines the appropriate action.
 */
class CommandProcessor(
    private val context: Context,
    private val navigationRepository: NavigationRepository,
    private val mediaRepository: MediaRepository,
    private val messagingRepository: MessagingRepository
) {

    suspend fun processCommand(text: String): VoiceCommand = withContext(Dispatchers.Default) {
        val lowerText = text.lowercase()
        
        // Navigation commands
        if (lowerText.contains("navigate") || lowerText.contains("go to") || lowerText.contains("directions")) {
            val destination = extractDestination(lowerText)
            if (destination.isNotEmpty()) {
                return@withContext VoiceCommand(
                    type = VoiceCommandType.NAVIGATION,
                    text = text,
                    parameters = mapOf("destination" to destination),
                    response = "Navigating to $destination"
                )
            }
        }
        
        // Media commands
        if (lowerText.contains("play") || lowerText.contains("music") || lowerText.contains("song")) {
            val song = extractSong(lowerText)
            if (song.isNotEmpty()) {
                return@withContext VoiceCommand(
                    type = VoiceCommandType.MEDIA_PLAY,
                    text = text,
                    parameters = mapOf("song" to song),
                    response = "Playing $song"
                )
            }
        }
        
        if (lowerText.contains("pause") || lowerText.contains("stop music") || lowerText.contains("stop playing")) {
            return@withContext VoiceCommand(
                type = VoiceCommandType.MEDIA_PAUSE,
                text = text,
                parameters = emptyMap(),
                response = "Pausing music"
            )
        }
        
        if (lowerText.contains("next") || lowerText.contains("skip")) {
            return@withContext VoiceCommand(
                type = VoiceCommandType.MEDIA_NEXT,
                text = text,
                parameters = emptyMap(),
                response = "Playing next track"
            )
        }
        
        if (lowerText.contains("previous") || lowerText.contains("back")) {
            return@withContext VoiceCommand(
                type = VoiceCommandType.MEDIA_PREVIOUS,
                text = text,
                parameters = emptyMap(),
                response = "Playing previous track"
            )
        }
        
        // Messaging commands
        if (lowerText.contains("send message") || lowerText.contains("text") || lowerText.contains("send a message")) {
            val recipient = extractRecipient(lowerText)
            val message = extractMessage(lowerText)
            
            if (recipient.isNotEmpty() && message.isNotEmpty()) {
                return@withContext VoiceCommand(
                    type = VoiceCommandType.MESSAGING_SEND,
                    text = text,
                    parameters = mapOf(
                        "recipient" to recipient,
                        "message" to message
                    ),
                    response = "Sending message to $recipient: $message"
                )
            }
        }
        
        if (lowerText.contains("read messages") || lowerText.contains("check messages")) {
            return@withContext VoiceCommand(
                type = VoiceCommandType.MESSAGING_READ,
                text = text,
                parameters = emptyMap(),
                response = "Reading your messages"
            )
        }
        
        // System commands
        if (lowerText.contains("what time") || lowerText.contains("current time")) {
            val time = getCurrentTime()
            return@withContext VoiceCommand(
                type = VoiceCommandType.SYSTEM_TIME,
                text = text,
                parameters = emptyMap(),
                response = "The current time is $time"
            )
        }
        
        // Unknown command
        VoiceCommand(
            type = VoiceCommandType.UNKNOWN,
            text = text,
            parameters = emptyMap(),
            response = "I'm sorry, I didn't understand that command"
        )
    }
    
    private fun extractDestination(text: String): String {
        // In a real implementation, this would use NLP to extract the destination
        // For now, use a simple regex
        val patterns = listOf(
            "navigate to (.+)".toRegex(),
            "go to (.+)".toRegex(),
            "directions to (.+)".toRegex()
        )
        
        for (pattern in patterns) {
            val match = pattern.find(text)
            if (match != null) {
                return match.groupValues[1].trim()
            }
        }
        
        return ""
    }
    
    private fun extractSong(text: String): String {
        // In a real implementation, this would use NLP to extract the song
        // For now, use a simple regex
        val patterns = listOf(
            "play (.+)".toRegex(),
            "play song (.+)".toRegex(),
            "play music (.+)".toRegex()
        )
        
        for (pattern in patterns) {
            val match = pattern.find(text)
            if (match != null) {
                return match.groupValues[1].trim()
            }
        }
        
        return ""
    }
    
    private fun extractRecipient(text: String): String {
        // In a real implementation, this would use NLP to extract the recipient
        // For now, use a simple regex
        val patterns = listOf(
            "send message to (.+?) .*".toRegex(),
            "text (.+?) .*".toRegex(),
            "send a message to (.+?) .*".toRegex()
        )
        
        for (pattern in patterns) {
            val match = pattern.find(text)
            if (match != null) {
                return match.groupValues[1].trim()
            }
        }
        
        return ""
    }
    
    private fun extractMessage(text: String): String {
        // In a real implementation, this would use NLP to extract the message
        // For now, use a simple regex
        val patterns = listOf(
            "send message to .+? saying (.+)".toRegex(),
            "text .+? saying (.+)".toRegex(),
            "send a message to .+? saying (.+)".toRegex()
        )
        
        for (pattern in patterns) {
            val match = pattern.find(text)
            if (match != null) {
                return match.groupValues[1].trim()
            }
        }
        
        return ""
    }
    
    private fun getCurrentTime(): String {
        // In a real implementation, this would get the current time
        // For now, return a placeholder
        return "12:00 PM"
    }
}