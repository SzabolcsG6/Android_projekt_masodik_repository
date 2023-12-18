package com.tasty.recipesapp.activities

import com.tasty.recipesapp.dtos.InstructionDto
import com.tasty.recipesapp.models.InstructionModel
import com.tasty.recipesapp.models.InstructionTime

// Object for mapping InstructionDTOs to InstructionModels
object Mapping {
    // Function to map a single InstructionDTO to an InstructionModel
    fun InstructionDto.toModel(): InstructionModel {
        return InstructionModel(
            id = this.id,
            displayText = this.displayText,
            time = InstructionTime(this.startTime, this.endTime)
        )
    }

    // Function to map a list of InstructionDTOs to a list of InstructionModels
    fun List<InstructionDto>.toModelList(): List<InstructionModel> {
        // Utilizes the map function to convert each InstructionDTO to an InstructionModel
        return this.map { it.toModel() }
    }
}
