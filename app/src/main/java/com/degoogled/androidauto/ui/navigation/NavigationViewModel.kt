package com.degoogled.androidauto.ui.navigation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.degoogled.androidauto.data.model.Instruction
import com.degoogled.androidauto.data.model.Location
import com.degoogled.androidauto.data.model.Route
import com.degoogled.androidauto.domain.usecase.CalculateRouteUseCase
import com.degoogled.androidauto.domain.usecase.SearchLocationUseCase
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * ViewModel for the navigation screen.
 */
class NavigationViewModel(
    private val searchLocationUseCase: SearchLocationUseCase,
    private val calculateRouteUseCase: CalculateRouteUseCase
) : ViewModel() {

    private val _searchResults = MutableLiveData<List<Location>>()
    val searchResults: LiveData<List<Location>> = _searchResults
    
    private val _selectedDestination = MutableLiveData<Location?>()
    val selectedDestination: LiveData<Location?> = _selectedDestination
    
    private val _currentLocation = MutableLiveData<Location>()
    val currentLocation: LiveData<Location> = _currentLocation
    
    private val _currentRoute = MutableLiveData<Route?>()
    val currentRoute: LiveData<Route?> = _currentRoute
    
    private val _isNavigating = MutableLiveData(false)
    val isNavigating: LiveData<Boolean> = _isNavigating
    
    private val _currentInstruction = MutableLiveData<Instruction?>()
    val currentInstruction: LiveData<Instruction?> = _currentInstruction
    
    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage
    
    init {
        // Set a default current location (San Francisco)
        _currentLocation.value = Location(37.7749, -122.4194, "Current Location")
    }
    
    fun searchLocation(query: String) {
        viewModelScope.launch {
            try {
                val result = searchLocationUseCase(query)
                result.onSuccess { locations ->
                    _searchResults.value = locations
                }.onFailure { error ->
                    _errorMessage.value = "Search failed: ${error.message}"
                }
            } catch (e: Exception) {
                _errorMessage.value = "Search failed: ${e.message}"
            }
        }
    }
    
    fun selectDestination(location: Location) {
        _selectedDestination.value = location
        calculateRoute()
    }
    
    private fun calculateRoute() {
        viewModelScope.launch {
            val start = _currentLocation.value ?: return@launch
            val destination = _selectedDestination.value ?: return@launch
            
            try {
                val result = calculateRouteUseCase(start, destination)
                result.onSuccess { route ->
                    _currentRoute.value = route
                }.onFailure { error ->
                    _errorMessage.value = "Route calculation failed: ${error.message}"
                }
            } catch (e: Exception) {
                _errorMessage.value = "Route calculation failed: ${e.message}"
            }
        }
    }
    
    fun startNavigation() {
        val route = _currentRoute.value ?: return
        _isNavigating.value = true
        
        // In a real implementation, this would start turn-by-turn navigation
        // For now, just show the first instruction
        if (route.instructions.isNotEmpty()) {
            _currentInstruction.value = route.instructions.first()
        }
    }
    
    fun stopNavigation() {
        _isNavigating.value = false
        _currentInstruction.value = null
    }
    
    fun centerOnCurrentLocation() {
        // In a real implementation, this would center the map on the current location
    }
}