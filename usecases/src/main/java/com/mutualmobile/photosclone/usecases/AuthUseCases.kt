package com.mutualmobile.photosclone.usecases

import com.mutualmobile.photosclone.data.AuthRepository
import com.mutualmobile.photosclone.domain.AuthResult
import kotlinx.coroutines.flow.Flow

interface AuthUseCase {
    data class Params(
        var user: String,
        var pass: String
    )

    fun isUserLogged(): Boolean
    fun getUserUID(): String?
    fun performSignIn(params: Params): Flow<AuthResult>

    fun performSignUp(params: Params): Flow<AuthResult>
}

class AuthUseCaseImpl(private val authRepository: AuthRepository) : AuthUseCase {
    override fun isUserLogged(): Boolean {
        return authRepository.isUserLoggedIn()
    }

    override fun getUserUID(): String? {
        return authRepository.getUserUid()
    }

    override fun performSignIn(params: AuthUseCase.Params): Flow<AuthResult> {
        return authRepository.signInWithPassword(params.user, params.pass)
    }

    override fun performSignUp(params: AuthUseCase.Params): Flow<AuthResult> {
        return authRepository.signUpWithPassword(params.user, params.pass)
    }
}
