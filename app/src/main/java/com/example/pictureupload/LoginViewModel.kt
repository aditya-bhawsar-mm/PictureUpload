package com.example.pictureupload

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pictureupload.usecases.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    authUseCases: AuthUseCase
): ViewModel() {

    private fun logInWithCredentials(param: AuthUseCase.Params){

    }

    fun validateInputAndLogIn(mail: String, pass: String){

    }

    fun validateInput(params: AuthUseCase.Params): Boolean{
        //Not actual Implementation
        return true
    }
}

sealed class LoginViewState{
    object Loading: LoginViewState()
    class Error(val msg: String): LoginViewState()
    object Success: LoginViewState()
    object Idle: LoginViewState()
}