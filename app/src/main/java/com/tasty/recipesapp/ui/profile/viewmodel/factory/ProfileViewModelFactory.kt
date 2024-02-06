@file:Suppress("UNCHECKED_CAST")

package com.tasty.recipesapp.ui.profile.viewmodel.factory


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tasty.recipesapp.repository.RecipeRepository

import com.tasty.recipesapp.ui.recipe.viewmodel.ProfileViewModel

class ProfileViewModelFactory(private val repository: RecipeRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProfileViewModel(repository) as T
    }
}