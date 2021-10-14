package com.example.pictureupload.data

import com.example.pictureupload.domain.AuthResult
import kotlinx.coroutines.flow.Flow

class AuthRepository(private val authProvider: AuthProvider) {

    fun signInWithPassword(mail: String, pass: String): Flow<AuthResult> =
        authProvider.signInWithMailAndPassword(mail, pass)

    fun signUpWithPassword(mail: String, pass: String): Flow<AuthResult> =
        authProvider.signUpWithMailAndPassword(mail, pass)

    fun isUserLoggedIn(): Boolean =
        authProvider.isUserLoggedIn()

    fun getUserUid(): String? =
        authProvider.getLoggedUserUID()
}
