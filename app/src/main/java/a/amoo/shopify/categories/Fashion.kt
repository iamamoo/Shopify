package a.amoo.shopify.categories

import a.amoo.shopify.adapters.ProductAdapter
import a.amoo.shopify.databinding.ActivityFashionBinding
import a.amoo.shopify.models.MainCard
import a.amoo.shopify.viewmodels.FashionViewModel
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
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
            adapter.notifyItemChanged(fashionList.size)

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