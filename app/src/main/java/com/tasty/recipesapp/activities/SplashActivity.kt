package com.tasty.recipesapp.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.tasty.recipesapp.databinding.ActivitySplashBinding // Import the generated binding class
import com.tasty.recipesapp.R
import com.tasty.recipesapp.activities.MainActivity
import com.tasty.recipesapp.providers.RepositoryProvider

class SplashActivity : AppCompatActivity() {
    private val tag = "com.tasty.recipesapp.utils.activities.SplashActivity"
    private val splashTimeout = 2000 // 2000 milliseconds (2 seconds)
    private lateinit var binding: ActivitySplashBinding // Declare binding variable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        RepositoryProvider.initialize(this)
        // Simulate a delay before navigating to MainActivity
        Handler().postDelayed({
            navigateToMainActivity()
        }, splashTimeout.toLong())
    }

    private fun navigateToMainActivity() {
        val intent = Intent(this@SplashActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    // Additional lifecycle methods with logging for demonstration
    override fun onStart() {
        super.onStart()
        Log.d(tag, "onStart: com.tasty.recipesapp.utils.activities.SplashActivity started.")
    }

    override fun onResume() {
        super.onResume()
        Log.d(tag, "onResume: com.tasty.recipesapp.utils.activities.SplashActivity resumed.")
    }

    override fun onPause() {
        super.onPause()
        Log.d(tag, "onPause: com.tasty.recipesapp.utils.activities.SplashActivity paused.")
    }

    override fun onStop() {
        super.onStop()
        Log.d(tag, "onStop: com.tasty.recipesapp.utils.activities.SplashActivity stopped.")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(tag, "onDestroy: com.tasty.recipesapp.utils.activities.SplashActivity destroyed.")
    }
}
