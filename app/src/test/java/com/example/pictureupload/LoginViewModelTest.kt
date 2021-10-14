package com.example.pictureupload

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.pictureupload.usecases.AuthUseCase
import io.mockk.mockk
import io.mockk.verify
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class LoginViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val coroutineDispatcher = TestCoroutineDispatcher()

    lateinit var authUseCase: AuthUseCase
    lateinit var loginViewModel: LoginViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        authUseCase = mockk(relaxed = true)
        loginViewModel = LoginViewModel(authUseCase)
    }

    @Test
    fun `checking call to auth use case called`() = coroutineDispatcher.runBlockingTest {
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
    fun `checking what viewState returns on correct input`() = coroutineDispatcher.runBlockingTest {
        val user = "random@gmail.com"
        val pass = "123456789"

        loginViewModel.validateInputAndLogIn(user, pass)
        val state = loginViewModel.viewState.value

        assertTrue(
            "correct password and mail state change in vm",
            state is LoginViewState.Loading
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}
