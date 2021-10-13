package com.example.pictureupload.ui.screens.landing_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pictureupload.ui.screens.landing_screen.states.ImageUploadState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LandingScreenViewModel: ViewModel() {

  private val _imageState = MutableStateFlow<ImageUploadState>(ImageUploadState.Empty)
  val imageState: StateFlow<ImageUploadState> = _imageState

  init {
    initImageState()
  }

  /**
   * The method initImageState and the imageState flows are just for testing purposes and will not be present in the final code
   */
  private fun initImageState() {
    val listOfStates: List<ImageUploadState> = listOf(
      ImageUploadState.Empty,
      ImageUploadState.Uploading(),
      ImageUploadState.Paused(),
      ImageUploadState.Disabled(),
    )

    viewModelScope.launch {
      while (true) {
        _imageState.tryEmit(listOfStates.random())
        delay(2000)
      }
    }
  }

}