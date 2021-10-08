package com.example.pictureupload.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.pictureupload.ui.screens.BottomNavItem

@Composable
fun BottomNavigationHost(
  navController: NavHostController,
  innerPadding: PaddingValues
) {
  NavHost(navController = navController, startDestination = BottomNavItem.PhotosScreen.route, modifier = Modifier.padding(innerPadding)) {
    composable(route = BottomNavItem.PhotosScreen.route) {}
    composable(route = BottomNavItem.SearchScreen.route) {}
    composable(route = BottomNavItem.SharingScreen.route) {}
    composable(route = BottomNavItem.LibraryScreen.route) {}
  }
}