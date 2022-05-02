package com.heady.myresellhq.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.heady.myresellhq.MainActivity
import com.heady.myresellhq.R
import com.heady.myresellhq.databinding.ActivityLogInBinding
import com.heady.myresellhq.databinding.ActivityWelcomeScreenBinding

class LogIn : AppCompatActivity() {

    private lateinit var binding: ActivityLogInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnSignIn.setOnClickListener {
            Toast.makeText(this, "Sign In Clicked", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.tbtnSignUp.setOnClickListener {
            Toast.makeText(this, "Sign Un Clicked", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }
    }


}