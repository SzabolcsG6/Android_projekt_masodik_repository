package com.tasty.recipesapp.ui.recipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.tasty.recipesapp.databinding.RecipesFragmentBinding
import com.tasty.recipesapp.models.RecipeViewModel
import com.tasty.recipesapp.R
import com.tasty.recipesapp.data.RecipeAdapter

class RecipesFragment : Fragment(), RecipeAdapter.OnItemClickListener {

    private lateinit var binding: RecipesFragmentBinding
    private val recipeAdapter = RecipeAdapter(this)

    // Initialize ViewModel
    private val recipeViewModel: RecipeViewModel by lazy {
        ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
            .get(RecipeViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = RecipesFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recipesRecyclerView.adapter = recipeAdapter

        recipeViewModel.recipeList.observe(viewLifecycleOwner) { recipes ->
            recipeAdapter.setData(recipes)
        }

        context?.let { recipeViewModel.loadRecipes(it) }

        binding.newRecipeButton.setOnClickListener {
            // Navigate to addNewRecipeFragment
        }
    }

    override fun onItemClick(id: String) {
        // Handle item click here
        // For example: Navigate to recipe detail fragment with the provided ID
        val bundle = Bundle().apply {
            putString("recipeId", id)
        }
        findNavController().navigate(R.id.action_recipesFragment_to_recipeDetailFragment, bundle)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Release resources held by the binding without setting it to null
        binding.unbind()
    }
}


