import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tasty.recipesapp.models.RecipeViewModel

 paramaterkent megkapja a listener-t, amit a fragment-ben kezelunk le
class RecipeAdapter(private var listener: OnItemClickListener) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {


    interface OnItemClickListener {
        fun onItemClick(id: Int)
    }

    inner class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        //implementalja a View.OnClickListener-t
		//Vigyazat!!! Ez nem ugyanaz, mint az altalunk megadott interface, ez az alap OnClick

		//....


        init {
			//listener beallitasa
            itemView.setOnClickListener(this)
        }

		// implementacio, ahol megadjuk a mi sajat listener-unknek, hogy mi lesz a recipe ID.
         override fun onClick(view: View) {
             val position = adapterPosition
             if (position != RecyclerView.NO_POSITION) {
				//innen fogja tudni, hogy mi az id
                 listener.onItemClick(recipes[position].id)
             }
         }
    }

	fun setData(list: List<RecipeViewModel>?) {
        recipes.clear()
        recipes.addAll(list!!)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    //.....

}

