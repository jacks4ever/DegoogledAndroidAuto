package com.degoogled.minimalandroidauto.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * Adapter for the navigation ViewPager
 */
class NavigationPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    private val fragments = mutableMapOf<Int, Fragment>()

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        val fragment = when (position) {
            0 -> OsmMapsFragment()
            1 -> WazeFragment()
            else -> throw IllegalArgumentException("Invalid position: $position")
        }
        
        fragments[position] = fragment
        return fragment
    }
    
    /**
     * Get the fragment at the specified position
     */
    fun getFragmentAtPosition(position: Int): Fragment? {
        return fragments[position]
    }
}