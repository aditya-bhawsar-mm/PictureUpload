package com.example.pictureupload

import com.example.pictureupload.usecases.AuthUseCase

object ValidationChecker {

    fun validateLoginAuthParams(params: AuthUseCase.Params): ValidationResult {
        if (params.user.isEmpty() && params.pass.isEmpty())
            return ValidationResult.Failed("Please enter valid email and password")
        if (params.user.isEmpty())
            return ValidationResult.Failed("Please enter a valid email")
        if (params.pass.isEmpty())
            return ValidationResult.Failed("Please enter a valid password")

        return ValidationResult.Success
    }

    fun validateRegisterAuthParams(): ValidationResult {
        return ValidationResult.Success
    }
}

sealed class ValidationResult {
    object Success : ValidationResult()
    class Failed(val msg: String) : ValidationResult()
}
