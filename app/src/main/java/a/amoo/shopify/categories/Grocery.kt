package a.amoo.shopify.categories

import a.amoo.shopify.R
import a.amoo.shopify.databinding.ActivityFashionBinding
import a.amoo.shopify.databinding.ActivityGroceryBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

class Grocery : AppCompatActivity() {
    private lateinit var binding : ActivityGroceryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGroceryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.groceryTool)
        supportActionBar?.title = "Grocery"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)




    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.tool_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.cart){
            Toast.makeText(this@Grocery,"Added to Cart", Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }



    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}