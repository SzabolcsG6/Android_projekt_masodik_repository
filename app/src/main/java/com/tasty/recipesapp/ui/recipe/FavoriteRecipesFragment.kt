package com.tasty.recipesapp.ui.recipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.tasty.recipesapp.databinding.FragmentFavoritesBinding
import com.tasty.recipesapp.databinding.FragmentNewRecipeBinding
import com.tasty.recipesapp.ui.App
import com.tasty.recipesapp.ui.profile.viewmodel.factory.ProfileViewModelFactory
import com.tasty.recipesapp.ui.recipe.viewmodel.ProfileViewModel

class FavoriteRecipesFragment : Fragment() {
    private lateinit var binding: FragmentFavoritesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val factory = ProfileViewModelFactory(
//            (activity?.application as App).repository)
//        val viewModel =
//            ViewModelProvider(this, factory)[ProfileViewModel::class.java]
    }

    private fun navigateBack() {
        findNavController().popBackStack()
    }
}