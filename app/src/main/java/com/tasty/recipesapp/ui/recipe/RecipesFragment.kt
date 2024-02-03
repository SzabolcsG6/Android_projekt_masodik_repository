package com.tasty.recipesapp.ui.recipe

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.PopupMenu
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.tasty.recipesapp.R
import com.tasty.recipesapp.databinding.FragmentRecipesBinding
import com.tasty.recipesapp.repository.recipe.model.RecipeModel
import com.tasty.recipesapp.ui.recipe.adapter.RecipesListAdapter
import com.tasty.recipesapp.ui.recipe.viewmodel.RecipeListViewModel

class RecipesFragment : Fragment() {

    companion object {
        //private val TAG: String? = RecipesFragment::class.java.canonicalName
        const val BUNDLE_EXTRA_SELECTED_RECIPE_ID = "selected_recipe_id"
    }
    private lateinit var popupMenu: PopupMenu
    private lateinit var binding: FragmentRecipesBinding
    private lateinit var recipesAdapter: RecipesListAdapter
    //private lateinit var addToFavoritesListener: (RecipeModel) -> Unit
    private val favoritesList = mutableListOf<RecipeModel>()
//    val favoritesButton: Button? = view.findViewById(R.id.favoritesButton)
//displaying favorites crashes the app somehow

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecipesBinding.inflate(inflater, container, false)
        initRecyclerView()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val favoriteRecipeIds = getFavoriteRecipeIds()
        val viewModel = ViewModelProvider(this).get(RecipeListViewModel::class.java)

        viewModel.getAllRecipesFromApi()
        val sortButton: Button = view.findViewById(R.id.sortButton)
        val sortButton2: Button = view.findViewById(R.id.sortButton2)
        val sortButton3: Button = view.findViewById(R.id.sortButton3)
        val sortButton4: Button = view.findViewById(R.id.sortButton4)
        val chooserButton : FloatingActionButton = view.findViewById(R.id.chooserButton)
        sortButton.visibility = View.GONE
        sortButton2.visibility = View.GONE
        sortButton3.visibility = View.GONE
        sortButton4.visibility = View.GONE
        popupMenu = PopupMenu(requireContext(), chooserButton)
        popupMenu.menuInflater.inflate(R.menu.sort_menu, popupMenu.menu)
// Show the PopupMenu when the chooserButton is clicked
        chooserButton.setOnClickListener {
            popupMenu.show()
        }
        // Handle item click in the PopupMenu
        popupMenu.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.sortButton -> {
                    viewModel.sortRecipesByRating()
                    viewModel.recipeList.observe(viewLifecycleOwner) { recipes ->
                        recipesAdapter.setData(recipes)
                        recipesAdapter.notifyDataSetChanged()
                        scrollToTop()
                    }
                    true // Indicate that the menu item click is handled
                }
                R.id.sortButton2 -> {
                    viewModel.sortRecipesByRatingAscending()
                    viewModel.recipeList.observe(viewLifecycleOwner) { recipes ->
                        recipesAdapter.setData(recipes)
                        recipesAdapter.notifyDataSetChanged()
                        scrollToTop()
                    }
                    true // Indicate that the menu item click is handled
                }
                R.id.sortButton3 -> {
                    viewModel.sortRecipesByName()
                    viewModel.recipeList.observe(viewLifecycleOwner) { recipes ->
                        recipesAdapter.setData(recipes)
                        recipesAdapter.notifyDataSetChanged()
                        scrollToTop()
                    }
                    true // Indicate that the menu item click is handled
                }
                R.id.sortButton4 -> {
                    viewModel.sortRecipesByNameAscending()
                    viewModel.recipeList.observe(viewLifecycleOwner) { recipes ->
                        recipesAdapter.setData(recipes)
                        recipesAdapter.notifyDataSetChanged()
                        scrollToTop()
                    }
                    true // Indicate that the menu item click is handled
                }
                else -> false // Indicate that the menu item click is not handled
            }
        }



        viewModel.recipeList.observe(viewLifecycleOwner) {recipes ->
            recipesAdapter.setData(recipes)
            recipesAdapter.notifyItemRangeInserted(0, recipes.lastIndex)
        }

        viewModel.searchResults.observe(viewLifecycleOwner) { recipes ->
            val favoriteRecipes = recipes.filter { recipe ->
                favoriteRecipeIds.contains(recipe.id.toString())
            }
            recipesAdapter.setData(favoriteRecipes)
            // recipesAdapter.setData(searchResults)
            recipesAdapter.notifyDataSetChanged()
        }



    }

    private fun initRecyclerView() {
        recipesAdapter = RecipesListAdapter(
            ArrayList(),
            requireContext(),
            onItemClickListener = { recipe ->
                navigateToRecipeDetail(recipe)
            },
            onAddToFavoritesClick = { recipe ->
                // Handle adding the recipe to favorites here
                addToFavorites(recipe)
            }
        )

        binding.recyclerView.adapter = recipesAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                activity,
                DividerItemDecoration.VERTICAL
            )
        )
        //displaying favorites
//        if (favoritesButton != null) {
//            favoritesButton.setOnClickListener {
//                val favoriteRecipeIds = getFavoriteRecipeIds()
//
//                viewModel.recipeList.observe(viewLifecycleOwner) { recipes ->
//                    val favoriteRecipes = recipes.filter { recipe ->
//                        favoriteRecipeIds.contains(recipe.id.toString())
//                    }
//
//                    recipesAdapter.setData(favoriteRecipes)
//                    recipesAdapter.notifyDataSetChanged()
//                }
//            }
//        } else {
//            Log.d(TAG, "FavoritesButton is null")
//        }

    }private fun addToFavorites(recipe: RecipeModel) {
        val sharedPreferences = requireContext().getSharedPreferences("Favorites", Context.MODE_PRIVATE)
        val favoriteRecipeIds = getFavoriteRecipeIds().toMutableSet()
        favoriteRecipeIds.add(recipe.id.toString())
        sharedPreferences.edit().putStringSet("favoriteRecipeIds", favoriteRecipeIds).apply()
        Log.d("Favorites", "Recipe ${recipe.id} added to favorites")
    }


    private fun removeRecipeFromFavorites(recipe: RecipeModel) {
        favoritesList.remove(recipe)
        // Update the shared preferences to remove the favorite recipe
    }
    private fun navigateToRecipeDetail(recipe: RecipeModel) {
        findNavController().navigate(
            R.id.action_recipesFragment_to_recipeDetailFragment,
            bundleOf(BUNDLE_EXTRA_SELECTED_RECIPE_ID to recipe.id)
        )
    }
    private fun getFavoriteRecipeIds(): Set<String> {
        val sharedPreferences = requireContext().getSharedPreferences("Favorites", Context.MODE_PRIVATE)
        return sharedPreferences.getStringSet("favoriteRecipeIds", setOf()) ?: setOf()
    }

    private fun scrollToTop() {
        binding.recyclerView.scrollToPosition(0) // Scrolls to the top of the RecyclerView
    }
}