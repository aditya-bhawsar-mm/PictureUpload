package com.example.pictureupload.domain

sealed class StorageResult {
    object Success : StorageResult()
    object Loading : StorageResult()
    class Failure(val msg: String) : StorageResult()
}
