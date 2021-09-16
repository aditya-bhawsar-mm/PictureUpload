package com.example.pictureupload

import androidx.lifecycle.ViewModel
import com.example.pictureupload.usecases.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    authUseCases: AuthUseCase
): ViewModel() {

    private fun logInWithCredentials(param: AuthUseCase.Params){
        TODO("Not yet implemented")
    }

    fun validateInputAndLogIn(mail: String, pass: String){
        TODO("Not yet implemented")
    }

    fun validateInput(params: AuthUseCase.Params): Boolean{
        TODO("Not yet implemented")
    }
}

sealed class LoginViewState{
    class Loading(): LoginViewState()
    class Error(val msg: String): LoginViewState()
    class Success(): LoginViewState()
    class Idle(): LoginViewState()
}