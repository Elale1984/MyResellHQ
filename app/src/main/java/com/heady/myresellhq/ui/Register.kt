package com.heady.myresellhq.ui

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.heady.myresellhq.R
import com.heady.myresellhq.data.User
import com.heady.myresellhq.databinding.ActivityRegisterBinding


class Register : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var databaseReference: DatabaseReference

    private lateinit var dialog: Dialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Firebase Stuff
        mAuth = FirebaseAuth.getInstance()
        databaseReference = FirebaseDatabase.getInstance().getReference("Users")


        binding.btnSignUp.setOnClickListener {
            showProgressBar()
            createAccount()
        }

        binding.tbtnSignIn.setOnClickListener {
            Toast.makeText(this, "Sign In Clicked", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, LogIn::class.java)
            startActivity(intent)
        }
    }

    private fun createAccount() {

        val email = binding.etEmail.text
        val password = binding.etMagicWord.text

        mAuth.createUserWithEmailAndPassword(email.toString(), password.toString())
            .addOnCompleteListener(
                this
            ) { task ->
                if (task.isSuccessful) {
                   createFirebaseProfile()

                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(
                        this, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }


    }

    private fun createFirebaseProfile() {

        mAuth = FirebaseAuth.getInstance()
        databaseReference = FirebaseDatabase.getInstance().getReference("Users")
        val myUid = mAuth.currentUser?.uid

        val fName = binding.etFName.text.toString()
        val lName = binding.etLName.text.toString()
        val emailAddress = binding.etEmail.text.toString()

        val user = User(fName,lName,emailAddress)

        if(myUid != null) {

            databaseReference.child(myUid).setValue(user).addOnCompleteListener {
                if(it.isSuccessful) {
                    hideProgressBar()
                    Toast.makeText(this, "Successfully Added User Profile ", Toast.LENGTH_SHORT).show()

                }
                else {
                    hideProgressBar()
                    Toast.makeText(this, "Failed To Added User Profile ", Toast.LENGTH_SHORT).show()

                }
            }
        }

    }

    private fun showProgressBar(){
        dialog = Dialog(this@Register)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_wait)
        dialog.setCanceledOnTouchOutside(false)
        dialog.show()
    }

    private fun hideProgressBar(){
        dialog.dismiss()
    }

}