package com.tasty.recipesapp.ui.recipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.tasty.recipesapp.databinding.RecipesFragmentBinding
import com.tasty.recipesapp.models.RecipeModel
import com.tasty.recipesapp.R
import com.tasty.recipesapp.data.RecipesListAdapter
import com.tasty.recipesapp.models.RecipeListViewModel

class RecipesFragment : Fragment() {

    companion object {
        private val TAG: String? = RecipesFragment::class.java.canonicalName
        const val BUNDLE_EXTRA_SELECTED_RECIPE_ID = "selected_recipe_id"
    }

    private lateinit var binding: RecipesFragmentBinding
    private lateinit var recipesAdapter: RecipesListAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = RecipesFragmentBinding.inflate(inflater, container, false)
        initRecyclerView()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel =
            ViewModelProvider(this).get(RecipeListViewModel::class.java)

//        context?.let {
//            viewModel.fetchRecipeData(it)
//        }

        viewModel.getAllRecipesFromApi()

        viewModel.recipeList.observe(viewLifecycleOwner) {recipes ->
            recipesAdapter.setData(recipes)
            recipesAdapter.notifyItemRangeInserted(0, recipes.lastIndex)
        }

        viewModel.searchResults.observe(viewLifecycleOwner) { searchResults ->
            // Frissítsd a RecyclerView-t a keresési eredményekkel
            recipesAdapter.setData(searchResults)
            recipesAdapter.notifyDataSetChanged()
        }
    }

    private fun initRecyclerView() {
        recipesAdapter = RecipesListAdapter(ArrayList(), requireContext(),
            onItemClickListener =
            {
                    recipe ->
                navigateToRecipeDetail(recipe)
            })
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
            R.id.action_recipesFragment_to_recipeDetailFragment,
            bundleOf(BUNDLE_EXTRA_SELECTED_RECIPE_ID to recipe.id)
        )
    }


}