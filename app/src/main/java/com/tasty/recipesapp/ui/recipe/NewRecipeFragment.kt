package com.tasty.recipesapp.ui.recipe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.tasty.recipesapp.App
import com.tasty.recipesapp.data.RecipeEntity
import com.tasty.recipesapp.models.ProfileViewModel
import com.tasty.recipesapp.models.RecipeModel
import com.tasty.recipesapp.models.TotalTimeModel
import com.tasty.recipesapp.models.UserRatingsModel
import com.tasty.recipesapp.ui.profile.ProfileViewModelFactory

class NewRecipeFragment : Fragment() {
    private lateinit var binding: NewRecipeFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = NewRecipeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = ProfileViewModelFactory((activity?.application as App).repository)
        val viewModel =
            ViewModelProvider(this, factory)[ProfileViewModel::class.java]

        binding.saveButton.setOnClickListener {
            val recipeModel = RecipeModel(
                id = 0,
                name = binding.recipeNameInput.text.toString(),
                description = binding.recipeDescriptionInput.text.toString(),
                aspect_ratio = "16:9",
                thumbnailUrl = binding.recipeImageUrlInput.text.toString(),
                userRatings = UserRatingsModel(score = 0.0F),
                total_time_tier = TotalTimeModel(),
                instructions = emptyList()
            )

            val gson = com.google.gson.Gson()
            val jsonString = gson.toJson(recipeModel)
            val recipeEntity = RecipeEntity(json = jsonString)
            viewModel.insertRecipe(recipeModel)
            viewModel.insertRecipeToDatabase(recipeEntity)
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
    // Any other necessary methods or functions can be added here
}
