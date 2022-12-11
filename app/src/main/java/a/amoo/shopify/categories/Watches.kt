package a.amoo.shopify.categories

import a.amoo.shopify.adapters.ProductAdapter
import a.amoo.shopify.databinding.ActivityWatchesBinding
import a.amoo.shopify.models.MainCard
import a.amoo.shopify.viewmodels.WatchesViewModel
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager

class Watches : AppCompatActivity() {
    private lateinit var binding: ActivityWatchesBinding
    private var watchList : List<MainCard> = ArrayList()
    private val watchViewModel : WatchesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWatchesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.watchesTool)
        supportActionBar?.title = "Watches"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        getData()

    }

    private fun getData() {
        watchViewModel.getWatchList().observe(this) {
            watchList = it
            binding.watchesRec.hasFixedSize()
            binding.watchesRec.isNestedScrollingEnabled = false
            val adapter = ProductAdapter(watchList, this)
            binding.watchesRec.adapter = adapter
            binding.watchesRec.layoutManager = GridLayoutManager(this@Watches, 2)
            adapter.notifyItemChanged(watchList.size)

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