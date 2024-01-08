package com.tasty.recipesapp.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.tasty.recipesapp.R
import com.tasty.recipesapp.databinding.FragmentProfileBinding
import com.tasty.recipesapp.repository.recipe.model.RecipeModel
import com.tasty.recipesapp.ui.App
import com.tasty.recipesapp.ui.profile.viewmodel.ProfileViewModel
import com.tasty.recipesapp.ui.profile.viewmodel.factory.ProfileViewModelFactory
import com.tasty.recipesapp.ui.recipe.adapter.RecipesListAdapter

class ProfileFragment : Fragment() {


    companion object {
        private val TAG: String? = ProfileFragment::class.java.canonicalName
        const val BUNDLE_EXTRA_SELECTED_RECIPE_ID = "selected_recipe_id"
    }

    private lateinit var binding: FragmentProfileBinding
    private lateinit var recipesAdapter: RecipesListAdapter
    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        initRecyclerView()
        binding.addButton.setOnClickListener {
            navigateToNewRecipe()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = ProfileViewModelFactory((activity?.application as App).repository)
        viewModel = ViewModelProvider(this, factory)[ProfileViewModel::class.java]

        viewModel.fetchMyRecipesData()

        // Subscribe/Observe for recipe list changes.
        viewModel.myRecipesList.observe(viewLifecycleOwner) { myRecipes ->
            recipesAdapter.setData(myRecipes)

            recipesAdapter.notifyItemRangeChanged(0, myRecipes.lastIndex)
        }

        viewModel.deleteResult.observe(viewLifecycleOwner) {
            if (it) {
                Toast.makeText(context, "Recipe removed successfully", Toast.LENGTH_SHORT).show()
                recipesAdapter.notifyDataSetChanged()
            } else {
                Toast.makeText(context, "Failed to remove recipe", Toast.LENGTH_SHORT).show()
            }
        }
        val viewModel = ViewModelProvider(this)[ProfileViewModel::class.java]
//Favorites so far
//        // Observe the list of favorite recipes
//        viewModel.favoriteRecipes.observe(viewLifecycleOwner) { favoriteRecipes ->
//            // Update your UI to display the list of favorite recipes
//            // For example, assuming you have a separate adapter for favorite recipes:
//            val favoriteRecipesAdapter = FavoriteRecipesAdapter(favoriteRecipes)
//            binding.favoriteRecyclerView.adapter = favoriteRecipesAdapter
//            // Set layout manager, decoration, or any other configurations for the RecyclerView
//
//            // Example: Set a click listener for items in the favorite recipes list
//            favoriteRecipesAdapter.setOnItemClickListener { selectedRecipe ->
//                // Handle click action for the selected favorite recipe
//                // For instance, navigate to its detail screen
//            }
//        viewModel.favoriteRecipes.observe(viewLifecycleOwner) { favoriteRecipes ->
//            // Update UI to display favoriteRecipes in the RecyclerView
//            val favoriteRecipesAdapter = RecipesListAdapter(
//                favoriteRecipes,
//                requireContext(),
//                onItemClickListener = { recipe ->
//                    navigateToRecipeDetail(recipe)
//                },
//                onAddToFavoritesClick = { recipe ->
//                    // Handle adding to favorites if needed
//                }
//            )
//            binding.favoriteRecyclerView.adapter = favoriteRecipesAdapter
//            // Set layout manager, decoration, or any other configurations for the RecyclerView
//        }
//
//        // Fetch and observe favorite recipes
//        viewModel.fetchFavoriteRecipes()

    }

    private fun initRecyclerView() {
        // Init empty adapter and attach to recycler view.
        recipesAdapter = RecipesListAdapter (ArrayList(), requireContext(),
            onItemClickListener =
            { recipe ->
                navigateToRecipeDetail(recipe)
            },
            onItemLongClickListener =
            { recipe ->
                viewModel.deleteRecipe(recipe)
            },
            onAddToFavoritesClick = {})

        binding.recyclerView.adapter = recipesAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                activity,
                DividerItemDecoration.VERTICAL
            )
        )
    }

    private fun navigateToRecipeDetail(recipe: RecipeModel) {
        findNavController().navigate(
            R.id.action_profileFragment_to_recipeDetailFragment,
            bundleOf(BUNDLE_EXTRA_SELECTED_RECIPE_ID to recipe.id)
        )
    }

    private fun navigateToNewRecipe() {
        findNavController().navigate(
            R.id.action_profileFragment_to_newRecipeFragment
        )
    }


}