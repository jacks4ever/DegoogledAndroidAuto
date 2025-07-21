package com.degoogled.androidauto.domain.usecase

import com.degoogled.androidauto.data.model.Message
import com.degoogled.androidauto.data.repository.MessagingRepository

/**
 * Use case for sending messages.
 */
class SendMessageUseCase(
    private val messagingRepository: MessagingRepository
) {
    suspend operator fun invoke(recipientId: String, content: String): Result<Message> {
        return messagingRepository.sendMessage(recipientId, content)
    }
}