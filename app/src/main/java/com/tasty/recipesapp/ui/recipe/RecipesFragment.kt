package com.tasty.recipesapp.ui.recipe

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.SearchView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.tasty.recipesapp.R
import com.tasty.recipesapp.databinding.FragmentRecipesBinding
import com.tasty.recipesapp.repository.RecipeRepository
import com.tasty.recipesapp.repository.recipe.model.RecipeModel
import com.tasty.recipesapp.ui.recipe.adapter.RecipesListAdapter
import com.tasty.recipesapp.ui.recipe.viewmodel.RecipeListViewModel

class RecipesFragment : Fragment() {

    companion object {
        private val TAG: String? = RecipesFragment::class.java.canonicalName
        const val BUNDLE_EXTRA_SELECTED_RECIPE_ID = "selected_recipe_id"
    }

    private lateinit var binding: FragmentRecipesBinding
    private lateinit var recipesAdapter: RecipesListAdapter
    private lateinit var addToFavoritesListener: (RecipeModel) -> Unit



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

        // On button click, trigger sorting and update UI
        sortButton.setOnClickListener {
            viewModel.sortRecipesByRating()
            viewModel.recipeList.observe(viewLifecycleOwner) { recipes ->
                recipesAdapter.setData(recipes)
                recipesAdapter.notifyDataSetChanged()
                scrollToTop()
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
//        val searchView: SearchView = view.findViewById(R.id.searchView)
//
//        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                // Not required in this case
//                return true
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                newText?.let {
//                    viewModel.setSearchQuery(it)
//                }
//                return true
//            }
//        })
//
//        viewModel.searchResults.observe(viewLifecycleOwner) { recipes ->
//            // Update your RecyclerView or adapter with the search results
//            recipesAdapter.setData(recipes)
//            recipesAdapter.notifyDataSetChanged()
//        }


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


    }private fun addToFavorites(recipe: RecipeModel) {
        // Implement logic to add the recipe to favorites (e.g., using SharedPreferences or a database)
        // For example, using SharedPreferences:
        val sharedPreferences = requireContext().getSharedPreferences("Favorites", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean(recipe.id.toString(), true) // Store the recipe ID as a favorite
        editor.apply()
        Log.d("Favorites", "Recipe ${recipe.id} added to favorites")
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