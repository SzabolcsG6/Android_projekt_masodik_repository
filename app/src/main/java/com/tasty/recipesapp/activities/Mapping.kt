package com.tasty.recipesapp.activities

import com.tasty.recipesapp.data.dtos.InstructionDTO
import com.tasty.recipesapp.data.models.InstructionModel
import com.tasty.recipesapp.data.models.InstructionTime

object Mapping {
    fun InstructionDTO.toModel(): InstructionModel {
        return InstructionModel(
            id = this.id,
            displayText = this.displayText,
            time = InstructionTime(this.startTime, this.endTime)
        )
    }

    fun List<InstructionDTO>.toModelList(): List<InstructionModel> {
        return this.map { it.toModel() }
    }

}