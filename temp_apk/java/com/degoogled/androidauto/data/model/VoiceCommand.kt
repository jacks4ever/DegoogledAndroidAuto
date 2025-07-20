package com.degoogled.androidauto.data.model

/**
 * Represents a voice command processed by the voice assistant.
 */
data class VoiceCommand(
    val type: VoiceCommandType,
    val text: String,
    val parameters: Map<String, String> = emptyMap(),
    val response: String = ""
)

/**
 * Types of voice commands supported by the application.
 */
enum class VoiceCommandType {
    NAVIGATION,
    MEDIA_PLAY,
    MEDIA_PAUSE,
    MEDIA_NEXT,
    MEDIA_PREVIOUS,
    MESSAGING_SEND,
    MESSAGING_READ,
    SYSTEM_TIME,
    UNKNOWN
}