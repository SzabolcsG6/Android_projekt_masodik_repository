package com.tasty.recipesapp.models

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tasty.recipesapp.dtos.RecipeDto
import com.tasty.recipesapp.providers.RepositoryProvider

class RecipeListViewModel : ViewModel() {

    // LiveData to hold the list of InstructionModels
    // This should be recipes
    private val recipes = MutableLiveData<List<InstructionModel>>()
    val instructionModels: LiveData<List<InstructionModel>> = recipes

    // Function to load data from the repository
    // Context should be removed
    fun loadInstructionData(context: Context) {
        val data = RepositoryProvider.instructionsRepository.getAll(context)
        recipes.value = data
    }
    fun RecipeDto.toModel(): RecipeModel {
        // Perform the necessary transformation from recipeDto to RecipeListViewModel
        return RecipeModel(
           id = this.id,
            name = this.name,
            thumbnailUrl = this.thumbnailUrl,
            promotion = this.promotion,
            originalVideoUrl = this.originalVideoUrl,
            servingsNounPlural = this.servingsNounPlural,
            videoAdContent = this.videoAdContent,
            seoTitle = this.seoTitle,
            seoPath = this.seoPath,
            canonicalId = this.canonicalId,
            beautyUrl = this.beautyUrl,
            draftStatus = this.draftStatus,
            aspectRatio = this.aspectRatio,
            difficultyLevel = this.difficultyLevel,
            cuisineType = this.cuisineType,
            dietaryInformation = this.dietaryInformation,
            mealType = this.mealType,
            calories = this.calories,
            nutritionalInfo = this.nutritionalInfo,
            allergens = this.allergens,
        imageUrl=this.imageUrl)
    }
}