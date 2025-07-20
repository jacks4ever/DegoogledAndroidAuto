package com.degoogled.androidauto.data.model

/**
 * Data class representing a media item.
 */
data class Media(
    val id: String,
    val title: String,
    val artist: String = "",
    val album: String = "",
    val duration: Long = 0, // in milliseconds
    val uri: String,
    val artworkUri: String = "",
    val type: MediaType
)

/**
 * Enum representing the type of media.
 */
enum class MediaType {
    AUDIO,
    VIDEO,
    PLAYLIST
}

/**
 * Data class representing a media playlist.
 */
data class Playlist(
    val id: String,
    val name: String,
    val items: List<Media> = emptyList()
)