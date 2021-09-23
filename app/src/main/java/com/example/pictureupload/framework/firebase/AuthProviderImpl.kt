package com.example.pictureupload.framework.firebase

import com.example.pictureupload.data.AuthProvider
import com.example.pictureupload.domain.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow

class AuthProviderImpl(): AuthProvider {

    private val auth by lazy { FirebaseAuth.getInstance() }

    override fun signInWithMailAndPassword(mail: String, password: String) = callbackFlow{

        trySend(AuthResult.Loading)

        auth.signInWithEmailAndPassword(mail, password)
            .addOnSuccessListener { trySend(AuthResult.Success) }
            .addOnFailureListener { trySend(AuthResult.Failure(it.message?:"Something Went Wrong"))}

        awaitClose {}
    }

}