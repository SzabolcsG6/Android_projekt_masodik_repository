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
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        initRecyclerView()
        binding.addButton.setOnClickListener {
            navigateToNewRecipe()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Pass the context when creating the ViewModel
        val factory = ProfileViewModelFactory((activity?.application as App).repository, requireContext())
        viewModel = ViewModelProvider(this, factory)[ProfileViewModel::class.java]

        viewModel.fetchMyRecipesData()

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
    }

    private fun initRecyclerView() {
        recipesAdapter = RecipesListAdapter(
            ArrayList(),
            requireContext(),
            onItemClickListener = { recipe -> navigateToRecipeDetail(recipe) },
            onItemLongClickListener = { recipe -> viewModel.deleteRecipe(recipe) },
            onAddToFavoritesClick = {}
        )

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