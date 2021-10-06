package com.example.pictureupload.framework.firebase

import com.example.pictureupload.data.StorageProvider
import com.example.pictureupload.domain.StorageResult
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.lang.Exception
import java.util.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

@ExperimentalCoroutinesApi
class StorageProviderImpl(private val storage: FirebaseStorage): StorageProvider {

    lateinit var ref: StorageReference

    override fun createReferenceToUser(uid: String) {
        ref = storage.reference.child(uid)
    }

    override fun uploadFile(bytes: ByteArray): Flow<StorageResult> = callbackFlow {
        trySend(StorageResult.Loading)

        try {
            val uid = UUID.randomUUID().toString()
            val picRef = ref.child("$uid.jpeg")

            picRef.putBytes(bytes)
                .addOnSuccessListener { trySend(StorageResult.Success) }
                .addOnFailureListener { error -> trySend(StorageResult.Failure(error.message?:"Something Went Wrong")) }


        }catch (e: Exception){
            trySend(StorageResult.Failure(e.message?:"Something went Wrong"))
        }

        awaitClose {}
    }
}