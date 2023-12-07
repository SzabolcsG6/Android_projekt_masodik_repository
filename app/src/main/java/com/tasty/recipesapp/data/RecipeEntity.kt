package com.tasty.recipesapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipes")
data class recipeEntity(
    @PrimaryKey val id: String, // Assuming id is a string
    val name: String,
    val imageUrl: String,
    val thumbnailAltText: String?,
    val promotion: String?,
    val originalVideoUrl: String?,
    val servingsNounPlural: String?,
    val videoAdContent: String?,
    val seoTitle: String?,
    val seoPath: String?,
    val canonicalId: String?,
    val beautyUrl: String?,
    val draftStatus: String?,

)
