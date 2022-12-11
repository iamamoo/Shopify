package a.amoo.shopify.categories

import a.amoo.shopify.adapters.ProductAdapter
import a.amoo.shopify.databinding.ActivityShoesBinding
import a.amoo.shopify.models.MainCard
import a.amoo.shopify.viewmodels.ShoeViewModel
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager

class Shoes : AppCompatActivity() {
    private lateinit var binding : ActivityShoesBinding
    private var shoeList : List<MainCard> = ArrayList()
    private val shoeViewModel : ShoeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShoesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.shoesTool)
        supportActionBar?.title = "Shoes"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        getData()



    }

    private fun getData() {
        shoeViewModel.getShoesList().observe(this) {
            shoeList = it
            binding.shoesRec.hasFixedSize()
            binding.shoesRec.isNestedScrollingEnabled = false
            val adapter = ProductAdapter(shoeList, this)
            binding.shoesRec.adapter = adapter
            binding.shoesRec.layoutManager = GridLayoutManager(this@Shoes, 2)
            adapter.notifyItemChanged(shoeList.size)

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