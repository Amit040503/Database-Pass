package com.example.databasepass

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.Job

class SignInActivity : AppCompatActivity() {

    lateinit var databaseReference: DatabaseReference
    companion object{
        const val KEY1="com.example.databasepass.SignInActivity.email"
        const val KEY2="com.example.databasepass.SignInActivity.name"
        const val KEY3="com.example.databasepass.SignInActivity.userName"


    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_in)

        val signInButton=findViewById<Button>(R.id.btnSignIn)
        val userName=findViewById<TextInputEditText>(R.id.etUserName)

        signInButton.setOnClickListener {
            //Taken ref till node "Users"
            val userNameString = userName.text.toString()

            if(userNameString.isNotEmpty()){
                readData(userNameString)
            }else{
                Toast.makeText(this,"Please enter User name", Toast.LENGTH_SHORT).show()
            }
        }

    } // onCreate Method over
   private fun readData(userName: String) {
       databaseReference = FirebaseDatabase.getInstance().getReference("Users")
       databaseReference.child(userName).get().addOnSuccessListener {
           // if user exist or not
           if (it.exists()) {
               val email = it.child("email").value
               val name = it.child("name").value
               val userName = it.child("userName").value

               Toast.makeText(this, "Found: $name", Toast.LENGTH_SHORT).show()

               val intentWelcome = Intent(this, WelcomeActivity::class.java)
               intentWelcome.putExtra(KEY1, email.toString())
               intentWelcome.putExtra(KEY2, name.toString())
               intentWelcome.putExtra(KEY3, userName.toString())
               startActivity(intentWelcome)
           } else {
               Toast.makeText(this, "User not found in DB", Toast.LENGTH_SHORT).show()
           }

       }

   }
}