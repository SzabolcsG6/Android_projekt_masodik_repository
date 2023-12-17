package com.tasty.recipesapp.dtos

import android.adservices.topics.Topic
import android.nfc.Tag
import androidx.core.view.KeyEventDispatcher

data class RecipeDto(
    val id: String,
    val name: String,
    val nutrition: Nutrition?,
    val credits: List<Credit>,
    val instructions: List<Instruction>,
    val sections: List<Section>,
    val components: List<Component>,
    val tags: List<com.tasty.recipesapp.dtos.Tag>,
    val topics: List<com.tasty.recipesapp.dtos.Topic>,
    val userRatings: List<UserRating>,
    val thumbnailUrl: String,
    val promotion: String?,
    val originalVideoUrl: String?,
    val servingsNounPlural: String?,
    val videoAdContent: String?,
    val seoTitle: String?,
    val seoPath: String?,
    val canonicalId: String?,
    val beautyUrl: String?,
    val draftStatus: String?,
    val aspectRatio: String?,
    val difficultyLevel: String?,
    val cuisineType: String?,
    val dietaryInformation: String?,
    val mealType: String?,
    val calories: Int?,
    val nutritionalInfo: String?,
    val allergens: String?,
    val imageUrl: String
)


data class Recipe(
    val id: Int,
    val name: String,
    val type: String,
    val nutrition: Nutrition,
    val credits: List<Credit>,
    val instructions: List<Instruction>,
    val sections: List<Section>,
    val components: List<KeyEventDispatcher.Component>,
    val tags: List<Tag>,
    val topics: List<Topic>,
    val userRatings: List<UserRating>
)

data class NutritionDTO(
    val calories: Int,
    val fat: Int,
    val protein: Int,
    val carbohydrates: Int
)

data class Nutrition(
    val calories: Int,
    val fat: Int,
    val protein: Int,
    val carbohydrates: Int
)

data class CreditDTO(
    val name: String,
    val role: String
)

data class Credit(
    val name: String,
    val role: String
)
data class SectionDTO(
    val id: Int,
    val title: String,
    val description: String
)

data class Section(
    val id: Int,
    val title: String,
    val description: String
)

data class ComponentDTO(
    val id: Int,
    val name: String,
    val quantity: Double,
    val measurement: MeasurementDTO
)

data class Component(
    val id: Int,
    val name: String,
    val quantity: Double,
    val measurement: Measurement?
)

data class TagDTO(
    val id: Int,
    val name: String
)

data class Tag(
    val id: Int,
    val name: String
)

data class TopicDTO(
    val id: Int,
    val title: String,
    val description: String
)

data class Topic(
    val id: Int,
    val title: String,
    val description: String
)

data class UserRatingDTO(
    val userId: Int,
    val rating: Int
)

data class UserRating(
    val userId: Int,
    val rating: Int
)

data class Instruction(
    val id: Int,
    val appliance: String?,
    val endTime: Int,
    val temperature: String?,
    val position: Int,
    val displayText: String,
    val startTime: Int
)
data class MeasurementDTO(
    val id: Int,
    val unit: UnitDTO,
    val quantity: Double
)

data class Measurement(
    val id: Int,
    val unit: Unit,
    val quantity: Double
)

data class UnitDTO(
    val id: Int,
    val name: String,
    val symbol: String
)

data class Unit(
    val id: Int,
    val name: String,
    val symbol: String
)

// Similarly, create DTO and Model classes for other entities such as Instruction, Section, Component, Ingredient, Measurement, Unit, Tag, Topic, and UserRatings following a similar structure and mapping.
