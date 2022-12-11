package a.amoo.shopify.categories

import a.amoo.shopify.R
import a.amoo.shopify.adapters.ProductAdapter
import a.amoo.shopify.databinding.ActivityFashionBinding
import a.amoo.shopify.databinding.ActivityGroceryBinding
import a.amoo.shopify.models.MainCard
import a.amoo.shopify.viewmodels.FashionViewModel
import a.amoo.shopify.viewmodels.GroceryViewModel
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager

class Grocery : AppCompatActivity() {
    private lateinit var binding : ActivityGroceryBinding
    private var groceryList : List<MainCard> = ArrayList()
    private val groceryViewModel : GroceryViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGroceryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.groceryTool)
        supportActionBar?.title = "Grocery"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        getData()


    }


    private fun getData() {
        groceryViewModel.getGroceryList().observe(this) {
            groceryList = it
            binding.glassesRec.hasFixedSize()
            binding.glassesRec.isNestedScrollingEnabled = false
            val adapter = ProductAdapter(groceryList, this)
            binding.glassesRec.adapter = adapter
            binding.glassesRec.layoutManager = GridLayoutManager(this@Grocery, 2)
            adapter.notifyItemChanged(groceryList.size)

            //Loading complete
            binding.progressBar.visibility = View.GONE
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        finish()
        super.onBackPressed()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}