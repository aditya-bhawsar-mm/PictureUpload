package com.example.pictureupload.ui.theme

import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)

val White = Color(0xffffffff)
val Black = Color(0xff000000)
val Grey = Color(0x11000000)

val DarkModeSurfaceColor = Color(0xFF1F1F1F)
val LightModeTextColor = Color(0xFF5F6267)

val Blue = Color(0xff004868)
val LandingScreenNavBarColor = Color(0xFF5A5A5A)
val LandingScreenStatusBarColor = Color(0xFF565656)

val TopAppBarBackgroundLight = Color(0xFFF1F4FB)
val TopAppBarBackgroundDark = Color(0xFF2D2F32)

val Colors.topBarBackgroundColor: Color
  @Composable
  get() = if (isLight) TopAppBarBackgroundLight else TopAppBarBackgroundDark

val BottomNavDarkSelect = Color(0xFF004A77)
val BottomNavLightSelect = Color(0xFFC2E7FF)

val BottomNavDarkIcon = Color(0xFFC4C7C5)
val BottomNavLightIcon = Color(0xFF001D35)

val BottomNavBarDark = Color(0xFF2D2F33)
val BottomNavBarLight = Color(0xFFF1F5FB)

val SurfaceColorDark = Color(0xFF1f1f1f)
val SurfaceColorLight = Color(0xFFFFFFFF)

val Colors.bottomNavSelectedColor: Color
    @Composable
    get() = if(isLight) BottomNavLightSelect else BottomNavDarkSelect

val Colors.bottomNavIconColor: Color
    @Composable
    get() = if(isLight) BottomNavLightIcon else BottomNavDarkIcon

val Colors.bottomNavBarColor: Color
    @Composable
    get() = if(isLight) BottomNavBarLight else BottomNavBarDark

val Colors.backgroundSurfaceColor: Color
    @Composable
    get() = if(isLight) SurfaceColorLight else SurfaceColorDark
