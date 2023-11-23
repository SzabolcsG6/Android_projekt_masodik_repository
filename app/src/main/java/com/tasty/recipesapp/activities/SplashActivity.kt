package com.tasty.recipesapp.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.tasty.recipesapp.databinding.ActivitySplashBinding // Import the generated binding class
import com.tasty.recipesapp.R

class SplashActivity : AppCompatActivity() {
    private val tag = "com.tasty.recipesapp.utils.activities.SplashActivity"
    private val splashTimeout = 2000 // 2000 milliseconds (2 seconds)
    private lateinit var binding: ActivitySplashBinding // Declare binding variable

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivitySplashBinding.inflate(layoutInflater) // Initialize binding
//        setContentView(binding.root)
//        Log.d(tag, "onCreate: com.tasty.recipesapp.utils.activities.SplashActivity created.")

override fun onCreate(savedInstanceState: Bundle?) {
    binding = ActivitySplashBinding.inflate(layoutInflater)
    setContentView(binding.root)
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_splash)
    binding.button.setOnClickListener {
        //val message = binding.editText.text.toString() // Retrieve text from EditText
        val intent = Intent(this@SplashActivity, MainActivity::class.java)
        //intent.putExtra("message", message) // Pass the text as an extra
        startActivity(intent) // Start MainActivity
        finish() // Finish SplashActivity if needed
    }

        val handlerThread = HandlerThread("SplashHandlerThread", -10)
        handlerThread.start()
        val handler = Handler(handlerThread.looper)

        handler.post {
            loadData()
        }

        handler.postDelayed({
            // Navigate to MainActivity after splash timeout
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 2000)
    }

    private fun loadData() {
        // This method is executed on the background thread
        Log.d(tag, "loadData: Loading data in the background...")
        // Your data loading logic goes here
    }

    private fun navigateToMainActivity(message: String) {
        val intent = Intent(this@SplashActivity, MainActivity::class.java)
        intent.putExtra("message", message)
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
