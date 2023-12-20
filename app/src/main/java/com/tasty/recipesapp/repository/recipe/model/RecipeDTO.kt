package com.tasty.recipesapp.repository.recipe.model

data class RecipeDTO(
    val id: Int,
    val name: String,
    val description: String? = "Default description",
    val aspect_ratio: String,
    val user_ratings: UserRatingsDTO,
    val thumbnail_url: String,
    val total_time_tier: TotalTimeDTO,
    val instructions: List<InstructionDTO>

)

fun RecipeDTO.toModel(): RecipeModel {
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

fun TotalTimeDTO?.toModel(): TotalTimeModel {
    // Check for null and return a default TotalTimeModel if null
    return this?.let {
        // Convert TotalTimeDTO to TotalTimeModel here
        // Replace this block with your actual conversion logic
        // Example:
        TotalTimeModel(/* pass relevant parameters */)
    } ?: TotalTimeModel.default() // Return default TotalTimeModel if null
}

fun List<RecipeDTO>.toModelList(): List<RecipeModel> {
    return this.map { it.toModel() }
}