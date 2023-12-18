package com.tasty.recipesapp.models

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tasty.recipesapp.data.RecipeEntity
import com.tasty.recipesapp.dtos.RecipeDto
import com.tasty.recipesapp.repositories.RecipeRepository
import kotlinx.coroutines.launch
import java.io.IOException
data class RecipeModel(
    val id: Int,
    val name: String,
    val description: String? = "Default description",
    val aspect_ratio: String? ="16:9",
    val thumbnailUrl: String,
    val userRatings: UserRatingsModel,
    val total_time_tier: TotalTimeModel,
    val instructions: List<InstructionModel>
)