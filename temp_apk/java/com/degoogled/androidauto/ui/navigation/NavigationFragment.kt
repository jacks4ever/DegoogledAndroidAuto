package com.degoogled.androidauto.ui.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import com.degoogled.androidauto.databinding.FragmentNavigationBinding
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Fragment for navigation and maps.
 */
class NavigationFragment : Fragment() {

    private var _binding: FragmentNavigationBinding? = null
    private val binding get() = _binding!!
    
    private val viewModel: NavigationViewModel by viewModel()
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNavigationBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupSearchBar()
        setupButtons()
        observeViewModel()
        
        // In a real implementation, this would initialize the MapLibre map view
        // and set up the map with OpenStreetMap data
    }
    
    private fun setupSearchBar() {
        binding.editSearch.setOnEditorActionListener { textView, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val query = textView.text.toString()
                if (query.isNotEmpty()) {
                    viewModel.searchLocation(query)
                    return@setOnEditorActionListener true
                }
            }
            false
        }
        
        binding.buttonSearch.setOnClickListener {
            val query = binding.editSearch.text.toString()
            if (query.isNotEmpty()) {
                viewModel.searchLocation(query)
            }
        }
    }
    
    private fun setupButtons() {
        binding.buttonStartNavigation.setOnClickListener {
            viewModel.startNavigation()
        }
        
        binding.buttonStopNavigation.setOnClickListener {
            viewModel.stopNavigation()
        }
        
        binding.fabMyLocation.setOnClickListener {
            viewModel.centerOnCurrentLocation()
        }
    }
    
    private fun observeViewModel() {
        viewModel.searchResults.observe(viewLifecycleOwner) { locations ->
            // In a real implementation, this would show search results
            // For now, just use the first result
            if (locations.isNotEmpty()) {
                val location = locations.first()
                viewModel.selectDestination(location)
            } else {
                Snackbar.make(
                    binding.root,
                    "No locations found",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }
        
        viewModel.selectedDestination.observe(viewLifecycleOwner) { destination ->
            if (destination != null) {
                binding.textDestination.text = destination.name
                binding.cardRouteInfo.visibility = View.VISIBLE
                
                // In a real implementation, this would center the map on the destination
            } else {
                binding.cardRouteInfo.visibility = View.GONE
            }
        }
        
        viewModel.currentRoute.observe(viewLifecycleOwner) { route ->
            if (route != null) {
                binding.textDistance.text = "${route.distance / 1000} km"
                binding.textDuration.text = "${route.duration / 60} min"
                
                // In a real implementation, this would draw the route on the map
            }
        }
        
        viewModel.isNavigating.observe(viewLifecycleOwner) { isNavigating ->
            if (isNavigating) {
                binding.cardRouteInfo.visibility = View.GONE
                binding.cardNavigationInstructions.visibility = View.VISIBLE
            } else {
                binding.cardNavigationInstructions.visibility = View.GONE
                if (viewModel.selectedDestination.value != null) {
                    binding.cardRouteInfo.visibility = View.VISIBLE
                }
            }
        }
        
        viewModel.currentInstruction.observe(viewLifecycleOwner) { instruction ->
            if (instruction != null) {
                binding.textInstruction.text = instruction.text
                binding.textInstructionDistance.text = "In ${instruction.distance} meters"
            }
        }
        
        viewModel.errorMessage.observe(viewLifecycleOwner) { errorMessage ->
            if (errorMessage.isNotEmpty()) {
                Snackbar.make(
                    binding.root,
                    errorMessage,
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}