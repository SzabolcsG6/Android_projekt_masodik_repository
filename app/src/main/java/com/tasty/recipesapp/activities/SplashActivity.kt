package com.tasty.recipesapp.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.tasty.recipesapp.databinding.ActivitySplashBinding // Import the generated binding class
import com.tasty.recipesapp.R
import com.tasty.recipesapp.activities.MainActivity

// SplashActivity responsible for showing the splash screen
@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "SplashActivity"
    }

    private val SPLASH_TIME_OUT: Long = 2000 // 2 seconds
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
//        Log.d(TAG, "onCreate: SplashActivity created.")
//        val binding = ActivitySplashBinding.inflate(layoutInflater)
//        setContentView(binding.root)

        // Use a HandlerThread to create a background thread
//        val handlerThread = HandlerThread("SplashHandlerThread", -10)
//        handlerThread.start() // Create a Handler on the new HandlerThread
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
