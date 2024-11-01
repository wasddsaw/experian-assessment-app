package com.example.experianfirebaseauth.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.experianfirebaseauth.model.User
import com.example.experianfirebaseauth.repository.AuthRepository

class AuthenticateUser : ViewModel() {

    private val authRepository = AuthRepository()
    val loginStatus: LiveData<String> = authRepository.loginStatus

    fun login(email: String, password: String) {
        authRepository.login(email, password)
    }
}

