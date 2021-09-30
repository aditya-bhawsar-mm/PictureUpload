package com.example.pictureupload

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pictureupload.domain.AuthResult
import com.example.pictureupload.usecases.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@HiltViewModel
class RegisterViewModel @Inject constructor(private val authUseCase: AuthUseCase): ViewModel(){

    private val _viewState =  MutableLiveData<RegisterViewState>(RegisterViewState.Idle)
    val viewState: LiveData<RegisterViewState> get() = _viewState

    fun resetViewState(){ _viewState.value = RegisterViewState.Idle }

    fun validateInputAndRegister(mail: String, passOrg: String, passConf:String){

        println("Mail $mail")
        println("Pass $passOrg")
        println("PassConf $passConf")

        when(val validatedInput = ValidationChecker.validateRegisterAuthParams(mail, passOrg, passConf)){
            is ValidationResult.Failed -> {
                _viewState.value = RegisterViewState.Error(msg = validatedInput.msg)
            }
            is ValidationResult.Success -> {
                val params = AuthUseCase.Params(mail, passOrg)
                signUpWithCredentials(params)
            }
        }
    }

    private fun signUpWithCredentials(params: AuthUseCase.Params)= viewModelScope.launch{
        _viewState.value = RegisterViewState.Loading
        authUseCase.performSignUp(params).collect {  authResult ->
            when(authResult){
                is AuthResult.Loading -> { _viewState.value = RegisterViewState.Loading }
                is AuthResult.Success -> { _viewState.value = RegisterViewState.Success }
                is AuthResult.Failure -> { _viewState.value = RegisterViewState.Error(msg = authResult.msg) }
            }
        }
    }
}

sealed class RegisterViewState{
    object Idle: RegisterViewState()
    object Loading: RegisterViewState()
    object Success: RegisterViewState()
    class Error(val msg: String): RegisterViewState()
}