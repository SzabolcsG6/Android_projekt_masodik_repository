package com.tasty.recipesapp.models

import androidx.lifecycle.ViewModel

class NewRecipeViewModel : ViewModel() {
    // Properties for storing data entered in the UI
    var title: String = ""
    var description: String = ""
    var pictureURL: String = ""
    var videoURL: String = ""
    var ingredients: MutableList<String> = mutableListOf()
    var instructions: MutableList<String> = mutableListOf()

    // Function to save the recipe data
    fun saveRecipe() {
        // Perform actions to save the recipe with the collected data
        // For example, you might use these properties to create a Recipe object and save it to a database
    }
}