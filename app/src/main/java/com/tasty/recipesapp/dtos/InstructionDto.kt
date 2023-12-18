package com.tasty.recipesapp.dtos

import com.google.gson.annotations.SerializedName
import com.tasty.recipesapp.models.InstructionModel

data class InstructionDto(
    val id: Int,
    val position: Int,
    val display_text: String,
    val start_time: Int,
    val end_time: Int,
    val appliance: String?, // Az eszköz típusa, opcionális
    val temperature: Int? // Hőmérséklet, opcionális
)
fun InstructionDto.toModel(): InstructionModel {
    return InstructionModel(
        id = this.id,
        position = this.position,
        display_text = this.display_text,
        start_time = this.start_time,
        end_time = this.end_time,
        appliance = this.appliance ?: "", // Kezeld a null értéket, pl. üres stringgel
        temperature = this.temperature
    )
}
fun List<InstructionDto>.toModelList(): List<InstructionModel> {
    return this.map { it.toModel() }
}
