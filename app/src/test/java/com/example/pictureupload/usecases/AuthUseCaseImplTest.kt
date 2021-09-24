package com.example.pictureupload.usecases

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.pictureupload.data.AuthProvider
import com.example.pictureupload.data.AuthRepository
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.*

import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AuthUseCaseImplTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    lateinit var authUseCaseImpl: AuthUseCaseImpl
    lateinit var authRepository: AuthRepository

    @Before
    fun setUp() {
        authRepository = mockk(relaxed = true)
        authUseCaseImpl = AuthUseCaseImpl(authRepository)
    }

    @Test
    fun `check call to authRepository from authUseCase`(){
        val user = "random@gmail.com"
        val pass = "123456789"
        val params = AuthUseCase.Params(user,pass)

        authUseCaseImpl.performSignIn(params)

        verify { authRepository.signInWithPassword(user, pass) }
    }

}