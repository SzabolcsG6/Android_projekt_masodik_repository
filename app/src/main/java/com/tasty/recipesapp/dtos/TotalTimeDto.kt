package com.tasty.recipesapp.dtos

import com.tasty.recipesapp.models.TotalTimeModel

data class TotalTimeDto(
    val tier: String? = "0",
    val display_tier: String? = "0"
)

fun TotalTimeDto.toModel(): TotalTimeModel {
    return TotalTimeModel(
        tier = this.tier,
        display_tier = this.display_tier
    )
}