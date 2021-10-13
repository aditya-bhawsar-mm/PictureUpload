package com.example.pictureupload.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.pictureupload.ui.screens.BottomNavItem
import com.example.pictureupload.ui.screens.landing_screen.components.LibraryScreen
import com.example.pictureupload.ui.screens.landing_screen.components.PhotosScreen
import com.example.pictureupload.ui.screens.landing_screen.components.SearchScreen
import com.example.pictureupload.ui.screens.landing_screen.components.SharingScreen

@Composable
fun BottomNavigationHost(
  navController: NavHostController,
  innerPadding: PaddingValues
) {
  NavHost(navController = navController, startDestination = BottomNavItem.PhotosScreen.route, modifier = Modifier.padding(innerPadding)) {
    composable(route = BottomNavItem.PhotosScreen.route) {
      PhotosScreen()
    }
    composable(route = BottomNavItem.SearchScreen.route) {
      SearchScreen()
    }
    composable(route = BottomNavItem.SharingScreen.route) {
      SharingScreen()
    }
    composable(route = BottomNavItem.LibraryScreen.route) {
      LibraryScreen()
    }
  }
}