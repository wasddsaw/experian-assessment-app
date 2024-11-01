package com.example.experianfirebaseauth.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.experianfirebaseauth.model.User

class LoginViewModel : ViewModel() {

    private val _loginStatus = MutableLiveData<String>()
    val loginStatus: LiveData<String> = _loginStatus

    fun login(email: String, password: String) {
        val user = User(email, password)

        if (!user.isEmailValid()) {
            _loginStatus.value = "Invalid email format"
        } else if (!user.isPasswordValid()) {
            _loginStatus.value = "Password should be at least 6 characters"
        } else {
            if (email == "test@example.com" && password == "password") {
                _loginStatus.value = "Login Successful"
            } else {
                _loginStatus.value = "Invalid email or password"
            }
        }
    }
}
