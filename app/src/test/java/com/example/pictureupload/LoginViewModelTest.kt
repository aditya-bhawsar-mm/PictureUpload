package com.example.pictureupload

import com.example.pictureupload.usecases.AuthUseCase
import com.example.pictureupload.usecases.AuthUseCaseImpl
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.verify
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class LoginViewModelTest {

    @Inject lateinit var loginViewModel : LoginViewModel

    @Inject lateinit var authUseCase: AuthUseCaseImpl

    @Before
    fun setUp() {}

    @Test
    fun `checking the call to auth use case called`(){
        val user = "randomEmail@gmail.com"
        val pass = "123456789"
        val params = AuthUseCase.Params(user, pass)

        loginViewModel.logInWithCredentials(params)

        verify { authUseCase.perform(params) }
    }
}