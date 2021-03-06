package com.mutualmobile.photosclone.framework.firebase

import com.mutualmobile.photosclone.data.AuthProvider
import com.mutualmobile.photosclone.domain.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

@ExperimentalCoroutinesApi
class AuthProviderImpl(private val auth: FirebaseAuth) : AuthProvider {

    override fun isUserLoggedIn(): Boolean {
        return auth.currentUser != null
    }

    override fun getLoggedUserUID(): String? {
        return if (isUserLoggedIn()) auth.currentUser!!.uid
        else null
    }

    override fun signInWithMailAndPassword(mail: String, password: String): Flow<AuthResult> =
        callbackFlow {

            trySend(AuthResult.Loading)

            auth.signInWithEmailAndPassword(mail, password)
                .addOnSuccessListener { trySend(AuthResult.Success) }
                .addOnFailureListener {
                    trySend(
                        AuthResult.Failure(
                            it.message ?: "Something Went Wrong"
                        )
                    )
                }

            awaitClose {}
        }

    override fun signUpWithMailAndPassword(mail: String, password: String): Flow<AuthResult> =
        callbackFlow {

            trySend(AuthResult.Loading)

            auth.createUserWithEmailAndPassword(mail, password)
                .addOnSuccessListener { trySend(AuthResult.Success) }
                .addOnFailureListener {
                    trySend(
                        AuthResult.Failure(
                            it.message ?: "Something Went Wrong"
                        )
                    )
                }

            awaitClose {}
        }
}
