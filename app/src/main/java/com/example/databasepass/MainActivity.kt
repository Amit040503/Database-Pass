package com.example.databasepass

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val signButton=findViewById<Button>(R.id.btnSignUp)
        val etName=findViewById<TextInputEditText>(R.id.etName)
        val etEmail=findViewById<TextInputEditText>(R.id.etEmail)
        val etUserName=findViewById<TextInputEditText>(R.id.etUserName)
        val etPassword=findViewById<TextInputEditText>(R.id.etPassword)

        signButton.setOnClickListener {

            val name = etName.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val userName = etUserName.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (name.isEmpty() || email.isEmpty() || userName.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val user = User(name, email, password, userName)

            database = FirebaseDatabase.getInstance().getReference("Users")
            database.child(userName).setValue(user).addOnSuccessListener {
                etEmail.text?.clear()
                etName.text?.clear()
                etPassword.text?.clear()
                etUserName.text?.clear()

                Toast.makeText(this, "User Registered", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(this, "Failed to register", Toast.LENGTH_SHORT).show()
            }
        }


        val signInText=findViewById<TextView>(R.id.tvSignIn)
        signInText.setOnClickListener {
            val openSignInActivity= Intent(this, SignInActivity::class.java)
            startActivity(openSignInActivity)
        }


    }
}