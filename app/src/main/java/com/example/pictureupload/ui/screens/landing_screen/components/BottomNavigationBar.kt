package com.example.pictureupload.ui.screens.landing_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Absolute.Center
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.pictureupload.ui.navigation.BottomNavigationHost
import com.example.pictureupload.ui.screens.BottomNavItem
import com.example.pictureupload.ui.theme.BOTTOM_NAV_LABEL_PADDING
import com.example.pictureupload.ui.theme.BOTTOM_NAV_SELECTION_CORNER
import com.example.pictureupload.ui.theme.BOTTOM_NAV_SELECTION_FRACTION
import com.example.pictureupload.ui.theme.BOTTOM_NAV_SELECTION_PADDING
import com.example.pictureupload.ui.theme.ICON_SIZE_BOTTOM_NAV
import com.example.pictureupload.ui.theme.NAV_BAR_SIZE
import com.example.pictureupload.ui.theme.bottomNavBarColor
import com.example.pictureupload.ui.theme.bottomNavIconColor
import com.example.pictureupload.ui.theme.bottomNavSelectedColor

@Composable
fun BottomNavigationBar(
  modifier: Modifier,
  topBarHeight: Dp,
  changeTopBarColor: (changeColor: Boolean) -> Unit
) {
  val navController = rememberNavController()

  val screens = listOf(
    BottomNavItem.PhotosScreen,
    BottomNavItem.SearchScreen,
    BottomNavItem.SharingScreen,
    BottomNavItem.LibraryScreen,
  )

  Scaffold(
    bottomBar = {
      BottomNavigation(
        backgroundColor = MaterialTheme.colors.bottomNavBarColor,
        modifier = modifier.height(NAV_BAR_SIZE)
      ) {
      val navBackStackEntry by navController.currentBackStackEntryAsState()
      val currentDestination = navBackStackEntry?.destination

      screens.forEach { screen ->
        val selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true

        BottomNavigationItem(
          icon = {
            BottomNavIcon(
              selected = selected,
              screen = screen,
              modifier = modifier
            )
          },
          selected = selected,
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
        )
      }
    }
  }) { innerPadding ->
    BottomNavigationHost(
      navController = navController,
      innerPadding = innerPadding,
      modifier = modifier,
      topBarHeight = topBarHeight,
      changeTopBarColor = changeTopBarColor
    )
  }
}

@Composable
fun BottomNavIcon(
  selected: Boolean,
  screen: BottomNavItem,
  modifier: Modifier
){
  Column(
    modifier = modifier.fillMaxSize(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Box(modifier = modifier,contentAlignment = Alignment.Center){
      if(selected){
        Box(modifier = modifier
          .fillMaxWidth(BOTTOM_NAV_SELECTION_FRACTION)
          .size(ICON_SIZE_BOTTOM_NAV)
          .padding(top = BOTTOM_NAV_SELECTION_PADDING, bottom = BOTTOM_NAV_SELECTION_PADDING)
          .background(
            color = MaterialTheme.colors.bottomNavSelectedColor,
            shape = RoundedCornerShape(BOTTOM_NAV_SELECTION_CORNER)
          )
        )
      }
      Icon(
        painter = painterResource(id = screen.iconId),
        contentDescription = screen.route,
        tint = MaterialTheme.colors.bottomNavIconColor,
        modifier = modifier.height(ICON_SIZE_BOTTOM_NAV)
      )
    }
    Text(
      text = stringResource(id = screen.label),
      modifier = modifier.padding(bottom = BOTTOM_NAV_LABEL_PADDING),
      style = MaterialTheme.typography.body1,
      color = MaterialTheme.colors.bottomNavIconColor
    )
  }
}