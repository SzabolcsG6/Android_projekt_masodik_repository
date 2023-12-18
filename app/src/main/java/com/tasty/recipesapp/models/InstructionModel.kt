package com.tasty.recipesapp.models

data class InstructionModel(
    val id: Int? = 0,
    val position: Int? = 0,
    val display_text: String? = "",
    val start_time: Int? = 0,
    val end_time: Int? = 0,
    val appliance: String? = "",
    val temperature: Int? = 0
)