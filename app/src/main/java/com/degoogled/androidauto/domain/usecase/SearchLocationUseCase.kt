package com.degoogled.androidauto.domain.usecase

import com.degoogled.androidauto.data.model.Location
import com.degoogled.androidauto.data.repository.MapRepository

/**
 * Use case for searching locations.
 */
class SearchLocationUseCase(
    private val mapRepository: MapRepository
) {
    suspend operator fun invoke(query: String): Result<List<Location>> {
        return try {
            val locations = mapRepository.searchLocation(query)
            Result.success(locations)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}