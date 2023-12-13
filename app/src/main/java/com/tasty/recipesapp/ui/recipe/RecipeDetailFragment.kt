package com.tasty.recipesapp.ui.recipe

import android.os.Bundle
import androidx.fragment.app.Fragment

class RecipeDetailFragment : Fragment {

    // Add any necessary variables or properties for initialization here
    private var recipeId: Int = 0

    // Primary constructor to pass arguments
    constructor()

    // Secondary constructor to pass arguments
    constructor(recipeId: Int) {
        this.recipeId = recipeId
    }

    // Overriding onCreateView and other necessary methods
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Access the initialized properties here if needed
        // Example: Log.d("RecipeId", "Recipe ID: $recipeId")
    }
}
