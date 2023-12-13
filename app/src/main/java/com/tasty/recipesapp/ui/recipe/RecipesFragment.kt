package com.tasty.recipesapp.ui.recipe

import RecipeAdapter
import android.os.Build.VERSION_CODES.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tasty.recipesapp.R
import com.tasty.recipesapp.databinding.RecipesFragmentBinding
import com.tasty.recipesapp.models.RecipeViewModel

class RecipesFragment : Fragment(), RecipeAdapter.OnItemClickListener {

    private lateinit var binding: RecipesFragmentBinding
    private val recipeAdapter = RecipeAdapter(this)
    private val recipeViewModel: RecipeViewModel by lazy {
        ViewModelProvider(this).get(RecipeViewModel::class.java)
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

        recipeViewModel.readAllRecipes(requireContext())
        recipeViewModel.recipeList.observe(viewLifecycleOwner) {
            recipeAdapter.setData(it)
        }

        binding.newRecipeButton.setOnClickListener {

            findNavController().navigate(R.id.action_recipesFragment_to_addEditRecipeFragment)
        }
    }

//    override fun onItemClick(id: Int) {
//
//        findNavController().navigate(R.id.action_recipesFragment_to_recipeDetailFragment, bundleOf("recipeId" to id))
//    }
override fun onItemClick(id: Int) {
    val bundle = Bundle().apply {
        putInt("recipeId", id)
    }
    findNavController().navigate(R.id.action_recipesFragment_to_recipeDetailFragment, bundle)
}

    override fun onDestroyView() {
        super.onDestroyView()
        // Release the binding when the fragment's view is destroyed to avoid memory leaks
        binding = null
    }
}

