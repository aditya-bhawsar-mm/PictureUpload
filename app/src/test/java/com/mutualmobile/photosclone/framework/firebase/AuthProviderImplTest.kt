package com.mutualmobile.photosclone.framework.firebase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mutualmobile.photosclone.domain.AuthResult
import com.google.firebase.auth.FirebaseAuth
import io.mockk.mockk
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class AuthProviderImplTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val coroutineDispatcher = TestCoroutineDispatcher()

    lateinit var authProviderImpl: AuthProviderImpl
    lateinit var auth: FirebaseAuth

    @Before
    fun setUp() {
        auth = mockk(relaxed = true)
        authProviderImpl = AuthProviderImpl(auth)
    }

    @Test
    fun `check flow returned from the method for logIn`() = coroutineDispatcher.runBlockingTest {
        val user = "random@gmail.com"
        val pass = "123456789"

        val flow = authProviderImpl.signInWithMailAndPassword(user, pass).take(1).first()

        assertTrue(
            "determining first value for the flow logIn",
            flow is AuthResult.Loading
        )
    }

    @Test
    fun `check flow returned from the method signUp`() = coroutineDispatcher.runBlockingTest {
        val user = "random@gmail.com"
        val pass = "123456789"

        val flow = authProviderImpl.signUpWithMailAndPassword(user, pass).take(1).first()

        assertTrue(
            "determining first value for flow while signUp",
            flow is AuthResult.Loading
        )
    }
}
