package com.degoogled.androidauto.data.repository

import android.content.Context
import com.degoogled.androidauto.data.model.Contact
import com.degoogled.androidauto.data.model.Conversation
import com.degoogled.androidauto.data.model.Message
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import java.util.Date
import java.util.UUID

/**
 * Repository interface for messaging-related operations.
 */
interface MessagingRepository {
    fun getConversations(): Flow<List<Conversation>>
    fun getMessages(conversationId: String): Flow<List<Message>>
    fun getContacts(): Flow<List<Contact>>
    suspend fun sendMessage(recipientId: String, content: String): Result<Message>
    suspend fun markAsRead(messageId: String): Boolean
}

/**
 * Implementation of the messaging repository using Matrix protocol.
 */
class MessagingRepositoryImpl(
    private val context: Context
) : MessagingRepository {
    
    private val conversationsFlow = MutableStateFlow<List<Conversation>>(emptyList())
    private val messagesMap = mutableMapOf<String, MutableStateFlow<List<Message>>>()
    private val contactsFlow = MutableStateFlow<List<Contact>>(emptyList())
    
    init {
        // In a real implementation, this would connect to a Matrix server
        // For now, add some dummy data
        val dummyContacts = listOf(
            Contact(
                id = "1",
                name = "John Doe"
            ),
            Contact(
                id = "2",
                name = "Jane Smith"
            ),
            Contact(
                id = "3",
                name = "Bob Johnson"
            )
        )
        
        val dummyMessages1 = listOf(
            Message(
                id = "1",
                senderId = "1",
                recipientId = "user",
                content = "Hello there!",
                timestamp = Date(System.currentTimeMillis() - 3600000),
                isRead = true
            ),
            Message(
                id = "2",
                senderId = "user",
                recipientId = "1",
                content = "Hi John, how are you?",
                timestamp = Date(System.currentTimeMillis() - 3500000),
                isRead = true
            ),
            Message(
                id = "3",
                senderId = "1",
                recipientId = "user",
                content = "I'm good, thanks for asking!",
                timestamp = Date(System.currentTimeMillis() - 3400000),
                isRead = false
            )
        )
        
        val dummyMessages2 = listOf(
            Message(
                id = "4",
                senderId = "2",
                recipientId = "user",
                content = "Are we still meeting tomorrow?",
                timestamp = Date(System.currentTimeMillis() - 7200000),
                isRead = true
            ),
            Message(
                id = "5",
                senderId = "user",
                recipientId = "2",
                content = "Yes, at 2 PM",
                timestamp = Date(System.currentTimeMillis() - 7100000),
                isRead = true
            )
        )
        
        val dummyConversations = listOf(
            Conversation(
                id = "1",
                participants = listOf(dummyContacts[0]),
                lastMessage = dummyMessages1.last(),
                unreadCount = 1
            ),
            Conversation(
                id = "2",
                participants = listOf(dummyContacts[1]),
                lastMessage = dummyMessages2.last(),
                unreadCount = 0
            )
        )
        
        contactsFlow.value = dummyContacts
        conversationsFlow.value = dummyConversations
        
        messagesMap["1"] = MutableStateFlow(dummyMessages1)
        messagesMap["2"] = MutableStateFlow(dummyMessages2)
    }
    
    override fun getConversations(): Flow<List<Conversation>> = conversationsFlow
    
    override fun getMessages(conversationId: String): Flow<List<Message>> {
        return messagesMap.getOrPut(conversationId) { MutableStateFlow(emptyList()) }
    }
    
    override fun getContacts(): Flow<List<Contact>> = contactsFlow
    
    override suspend fun sendMessage(recipientId: String, content: String): Result<Message> {
        // In a real implementation, this would send a message via Matrix protocol
        val message = Message(
            id = UUID.randomUUID().toString(),
            senderId = "user",
            recipientId = recipientId,
            content = content,
            timestamp = Date(),
            isRead = true
        )
        
        // Find the conversation or create a new one
        val conversations = conversationsFlow.value.toMutableList()
        val conversationIndex = conversations.indexOfFirst { it.participants.any { p -> p.id == recipientId } }
        
        if (conversationIndex >= 0) {
            // Update existing conversation
            val conversation = conversations[conversationIndex]
            val messages = messagesMap[conversation.id]?.value?.toMutableList() ?: mutableListOf()
            messages.add(message)
            messagesMap[conversation.id]?.value = messages
            
            conversations[conversationIndex] = conversation.copy(lastMessage = message)
            conversationsFlow.value = conversations
        } else {
            // Create new conversation
            val contact = contactsFlow.value.find { it.id == recipientId }
                ?: return Result.failure(IllegalArgumentException("Contact not found"))
            
            val conversationId = UUID.randomUUID().toString()
            val newConversation = Conversation(
                id = conversationId,
                participants = listOf(contact),
                lastMessage = message,
                unreadCount = 0
            )
            
            conversations.add(newConversation)
            conversationsFlow.value = conversations
            
            messagesMap[conversationId] = MutableStateFlow(listOf(message))
        }
        
        return Result.success(message)
    }
    
    override suspend fun markAsRead(messageId: String): Boolean {
        // In a real implementation, this would mark a message as read via Matrix protocol
        var success = false
        
        conversationsFlow.value.forEach { conversation ->
            val messages = messagesMap[conversation.id]?.value ?: return@forEach
            val messageIndex = messages.indexOfFirst { it.id == messageId }
            
            if (messageIndex >= 0) {
                val message = messages[messageIndex]
                if (!message.isRead) {
                    val updatedMessages = messages.toMutableList()
                    updatedMessages[messageIndex] = message.copy(isRead = true)
                    messagesMap[conversation.id]?.value = updatedMessages
                    
                    // Update unread count in conversation
                    val conversations = conversationsFlow.value.toMutableList()
                    val conversationIndex = conversations.indexOfFirst { it.id == conversation.id }
                    if (conversationIndex >= 0) {
                        val updatedConversation = conversations[conversationIndex].copy(
                            unreadCount = (conversations[conversationIndex].unreadCount - 1).coerceAtLeast(0)
                        )
                        conversations[conversationIndex] = updatedConversation
                        conversationsFlow.value = conversations
                    }
                    
                    success = true
                }
            }
        }
        
        return success
    }
}