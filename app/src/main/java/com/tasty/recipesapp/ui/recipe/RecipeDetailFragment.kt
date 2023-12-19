package com.tasty.recipesapp.ui.recipe

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.tasty.recipesapp.R
import com.tasty.recipesapp.databinding.RecipeDetailFragmentBinding
import com.tasty.recipesapp.models.RecipeDetailViewModel
import com.tasty.recipesapp.models.RecipeModel
import com.tasty.recipesapp.databinding.RecipesFragmentBinding

class RecipeDetailFragment : Fragment() {

    private lateinit var binding: RecipeDetailFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = RecipeDetailFragmentBinding.inflate(inflater, container, false)

        return binding.root
        //return inflater.inflate(R.layout.fragment_recipe_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recipeId = arguments?.getInt(RecipesFragment.BUNDLE_EXTRA_SELECTED_RECIPE_ID)
        Log.d(TAG, "Show details for recipe with ID = $recipeId")

        val viewModel =
            ViewModelProvider(this)[RecipeDetailViewModel::class.java]

        //Trigger details loading for the recipe.
        recipeId?.let {viewModel.fetchRecipeDetail(it) }

        // Subscribe/observe for recipe details.
        viewModel.recipe.observe(viewLifecycleOwner) { recipeModel ->
            recipeModel?.let {
                Log.d(TAG, "Selected recipe's details: $it")
                updateView(it)
            } ?: run {
                Log.e(TAG, "RecipeModel is null in onChanged callback")
            }
        }


    }

    private fun updateView(recipeModel: RecipeModel?) {
        recipeModel?.let { it ->
            try {
                binding.recipeItemTitleView.text = it.name
                binding.recipeItemDescriptionView.text = it.description

                Glide.with(requireActivity())
                    .load(it.thumbnailUrl)
                    .centerCrop()
                    .placeholder(R.drawable.ic_launcher_background)
                    .fallback(R.drawable.ic_launcher_background)
                    .into(binding.recipeImageView)

                val ratingsLabel = requireActivity().getString(R.string.user_ratings_label)
                val ratingsText = ratingsLabel + " " + (it.userRatings.score ?: "")
                binding.recipeRatingsView.text = ratingsText

                binding.totalTimeView.text = it.total_time_tier.display_tier ?: ""

                val instructionsString = it.instructions.joinToString("\n") { it2 ->
                    it2.position.toString() + ". " + it2.display_text
                } ?: ""

                binding.instructionsView.text = instructionsString
            } catch (e: Exception) {
                Log.e(TAG, "Exception in updateView: ${e.message}")
                e.printStackTrace()
            }


        } ?: run {
            // Handle the case where recipeModel is null
            Log.e(TAG, "updateView: recipeModel is null")
        }
    }
}