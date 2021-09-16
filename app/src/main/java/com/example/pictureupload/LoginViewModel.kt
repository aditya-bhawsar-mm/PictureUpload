package com.example.pictureupload

import androidx.lifecycle.ViewModel
import com.example.pictureupload.usecases.AuthUseCase
import com.example.pictureupload.usecases.AuthUseCaseImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    authUseCases: AuthUseCaseImpl
): ViewModel() {

    fun logInWithCredentials(param: AuthUseCase.Params){}

}