package com.tasty.recipesapp.data.dtos

import com.google.gson.annotations.SerializedName

data class InstructionDTO(
    @SerializedName("appliance")
    val appliance: String?,
    @SerializedName("end_time")
    val endTime: Int,
    @SerializedName("temperature")
    val temperature: String?,
    @SerializedName("id")
    val id: Int,
    @SerializedName("position")
    val position: Int,
    @SerializedName("display_text")
    val displayText: String,
    @SerializedName("start_time")
    val startTime: Int
)