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

    private val _viewState = MutableLiveData<LoginViewState>(LoginViewState.Idle)
    val viewState: LiveData<LoginViewState> get() = _viewState

    fun resetViewState(){}

    fun validateInputAndLogIn(mail: String, pass: String){}

    private fun validateInput(params: AuthUseCase.Params): Pair<Boolean, String>{
        return Pair(false, "")
    }

    private fun logInWithCredentials(param: AuthUseCase.Params){}
}

sealed class LoginViewState{
    object Loading: LoginViewState()
    class Error(val msg: String): LoginViewState()
    object Success: LoginViewState()
    object Idle: LoginViewState()
}