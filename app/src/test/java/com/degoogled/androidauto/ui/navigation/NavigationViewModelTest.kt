package com.degoogled.androidauto.ui.navigation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.degoogled.androidauto.data.model.Instruction
import com.degoogled.androidauto.data.model.Location
import com.degoogled.androidauto.data.model.Route
import com.degoogled.androidauto.domain.usecase.CalculateRouteUseCase
import com.degoogled.androidauto.domain.usecase.SearchLocationUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import java.io.IOException

@ExperimentalCoroutinesApi
class NavigationViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    
    private val testDispatcher = TestCoroutineDispatcher()
    
    private lateinit var viewModel: NavigationViewModel
    private lateinit var searchLocationUseCase: SearchLocationUseCase
    private lateinit var calculateRouteUseCase: CalculateRouteUseCase
    
    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        
        searchLocationUseCase = mock()
        calculateRouteUseCase = mock()
        
        viewModel = NavigationViewModel(searchLocationUseCase, calculateRouteUseCase)
    }
    
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }
    
    @Test
    fun `searchLocation success should update searchResults`() = testDispatcher.runBlockingTest {
        // Given
        val query = "San Francisco"
        val locations = listOf(
            Location(37.7749, -122.4194, "San Francisco, CA"),
            Location(37.7694, -122.4862, "Golden Gate Park, San Francisco, CA")
        )
        whenever(searchLocationUseCase(query)).thenReturn(Result.success(locations))
        
        // When
        viewModel.searchLocation(query)
        
        // Then
        assert(viewModel.searchResults.value == locations)
    }
    
    @Test
    fun `searchLocation failure should update errorMessage`() = testDispatcher.runBlockingTest {
        // Given
        val query = "Invalid Location"
        val errorMessage = "Search failed"
        whenever(searchLocationUseCase(query)).thenReturn(Result.failure(IOException(errorMessage)))
        
        // When
        viewModel.searchLocation(query)
        
        // Then
        assert(viewModel.errorMessage.value?.contains(errorMessage) == true)
    }
    
    @Test
    fun `selectDestination should update selectedDestination and calculate route`() = testDispatcher.runBlockingTest {
        // Given
        val location = Location(37.7749, -122.4194, "San Francisco, CA")
        val route = Route(
            points = listOf(
                Location(37.7749, -122.4194, "Start"),
                Location(37.7750, -122.4195, "End")
            ),
            instructions = listOf(
                Instruction(
                    text = "Go straight",
                    distance = 100,
                    startLocation = Location(37.7749, -122.4194, "Start"),
                    endLocation = Location(37.7750, -122.4195, "End")
                )
            ),
            distance = 100,
            duration = 60
        )
        whenever(calculateRouteUseCase(any(), any())).thenReturn(Result.success(route))
        
        // When
        viewModel.selectDestination(location)
        
        // Then
        assert(viewModel.selectedDestination.value == location)
        assert(viewModel.currentRoute.value == route)
    }
    
    @Test
    fun `startNavigation should update isNavigating`() = testDispatcher.runBlockingTest {
        // Given
        val location = Location(37.7749, -122.4194, "San Francisco, CA")
        val route = Route(
            points = listOf(
                Location(37.7749, -122.4194, "Start"),
                Location(37.7750, -122.4195, "End")
            ),
            instructions = listOf(
                Instruction(
                    text = "Go straight",
                    distance = 100,
                    startLocation = Location(37.7749, -122.4194, "Start"),
                    endLocation = Location(37.7750, -122.4195, "End")
                )
            ),
            distance = 100,
            duration = 60
        )
        whenever(calculateRouteUseCase(any(), any())).thenReturn(Result.success(route))
        viewModel.selectDestination(location)
        
        // When
        viewModel.startNavigation()
        
        // Then
        assert(viewModel.isNavigating.value == true)
        assert(viewModel.currentInstruction.value == route.instructions.first())
    }
    
    @Test
    fun `stopNavigation should update isNavigating`() = testDispatcher.runBlockingTest {
        // Given
        val location = Location(37.7749, -122.4194, "San Francisco, CA")
        val route = Route(
            points = listOf(
                Location(37.7749, -122.4194, "Start"),
                Location(37.7750, -122.4195, "End")
            ),
            instructions = listOf(
                Instruction(
                    text = "Go straight",
                    distance = 100,
                    startLocation = Location(37.7749, -122.4194, "Start"),
                    endLocation = Location(37.7750, -122.4195, "End")
                )
            ),
            distance = 100,
            duration = 60
        )
        whenever(calculateRouteUseCase(any(), any())).thenReturn(Result.success(route))
        viewModel.selectDestination(location)
        viewModel.startNavigation()
        
        // When
        viewModel.stopNavigation()
        
        // Then
        assert(viewModel.isNavigating.value == false)
        assert(viewModel.currentInstruction.value == null)
    }
}