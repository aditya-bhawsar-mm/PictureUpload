package com.example.pictureupload.usecases

interface AuthUseCase{
    data class Params(
        var user: String,
        var pass: String
    )

    fun perform(params: Params)
}

class AuthUseCaseImpl: AuthUseCase {

    override fun perform(params: AuthUseCase.Params) {

    }

}