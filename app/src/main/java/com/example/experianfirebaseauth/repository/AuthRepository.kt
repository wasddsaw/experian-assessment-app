package com.example.experianfirebaseauth.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth

class AuthRepository {

    private val firebaseAuth = FirebaseAuth.getInstance()

    private val _loginStatus = MutableLiveData<String>()
    val loginStatus: LiveData<String> = _loginStatus

    fun login(email: String, password: String) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _loginStatus.value = "Login Successful"
                } else {
                    _loginStatus.value = task.exception?.message ?: "Login Failed"
                }
            }
    }
}