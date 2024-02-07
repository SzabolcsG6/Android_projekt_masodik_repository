package com.tasty.recipesapp.repository.recipe.enitity

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavoriteDAO {
    @Query("SELECT * FROM favorite")
     fun getAllFavorites(): LiveData<List<FavoriteEntity>>

    @Insert
     fun insertFavorite(favorite: FavoriteEntity)

    @Delete
     fun deleteFavorite(favorite: FavoriteEntity)
}