package com.example.pictureupload

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
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

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    lateinit var authUseCase: AuthUseCase
    lateinit var loginViewModel :LoginViewModel

    @Before
    fun setUp() {
        authUseCase = mockk()
        loginViewModel = LoginViewModel(authUseCase)
    }

    @Test
    fun `checking call to auth use case called`(){
        val user = "randomEmail@gmail.com"
        val pass = "123456789"
        val params = AuthUseCase.Params(user, pass)

        loginViewModel.validateInputAndLogIn(user, pass)

        verify { authUseCase.perform(params) }
    }

    @Test
    fun `checking what validate returns on empty password`(){
        val user = "randomEmail@gmail.com"
        val pass = ""
        val params = AuthUseCase.Params(user, pass)

        val validation = loginViewModel.validateInput(params)

        assertEquals(false, validation)
    }

    @Test
    fun `checking what validate returns on empty mail`(){
        val user = ""
        val pass = "123456789"
        val params = AuthUseCase.Params(user, pass)

        val validation = loginViewModel.validateInput(params)

        assertEquals(false, validation)
    }

    @Test
    fun `checking what validate returns on empty input`(){
        val user = ""
        val pass = ""
        val params = AuthUseCase.Params(user, pass)

        val validation = loginViewModel.validateInput(params)

        assertEquals(false, validation)
    }

    @Test
    fun `checking what validate returns on correct input`(){
        val user = "random@gmail.com"
        val pass = "123456789"
        val params = AuthUseCase.Params(user, pass)

        val validation = loginViewModel.validateInput(params)

        assertEquals(true, validation)
    }
}