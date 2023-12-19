package com.tasty.recipesapp.models

data class RecipeModel(
    val id: Int,
    val name: String,
    val description: String? = "Default description",
    val aspect_ratio: String? ="16:9",
    val thumbnailUrl: String,
    val userRatings: UserRatingsModel,
    val total_time_tier: TotalTimeModel,
    val instructions: List<InstructionModel>
)