package com.example.pictureupload

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pictureupload.usecases.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val authUseCase: AuthUseCase): ViewModel(){

    private val _viewState =  MutableLiveData<RegisterViewState>(RegisterViewState.Loading)
    val viewState: LiveData<RegisterViewState> get() = _viewState

    fun resetViewState(){  }

    fun validateInputAndRegister(mail: String, passOrg: String, confPass:String){

    }

    private fun signUpWithCredentials(params: AuthUseCase.Params){

    }
}

sealed class RegisterViewState{
    object Idle: RegisterViewState()
    object Loading: RegisterViewState()
    object Success: RegisterViewState()
    class Error(msg: String): RegisterViewState()
}