package com.example.pictureupload.data

import com.example.pictureupload.domain.StorageResult
import kotlinx.coroutines.flow.Flow

interface StorageProvider {

    fun createReferenceToUser(uid: String)
    fun uploadFile(bytes: ByteArray): Flow<StorageResult>

}