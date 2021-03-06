package com.mutualmobile.photosclone.ui.screens.landing_screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import coil.annotation.ExperimentalCoilApi
import com.mutualmobile.photosclone.ui.screens.landing_screen.components.LandingScreen
import com.mutualmobile.photosclone.ui.theme.LandingScreenStatusBarColor
import com.mutualmobile.photosclone.ui.theme.PictureUploadTheme
import com.mutualmobile.photosclone.ui.theme.bottomNavBarColor
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class LandingScreenActivity : ComponentActivity() {

    @ExperimentalAnimationApi
    @ExperimentalCoilApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            PictureUploadTheme {
                ProvideWindowInsets {
                    val systemUiController = rememberSystemUiController()
                    val useDarkIcons = MaterialTheme.colors.isLight
                    val navBarColor = MaterialTheme.colors.bottomNavBarColor

                    SideEffect {
                        systemUiController.setStatusBarColor(
                            color = LandingScreenStatusBarColor,
                            darkIcons = useDarkIcons
                        )
                        systemUiController.setNavigationBarColor(
                            color = navBarColor,
                            darkIcons = useDarkIcons
                        )
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = MaterialTheme.colors.surface)
                            .statusBarsPadding()
                            .navigationBarsPadding(),
                        contentAlignment = Alignment.Center,
                    ) {
                        // BottomNavigationBar()
                        LandingScreen(systemUiController = systemUiController)
                    }
                }
            }
        }
    }
}
