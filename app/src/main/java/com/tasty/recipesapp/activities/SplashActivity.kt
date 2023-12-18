package com.tasty.recipesapp.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.tasty.recipesapp.databinding.ActivitySplashBinding // Import the generated binding class
import com.tasty.recipesapp.R
import com.tasty.recipesapp.activities.MainActivity
import com.tasty.recipesapp.providers.RepositoryProvider

// SplashActivity responsible for showing the splash screen
class SplashActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "SplashActivity"
    }

    private val SPLASH_TIME_OUT: Long = 2000 // 2 seconds

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash) // Set the layout for the splash screen

        val handler = Handler()
        handler.postDelayed(
            {
                // Navigate to MainActivity after the delay
                val intent = Intent(this@SplashActivity, MainActivity::class.java)
                startActivity(intent)
                finish() // Finish the current activity to prevent coming back to the splash screen on back press
            },
            SPLASH_TIME_OUT // Delay for the splash screen
        )
    }
}
