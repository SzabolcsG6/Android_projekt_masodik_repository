package com.tasty.recipesapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "recipe")
data class RoomEntity(
    @PrimaryKey(autoGenerate = true)
    val internalId: Long = 0L, // Room will handle generating this ID
    @SerializedName("json_data")
    val json: String
)
