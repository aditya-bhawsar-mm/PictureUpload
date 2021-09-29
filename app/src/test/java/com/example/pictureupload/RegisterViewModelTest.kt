package com.example.pictureupload

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.pictureupload.usecases.AuthUseCase
import io.mockk.mockk
import io.mockk.verify
import junit.framework.TestCase
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After

import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class RegisterViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val coroutineDispatcher = TestCoroutineDispatcher()

    lateinit var authUseCase: AuthUseCase
    lateinit var registerViewModel: RegisterViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        authUseCase = mockk(relaxed = true)
        registerViewModel = RegisterViewModel(authUseCase)
    }

    @Test
    fun `checking the call to auth use case`() {
        val user = "randomEmail@reddifmail.com"
        val passOrg = "123456789"
        val passConf = "123456789"
        val params = AuthUseCase.Params(user, passOrg)

        registerViewModel.validateInputAndRegister(user, passOrg, passConf)

        verify { authUseCase.performSignUp(params) }
    }

    @Test
    fun `checking call to resetViewState makes changes to ViewState`() {
        registerViewModel.resetViewState()
        val state = registerViewModel.viewState.value

        TestCase.assertTrue(
            "Current State after call to reset state is Idle",
            state is RegisterViewState.Idle
        )
    }

    @Test
    fun `checking viewState return on not matching Passwords`(){
        val mail= "random@gmail.com"
        val passOrg = "android@1"
        val passConf = "ios@12345"

        registerViewModel.validateInputAndRegister(mail, passOrg, passConf)
        val state = registerViewModel.viewState.value

        assertTrue(
            "checking change of state for not matching passwords", state is RegisterViewState.Error
        )
    }

    @Test
    fun `checking viewState returns on empty Passwords`(){
        val mail= "random@gmail.com"
        val passOrg = ""
        val passConf = ""

        registerViewModel.validateInputAndRegister(mail, passOrg, passConf)
        val state = registerViewModel.viewState.value

        assertTrue(
            "checking change of state for empty password", state is RegisterViewState.Error
        )
    }

    @Test
    fun `checking viewState returns on empty Email`(){
        val mail= ""
        val passOrg = "android@1"
        val passConf = "android@1"

        registerViewModel.validateInputAndRegister(mail, passOrg, passConf)
        val state = registerViewModel.viewState.value

        assertTrue(
            "checking change of state with empty mail", state is RegisterViewState.Error
        )
    }

    @Test
    fun `checking viewState returns on empty input`(){
        val mail= ""
        val passOrg = ""
        val passConf = ""

        registerViewModel.validateInputAndRegister(mail, passOrg, passConf)
        val state = registerViewModel.viewState.value

        assertTrue(
            "checking change of state for no input", state is RegisterViewState.Error
        )
    }

    @Test
    fun `checking viewState returns on correct input`(){
        val mail= "random@gmail.com"
        val passOrg = "android@1"
        val passConf = "android@1"

        registerViewModel.validateInputAndRegister(mail, passOrg, passConf)
        val state = registerViewModel.viewState.value

        assertTrue(
            "checking change of state for correct input", state is RegisterViewState.Loading
        )
    }

    @After
    fun tearDown(){
        Dispatchers.resetMain()
    }
}