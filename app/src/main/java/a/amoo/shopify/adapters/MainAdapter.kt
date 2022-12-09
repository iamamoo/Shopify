package a.amoo.shopify.adapters

import a.amoo.shopify.R
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import a.amoo.shopify.DetailActivity
import com.amoo.epro.models.MainCard
import com.squareup.picasso.Picasso

class MainAdapter(private val list : ArrayList<MainCard>, private val context : Context) :
    RecyclerView.Adapter<MainAdapter.InnerClass>() {

        inner class InnerClass(itemView: View) : RecyclerView.ViewHolder(itemView){
            val image : ImageView = itemView.findViewById(R.id.mainCardImage)
            val card : CardView = itemView.findViewById(R.id.main_card)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InnerClass {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.maincard_sample,parent,false)
        return InnerClass(view)
    }

    override fun onBindViewHolder(holder: InnerClass, position: Int) {
        val model = list[position]
        Picasso.get().load(model.url).into(holder.image)
        holder.card.setOnClickListener {
            val intent = Intent(context,DetailActivity::class.java)
            intent.putExtra("url",model.url)
            intent.putExtra("category",model.category)
            intent.putExtra("title",model.title)
            intent.putExtra("description",model.description)
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int = list.size
}