package com.degoogled.androidauto.domain.usecase

import com.degoogled.androidauto.data.model.Location
import com.degoogled.androidauto.data.model.Media
import com.degoogled.androidauto.data.model.MediaType
import com.degoogled.androidauto.data.model.VoiceCommand
import com.degoogled.androidauto.data.model.VoiceCommandType
import com.degoogled.androidauto.data.repository.MapRepository
import com.degoogled.androidauto.data.repository.MediaRepository
import com.degoogled.androidauto.data.repository.MessagingRepository
import com.degoogled.androidauto.data.voice.CommandProcessor
import kotlinx.coroutines.flow.first

/**
 * Use case for processing voice commands.
 */
class ProcessVoiceCommandUseCase(
    private val commandProcessor: CommandProcessor,
    private val mapRepository: MapRepository,
    private val mediaRepository: MediaRepository,
    private val messagingRepository: MessagingRepository
) {
    suspend operator fun invoke(text: String): Result<VoiceCommand> {
        return try {
            val command = commandProcessor.processCommand(text)
            
            // Execute the command based on its type
            when (command.type) {
                VoiceCommandType.NAVIGATION -> {
                    val destination = command.parameters["destination"] ?: ""
                    if (destination.isNotEmpty()) {
                        val locations = mapRepository.searchLocation(destination)
                        if (locations.isNotEmpty()) {
                            // In a real app, this would start navigation to the location
                            val location = locations.first()
                            mapRepository.calculateRoute(
                                mapRepository.getCurrentLocation().first(),
                                location
                            )
                        }
                    }
                }
                VoiceCommandType.MEDIA_PLAY -> {
                    val song = command.parameters["song"] ?: ""
                    if (song.isNotEmpty()) {
                        val mediaList = mediaRepository.getAllMedia().first()
                        val media = mediaList.find { 
                            it.title.contains(song, ignoreCase = true) || 
                            it.artist.contains(song, ignoreCase = true) 
                        }
                        if (media != null) {
                            mediaRepository.playMedia(media)
                        } else {
                            // Create a dummy media item for demonstration
                            val dummyMedia = Media(
                                id = "dummy",
                                title = song,
                                artist = "Unknown",
                                album = "Unknown",
                                uri = "dummy://media",
                                type = MediaType.AUDIO,
                                duration = 0,
                                artworkUri = null
                            )
                            mediaRepository.playMedia(dummyMedia)
                        }
                    }
                }
                VoiceCommandType.MEDIA_PAUSE -> {
                    mediaRepository.pauseMedia()
                }
                VoiceCommandType.MEDIA_NEXT -> {
                    mediaRepository.nextTrack()
                }
                VoiceCommandType.MEDIA_PREVIOUS -> {
                    mediaRepository.previousTrack()
                }
                VoiceCommandType.MESSAGING_SEND -> {
                    val recipient = command.parameters["recipient"] ?: ""
                    val message = command.parameters["message"] ?: ""
                    if (recipient.isNotEmpty() && message.isNotEmpty()) {
                        val conversations = messagingRepository.getConversations().first()
                        val conversation = conversations.find { 
                            it.name.contains(recipient, ignoreCase = true) ||
                            it.participants.any { participant -> 
                                participant.name.contains(recipient, ignoreCase = true) 
                            }
                        }
                        if (conversation != null) {
                            messagingRepository.sendMessage(conversation.id, message)
                        }
                    }
                }
                VoiceCommandType.MESSAGING_READ -> {
                    // Get unread conversations
                    val conversations = messagingRepository.getConversations().first()
                    val unreadConversations = conversations.filter { it.unreadCount > 0 }
                    
                    if (unreadConversations.isNotEmpty()) {
                        // Mark messages as read
                        unreadConversations.forEach { conversation ->
                            val messages = messagingRepository.getMessages().first()[conversation.id] ?: emptyList()
                            messages.filter { !it.isRead }.forEach { message ->
                                messagingRepository.markAsRead(message.id)
                            }
                        }
                    }
                }
                VoiceCommandType.SYSTEM_TIME -> {
                    // System time is handled in the CommandProcessor
                }
                VoiceCommandType.UNKNOWN -> {
                    // Unknown commands are handled with a generic response
                }
            }
            
            Result.success(command)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}