package com.mutualmobile.photosclone

object ValidationChecker {

    fun validateLoginAuthParams(mail: String, pass: String): ValidationResult {
        if (mail.isEmpty() && pass.isEmpty())
            return ValidationResult.Failed("Please enter valid email and password")
        if (mail.isEmpty())
            return ValidationResult.Failed("Please enter a valid email")
        if (pass.isEmpty())
            return ValidationResult.Failed("Please enter a valid password")

        return ValidationResult.Success
    }

    fun validateRegisterAuthParams(mail: String, passOrg: String, passConf: String): ValidationResult {
        if (mail.isEmpty() && passConf.isEmpty() && passOrg.isEmpty())
            return ValidationResult.Failed("Please enter a valid set of input")
        if (mail.isEmpty())
            return ValidationResult.Failed("Please enter a valid email")
        if (passConf.isEmpty() || passOrg.isEmpty())
            return ValidationResult.Failed("Please enter valid passwords")
        if (passConf != passOrg)
            return ValidationResult.Failed("Please enter same password to confirm")

        return ValidationResult.Success
    }
}

sealed class ValidationResult {
    object Success : ValidationResult()
    class Failed(val msg: String) : ValidationResult()
}
