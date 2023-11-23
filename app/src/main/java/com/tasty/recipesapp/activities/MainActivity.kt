package com.tasty.recipesapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.tasty.recipesapp.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        navView.nextFocusUpId = R.id.bottomNavigationView

        //val navController = this.findNavController(R.id.myNavHostFragment)
      //  val navView: BottomNavigationView = binding.bottomNavigationView
        //navView.setupWithNavController(navController)

    }
    private fun goToSplashActivity() {
        val intent = Intent(this, SplashActivity::class.java)
        startActivity(intent)
        finish() // Optional: Finish MainActivity to prevent it from stacking upon SplashActivity
    }
    override fun onBackPressed() {
        super.onBackPressed()
        goToSplashActivity()
    }
}