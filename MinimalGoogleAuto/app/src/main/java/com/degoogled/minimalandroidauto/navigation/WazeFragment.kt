package com.degoogled.minimalandroidauto.navigation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.degoogled.minimalandroidauto.R
import com.degoogled.minimalandroidauto.navigation.waze.WazeIntegration
import com.degoogled.minimalandroidauto.utils.PrivacyPreferences
import com.google.android.material.switchmaterial.SwitchMaterial
import kotlinx.coroutines.launch

/**
 * Fragment for Waze navigation
 */
class WazeFragment : Fragment() {

    companion object {
        private const val TAG = "WazeFragment"
        private const val WAZE_PACKAGE = "com.waze"
        private const val WAZE_PLAY_STORE_URL = "https://play.google.com/store/apps/details?id=com.waze"
    }

    private lateinit var switchWazePrivacy: SwitchMaterial
    private lateinit var buttonLaunchWaze: Button
    private lateinit var textWazeStatus: TextView
    
    private lateinit var wazeIntegration: WazeIntegration
    
    private var pendingSearchQuery: String? = null
    private var pendingLatitude: Double? = null
    private var pendingLongitude: Double? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_waze, container, false)
        
        // Initialize views
        switchWazePrivacy = view.findViewById(R.id.switchWazePrivacy)
        buttonLaunchWaze = view.findViewById(R.id.buttonLaunchWaze)
        textWazeStatus = view.findViewById(R.id.textWazeStatus)
        
        // Initialize Waze integration
        wazeIntegration = WazeIntegration(requireContext())
        
        // Set up switch
        switchWazePrivacy.isChecked = PrivacyPreferences.isPrivacyModeEnabled(requireContext())
        switchWazePrivacy.setOnCheckedChangeListener { _, isChecked ->
            PrivacyPreferences.setWazePrivacyEnabled(requireContext(), isChecked)
            updatePrivacyStatus(isChecked)
        }
        
        // Set up button
        buttonLaunchWaze.setOnClickListener {
            launchWaze()
        }
        
        // Check if Waze is installed
        updateWazeStatus()
        
        return view
    }

    override fun onResume() {
        super.onResume()
        
        // Check if Waze is installed (in case it was installed while we were in the background)
        updateWazeStatus()
        
        // Process any pending navigation requests
        processPendingNavigationRequests()
    }

    /**
     * Update the Waze status text and button
     */
    private fun updateWazeStatus() {
        val isWazeInstalled = wazeIntegration.isWazeInstalled()
        
        if (isWazeInstalled) {
            textWazeStatus.text = getString(R.string.waze_installed)
            textWazeStatus.setTextColor(resources.getColor(android.R.color.holo_green_light, null))
            buttonLaunchWaze.text = getString(R.string.launch_waze)
        } else {
            textWazeStatus.text = getString(R.string.waze_not_installed)
            textWazeStatus.setTextColor(resources.getColor(R.color.error, null))
            buttonLaunchWaze.text = getString(R.string.install_waze)
        }
    }

    /**
     * Update the privacy status text
     */
    private fun updatePrivacyStatus(isEnabled: Boolean) {
        if (isEnabled) {
            textWazeStatus.text = getString(R.string.waze_privacy_enabled)
            textWazeStatus.setTextColor(resources.getColor(android.R.color.holo_green_light, null))
        } else {
            textWazeStatus.text = getString(R.string.waze_privacy_disabled)
            textWazeStatus.setTextColor(resources.getColor(android.R.color.holo_orange_light, null))
        }
    }

    /**
     * Launch Waze or go to Play Store if not installed
     */
    private fun launchWaze() {
        if (wazeIntegration.isWazeInstalled()) {
            // If we have pending coordinates, navigate to them
            if (pendingLatitude != null && pendingLongitude != null) {
                navigateToCoordinates(pendingLatitude!!, pendingLongitude!!)
                return
            }
            
            // If we have a pending search query, search for it
            if (pendingSearchQuery != null) {
                searchLocation(pendingSearchQuery!!)
                return
            }
            
            // Otherwise, just launch Waze
            lifecycleScope.launch {
                wazeIntegration.navigateToCoordinates(0.0, 0.0)
            }
        } else {
            // Open Play Store to install Waze
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(WAZE_PLAY_STORE_URL))
            startActivity(intent)
        }
    }

    /**
     * Process any pending navigation requests
     */
    private fun processPendingNavigationRequests() {
        if (!wazeIntegration.isWazeInstalled()) {
            return
        }
        
        // If we have pending coordinates, navigate to them
        if (pendingLatitude != null && pendingLongitude != null) {
            navigateToCoordinates(pendingLatitude!!, pendingLongitude!!)
            return
        }
        
        // If we have a pending search query, search for it
        if (pendingSearchQuery != null) {
            searchLocation(pendingSearchQuery!!)
        }
    }

    /**
     * Search for a location
     */
    fun searchLocation(query: String) {
        if (!wazeIntegration.isWazeInstalled()) {
            // Save the query for later
            pendingSearchQuery = query
            return
        }
        
        // Clear any pending requests
        pendingSearchQuery = null
        pendingLatitude = null
        pendingLongitude = null
        
        // Launch Waze with the search query
        lifecycleScope.launch {
            wazeIntegration.navigateToAddress(query)
        }
    }

    /**
     * Navigate to coordinates
     */
    fun navigateToCoordinates(latitude: Double, longitude: Double) {
        if (!wazeIntegration.isWazeInstalled()) {
            // Save the coordinates for later
            pendingLatitude = latitude
            pendingLongitude = longitude
            return
        }
        
        // Clear any pending requests
        pendingSearchQuery = null
        pendingLatitude = null
        pendingLongitude = null
        
        // Launch Waze with the coordinates
        lifecycleScope.launch {
            wazeIntegration.navigateToCoordinates(latitude, longitude)
        }
    }
}