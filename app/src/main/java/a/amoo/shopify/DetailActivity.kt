package a.amoo.shopify

import a.amoo.shopify.databinding.ActivityDetailBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.squareup.picasso.Picasso

class DetailActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = intent
        setSupportActionBar(binding.detailTool)
        supportActionBar?.title = ""
        binding.category.text = data.getStringExtra("category")
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val url = data.getStringExtra("url")
        Picasso.get().load(url).into(binding.productImage)

        binding.title.text = data.getStringExtra("title")
        binding.description.text = data.getStringExtra("description")
        val p = data.getStringExtra("price").toString()
        binding.price.text = "$$p"

        binding.buyNow.setOnClickListener {
            Toast.makeText(this@DetailActivity,"Purchased Successful", Toast.LENGTH_LONG).show()
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}