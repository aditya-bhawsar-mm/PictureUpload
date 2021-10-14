package com.mutualmobile.photosclone.data

import com.mutualmobile.photosclone.domain.StorageResult
import kotlinx.coroutines.flow.Flow

interface StorageProvider {

    fun createReferenceToUser(uid: String)
    suspend fun uploadFile(bytes: ByteArray): Flow<StorageResult>
}
