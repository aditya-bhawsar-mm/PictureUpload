package com.mutualmobile.photosclone.domain

sealed class AuthResult {
    object Success : AuthResult()
    object Loading : AuthResult()
    class Failure(val msg: String) : AuthResult()
}
