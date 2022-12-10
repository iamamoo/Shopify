package a.amoo.shopify

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import a.amoo.shopify.databinding.ActivityMainBinding
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import a.amoo.shopify.adapters.CategoryAdapter
import a.amoo.shopify.adapters.MainAdapter
import a.amoo.shopify.categories.*
import com.amoo.epro.models.CategoryCard
import a.amoo.shopify.models.MainCard
import android.content.Intent
import com.google.firebase.firestore.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var categoryList: ArrayList<CategoryCard>
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.mainTool)
        supportActionBar?.title = ""
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        toggle = ActionBarDrawerToggle(
            this@MainActivity, binding.drawerLayout, binding.mainTool,
            R.string.open, R.string.close
        )

        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // MainCard Recyclerview
        getData()

        // Category RecyclerView
        categoryData()

        // Setting NavBar Item Click Listener
        binding.navigationView.setNavigationItemSelectedListener {

            when (it.itemId) {
                R.id.feedback -> {
                    Toast.makeText(this, "You click on ${it.title}", Toast.LENGTH_LONG).show()
                    binding.drawerLayout.closeDrawers()

                }
                R.id.privacy -> {
                    Toast.makeText(this, "You click on ${it.title}", Toast.LENGTH_LONG).show()
                    binding.drawerLayout.closeDrawers()

                }
                R.id.terrms -> {
                    Toast.makeText(this, "You click on ${it.title}", Toast.LENGTH_LONG).show()
                    binding.drawerLayout.closeDrawers()

                }
                R.id.exit -> {
                    Toast.makeText(this, "You click on ${it.title}", Toast.LENGTH_LONG).show()
                    binding.drawerLayout.closeDrawers()

                }


            }
            true
        }




    }

    private fun getData() {
        val list = ArrayList<MainCard>()
        db = FirebaseFirestore.getInstance()
        db.collection("main_card").addSnapshotListener(object : EventListener<QuerySnapshot>{
            override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                if (error != null){
                    Log.e("Firebase Error",error.localizedMessage!!)
                    return
                }
                for (d: DocumentChange in value!!.documentChanges){
                    if (d.type == DocumentChange.Type.ADDED){
                        list.add(d.document.toObject(MainCard::class.java))
                    }


                }
                binding.mainCard.layoutManager = LinearLayoutManager(this@MainActivity,
                    LinearLayoutManager.HORIZONTAL,false)
                val adapter = MainAdapter(list,this@MainActivity)
                binding.mainCard.adapter = adapter
                adapter.notifyDataSetChanged()

            }

        })

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.tool_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.cart) {
            Toast.makeText(this@MainActivity, "You clicked to cart", Toast.LENGTH_SHORT).show()
        }
        return true
    }

    private fun categoryData() {
        categoryList = ArrayList()
        categoryList.add(CategoryCard(R.drawable.shoes,"Shoes"))
        categoryList.add(CategoryCard(R.drawable.fashion,"Clothes"))
        categoryList.add(CategoryCard(R.drawable.watches,"Watches"))
        categoryList.add(CategoryCard(R.drawable.glasses,"Glasses"))
        categoryList.add(CategoryCard(R.drawable.jewelery,"Jewellery"))
        categoryList.add(CategoryCard(R.drawable.grocery,"Grocery"))

        binding.categoryCard.layoutManager = GridLayoutManager(this@MainActivity,2)
        val adapter = CategoryAdapter(categoryList)
        binding.categoryCard.adapter = adapter

        // Setting Click Listener on RecyclerView items...
        adapter.setOnItemClickListener(object : CategoryAdapter.OnItemClickListener{
            override fun itemClickListener(position: Int) {
                if (position == 0) {
                    val intent = Intent(this@MainActivity, Shoes::class.java)
                    startActivity(intent)
                }
                if (position == 1) {
                    val intent = Intent(this@MainActivity, Fashion::class.java)
                    startActivity(intent)
                }
                if (position == 2) {
                    val intent = Intent(this@MainActivity, Watches::class.java)
                    startActivity(intent)
                }
                if (position == 3) {
                    val intent = Intent(this@MainActivity, Glasses::class.java)
                    startActivity(intent)
                }
                if (position == 4) {
                    val intent = Intent(this@MainActivity, Jewellery::class.java)
                    startActivity(intent)
                }
                if (position == 5) {
                    val intent = Intent(this@MainActivity, Grocery::class.java)
                    startActivity(intent)
                }
            }

        })

    }
}