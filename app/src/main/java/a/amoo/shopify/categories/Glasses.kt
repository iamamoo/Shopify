package a.amoo.shopify.categories

import a.amoo.shopify.adapters.ProductAdapter
import a.amoo.shopify.databinding.ActivityGlassesBinding
import a.amoo.shopify.models.MainCard
import a.amoo.shopify.viewmodels.GlassesViewModel
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager

class Glasses : AppCompatActivity() {
    private lateinit var binding : ActivityGlassesBinding
    private var glassesList : List<MainCard> = ArrayList()
    private val glassesViewModel : GlassesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGlassesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.glassesTool)
        supportActionBar?.title = "Glasses"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        getData()



    }

    private fun getData() {

        glassesViewModel.getGlassesList().observe(this) {
            glassesList = it
            binding.glassesRec.hasFixedSize()
            binding.glassesRec.isNestedScrollingEnabled = false
            val adapter = ProductAdapter(glassesList, this)
            binding.glassesRec.adapter = adapter
            binding.glassesRec.layoutManager = GridLayoutManager(this@Glasses, 2)
           adapter.notifyDataSetChanged()

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