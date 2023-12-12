import android.os.Build.VERSION_CODES.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tasty.recipesapp.R
import com.tasty.recipesapp.models.RecipeViewModel


class RecipesFragment : Fragment(), RecipeAdapter.OnItemClickListener {

    private lateinit var binding: FragmentRecipesBinding
    private val recipeAdapter = RecipeAdapter(this)
    private val recipeViewModel: RecipeViewModel by lazy {
        ViewModelProvider(this).get(RecipeViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecipesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerViewRecipes.adapter = recipeAdapter

        recipeViewModel.readAllRecipes(requireContext())
        recipeViewModel.recipeList.observe(viewLifecycleOwner) {
            recipeAdapter.setData(it)
        }

        binding.newRecipeButton.setOnClickListener {
            findNavController().navigate(R.id.action_recipesFragment_to_addEditRecipeFragment)
        }
    }

    override fun onItemClick(id: Int) {
        findNavController().navigate(R.id.action_recipesFragment_to_recipeDetailFragment, bundleOf("recipeId" to id))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Release the binding when the fragment's view is destroyed to avoid memory leaks
        binding = null
    }
}

//class RecipesFragment : Fragment(), RecipeAdapter.OnItemClickListener {
//
//    // itt peldanyositjuk az adaptert
//    private val recipeAdapter = RecipeAdapter(this)
//    private val binding get() = binding
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        binding.recyclerViewRecipes.adapter = recipeAdapter
//
//         //ez meg az allomanybol valo olvasas meghivasa
//        RecipeViewModel.readAllRecipes(requireContext())
//        RecipeViewModel.recipeList.observe(viewLifecycleOwner) {
//            // ha valtozik az adat update-et hivunk
//            recipeAdapter.setData(it)
//        super.onViewCreated(view, savedInstanceState)
//
//        binding.recipesRecyclerView.adapter = recipeAdapter
//
//        // Find the "New" button and set its click listener
//        binding.newRecipeButton.setOnClickListener {
//            // Navigate to the fragment for adding a new recipe
//            findNavController().navigate(R.id.action_recipesFragment_to_addEditRecipeFragment)
//        }
//        binding.recipesRecyclerView.adapter = recipeAdapter
//
//        // Find the "New" button and set its click listener
//        binding.newRecipeButton.setOnClickListener {
//            // Navigate to the NewRecipeFragment
//            findNavController().navigate(R.id.action_recipesFragment_to_newRecipeFragment)
//        }
//        }
//    }
//
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        // Inflate the layout for this fragment
//        _binding = FragmentRecipesBinding.inflate(inflater, container, false)
//        return binding.root
//    }
//
////    // es itt kezeljuk le a click event-et, atmegyunk mas fragment-re
//    override fun onItemClick(id: Int) {
//        findNavController().navigate(R.id.action_recipesFragment_to_recipeDetailFragment,  bundleOf("recipeId" to id))
//    }
//}}
