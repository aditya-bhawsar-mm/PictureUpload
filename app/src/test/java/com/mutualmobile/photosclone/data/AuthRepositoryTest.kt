package com.mutualmobile.photosclone.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AuthRepositoryTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    lateinit var authRepository: AuthRepository
    lateinit var authProvider: AuthProvider

    @Before
    fun setUp() {
        authProvider = mockk(relaxed = true)
        authRepository = AuthRepository(authProvider)
    }

    @Test
    fun `check the call from authRepo to authProvider for Login`() {
        val user = "random@gmail.com"
        val pass = "123456789"

        authRepository.signInWithPassword(user, pass)

        verify { authProvider.signInWithMailAndPassword(user, pass) }
    }

    @Test
    fun `check the call from authRepo to authProvider for signUp`() {
        val user = "android@gmail.com"
        val pass = "1234567890"

        authRepository.signUpWithPassword(user, pass)

        verify { authProvider.signUpWithMailAndPassword(user, pass) }
    }
}
