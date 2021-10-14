package com.example.pictureupload.ui.screens.landing_screen.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import com.example.pictureupload.ui.screens.landing_screen.LandingScreenViewModel
import com.example.pictureupload.ui.screens.landing_screen.states.ImageUploadState
import com.example.pictureupload.ui.theme.topBarBackgroundColor
import com.google.accompanist.systemuicontroller.SystemUiController
import kotlin.math.roundToInt

private const val TAG = "LandingScreen"

@ExperimentalAnimationApi
@ExperimentalCoilApi
@Composable
fun LandingScreen(
  modifier: Modifier = Modifier,
  systemUiController: SystemUiController
) {
  val ctx = LocalContext.current
  val toolbarHeight = 64.dp
  val toolbarHeightPx = with(LocalDensity.current) { toolbarHeight.roundToPx().toFloat() }
  val toolbarOffsetHeightPx = remember { mutableStateOf(0f) }

  val themeTypography = MaterialTheme.typography
  val themeColors = MaterialTheme.colors

  var topBarColorState by remember { mutableStateOf(themeColors.surface) }
  val topBarColor by animateColorAsState(targetValue = topBarColorState, tween(durationMillis = 300))

  val topBarBackgroundColor = themeColors.topBarBackgroundColor

  var topBarTitleSizeState by remember { mutableStateOf(themeTypography.h1.fontSize.value) }
  val topBarTitleSize by animateFloatAsState(targetValue = topBarTitleSizeState, tween(durationMillis = 300))

  val landingScreenViewModel: LandingScreenViewModel = hiltViewModel()
  val topBarImageState = landingScreenViewModel.imageState.collectAsState().value

  val topAppBarModifier = when (topBarImageState) {
    is ImageUploadState.Empty -> {
      topBarTitleSizeState = themeTypography.h1.fontSize.value
      modifier
        .background(topBarColor)
        .fillMaxSize()
    }
    else -> {
      topBarTitleSizeState = themeTypography.h1.fontSize.value - 4
      modifier
        .background(topBarColor)
        .fillMaxWidth()
    }
  }

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
        topBarColorState = if (changeColor) themeColors.surface else topBarBackgroundColor
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
        Column {
          Row(
            modifier = topAppBarModifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
          ) {
            Text(text = "Google", style = MaterialTheme.typography.h1, color = themeColors.onSurface, fontSize = topBarTitleSize.sp)
            Text(text = " Photos", style = MaterialTheme.typography.h6, color = themeColors.onSurface, fontSize = topBarTitleSize.sp)
          }
          AnimatedVisibility(
            visible = topBarImageState !is ImageUploadState.Empty,
            enter = fadeIn(),
            exit = fadeOut()
          ) {
            Row(
              modifier = topAppBarModifier,
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.Center
            ) {
              Text(text = ctx.getString(topBarImageState.msg), style = MaterialTheme.typography.subtitle2, color = themeColors.onSurface)
            }
          }
        }
        TopBarProfileButton()
      }
    }

  }
}