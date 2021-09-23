package com.example.pictureupload.usecases

import com.example.pictureupload.data.AuthRepository
import com.example.pictureupload.domain.AuthResult
import kotlinx.coroutines.flow.Flow

interface AuthUseCase{
    data class Params(
        var user: String,
        var pass: String
    )

    fun performSignIn(params: Params): Flow<AuthResult>
}

class AuthUseCaseImpl(private val authRepository: AuthRepository): AuthUseCase {

    override fun performSignIn(params: AuthUseCase.Params): Flow<AuthResult> {
        return authRepository.signInWithPassword(params.user, params.pass)
    }

}