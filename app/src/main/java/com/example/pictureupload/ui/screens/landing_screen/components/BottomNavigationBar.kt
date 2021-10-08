package com.example.pictureupload.ui.screens.landing_screen.components

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.pictureupload.R
import com.example.pictureupload.ui.screens.Screens

@Composable
fun BottomNavigationBar() {
  // val navController = rememberNavController()
  val screens = listOf(
    Screens.PhotosScreen,
    Screens.SearchScreen,
    Screens.SharingScreen,
    Screens.LibraryScreen,
  )

  Scaffold(bottomBar = {
    BottomNavigation(
      backgroundColor = Color.DarkGray
    ) {
      // val navBackStackEntry by navController.currentBackStackEntryAsState()
      // val currentDestination = navBackStackEntry?.destination

      screens.forEach { screen ->
        BottomNavigationItem(
          icon = {
            when (screen) {
              Screens.PhotosScreen -> Icon(painter = painterResource(id = R.drawable.ic_photo_selected), contentDescription = null)
              Screens.SearchScreen -> Icon(painter = painterResource(id = R.drawable.ic_search), contentDescription = null)
              Screens.SharingScreen -> Icon(painter = painterResource(id = R.drawable.ic_sharing), contentDescription = null)
              Screens.LibraryScreen -> Icon(painter = painterResource(id = R.drawable.ic_library), contentDescription = null)
            }
          },
          label = {
            when (screen) {
              Screens.PhotosScreen -> Text(text = stringResource(R.string.bottomNavItemTitle_photos))
              Screens.SearchScreen -> Text(text = stringResource(R.string.bottomNavItemTitle_search))
              Screens.SharingScreen -> Text(text = stringResource(R.string.bottomNavItemTitle_sharing))
              Screens.LibraryScreen -> Text(text = stringResource(R.string.bottomNavItemTitle_library))
            }
          },
          // selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
          // onClick = {
          //   navController.navigate(screen.route) {
          //     popUpTo(navController.graph.findStartDestination().id) {
          //       saveState = true
          //     }
          //     launchSingleTop = true
          //     restoreState = true
          //   }
          // },
          //Remove these temporary onClick and selected parameters and uncomment the ones above after adding navBackStackEntry and currentDestination
          onClick = {},
          selected = false,
          selectedContentColor = Color.White,
          unselectedContentColor = Color.LightGray,
          // alwaysShowLabel = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
        )
      }
    }
  }) {
    //todo: add the navHost component here
  }
}