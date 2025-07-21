package com.degoogled.androidauto.domain.usecase

import com.degoogled.androidauto.data.model.Location
import com.degoogled.androidauto.data.model.Route
import com.degoogled.androidauto.data.repository.NavigationRepository

/**
 * Use case for calculating a navigation route.
 */
class CalculateRouteUseCase(
    private val navigationRepository: NavigationRepository
) {
    suspend operator fun invoke(start: Location, destination: Location): Result<Route> {
        return try {
            val route = navigationRepository.calculateRoute(start, destination)
            if (route != null) {
                Result.success(route)
            } else {
                Result.failure(Exception("Failed to calculate route"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}