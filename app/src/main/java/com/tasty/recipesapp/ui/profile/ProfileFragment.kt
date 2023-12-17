package com.tasty.recipesapp.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tasty.recipesapp.R
import com.tasty.recipesapp.data.RecipeAdapter
import com.tasty.recipesapp.models.RecipeViewModel

class ProfileFragment : Fragment(), RecipeAdapter.OnItemClickListener {

    private lateinit var recipeViewModel: RecipeViewModel
    private lateinit var recipeAdapter: RecipeAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.profile_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize ViewModel
        recipeViewModel = ViewModelProvider(requireActivity()).get(RecipeViewModel::class.java)

        // Initialize RecyclerView and its adapter
        val recyclerView: RecyclerView = view.findViewById(R.id.profileRecyclerView)
        recipeAdapter = RecipeAdapter(this) // Pass the click listener

        recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = recipeAdapter
        }

        // Observe the LiveData containing the recipe list in the ViewModel
        recipeViewModel.recipeList.observe(viewLifecycleOwner) { recipes ->
            // Randomly select a few recipes (e.g., 3 recipes) for the profile screen
            val randomRecipes = recipes.shuffled().take(3)

            // Update the adapter with the selected recipes
            recipeAdapter.setData(randomRecipes)
        }
    }

    // Implement the onItemClick function from the RecipeAdapter's interface
    override fun onItemClick(id: String) {
        // Handle click event for a recipe item
        // You might navigate to the recipe detail fragment here
    }

}
