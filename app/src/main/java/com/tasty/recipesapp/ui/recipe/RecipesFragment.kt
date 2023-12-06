import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tasty.recipesapp.R

class RecipesFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var recipeAdapter: RecipeAdapter
    private lateinit var recipeViewModel: RecipeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.recipes_fragment, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        recipeAdapter = RecipeAdapter(/* pass recipe list here */)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = recipeAdapter
        }

        // Initialize ViewModel and fetch recipes
        recipeViewModel = ViewModelProvider(this).get(RecipeViewModel::class.java)
        recipeViewModel.readAllRecipes(requireContext())

        // Observe the LiveData containing the recipe list
        recipeViewModel.recipeList.observe(viewLifecycleOwner) { recipes ->
            recipeAdapter.submitList(recipes)
        }

        return view
    }
}
