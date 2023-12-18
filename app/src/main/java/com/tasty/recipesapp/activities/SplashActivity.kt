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
    companion object {
        private const val TAG = "SplashActivity"
    }
    private val SPLASH_TIME_OUT: Long = 2000 // 2 seconds
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val handler = Handler()
        handler.postDelayed(
            {
                // Navigate to MainActivity after the delay
                val intent = Intent(this@SplashActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            },
            SPLASH_TIME_OUT
        )
    }


}