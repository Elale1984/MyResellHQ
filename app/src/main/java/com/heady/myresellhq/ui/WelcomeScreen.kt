package com.heady.myresellhq.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.heady.myresellhq.R
import com.heady.myresellhq.databinding.ActivityMainBinding
import com.heady.myresellhq.databinding.ActivityWelcomeScreenBinding

class WelcomeScreen : AppCompatActivity() {

    private lateinit var binding:ActivityWelcomeScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnLogin.setOnClickListener {
            Toast.makeText(this, "Sign In Clicked", Toast.LENGTH_SHORT).show()
        }

        binding.btnRegister.setOnClickListener {
            Toast.makeText(this, "Sign Up Clicked", Toast.LENGTH_SHORT).show()
        }


    }
}