package com.degoogled.androidauto.ui.home

import androidx.annotation.DrawableRes

/**
 * Data class representing a main menu item.
 */
data class MenuItem(
    val id: String,
    val title: String,
    val description: String,
    @DrawableRes val iconResId: Int
)