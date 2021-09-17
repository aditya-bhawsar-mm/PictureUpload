package com.example.pictureupload

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pictureupload.usecases.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authUseCases: AuthUseCase
): ViewModel() {

    private val _viewState = MutableLiveData<LoginViewState>(LoginViewState.Idle)
    val viewState: LiveData<LoginViewState> get() = _viewState

    fun resetViewState(){ _viewState.value = LoginViewState.Idle }

    fun validateInputAndLogIn(mail: String, pass: String){
        val params = AuthUseCase.Params(mail, pass)
        val validInput = validateInput(params)

        if(!validInput.first){
            _viewState.value = LoginViewState.Error(validInput.second)
            return
        }

        logInWithCredentials(params)
    }

    private fun validateInput(params: AuthUseCase.Params): Pair<Boolean, String>{
        if(params.user.isEmpty() && params.pass.isEmpty())
            return Pair(false, "Please enter valid email and password")
        if(params.user.isEmpty())
            return Pair(false, "Please enter a valid email")
        if(params.pass.isEmpty())
            return Pair(false, "Please enter a valid password")

        return Pair(true,"")
    }

    private fun logInWithCredentials(params: AuthUseCase.Params){
        _viewState.value = LoginViewState.Loading
        authUseCases.perform(params)
    }
}

sealed class LoginViewState{
    object Loading: LoginViewState()
    class Error(val msg: String): LoginViewState()
    object Success: LoginViewState()
    object Idle: LoginViewState()
}