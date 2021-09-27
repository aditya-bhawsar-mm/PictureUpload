package com.example.pictureupload

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pictureupload.domain.AuthResult
import com.example.pictureupload.usecases.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val authUseCases: AuthUseCase): ViewModel() {

    private val _viewState = MutableLiveData<LoginViewState>(LoginViewState.Idle)
    val viewState: LiveData<LoginViewState> get() = _viewState

    fun resetViewState(){ _viewState.value = LoginViewState.Idle }

    fun validateInputAndLogIn(mail: String, pass: String){
        val params = AuthUseCase.Params(mail, pass)

        when(val validatedInput = ValidationChecker.validateAuthParams(params)){
            is ValidationResult.Failed ->{
                _viewState.value = LoginViewState.Error(validatedInput.msg)
            }
            is ValidationResult.Success ->{
                logInWithCredentials(params)
            }
        }
    }

    private fun logInWithCredentials(params: AuthUseCase.Params) {
        _viewState.value = LoginViewState.Loading
        viewModelScope.launch {
            authUseCases.performSignIn(params).collect { authResult ->
                when(authResult){
                    is AuthResult.Loading->{
                        _viewState.value = LoginViewState.Loading
                    }
                    is AuthResult.Failure->{
                        _viewState.value = LoginViewState.Error(authResult.msg)
                    }
                    is AuthResult.Success->{
                        _viewState.value = LoginViewState.Success
                    }
                }
            }
        }
    }
}

sealed class LoginViewState{
    object Loading: LoginViewState()
    class Error(val msg: String): LoginViewState()
    object Success: LoginViewState()
    object Idle: LoginViewState()
}