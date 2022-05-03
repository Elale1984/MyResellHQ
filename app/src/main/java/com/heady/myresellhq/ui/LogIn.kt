package com.heady.myresellhq.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.heady.myresellhq.MainActivity
import com.heady.myresellhq.databinding.ActivityLogInBinding

class LogIn : AppCompatActivity() {



    private lateinit var mAuth: FirebaseAuth


    private lateinit var binding: ActivityLogInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mAuth = FirebaseAuth.getInstance()

        // user logs in
        binding.btnSignIn.setOnClickListener {
            login()
        }

        // user navigated to registration page
        binding.tbtnSignUp.setOnClickListener {
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }

    }

    // checking to see if the user is still logged in
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = mAuth.currentUser
        if(currentUser != null){
            Toast.makeText(this, "Already Signed In", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }


    // Login function uses the firebase auth checking method for validation
    private fun login() {
        val email = binding.etEmail.text
        val password = binding.etMagicWord.text
        mAuth.signInWithEmailAndPassword(email.toString(),password.toString()).addOnCompleteListener { task ->
            if(task.isSuccessful){
                val intent= Intent(this,MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }.addOnFailureListener { exception ->
            Toast.makeText(applicationContext,exception.localizedMessage, Toast.LENGTH_LONG).show()
        }

    }

}

