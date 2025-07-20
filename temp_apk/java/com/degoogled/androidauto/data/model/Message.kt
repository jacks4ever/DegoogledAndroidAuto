package com.degoogled.androidauto.data.model

import java.util.Date

/**
 * Data class representing a message.
 */
data class Message(
    val id: String,
    val senderId: String,
    val recipientId: String,
    val content: String,
    val timestamp: Date,
    val isRead: Boolean = false,
    val attachments: List<String> = emptyList()
)

/**
 * Data class representing a conversation.
 */
data class Conversation(
    val id: String,
    val participants: List<Contact>,
    val lastMessage: Message?,
    val unreadCount: Int = 0
)

/**
 * Data class representing a contact.
 */
data class Contact(
    val id: String,
    val name: String,
    val avatarUri: String = ""
)