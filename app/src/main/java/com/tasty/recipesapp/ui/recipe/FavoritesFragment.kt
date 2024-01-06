package com.tasty.recipesapp.ui.recipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.tasty.recipesapp.R
import com.tasty.recipesapp.repository.recipe.model.RecipeModel
import com.tasty.recipesapp.ui.recipe.RecipesFragment.Companion.BUNDLE_EXTRA_SELECTED_RECIPE_ID
import com.tasty.recipesapp.ui.recipe.adapter.FavoritesListAdapter
import com.tasty.recipesapp.ui.recipe.viewmodel.FavoritesViewModel

interface FavoritesAdapterListener{
    //fun onRecipeItemClick(recipe: recipeList)
    //fun deleteFromFavorite(recipe: recipeList)
}
//class FavoritesFragment : Fragment(),FavoritesAdapterListener {

//    private var _binding: FragmentListRecipesBinding? = null
//    private val binding get() = _binding!!
//    private val viewModel: FavoritesViewModel by viewModels()
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        _binding = FragmentListRecipesBinding.inflate(inflater, container, false)
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        viewModel.loadFavoriteRecipes()
//        viewModel.favoritesRecipes.observe(viewLifecycleOwner){ recipes ->
//            val adapter = FavoritesListAdapter(recipes, this)
//            binding.recyclerView.adapter = adapter
//        }
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
//
//    override fun onRecipeItemClick(recipe: recipeList) {
//        TODO("Not yet implemented")
//    }
//
//    override fun deleteFromFavorite(recipe: recipeList) {
//        viewModel.deleteFromFavorite(recipe)
//    }

//}