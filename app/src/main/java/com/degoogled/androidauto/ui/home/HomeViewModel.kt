package com.degoogled.androidauto.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.degoogled.androidauto.R

/**
 * ViewModel for the home screen.
 */
class HomeViewModel : ViewModel() {

    private val _menuItems = MutableLiveData<List<MenuItem>>()
    val menuItems: LiveData<List<MenuItem>> = _menuItems
    
    init {
        loadMenuItems()
    }
    
    private fun loadMenuItems() {
        val items = listOf(
            MenuItem(
                id = "navigation",
                title = "Navigation",
                description = "Find your way using OpenStreetMap",
                iconResId = R.drawable.ic_navigation
            ),
            MenuItem(
                id = "media",
                title = "Media",
                description = "Play music and podcasts",
                iconResId = R.drawable.ic_media
            ),
            MenuItem(
                id = "messaging",
                title = "Messaging",
                description = "Send and receive messages",
                iconResId = R.drawable.ic_messaging
            ),
            MenuItem(
                id = "settings",
                title = "Settings",
                description = "Configure app preferences",
                iconResId = R.drawable.ic_settings
            )
        )
        _menuItems.value = items
    }
}