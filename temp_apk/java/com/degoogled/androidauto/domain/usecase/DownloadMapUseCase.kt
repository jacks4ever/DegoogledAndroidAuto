package com.degoogled.androidauto.domain.usecase

import com.degoogled.androidauto.data.repository.MapRepository

/**
 * Use case for downloading map data.
 */
class DownloadMapUseCase(
    private val mapRepository: MapRepository
) {
    suspend operator fun invoke(region: String): Result<Boolean> {
        return try {
            val success = mapRepository.downloadMapForRegion(region)
            if (success) {
                Result.success(true)
            } else {
                Result.failure(Exception("Failed to download map for region: $region"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}