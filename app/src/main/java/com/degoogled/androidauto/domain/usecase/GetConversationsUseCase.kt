package com.degoogled.androidauto.domain.usecase

import com.degoogled.androidauto.data.model.Conversation
import com.degoogled.androidauto.data.repository.MessagingRepository
import kotlinx.coroutines.flow.Flow

/**
 * Use case for getting conversations.
 */
class GetConversationsUseCase(
    private val messagingRepository: MessagingRepository
) {
    operator fun invoke(): Flow<List<Conversation>> {
        return messagingRepository.getConversations()
    }
}