package a.amoo.shopify.adapters

import a.amoo.shopify.DetailActivity
import a.amoo.shopify.R
import a.amoo.shopify.models.MainCard
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class ProductAdapter(private val list : List<MainCard>, private val context : Context) : RecyclerView.Adapter<ProductAdapter.InnerClass>() {

    inner class InnerClass(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val card : CardView = itemView.findViewById(R.id.product_card)
        val title : TextView = itemView.findViewById(R.id.product_title)
//        val description : TextView = itemView.findViewById(R.id.product_description)
//        val price : TextView = itemView.findViewById(R.id.product_price)
        val url : ImageView = itemView.findViewById(R.id.productImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InnerClass {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.product_sample,parent,false)
        return InnerClass(v)
    }

    override fun onBindViewHolder(holder: InnerClass, position: Int) {
        val model = list[position]
        Picasso.get().load(model.url).into(holder.url)
        val p = model.price.toString()
//        holder.price.text = "$$p"
        holder.title.text = model.title.toString()
//        holder.description.text = model.description.toString()

        holder.card.setOnClickListener {
            val intent = Intent(context,DetailActivity::class.java)
            intent.putExtra("category",model.category)
            intent.putExtra("url",model.url)
            intent.putExtra("title",model.title)
            intent.putExtra("price",model.price)
            intent.putExtra("description",model.description)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = list.size
}