package com.example.experianfirebaseauth

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.experianfirebaseauth.viewmodel.AuthenticateUser
import com.example.experianfirebaseauth.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {

    private val loginViewModel: LoginViewModel by viewModels()
    private val authenticateUser: AuthenticateUser by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        val editTextEmail = findViewById<EditText>(R.id.editTextEmail)
        val editTextPassword = findViewById<EditText>(R.id.editTextPassword)
        val buttonLogin = findViewById<Button>(R.id.buttonLogin)
        val textViewStatus = findViewById<TextView>(R.id.textViewStatus)

        authenticateUser.loginStatus.observe(this) { status ->
            textViewStatus.text = status
        }

        buttonLogin.setOnClickListener {
            val email = editTextEmail.text.toString().trim()
            val password = editTextPassword.text.toString().trim()
            authenticateUser.login(email, password)

            // loginViewModel.login(email, password)
        }
    }
}