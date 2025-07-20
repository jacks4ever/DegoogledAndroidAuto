package com.degoogled.androidauto.domain.usecase

import com.degoogled.androidauto.data.model.Media
import com.degoogled.androidauto.data.repository.MediaRepository

/**
 * Use case for playing media.
 */
class PlayMediaUseCase(
    private val mediaRepository: MediaRepository
) {
    suspend operator fun invoke(media: Media): Result<Unit> {
        return try {
            mediaRepository.playMedia(media)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}