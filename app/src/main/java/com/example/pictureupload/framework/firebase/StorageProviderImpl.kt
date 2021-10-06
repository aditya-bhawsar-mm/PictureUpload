package com.example.pictureupload.framework.firebase

import com.example.pictureupload.data.StorageProvider
import com.example.pictureupload.domain.StorageResult
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.lang.Exception
import java.util.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class StorageProviderImpl(private val storage: FirebaseStorage): StorageProvider {

    lateinit var ref: StorageReference

    override fun createReferenceToUser(uid: String) {
        ref = storage.reference.child(uid)
    }

    override suspend fun uploadFile(bytes: ByteArray): Flow<StorageResult> = callbackFlow {
        trySend(StorageResult.Loading)

        try {
            val uid = UUID.randomUUID().toString()
            val picRef = ref.child("$uid.jpeg")

            picRef.putBytes(bytes)
                .addOnSuccessListener {
                    trySend(StorageResult.Success)
                    CoroutineScope(Dispatchers.IO).launch {
                        delay(200)
                        trySend(StorageResult.Complete)
                    }
                }
                .addOnFailureListener { error ->
                    trySend(StorageResult.Failure(error.message?:"Something Went Wrong"))
                    CoroutineScope(Dispatchers.IO).launch {
                        delay(200)
                        trySend(StorageResult.Complete)
                    }
                }


        }catch (e: Exception){
            trySend(StorageResult.Failure(e.message?:"Something went Wrong"))
            CoroutineScope(Dispatchers.IO).launch {
                delay(200)
                trySend(StorageResult.Complete)
            }
        }

        awaitClose {}
    }
}