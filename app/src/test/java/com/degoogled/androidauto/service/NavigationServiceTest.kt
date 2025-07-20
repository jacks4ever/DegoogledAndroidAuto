package com.degoogled.androidauto.service

import android.content.Context
import android.location.Location
import com.degoogled.androidauto.data.map.GraphHopperRouter
import com.degoogled.androidauto.data.map.MapLibreManager
import com.degoogled.androidauto.data.map.NominatimGeocoder
import com.degoogled.androidauto.data.model.Route
import com.degoogled.androidauto.data.model.RouteStep
import com.degoogled.androidauto.data.repository.MapRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.util.concurrent.TimeUnit

@ExperimentalCoroutinesApi
class NavigationServiceTest {

    private val testDispatcher = TestCoroutineDispatcher()
    
    private lateinit var service: NavigationService
    private lateinit var context: Context
    private lateinit var mapRepository: MapRepository
    private lateinit var mapLibreManager: MapLibreManager
    private lateinit var graphHopperRouter: GraphHopperRouter
    private lateinit var nominatimGeocoder: NominatimGeocoder
    
    private val mockCurrentLocation = mock<Location>().apply {
        whenever(latitude).thenReturn(37.7749)
        whenever(longitude).thenReturn(-122.4194)
    }
    
    private val mockDestination = com.degoogled.androidauto.data.model.Location(
        id = "dest1",
        name = "Golden Gate Bridge",
        latitude = 37.8199,
        longitude = -122.4783,
        address = "Golden Gate Bridge, San Francisco, CA"
    )
    
    private val mockRoute = Route(
        id = "route1",
        name = "Route to Golden Gate Bridge",
        distance = 10000.0, // 10 km
        duration = TimeUnit.MINUTES.toMillis(30), // 30 minutes
        startLocation = com.degoogled.androidauto.data.model.Location(
            id = "start1",
            name = "Current Location",
            latitude = 37.7749,
            longitude = -122.4194,
            address = "San Francisco, CA"
        ),
        endLocation = mockDestination,
        steps = listOf(
            RouteStep(
                id = "step1",
                instruction = "Head north on Market St",
                distance = 1000.0,
                duration = TimeUnit.MINUTES.toMillis(5),
                startLocation = com.degoogled.androidauto.data.model.Location(
                    id = "step1_start",
                    name = "Market St",
                    latitude = 37.7749,
                    longitude = -122.4194,
                    address = "Market St, San Francisco, CA"
                ),
                endLocation = com.degoogled.androidauto.data.model.Location(
                    id = "step1_end",
                    name = "Market St & 2nd St",
                    latitude = 37.7849,
                    longitude = -122.4294,
                    address = "Market St & 2nd St, San Francisco, CA"
                )
            ),
            RouteStep(
                id = "step2",
                instruction = "Turn left onto 2nd St",
                distance = 2000.0,
                duration = TimeUnit.MINUTES.toMillis(10),
                startLocation = com.degoogled.androidauto.data.model.Location(
                    id = "step2_start",
                    name = "Market St & 2nd St",
                    latitude = 37.7849,
                    longitude = -122.4294,
                    address = "Market St & 2nd St, San Francisco, CA"
                ),
                endLocation = com.degoogled.androidauto.data.model.Location(
                    id = "step2_end",
                    name = "2nd St & Folsom St",
                    latitude = 37.7949,
                    longitude = -122.4394,
                    address = "2nd St & Folsom St, San Francisco, CA"
                )
            )
        )
    )
    
    @Before
    fun setup() {
        context = mock()
        mapRepository = mock()
        mapLibreManager = mock()
        graphHopperRouter = mock()
        nominatimGeocoder = mock()
        
        // Set up mock repository responses
        val locationFlow = MutableStateFlow(mockCurrentLocation)
        whenever(mapRepository.getLocationUpdates()).thenReturn(locationFlow)
        
        whenever(mapRepository.calculateRoute(any(), any())).thenReturn(mockRoute)
        
        service = NavigationService()
    }
    
    @Test
    fun `startNavigation should set up navigation state`() = testDispatcher.runBlockingTest {
        // Given
        val destination = mockDestination
        
        // When
        service.startNavigation(destination)
        
        // Then
        assert(service.navigationState.value.isNavigating)
        assert(service.navigationState.value.destination == destination)
    }
    
    @Test
    fun `updateNavigation should update navigation progress`() = testDispatcher.runBlockingTest {
        // Given
        service.startNavigation(mockDestination)
        
        // When
        service.updateNavigation(mockCurrentLocation)
        
        // Then
        assert(service.navigationState.value.currentLocation == mockCurrentLocation)
        // In a real implementation, we would verify that the distance to destination and ETA are updated
    }
    
    @Test
    fun `stopNavigation should reset navigation state`() = testDispatcher.runBlockingTest {
        // Given
        service.startNavigation(mockDestination)
        
        // When
        service.stopNavigation()
        
        // Then
        assert(!service.navigationState.value.isNavigating)
        assert(service.navigationState.value.destination == null)
    }
    
    @Test
    fun `getNextInstruction should return the next step`() = testDispatcher.runBlockingTest {
        // Given
        service.startNavigation(mockDestination)
        service.setRoute(mockRoute)
        
        // When
        val instruction = service.getNextInstruction()
        
        // Then
        assert(instruction == mockRoute.steps[0].instruction)
    }
}