package a.amoo.shopify.categories

import a.amoo.shopify.adapters.ProductAdapter
import a.amoo.shopify.databinding.ActivityJewelleryBinding
import a.amoo.shopify.models.MainCard
import a.amoo.shopify.viewmodels.JewelleryViewModel
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager

class Jewellery : AppCompatActivity() {
    private lateinit var binding : ActivityJewelleryBinding
    private var jewelList : List<MainCard> = ArrayList()
    private val jewelViewModel : JewelleryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJewelleryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.jewelleryTool)
        supportActionBar?.title = "Jewellery"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        getData()



    }

    private fun getData() {
        jewelViewModel.getJewelList().observe(this) {
            jewelList = it
            binding.jewelRec.hasFixedSize()
            binding.jewelRec.isNestedScrollingEnabled = false
            val adapter = ProductAdapter(jewelList, this)
            binding.jewelRec.adapter = adapter
            binding.jewelRec.layoutManager = GridLayoutManager(this@Jewellery, 2)
            adapter.notifyItemChanged(jewelList.size)

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