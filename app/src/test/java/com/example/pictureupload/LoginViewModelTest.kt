package com.example.pictureupload

import com.example.pictureupload.usecases.AuthUseCase
import com.example.pictureupload.usecases.AuthUseCaseImpl
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.*

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import kotlin.math.log

class LoginViewModelTest {

    lateinit var authUseCase: AuthUseCase
    lateinit var loginViewModel :LoginViewModel

    @Before
    fun setUp() {
        authUseCase = mockk<AuthUseCase>()
        loginViewModel = LoginViewModel(authUseCase)
    }

    @Test
    fun `checking the call to auth use case called`(){
        val user = "randomEmail@gmail.com"
        val pass = "123456789"
        val params = AuthUseCase.Params(user, pass)

        loginViewModel.validateInputAndLogIn(user, pass)

        verify { authUseCase.perform(params) }
    }

    @Test
    fun `checking what validate returns on input incorrect pass`(){
        val user = "randomEmail@gmail.com"
        val pass = ""
        val params = AuthUseCase.Params(user, pass)

        val validation = loginViewModel.validateInput(params)

        assertEquals(false, validation)
    }

    @Test
    fun `checking what validate returns on input incorrect mail`(){
        val user = ""
        val pass = "123456789"
        val params = AuthUseCase.Params(user, pass)

        val validation = loginViewModel.validateInput(params)

        assertEquals(false, validation)
    }

    @Test
    fun `checking what validate returns on input incorrect`(){
        val user = "random@gmail.com"
        val pass = "123456789"
        val params = AuthUseCase.Params(user, pass)

        val validation = loginViewModel.validateInput(params)

        assertEquals(true, validation)
    }

    @Test
    fun `checking what validate returns on input correct`(){
        val user = "random@gmail.com"
        val pass = "123456789"
        val params = AuthUseCase.Params(user, pass)

        val validation = loginViewModel.validateInput(params)

        assertEquals(true, validation)
    }
}