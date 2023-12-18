package com.tasty.recipesapp.dtos

import com.tasty.recipesapp.models.UserRatingsModel

data class UserRatingsDto(
    val count_positive: Int,
    val score: Float,
    val count_negative: Int
)

fun UserRatingsDto.toModel(): UserRatingsModel {
    return UserRatingsModel(
        score = this.score * 10
    )
}