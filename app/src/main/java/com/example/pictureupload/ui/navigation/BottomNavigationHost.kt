package com.example.pictureupload.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.pictureupload.ui.screens.Screens

@Composable
fun BottomNavigationHost(
  navController: NavHostController,
  innerPadding: PaddingValues
) {
  NavHost(navController = navController, startDestination = Screens.PhotosScreen.route, modifier = Modifier.padding(innerPadding)) {
    composable(route = Screens.PhotosScreen.route) {}
    composable(route = Screens.SearchScreen.route) {}
    composable(route = Screens.SharingScreen.route) {}
    composable(route = Screens.LibraryScreen.route) {}
  }
}