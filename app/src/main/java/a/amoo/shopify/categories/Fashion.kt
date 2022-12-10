package a.amoo.shopify.categories

import a.amoo.shopify.R
import a.amoo.shopify.adapters.ProductAdapter
import a.amoo.shopify.databinding.ActivityFashionBinding
import a.amoo.shopify.models.MainCard
import a.amoo.shopify.repository.ShoeRepo
import a.amoo.shopify.viewmodels.FashionViewModel
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager

class Fashion : AppCompatActivity() {
    private lateinit var binding : ActivityFashionBinding

    private var fashionList : List<MainCard> = ArrayList()


    private val fashionViewModel : FashionViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFashionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.fashTool)
        supportActionBar?.title = "Clothes"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        binding.progressBar.visibility = View.VISIBLE

        // make request to get data from database...
        getData()




    }
    private fun getData() {

        fashionViewModel.getFashionList().observe(this) {
            fashionList = it
            binding.fashionRec.hasFixedSize()
            binding.fashionRec.isNestedScrollingEnabled = false
            val adapter = ProductAdapter(fashionList, this)
            binding.fashionRec.adapter = adapter
            binding.fashionRec.layoutManager = GridLayoutManager(this@Fashion, 2)
            adapter.notifyDataSetChanged()

            //Loading complete
            binding.progressBar.visibility = View.GONE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.tool_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.cart){
            Toast.makeText(this@Fashion,"Added to Cart", Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }



    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}