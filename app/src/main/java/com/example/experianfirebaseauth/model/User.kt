package com.example.experianfirebaseauth.model

data class User(val email: String, val password: String) {
    fun isEmailValid(): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isPasswordValid(): Boolean {
        return password.length >= 6
    }
}