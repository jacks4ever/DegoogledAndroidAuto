package com.degoogled.androidauto.ui.media

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * Adapter for the media view pager.
 */
class MediaPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2
    
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> MediaLibraryFragment()
            1 -> PlaylistsFragment()
            else -> throw IllegalArgumentException("Invalid position: $position")
        }
    }
}