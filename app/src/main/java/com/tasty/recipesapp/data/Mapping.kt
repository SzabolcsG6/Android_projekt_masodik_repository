package com.tasty.recipesapp.data

import com.tasty.recipesapp.dtos.InstructionDTO
import com.tasty.recipesapp.models.InstructionModel
import com.tasty.recipesapp.models.InstructionTime

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