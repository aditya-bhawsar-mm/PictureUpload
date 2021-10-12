package com.example.pictureupload.ui.screens.landing_screen.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import com.example.pictureupload.ui.theme.LandingScreenStatusBarColor
import com.google.accompanist.systemuicontroller.SystemUiController
import kotlin.math.roundToInt

@ExperimentalCoilApi
@Composable
fun TopBar(
  modifier: Modifier = Modifier,
  systemUiController: SystemUiController
) {
  val toolbarHeight = 64.dp
  val toolbarHeightPx = with(LocalDensity.current) { toolbarHeight.roundToPx().toFloat() }
  val toolbarOffsetHeightPx = remember { mutableStateOf(0f) }

  val themeColors = MaterialTheme.colors
  var topBarColorState by remember { mutableStateOf(themeColors.surface) }
  val topBarColor by animateColorAsState(targetValue = topBarColorState, tween(durationMillis = 300))

  val nestedScrollConnection = remember {
    object : NestedScrollConnection {
      override fun onPreScroll(
        available: Offset,
        source: NestedScrollSource
      ): Offset {
        val delta = available.y
        val newOffset = toolbarOffsetHeightPx.value + delta
        toolbarOffsetHeightPx.value = newOffset.coerceIn(-toolbarHeightPx, 0f)
        return Offset.Zero
      }
    }
  }
  Box(
    modifier
      .fillMaxSize()
      .nestedScroll(nestedScrollConnection)
  ) {

    BottomNavigationBar(
      modifier = modifier,
      topBarHeight = toolbarHeight,
      changeTopBarColor = { changeColor: Boolean ->
        topBarColorState = if (changeColor) themeColors.surface else LandingScreenStatusBarColor
        systemUiController.setStatusBarColor(color = topBarColor)
      }
    )

    TopAppBar(
      modifier = modifier
        .height(toolbarHeight)
        .offset { IntOffset(x = 0, y = toolbarOffsetHeightPx.value.roundToInt()) },
      backgroundColor = topBarColor,
      elevation = 0.dp
    ) {
      Box(contentAlignment = Alignment.CenterEnd) {
        Row(
          modifier = modifier
            .fillMaxSize()
            .background(topBarColor), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement
          .Center
        ) {
          Text(text = "Google", style = MaterialTheme.typography.h1, color = themeColors.onSurface)
          Text(text = " Photos", style = MaterialTheme.typography.body2, color = themeColors.onSurface)
        }
        TopBarProfileButton()
      }
    }

  }
}