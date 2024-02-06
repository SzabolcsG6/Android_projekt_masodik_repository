package com.tasty.recipesapp.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.tasty.recipesapp.databinding.FragmentNewRecipeBinding
import com.tasty.recipesapp.repository.recipe.enitity.RecipeEntity
import com.tasty.recipesapp.repository.recipe.model.RecipeModel
import com.tasty.recipesapp.repository.recipe.model.TotalTimeModel
import com.tasty.recipesapp.repository.recipe.model.UserRatingsModel
import com.tasty.recipesapp.ui.App
import com.tasty.recipesapp.ui.recipe.viewmodel.ProfileViewModel
import com.tasty.recipesapp.ui.profile.viewmodel.factory.ProfileViewModelFactory
import kotlinx.coroutines.launch
import androidx.lifecycle.viewModelScope

class NewRecipeFragment : Fragment() {

    private lateinit var binding: FragmentNewRecipeBinding
//    val recipeDatabase = RecipeDatabase.getDatabase(requireContext()) // Assuming RecipeDatabase is a singleton
//    val recipeDao = recipeDatabase.recipeDao() // Get an instance of RecipeDAO from the database


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewRecipeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = ProfileViewModelFactory(
            (activity?.application as App).repository)
        val viewModel =
            ViewModelProvider(this, factory)[ProfileViewModel::class.java]
//viewModel.fetchDatabaseRecipes()
        //viewModel.fetchMyRecipesData()
        binding.saveButton.setOnClickListener {
            val recipeModel = RecipeModel(
                id = 0, // vagy más, adataidtól függő érték
                name = binding.recipeNameInput.text.toString(),
                description = binding.recipeDescriptionInput.text.toString(),
                aspect_ratio = "16:9", // vagy más, az igényeidtől függő érték
                thumbnailUrl = binding.recipeImageUrlInput.text.toString(),
                userRatings = UserRatingsModel(score = 0.0F), // vagy használhatsz egy létező értéket
                total_time_tier = TotalTimeModel(), // vagy használhatsz egy létező értéket
                instructions = emptyList() // vagy használhatsz egy létező listát
            )

            val gson = com.google.gson.Gson()
            val jsonString = gson.toJson(recipeModel)
            val recipeEntity = RecipeEntity(json = jsonString)
            viewModel.viewModelScope.launch {
                viewModel.insertRecipeToDatabase(recipeEntity)
                navigateBack()

                // No need to fetch recipes here since it's already observed
            }

            navigateBack()
        }

        viewModel.insertResult.observe(viewLifecycleOwner) {
            if (it) {
                navigateBack()
            } else {
                Toast.makeText(context, "Insert Failed", Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun navigateBack() {
        findNavController().popBackStack()
    }
//    private fun displayFavoriteRecipes() {
////         val sharedPreferences = requireContext().getSharedPreferences("Favorites", Context.MODE_PRIVATE)
////        val favoriteRecipeIds = sharedPreferences.getStringSet("favoriteRecipeIds", setOf()) ?: setOf()
////
////        // Assuming you have a predefined list of recipes or a method to fetch them
////        val allRecipes = getAllRecipesFromApi() // Replace this with your method to get recipes
////
////        // Filter the recipes to display only the favorites
////        val favoriteRecipes = allRecipes.filter { it.id.toString() in favoriteRecipeIds }
////
////        if (favoriteRecipes.isNotEmpty()) {
////            // Example: Show favorite recipe names in a toast
////            val favoriteRecipeNames = favoriteRecipes.joinToString(separator = "\n") { it.name }
////            Toast.makeText(requireContext(), "Favorite Recipes:\n$favoriteRecipeNames", Toast.LENGTH_LONG).show()
////        } else {
////            Toast.makeText(requireContext(), "No favorite recipes found", Toast.LENGTH_SHORT).show()
////        }
//    }



}