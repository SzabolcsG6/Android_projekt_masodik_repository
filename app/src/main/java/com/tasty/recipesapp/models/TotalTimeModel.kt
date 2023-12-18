package com.tasty.recipesapp.models

data class TotalTimeModel(
    val tier: String? = "0",
    val display_tier: String? = "0"
) {
    companion object {
        fun default(): TotalTimeModel {
            return TotalTimeModel();
        }
    }


}