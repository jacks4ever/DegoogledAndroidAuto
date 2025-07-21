package com.degoogled.androidauto.ui.messaging

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.degoogled.androidauto.data.model.Message
import com.degoogled.androidauto.data.repository.MessagingRepository
import com.degoogled.androidauto.domain.usecase.SendMessageUseCase
import kotlinx.coroutines.launch

/**
 * ViewModel for the conversation screen.
 */
class ConversationViewModel(
    private val conversationId: String,
    private val messagingRepository: MessagingRepository,
    private val sendMessageUseCase: SendMessageUseCase
) : ViewModel() {

    val messages: LiveData<List<Message>> = messagingRepository.getMessages(conversationId).asLiveData()
    
    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage
    
    init {
        // Mark all messages as read when opening the conversation
        viewModelScope.launch {
            messages.value?.forEach { message ->
                if (!message.isRead) {
                    messagingRepository.markAsRead(message.id)
                }
            }
        }
    }
    
    fun sendMessage(content: String) {
        viewModelScope.launch {
            // Get the recipient ID from the conversation
            val conversation = messagingRepository.getConversations().value.find { it.id == conversationId }
            val recipientId = conversation?.participants?.firstOrNull()?.id
            
            if (recipientId != null) {
                sendMessageUseCase(recipientId, content)
                    .onFailure { error ->
                        _errorMessage.value = "Failed to send message: ${error.message}"
                    }
            } else {
                _errorMessage.value = "Recipient not found"
            }
        }
    }
}