package com.tasty.recipesapp.dtos

import android.adservices.topics.Topic
import android.nfc.Tag
import androidx.core.view.KeyEventDispatcher
import com.tasty.recipesapp.models.RecipeModel
import com.tasty.recipesapp.models.TotalTimeModel

data class RecipeDto(
    val id: Int,
    val name: String,
    val description: String? = "Default description",
    val aspect_ratio: String,
    val user_ratings: UserRatingsDto,
    val thumbnail_url: String,
    val total_time_tier: TotalTimeDto,
    val instructions: List<InstructionDto>
)


fun RecipeDto.toModel(): RecipeModel {
    return RecipeModel(
        id = this.id,
        name = this.name,
        description = this.description ?: "Default description",
        aspect_ratio = this.aspect_ratio,
        thumbnailUrl = this.thumbnail_url,
        userRatings = this.user_ratings.toModel(),
        total_time_tier = this.total_time_tier?.toModel() ?: TotalTimeModel.default(), // Handle null case
        instructions = this.instructions.toModelList()
    )
}

fun TotalTimeDto?.toModel(): TotalTimeModel {
    // Check for null and return a default TotalTimeModel if null
    return this?.let {
        // Convert TotalTimeDTO to TotalTimeModel here
        // Replace this block with your actual conversion logic
        // Example:
        TotalTimeModel(/* pass relevant parameters */)
    } ?: TotalTimeModel.default() // Return default TotalTimeModel if null
}

fun List<RecipeDto>.toModelList(): List<RecipeModel> {
    return this.map { it.toModel() }
}