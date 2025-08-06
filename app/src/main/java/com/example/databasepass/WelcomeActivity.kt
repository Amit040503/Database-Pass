package com.example.databasepass

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_welcome)
        val email = intent.getStringExtra(SignInActivity.KEY1)
        val name = intent.getStringExtra(SignInActivity.KEY2)
        val userName = intent.getStringExtra(SignInActivity.KEY3)

        val welcomeText = findViewById<TextView>(R.id.tvWelcome)
        val emailText = findViewById<TextView>(R.id.tvMail)
        val userNameText = findViewById<TextView>(R.id.tvUserName)

        welcomeText.text = "Welcome $name"
        emailText.text = "Mail : $email"
        userNameText.text = "Username : $userName"

    }
}
