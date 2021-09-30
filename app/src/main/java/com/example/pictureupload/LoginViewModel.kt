package com.example.pictureupload

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pictureupload.domain.AuthResult
import com.example.pictureupload.usecases.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val authUseCases: AuthUseCase) : ViewModel() {

    private val _viewState = MutableLiveData<LoginViewState>(LoginViewState.Idle)
    val viewState: LiveData<LoginViewState> get() = _viewState

    fun resetViewState() { _viewState.value = LoginViewState.Idle }

    fun validateInputAndLogIn(mail: String, pass: String) {
        when (val validatedInput = ValidationChecker.validateLoginAuthParams(mail, pass)) {
            is ValidationResult.Failed -> {
                _viewState.value = LoginViewState.Error(msg = validatedInput.msg)
            }
            is ValidationResult.Success -> {
                val params = AuthUseCase.Params(mail, pass)
                logInWithCredentials(params)
            }
        }
    }

    private fun logInWithCredentials(params: AuthUseCase.Params) = viewModelScope.launch {
        _viewState.value = LoginViewState.Loading
        authUseCases.performSignIn(params).collect { authResult ->
            when (authResult) {
                is AuthResult.Loading -> { _viewState.value = LoginViewState.Loading }
                is AuthResult.Success -> { _viewState.value = LoginViewState.Success }
                is AuthResult.Failure -> { _viewState.value = LoginViewState.Error(msg = authResult.msg) }
            }
        }
    }
}

sealed class LoginViewState {
    object Loading : LoginViewState()
    class Error(val msg: String) : LoginViewState()
    object Success : LoginViewState()
    object Idle : LoginViewState()
}
