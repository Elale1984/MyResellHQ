package com.heady.myresellhq.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.heady.myresellhq.MainActivity
import com.heady.myresellhq.R
import com.heady.myresellhq.databinding.ActivityRegisterBinding

class Register : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSignUp.setOnClickListener {
            Toast.makeText(this, "Sign Up Clicked", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, LogIn::class.java)
            startActivity(intent)
        }

        binding.tbtnSignIn.setOnClickListener {
            Toast.makeText(this, "Sign In Clicked", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, LogIn::class.java)
            startActivity(intent)
        }
    }
}