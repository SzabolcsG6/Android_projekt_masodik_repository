@file:Suppress("NAME_SHADOWING")

package com.tasty.recipesapp.ui.recipe.adapter

import android.content.ContentValues
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tasty.recipesapp.R
import com.tasty.recipesapp.databinding.RecipeListItemBinding
import com.tasty.recipesapp.repository.recipe.model.RecipeModel

class RecipesListAdapter (
    private val recipesList: MutableList<RecipeModel> = mutableListOf(),
            private val context: Context,
    private val onItemClickListener: (RecipeModel) -> Unit,
    private val onItemLongClickListener: (RecipeModel) -> Unit = {},
    private val onAddToFavoritesClick: (RecipeModel) -> Unit // New callback
) : RecyclerView.Adapter<RecipesListAdapter.RecipeItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeItemViewHolder {
        val binding = RecipeListItemBinding
            .inflate(LayoutInflater.from(context), parent, false)
        return RecipeItemViewHolder(binding)
    }

    override fun getItemCount(): Int = recipesList.size

    override fun onBindViewHolder(holder: RecipeItemViewHolder, position: Int) {
        val currentRecipe = recipesList[position]

        holder.recipeTitleView.text = currentRecipe.name
        holder.recipeDescriptionView.text = currentRecipe.description

        Log.d(ContentValues.TAG, "Recipe's thumbnail URL: ${currentRecipe.thumbnailUrl}")
        //Download image from url
        Glide.with(context)
            .load(currentRecipe.thumbnailUrl)
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_background)
            .fallback(R.drawable.ic_launcher_background)
            .into(holder.recipeImageView)

        val ratingsLabel = context.getString(R.string.user_ratings_label)
        holder.recipeRatingsView.text = ratingsLabel
            .plus(" ")
            .plus(currentRecipe.userRatings.score)

        holder.btnAddToFavorites.setOnClickListener {
            val currentPosition = holder.adapterPosition
            val currentRecipe = recipesList[currentPosition]
            onAddToFavoritesClick(currentRecipe) // Invoke the callback
        }



    }

    fun setData(newList: List<RecipeModel>) {

        recipesList.addAll(newList) // Add all elements from the new list
    }


    inner class RecipeItemViewHolder(binding: RecipeListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val recipeTitleView: TextView = binding.recipeItemTitleView
        val recipeDescriptionView: TextView = binding.recipeItemDescriptionView
        val recipeImageView : ImageView = binding.recipeImageView
        val recipeRatingsView: TextView = binding.recipeRatingsView
        val btnAddToFavorites: ImageButton = binding.btnAddToFavorites



        init {
            binding.root.setOnClickListener {
                val currentPosition = this.adapterPosition
                val currentRecipe = recipesList[currentPosition]

                onItemClickListener(currentRecipe)
            }


            binding.root.setOnLongClickListener {
                val currentPosition = this.adapterPosition
                val currentRecipe = recipesList[currentPosition]

                onItemLongClickListener(currentRecipe)
                true
            }
        }

    }

}

