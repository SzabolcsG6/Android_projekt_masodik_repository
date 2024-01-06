package com.tasty.recipesapp.ui.recipe.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tasty.recipesapp.R
import com.tasty.recipesapp.databinding.RecipeListItemBinding
import com.tasty.recipesapp.repository.recipe.model.RecipeModel

class FavoritesListAdapter(
    private val favoritesList: List<RecipeModel>,
    private val requireContext: Context,
    private val onItemClickListener: (recipe: RecipeModel) -> Unit

) : RecyclerView.Adapter<FavoritesListAdapter.FavoritesViewHolder>() {

    class FavoritesViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(recipe: RecipeModel) {
            // Bind recipe data to the view here
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)


        val binding = RecipeListItemBinding.inflate(layoutInflater, parent, false)
        return FavoritesViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        val recipe = favoritesList[position]
        holder.bind(recipe)
        holder.itemView.setOnClickListener {
            onItemClickListener(recipe)
        }
    }

    override fun getItemCount(): Int = favoritesList.size
}