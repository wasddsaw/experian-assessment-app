package com.example.experianfirebaseauth.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.experianfirebaseauth.model.User
import com.example.experianfirebaseauth.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth

class AuthenticateUser : ViewModel() {

    private val firebaseAuth = FirebaseAuth.getInstance()
    private val authRepository = AuthRepository(firebaseAuth)
    val loginStatus: LiveData<String> = authRepository.loginStatus

    fun login(email: String, password: String) {
        authRepository.login(email, password)
    }
}

