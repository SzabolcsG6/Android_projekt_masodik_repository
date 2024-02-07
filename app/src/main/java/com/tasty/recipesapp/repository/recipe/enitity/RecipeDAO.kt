package com.tasty.recipesapp.repository.recipe.enitity

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
public interface RecipeDAO {
    @Insert
    suspend fun insertRecipe(recipe: RecipeEntity)
    @Query("SELECT * FROM recipe WHERE internalId = :id")
    suspend fun getRecipeById(id: Long): RecipeEntity?

    @Delete
    suspend fun deleteRecipe(recipe: RecipeEntity)
    @Query("SELECT * FROM recipe")
    fun getAllRecipesLiveData(): LiveData<List<RecipeEntity>>




//    @Query("SELECT * FROM recipe WHERE isFavorite = 1")
//    suspend fun getAllFavoriteRecipes(): List<RecipeEntity>
//new additions
}
