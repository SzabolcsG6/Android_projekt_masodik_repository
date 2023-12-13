import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tasty.recipesapp.R
import com.tasty.recipesapp.dtos.Recipe

//paramaterkent megkapja a listener-t, amit a fragment-ben kezelunk le
class RecipeAdapter(private var listener: OnItemClickListener) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    private val recipes: MutableList<Recipe> = mutableListOf()
    interface OnItemClickListener {
        fun onItemClick(id: Int)
    }

    inner class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        //implementalja a View.OnClickListener-t
		//Vigyazat!!! Ez nem ugyanaz, mint az altalunk megadott interface, ez az alap OnClick

		//....
        fun bind(recipe: Recipe) {
            // Bind data to views here, for example:
            itemView.setOnClickListener {
                listener.onItemClick(recipe.id)
            }
            // Set other views with recipe details using recipe properties
        }


        init {
			//listener beallitasa
            itemView.setOnClickListener(this)
        }

		// implementacio, ahol megadjuk a mi sajat listener-unknek, hogy mi lesz a recipe ID.
         override fun onClick(view: View) {
             val position = adapterPosition
             if (position != RecyclerView.NO_POSITION) {
				//innen fogja tudni, hogy mi az id
                // listener.onItemClick(recipes[position].id)
             }
         }
    }

	fun setData(list: List<Recipe>) {
        recipes.clear()
       recipes.addAll(list!!)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recipe_detail_fragment, parent, false)
        return RecipeViewHolder(itemView)
    }


    override fun getItemCount(): Int {
        // Return the size of your data list
        return recipes.size
    }


    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val currentRecipe = recipes[position]
        // Bind data to views in the ViewHolder
        holder.bind(currentRecipe)
    }



}

