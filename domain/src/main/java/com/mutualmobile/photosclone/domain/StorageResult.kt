package com.mutualmobile.photosclone.domain

sealed class StorageResult {
    object Success : StorageResult()
    object Loading : StorageResult()
    object Complete : StorageResult()
    class Failure(val msg: String) : StorageResult()
}
