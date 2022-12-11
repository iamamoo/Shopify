package a.amoo.shopify.adapters

import a.amoo.shopify.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.amoo.epro.models.CategoryCard

class CategoryAdapter(private val list: ArrayList<CategoryCard>) :
    RecyclerView.Adapter<CategoryAdapter.ViewHOlder>() {

    private lateinit var positionListener: OnItemClickListener

    interface OnItemClickListener {
        fun itemClickListener(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        positionListener = listener
    }


    inner class ViewHOlder(itemView: View, myListener: OnItemClickListener) :
        RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.category_image)
//        val title: TextView = itemView.findViewById(R.id.category_title)

        init {
            itemView.setOnClickListener {
                myListener.itemClickListener(adapterPosition)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHOlder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.category_sample, parent, false)
        return ViewHOlder(v, positionListener)
    }

    override fun onBindViewHolder(holder: ViewHOlder, position: Int) {
        val model = list[position]
        model.image?.let { holder.image.setImageResource(it) }
//        holder.title.text = model.title
    }

    override fun getItemCount(): Int = list.size


}