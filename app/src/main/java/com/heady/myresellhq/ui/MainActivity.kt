package com.heady.myresellhq.ui

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.heady.myresellhq.R
import com.heady.myresellhq.databinding.ActivityMainBinding
import com.heady.myresellhq.ui.fragments.*

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
        mAuth.currentUser?.uid
        databaseReference = FirebaseDatabase.getInstance().getReference("Users")


        binding.navView.itemIconTintList = null

        toggle = ActionBarDrawerToggle(this, binding.drawerLayout, R.string.open, R.string.close)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        val dashboardUIFragment = DashboardUI()
        val notificationsUIFragment = NotificationsUI()
        val inventoryUIFragment = InventoryUI()
        val statsUIFragment = StatsUI()
        val settingsUIFragment = SettingsUI()
        val aboutUIFragment = AboutUI()

        // set initial fragment to container
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.currentFragment, dashboardUIFragment)
            commit()
        }


        binding.navView.setNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.menu_dashboard ->
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.currentFragment, dashboardUIFragment)
                        commit()
                    }
                R.id.menu_notifications ->

                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.currentFragment, notificationsUIFragment)
                        commit()
                    }
                R.id.menu_stats ->

                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.currentFragment, statsUIFragment)
                        commit()
                    }
                R.id.menu_inventory ->

                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.currentFragment, inventoryUIFragment)
                        commit()
                    }
                R.id.menu_settings ->

                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.currentFragment, settingsUIFragment)
                        commit()
                    }
                R.id.menu_about ->

                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.currentFragment, aboutUIFragment)
                        commit()
                    }
                R.id.menu_signOut -> {
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