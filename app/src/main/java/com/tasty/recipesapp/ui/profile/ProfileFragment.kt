package com.tasty.recipesapp.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.PopupMenu
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.tasty.recipesapp.R
import com.tasty.recipesapp.databinding.FragmentProfileBinding
import com.tasty.recipesapp.repository.recipe.RecipeDatabase
import com.tasty.recipesapp.repository.recipe.model.RecipeModel
import com.tasty.recipesapp.ui.App
import com.tasty.recipesapp.ui.recipe.viewmodel.ProfileViewModel
import com.tasty.recipesapp.ui.profile.viewmodel.factory.ProfileViewModelFactory
import com.tasty.recipesapp.ui.recipe.adapter.RecipesListAdapter



class ProfileFragment : Fragment() {


    companion object {
       // private val TAG: String? = ProfileFragment::class.java.canonicalName
        const val BUNDLE_EXTRA_SELECTED_RECIPE_ID = "selected_recipe_id"
    }

    private lateinit var binding: FragmentProfileBinding
    private lateinit var recipesAdapter: RecipesListAdapter
    private lateinit var viewModel: ProfileViewModel
    private lateinit var popupMenu: PopupMenu
    private val database by lazy { RecipeDatabase.getDatabase(requireContext()) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        initRecyclerView()
        binding.addButton.setOnClickListener {
            navigateToNewRecipe()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

      //   Pass the context when creating the ViewModel
        val factory = ProfileViewModelFactory(
            (activity?.application as App).repository)
        viewModel = ViewModelProvider(this, factory)[ProfileViewModel::class.java]

       // viewModel.loadRecipes()
viewModel.myRecipesList
        //Filebol receptek
//viewModel.fetchMyRecipesData()


        viewModel.myRecipesList.observe(viewLifecycleOwner) { myRecipes ->
            recipesAdapter.setData(myRecipes)

            recipesAdapter.notifyItemRangeChanged(0, myRecipes.lastIndex)


        }


        viewModel.deleteResult.observe(viewLifecycleOwner) { result ->
            val message = if (result) {
                "Recipe removed successfully"
            } else {
                "Failed to remove recipe"
            }
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()}

        viewModel.fetchRecipesFromDatabase()
//viewModel.deleteAllRecipes()

        val sortButton: Button = view.findViewById(R.id.sortButton)
        val sortButton2: Button = view.findViewById(R.id.sortButton2)
        val sortButton3: Button = view.findViewById(R.id.sortButton3)
        val sortButton4: Button = view.findViewById(R.id.sortButton4)
        val chooserButton : FloatingActionButton = view.findViewById(R.id.chooserButton)
        val searchButton : Button = view.findViewById(R.id.searchButton)
//val deleteButton : ImageButton = view.findViewById((R.id.deleteButton))
searchButton.visibility=View.GONE
chooserButton.visibility=View.GONE
        sortButton.visibility = View.GONE
        sortButton2.visibility = View.GONE
        sortButton3.visibility = View.GONE
        sortButton4.visibility = View.GONE
        //favoritesButton.visibility.View.GONE

        popupMenu = PopupMenu(requireContext(), chooserButton)
        popupMenu.menuInflater.inflate(R.menu.sort_menu, popupMenu.menu)
// Show the PopupMenu when the chooserButton is clicked
        chooserButton.setOnClickListener {
            popupMenu.show()
        }
        // Handle item click in the PopupMenu
        popupMenu.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.sortButton -> {
                    viewModel.getRecipesSortedByRatingDatabase()
                    viewModel.myRecipesList.observe(viewLifecycleOwner) { recipes ->
                        recipesAdapter.setData2(recipes)
                        recipesAdapter.notifyDataSetChanged()
                        scrollToTop()
                    }
                    true // Indicate that the menu item click is handled
                }
                R.id.sortButton2 -> {
                    viewModel.getRecipesSortedByRatingAscendingDatabase()
                    viewModel.myRecipesList.observe(viewLifecycleOwner) { recipes ->
                        recipesAdapter.setData2(recipes)
                        recipesAdapter.notifyDataSetChanged()
                        scrollToTop()
                    }
                    true // Indicate that the menu item click is handled
                }
                R.id.sortButton3 -> {
                    viewModel.getRecipesSortedByNameDatabase()
                    viewModel.myRecipesList.observe(viewLifecycleOwner) { recipes ->
                        recipesAdapter.setData2(recipes)
                        recipesAdapter.notifyDataSetChanged()
                        scrollToTop()
                    }
                    true // Indicate that the menu item click is handled
                }
                R.id.sortButton4 -> {
                    viewModel.getRecipesSortedByNameAscendingDatabase()
                    viewModel.myRecipesList.observe(viewLifecycleOwner) { recipes ->
                        recipesAdapter.setData2(recipes)
                        recipesAdapter.notifyDataSetChanged()
                        scrollToTop()
                    }
                    true // Indicate that the menu item click is handled
                }
                else -> false // Indicate that the menu item click is not handled
            }
        }
      //  viewModel.fetchRecipesFromDatabase()
//deleteButton.setOnClickListener {
//    //viewModel.deleteRecipe(recipe)
//}
    }

    private fun initRecyclerView() {
        recipesAdapter = RecipesListAdapter(
            ArrayList(),
            requireContext(),
            onItemClickListener = { recipe -> navigateToRecipeDetail(recipe) },
           // onItemLongClickListener = { recipe -> viewModel.deleteRecipe(recipe) },
            onAddToFavoritesClick = { recipe -> viewModel.insertFavoriteRecipe(recipe) },
            onDeleteClickListener = { recipe ->
                // Delete the recipe from the database
                viewModel.deleteRecipe(recipe)

            }
        )

        binding.recyclerView.adapter = recipesAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                activity,
                DividerItemDecoration.VERTICAL
            )
        )

    }


    private fun navigateToRecipeDetail(recipe: RecipeModel) {
        findNavController().navigate(
            R.id.action_profileFragment_to_recipeDetailFragment,
            bundleOf(BUNDLE_EXTRA_SELECTED_RECIPE_ID to recipe.id)
        )
    }

    private fun navigateToNewRecipe() {
        findNavController().navigate(
            R.id.action_profileFragment_to_newRecipeFragment
        )
    }

    private fun scrollToTop() {
        binding.recyclerView.scrollToPosition(0) // Scrolls to the top of the RecyclerView
    }
}