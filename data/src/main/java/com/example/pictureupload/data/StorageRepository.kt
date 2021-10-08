package com.example.pictureupload.data

class StorageRepository(private val storageProvider: StorageProvider) {

    fun createStorageRef(uid: String) =
        storageProvider.createReferenceToUser(uid)

    suspend fun uploadFile(bytes: ByteArray) =
        storageProvider.uploadFile(bytes)

}