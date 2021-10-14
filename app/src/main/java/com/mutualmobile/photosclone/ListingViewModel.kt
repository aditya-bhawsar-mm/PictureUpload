package com.mutualmobile.photosclone

import androidx.lifecycle.ViewModel
import com.mutualmobile.photosclone.usecases.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListingViewModel @Inject constructor(private val authUseCases: AuthUseCase) : ViewModel() {
    fun isUserLogged() = authUseCases.isUserLogged()
}
