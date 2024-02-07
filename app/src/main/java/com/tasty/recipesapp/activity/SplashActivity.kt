package com.tasty.recipesapp.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.navigation.NavigationBarView
import com.tasty.recipesapp.R
import com.tasty.recipesapp.repository.RecipeRepository
import com.tasty.recipesapp.repository.recipe.FavoriteDatabase
import com.tasty.recipesapp.repository.recipe.RecipeDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "SplashActivity"
    }

    private val SPLASH_TIME_OUT: Long = 2000 // 2 seconds
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Log.d(TAG, "onCreate: SplashActivity created.")
        GlobalScope.launch(Dispatchers.Main) {
            val db = RecipeDatabase.getDatabase(this@SplashActivity) // Get database instance
            val favdb= FavoriteDatabase.getDatabase(this@SplashActivity)
            RecipeRepository.initialize(db,favdb) // Initialize database with open database instance
            Log.d(TAG, "Database initialized successfully.")
        }

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