package com.example.pictureupload.ui.screens

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.pictureupload.R

sealed class BottomNavItem(val route: String, @StringRes val label: Int, @DrawableRes val iconId: Int) {
  object PhotosScreen : BottomNavItem(route = "photos_screen", label = R.string.bottomNavItemTitle_photos, iconId = R.drawable.ic_photo_selected)
  object SearchScreen : BottomNavItem(route = "search_screen", label = R.string.bottomNavItemTitle_search, iconId = R.drawable.ic_search)
  object SharingScreen : BottomNavItem(route = "sharing_screen", label = R.string.bottomNavItemTitle_sharing, iconId = R.drawable.ic_sharing)
  object LibraryScreen : BottomNavItem(route = "library_screen", label = R.string.bottomNavItemTitle_library, iconId = R.drawable.ic_library)
}
