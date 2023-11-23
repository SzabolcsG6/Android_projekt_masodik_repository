package com.tasty.recipesapp.ui.recipe

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.tasty.recipesapp.R
import com.tasty.recipesapp.models.RecipeListViewModel



class RecipesFragment : Fragment() {

    private val recipeViewModel : RecipeListViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //requireActivity().title = "Recipes"

        recipeViewModel.loadInstructionData(requireContext())
        recipeViewModel.instructionModels.observe(viewLifecycleOwner) {  instructions ->
            for (instructionModel in instructions) {
                Log.d("Instructions", instructionModel.toString())
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.recipes_fragment, container, false)
    }//lehessen hozzaadni recepteket, listazni a recepteket
}