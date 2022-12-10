package a.amoo.shopify.categories

import a.amoo.shopify.R
import a.amoo.shopify.databinding.ActivityFashionBinding
import a.amoo.shopify.databinding.ActivityJewelleryBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

class Jewellery : AppCompatActivity() {
    private lateinit var binding : ActivityJewelleryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJewelleryBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setSupportActionBar(binding.jewelleryTool)
        supportActionBar?.title = ""
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val data = intent
        binding.category.text = data.getStringExtra("category").toString()




    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.tool_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.cart){
            Toast.makeText(this@Jewellery,"Added to Cart", Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }



    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}