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
import com.example.pictureupload.R
import com.example.pictureupload.ui.screens.Screens

@Composable
fun BottomNavigationBar(modifier: Modifier = Modifier) {
  // val navController = rememberNavController()
  val iconSize by remember { mutableStateOf(60.dp) }
  val navBarSize by remember { mutableStateOf(iconSize + 20.dp) }
  val screens = listOf(
    Screens.PhotosScreen,
    Screens.SearchScreen,
    Screens.SharingScreen,
    Screens.LibraryScreen,
  )

  Scaffold(bottomBar = {
    BottomNavigation(
      backgroundColor = MaterialTheme.colors.surface,
      modifier = modifier.height(navBarSize)
    ) {
      // val navBackStackEntry by navController.currentBackStackEntryAsState()
      // val currentDestination = navBackStackEntry?.destination

      screens.forEach { screen ->
        BottomNavigationItem(
          icon = {
            when (screen) {
              Screens.PhotosScreen -> Icon(
                painter = painterResource(id = R.drawable.ic_photo_selected), contentDescription = null, modifier =
              modifier.height(iconSize)
              )
              Screens.SearchScreen -> Icon(
                painter = painterResource(id = R.drawable.ic_search), contentDescription = null, modifier =
              modifier.height(iconSize)
              )
              Screens.SharingScreen -> Icon(
                painter = painterResource(id = R.drawable.ic_sharing), contentDescription = null, modifier =
              modifier.height(iconSize)
              )
              Screens.LibraryScreen -> Icon(
                painter = painterResource(id = R.drawable.ic_library), contentDescription = null, modifier =
              modifier.height(iconSize)
              )
            }
          },
          label = {
            when (screen) {
              Screens.PhotosScreen -> Text(text = stringResource(R.string.bottomNavItemTitle_photos), style = MaterialTheme.typography.body1)
              Screens.SearchScreen -> Text(text = stringResource(R.string.bottomNavItemTitle_search), style = MaterialTheme.typography.body1)
              Screens.SharingScreen -> Text(text = stringResource(R.string.bottomNavItemTitle_sharing), style = MaterialTheme.typography.body1)
              Screens.LibraryScreen -> Text(text = stringResource(R.string.bottomNavItemTitle_library), style = MaterialTheme.typography.body1)
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
          //Remove these temporary 'onClick' and 'selected' parameters and uncomment the ones above after adding navBackStackEntry and currentDestination
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