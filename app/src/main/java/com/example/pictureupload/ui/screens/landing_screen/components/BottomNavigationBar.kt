package com.example.pictureupload.ui.screens.landing_screen.components

import androidx.compose.foundation.layout.height
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.pictureupload.ui.navigation.BottomNavigationHost
import com.example.pictureupload.ui.screens.BottomNavItem

@Composable
fun BottomNavigationBar(modifier: Modifier = Modifier) {
  val navController = rememberNavController()
  val iconSize by remember { mutableStateOf(60.dp) }
  val navBarSize by remember { mutableStateOf(iconSize + 20.dp) }
  val screens = listOf(
    BottomNavItem.PhotosScreen,
    BottomNavItem.SearchScreen,
    BottomNavItem.SharingScreen,
    BottomNavItem.LibraryScreen,
  )

  Scaffold(bottomBar = {
    BottomNavigation(
      backgroundColor = MaterialTheme.colors.surface,
      modifier = modifier.height(navBarSize)
    ) {
      val navBackStackEntry by navController.currentBackStackEntryAsState()
      val currentDestination = navBackStackEntry?.destination

      screens.forEach { screen ->
        BottomNavigationItem(
          icon = { Icon(painter = painterResource(id = screen.iconId), contentDescription = screen.route, modifier = modifier.height(iconSize)) },
          label = { Text(text = stringResource(id = screen.label), style = MaterialTheme.typography.body1) },
          selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
          onClick = {
            navController.navigate(screen.route) {
              popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
              }
              launchSingleTop = true
              restoreState = true
            }
          },
          selectedContentColor = Color.White,
          unselectedContentColor = Color.LightGray,
          // alwaysShowLabel = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
        )
      }
    }
  }) { innerPadding ->
    BottomNavigationHost(navController = navController, innerPadding = innerPadding)
  }
}