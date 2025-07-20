package com.degoogled.minimalandroidauto.navigation

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.degoogled.minimalandroidauto.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

/**
 * Activity for navigation with both OpenStreetMap and Waze options
 */
class NavigationActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout
    private lateinit var editTextSearch: EditText
    private lateinit var buttonSearch: ImageButton
    private lateinit var navigationPagerAdapter: NavigationPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)

        // Initialize views
        viewPager = findViewById(R.id.viewPager)
        tabLayout = findViewById(R.id.tabLayout)
        editTextSearch = findViewById(R.id.editTextSearch)
        buttonSearch = findViewById(R.id.buttonSearch)

        // Set up the ViewPager with the adapter
        navigationPagerAdapter = NavigationPagerAdapter(this)
        viewPager.adapter = navigationPagerAdapter

        // Connect the TabLayout with the ViewPager
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> getString(R.string.osm_maps)
                1 -> getString(R.string.waze)
                else -> null
            }
        }.attach()

        // Set up search button
        buttonSearch.setOnClickListener {
            val searchQuery = editTextSearch.text.toString().trim()
            if (searchQuery.isNotEmpty()) {
                performSearch(searchQuery)
            }
        }

        // Handle intent extras if any
        handleIntentExtras()
    }

    private fun handleIntentExtras() {
        intent.extras?.let { extras ->
            // Check if we should open a specific tab
            if (extras.containsKey(EXTRA_NAVIGATION_TAB)) {
                val tabIndex = extras.getInt(EXTRA_NAVIGATION_TAB, 0)
                viewPager.setCurrentItem(tabIndex, false)
            }

            // Check if we have a search query
            if (extras.containsKey(EXTRA_SEARCH_QUERY)) {
                val searchQuery = extras.getString(EXTRA_SEARCH_QUERY, "")
                if (searchQuery.isNotEmpty()) {
                    editTextSearch.setText(searchQuery)
                    performSearch(searchQuery)
                }
            }

            // Check if we have coordinates
            if (extras.containsKey(EXTRA_LATITUDE) && extras.containsKey(EXTRA_LONGITUDE)) {
                val latitude = extras.getDouble(EXTRA_LATITUDE, 0.0)
                val longitude = extras.getDouble(EXTRA_LONGITUDE, 0.0)
                navigateToCoordinates(latitude, longitude)
            }
        }
    }

    private fun performSearch(query: String) {
        // Get the current fragment and perform search
        val currentFragment = navigationPagerAdapter.getFragmentAtPosition(viewPager.currentItem)
        if (currentFragment is OsmMapsFragment) {
            currentFragment.searchLocation(query)
        } else if (currentFragment is WazeFragment) {
            currentFragment.searchLocation(query)
        }
    }

    private fun navigateToCoordinates(latitude: Double, longitude: Double) {
        // Get the current fragment and navigate to coordinates
        val currentFragment = navigationPagerAdapter.getFragmentAtPosition(viewPager.currentItem)
        if (currentFragment is OsmMapsFragment) {
            currentFragment.navigateToCoordinates(latitude, longitude)
        } else if (currentFragment is WazeFragment) {
            currentFragment.navigateToCoordinates(latitude, longitude)
        }
    }

    companion object {
        const val EXTRA_NAVIGATION_TAB = "com.degoogled.minimalandroidauto.extra.NAVIGATION_TAB"
        const val EXTRA_SEARCH_QUERY = "com.degoogled.minimalandroidauto.extra.SEARCH_QUERY"
        const val EXTRA_LATITUDE = "com.degoogled.minimalandroidauto.extra.LATITUDE"
        const val EXTRA_LONGITUDE = "com.degoogled.minimalandroidauto.extra.LONGITUDE"
        
        const val TAB_OSM = 0
        const val TAB_WAZE = 1
    }
}