package com.example.pictureupload.data

import com.example.pictureupload.domain.AuthResult
import kotlinx.coroutines.flow.Flow

interface AuthProvider {

    fun signInWithMailAndPassword(mail: String, password: String): Flow<AuthResult>
    fun signUpWithMailAndPassword(mail: String, password: String): Flow<AuthResult>
}
