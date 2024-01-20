package com.tasty.recipesapp.repository.recipe

import android.content.Context
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory.Companion.instance
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tasty.recipesapp.repository.recipe.enitity.RecipeDAO
import com.tasty.recipesapp.repository.recipe.enitity.RecipeEntity


@Database(entities = [RecipeEntity::class], version = 1, exportSchema =
false)
abstract class RecipeDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDAO

    companion object {
        // Create a singleton instance of the database
        @Volatile
        private var INSTANCE: RecipeDatabase? = null
        fun getDatabase(context: Context): RecipeDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RecipeDatabase::class.java,
                    "recipe_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }

       // fun open() = instance.writableDatabase
    }

}