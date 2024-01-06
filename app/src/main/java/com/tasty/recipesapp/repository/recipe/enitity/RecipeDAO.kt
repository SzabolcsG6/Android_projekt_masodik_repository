package com.tasty.recipesapp.repository.recipe.enitity

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import java.util.concurrent.Flow

@Dao
interface RecipeDAO {
    @Insert
    suspend fun insertRecipe(recipe: RecipeEntity)
    @Query("SELECT * FROM recipe WHERE internalId = :id")
    suspend fun getRecipeById(id: Long): RecipeEntity?
    @Query("SELECT * FROM recipe")
    suspend fun getAllRecipes(): List<RecipeEntity>
    @Delete
    suspend fun deleteRecipe(recipe: RecipeEntity)
//    @Query("SELECT * FROM recipe WHERE name LIKE '%' || :search || '%'")
//    fun getSearchedRecipes(search: String?): Flow<List<RecipeEntity>>
}
