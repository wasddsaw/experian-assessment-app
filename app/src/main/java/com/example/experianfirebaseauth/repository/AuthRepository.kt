package com.example.experianfirebaseauth.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.experianfirebaseauth.model.User
import com.google.firebase.auth.FirebaseAuth

class AuthRepository(private val firebaseAuth: FirebaseAuth) {

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
//        val user = User(email, password)
//
//        if (!user.isEmailValid()) {
//            _loginStatus.value = "Invalid email format"
//        } else if (!user.isPasswordValid()) {
//            _loginStatus.value = "Password should be at least 6 characters"
//        } else {
//            firebaseAuth.signInWithEmailAndPassword(email, password)
//                .addOnCompleteListener { task ->
//                    if (task.isSuccessful) {
//                        _loginStatus.value = "Login Successful"
//                    } else {
//                        _loginStatus.value = task.exception?.message ?: "Login Failed"
//                    }
//                }
//        }
    }
}