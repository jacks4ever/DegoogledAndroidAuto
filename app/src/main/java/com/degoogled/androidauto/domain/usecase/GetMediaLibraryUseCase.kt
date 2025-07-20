package com.degoogled.androidauto.domain.usecase

import com.degoogled.androidauto.data.model.Media
import com.degoogled.androidauto.data.repository.MediaRepository
import kotlinx.coroutines.flow.Flow

/**
 * Use case for getting the media library.
 */
class GetMediaLibraryUseCase(
    private val mediaRepository: MediaRepository
) {
    operator fun invoke(): Flow<List<Media>> {
        return mediaRepository.getMediaLibrary()
    }
}