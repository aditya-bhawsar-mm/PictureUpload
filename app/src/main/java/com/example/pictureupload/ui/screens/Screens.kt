package com.example.pictureupload.ui.screens

sealed class Screens(val route: String) {
  object PhotosScreen : Screens("photos_screen")
  object SearchScreen : Screens("search_screen")
  object SharingScreen : Screens("sharing_screen")
  object LibraryScreen : Screens("library_screen")
}
