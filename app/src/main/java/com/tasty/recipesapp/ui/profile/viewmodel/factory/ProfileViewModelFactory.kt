package com.tasty.recipesapp.ui.profile.viewmodel.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tasty.recipesapp.repository.RecipeRepository
import com.tasty.recipesapp.ui.recipe.viewmodel.ProfileViewModel

class ProfileViewModelFactory(private val repository: RecipeRepository, private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProfileViewModel(repository, context) as T
    }
}