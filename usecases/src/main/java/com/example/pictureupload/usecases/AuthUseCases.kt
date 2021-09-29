package com.example.pictureupload.usecases

import com.example.pictureupload.data.AuthRepository
import com.example.pictureupload.domain.AuthResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

interface AuthUseCase {
    data class Params(
        var user: String,
        var pass: String
    )

    fun performSignIn(params: Params): Flow<AuthResult>

    fun performSignUp(params: Params): Flow<AuthResult>
}

class AuthUseCaseImpl(private val authRepository: AuthRepository) : AuthUseCase {

    override fun performSignIn(params: AuthUseCase.Params): Flow<AuthResult> {
        return authRepository.signInWithPassword(params.user, params.pass)
    }

    override fun performSignUp(params: AuthUseCase.Params): Flow<AuthResult> {
        return flowOf(AuthResult.Loading)
    }
}
