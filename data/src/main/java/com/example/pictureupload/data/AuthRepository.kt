package com.example.pictureupload.data

class AuthRepository (private val authProvider: AuthProvider){

    fun signInWithPassword(mail:String, pass:String)
        = authProvider.signInWithMailAndPassword(mail,pass)

}