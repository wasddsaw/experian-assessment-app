package com.example.experianfirebaseauth

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.experianfirebaseauth.viewmodel.AuthenticateUser
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private val authenticateUser: AuthenticateUser by viewModels()

    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()

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
        }
    }
}