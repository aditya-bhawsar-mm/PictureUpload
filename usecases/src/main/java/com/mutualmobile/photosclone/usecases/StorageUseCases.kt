package com.mutualmobile.photosclone.usecases

import com.mutualmobile.photosclone.data.StorageRepository
import com.mutualmobile.photosclone.domain.StorageResult
import kotlinx.coroutines.flow.Flow

interface StorageUseCase {

    fun createStorageRef(uid: String)
    suspend fun uploadFile(bytes: ByteArray): Flow<StorageResult>
}

class StorageUseCaseImpl(private val storageRepo: StorageRepository) : StorageUseCase {

    override fun createStorageRef(uid: String) {
        storageRepo.createStorageRef(uid)
    }

    override suspend fun uploadFile(bytes: ByteArray): Flow<StorageResult> {
        return storageRepo.uploadFile(bytes)
    }
}
