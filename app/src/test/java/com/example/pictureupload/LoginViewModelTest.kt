package com.example.pictureupload

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.pictureupload.usecases.AuthUseCase
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class LoginViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    lateinit var authUseCase: AuthUseCase
    lateinit var loginViewModel: LoginViewModel

    @Before
    fun setUp() {
        authUseCase = mockk(relaxed = true)
        loginViewModel = LoginViewModel(authUseCase)
    }

    @Test
    fun `checking call to auth use case called`() {
        val user = "randomEmail@reddifmail.com"
        val pass = "123456789"
        val params = AuthUseCase.Params(user, pass)

        loginViewModel.validateInputAndLogIn(user, pass)

        verify { authUseCase.performSignIn(params) }
    }

    @Test
    fun `checking call to resetViewState makes changes to ViewState`() {
        loginViewModel.resetViewState()
        val state = loginViewModel.viewState.value

        assertTrue(
            "Current State after call to reset state is Idle",
            state is LoginViewState.Idle
        )
    }

    @Test
    fun `checking what viewState returns on empty password`() {
        val user = "randomEmail@ymail.com"
        val pass = ""

        loginViewModel.validateInputAndLogIn(user, pass)
        val state = loginViewModel.viewState.value

        assertTrue(
            "Empty password state change in vm",
            state is LoginViewState.Error
        )
    }

    @Test
    fun `checking what viewState returns on empty mail`() {
        val user = ""
        val pass = "123456789"

        loginViewModel.validateInputAndLogIn(user, pass)
        val state = loginViewModel.viewState.value

        assertTrue(
            "Empty mail state change in vm",
            state is LoginViewState.Error
        )
    }

    @Test
    fun `checking what viewState returns on empty input`() {
        val user = ""
        val pass = ""

        loginViewModel.validateInputAndLogIn(user, pass)
        val state = loginViewModel.viewState.value

        assertTrue(
            "Empty password and mail state change in vm",
            state is LoginViewState.Error
        )
    }

    @Test
    fun `checking what viewState returns on correct input`() {

        val user = "random@gmail.com"
        val pass = "123456789"

        loginViewModel.validateInputAndLogIn(user, pass)
        val state = loginViewModel.viewState.value

        assertTrue(
            "correct password and mail state change in vm",
            state is LoginViewState.Loading
        )
    }
}
