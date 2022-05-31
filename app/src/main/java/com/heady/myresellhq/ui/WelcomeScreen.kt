package com.heady.myresellhq.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import com.heady.myresellhq.databinding.ActivityWelcomeScreenBinding


class WelcomeScreen : AppCompatActivity() {

    private lateinit var binding:ActivityWelcomeScreenBinding

    private lateinit var authListener: AuthStateListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        authListener = AuthStateListener {
            val user = FirebaseAuth.getInstance().currentUser
            if (user != null) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }


        binding.btnLogin.setOnClickListener {
            Toast.makeText(this, "Sign In Clicked", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, LogIn::class.java)
            startActivity(intent)
        }

        binding.btnRegister.setOnClickListener {

            Toast.makeText(this, "Sign Up Clicked", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }


    }
}