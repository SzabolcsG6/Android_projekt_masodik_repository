package com.tasty.recipesapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipes")
data class RecipeEntity(
    @PrimaryKey val id: String, // Assuming id is a string
    val name: String,
    val imageUrl: String,
    val thumbnailAltUrl: String?,
    val promotion: String?,
    val originalVideoUrl: String?,
    val servingsNounPlural: String?,
    val videoAdContent: String?,
    val seoTitle: String?,
    val seoPath: String?,
    val canonicalId: String?,
    val beautyUrl: String?,
    val draftStatus: String?,
    val aspectRatio: String?, // New attribute: Aspect Ratio
    val difficultyLevel: String?, // New attribute: Difficulty Level
    val cuisineType: String?, // New attribute: Cuisine Type
    val dietaryInformation: String?, // New attribute: Dietary Information
    val mealType: String?, // New attribute: Meal Type
    val calories: Int?, // New attribute: Calories
    val nutritionalInfo: String?, // New attribute: Nutritional Information
    val allergens: String?, // New attribute: Allergens
    // ... other attributes as needed based on your requirements
)
