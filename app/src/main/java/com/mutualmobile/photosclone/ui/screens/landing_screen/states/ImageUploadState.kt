package com.mutualmobile.photosclone.ui.screens.landing_screen.states

import androidx.annotation.StringRes
import com.mutualmobile.photosclone.R

sealed class ImageUploadState(@StringRes val msg: Int) {
    object Empty : ImageUploadState(R.string.topBarStatusMsg_empty)
    class Uploading : ImageUploadState(R.string.topBarStatusMsg_uploading)
    class Paused : ImageUploadState(R.string.topBarStatusMsg_paused)
    class Disabled : ImageUploadState(R.string.topBarStatusMsg_disabled)
}
