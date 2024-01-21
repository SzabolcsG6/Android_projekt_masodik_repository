package com.tasty.recipesapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.google.android.material.navigation.NavigationBarView
import com.tasty.recipesapp.R
import com.tasty.recipesapp.databinding.ActivityMainBinding
import com.tasty.recipesapp.repository.RecipeRepository
import com.tasty.recipesapp.repository.RecipeRepository.getRecipesFromFile
import com.tasty.recipesapp.repository.RecipeRepository.initialize
import com.tasty.recipesapp.repository.recipe.RecipeDatabase
import com.tasty.recipesapp.repository.recipe.model.RecipeModel

class MainActivity : AppCompatActivity() {
    private lateinit var recipesList: List<RecipeModel> // Declare recipesList as an instance variable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        initialize(RecipeDatabase.getDatabase(this)) // Call initialize before getRecipes
//
//        // Fetch recipes from JSON file
//        recipesList = getRecipes(this)
        //setContentView(R.layout.activity_main)


        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigation.setOnItemSelectedListener(NavigationBarView.OnItemSelectedListener {
            when(it.itemId){
                R.id.homeFragment -> {
                    findNavController(R.id.nav_host_fragment).navigate(R.id.homeFragment)
                    return@OnItemSelectedListener true
                }
                R.id.recipesFragment -> {
                    findNavController(R.id.nav_host_fragment).navigate(R.id.recipesFragment)
                    return@OnItemSelectedListener true

                }
                R.id.profileFragment -> {
                    findNavController(R.id.nav_host_fragment).navigate(R.id.profileFragment)
                    return@OnItemSelectedListener true
                }
                else -> true
            }
        })


    }
}