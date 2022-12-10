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
        binding.price.text = data.getStringExtra("price").toString()

        binding.buyNow.setOnClickListener {
            Toast.makeText(this@DetailActivity,"Purchased Successful", Toast.LENGTH_LONG).show()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.like){
            Toast.makeText(this@DetailActivity,item.title, Toast.LENGTH_LONG).show()

        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}