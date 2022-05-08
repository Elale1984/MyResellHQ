package com.heady.myresellhq.ui

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.heady.myresellhq.R
import com.heady.myresellhq.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth

    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var binding: ActivityMainBinding
    private lateinit var databaseReference: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mAuth = FirebaseAuth.getInstance()
        val uid = mAuth.currentUser?.uid
        databaseReference = FirebaseDatabase.getInstance().getReference("Users")


        binding.navView.itemIconTintList = null

        toggle = ActionBarDrawerToggle(this, binding.drawerLayout, R.string.open, R.string.close)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)




        binding.navView.setNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.home -> Toast.makeText(applicationContext, "Clicked Home", Toast.LENGTH_SHORT).show()
                R.id.notifications -> Toast.makeText(applicationContext, "Clicked Notifications", Toast.LENGTH_SHORT).show()
                R.id.stats -> Toast.makeText(applicationContext, "Clicked Stats", Toast.LENGTH_SHORT).show()
                R.id.platforms -> Toast.makeText(applicationContext, "Clicked Platforms", Toast.LENGTH_SHORT).show()
                R.id.settings -> Toast.makeText(applicationContext, "Clicked Settings", Toast.LENGTH_SHORT).show()
                R.id.about -> Toast.makeText(applicationContext, "Clicked about", Toast.LENGTH_SHORT).show()
                R.id.logout -> {
                    Toast.makeText(applicationContext, "Goodbye", Toast.LENGTH_SHORT).show()
                    mAuth.signOut()
                    val logoutIntent = Intent(this, WelcomeScreen::class.java)
                    logoutIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(logoutIntent)
                }
            }
            true
        }
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(toggle.onOptionsItemSelected(item))
            return true
        return super.onOptionsItemSelected(item)
    }


}