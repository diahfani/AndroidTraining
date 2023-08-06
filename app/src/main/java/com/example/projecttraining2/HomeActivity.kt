package com.example.projecttraining2

import android.app.Notification
import android.os.Bundle
import android.widget.SearchView
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projecttraining2.databinding.ActivityHomeBinding
import com.example.projecttraining2.ui.dashboard.DashboardFragment
import com.example.projecttraining2.ui.home.HomeFragment
import com.example.projecttraining2.ui.notifications.NotificationsFragment

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var recylerView: RecyclerView
    private val list = ArrayList<Product>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        replaceFragment(HomeFragment())

        binding.navView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.navigation_home -> replaceFragment(HomeFragment())
                R.id.navigation_dashboard -> replaceFragment(DashboardFragment())
                R.id.navigation_notifications -> replaceFragment(NotificationsFragment())

                else ->  {

                }
            }
            true
        }

//        recylerView = findViewById(R.id.recView1)
//        recylerView.setHasFixedSize(true)
//        list.addAll(getListProduct())
//        showRecyclerList()

//        recylerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

//        val navView: BottomNavigationView = binding.navView
//
//        val navController = findNavController(R.id.nav_host_fragment_activity_home)
////         Passing each menu ID as a set of Ids because each
////         menu should be considered as top level destinations.
//        val appBarConfiguration = AppBarConfiguration.Builder(
//                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
//        ).build()
//        setupActionBarWithNavController(navController, appBarConfiguration)
//        navView.setupWithNavController(navController)
    }

//    private fun showRecyclerList() {
//        recylerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
//        val productAdapter = ProductAdapter(list)
//        recylerView.adapter = productAdapter
//    }
//
//    private fun getListProduct(): ArrayList<Product> {
//        val name = resources.getStringArray(R.array.name_product)
//        val img = resources.getStringArray(R.array.img_product)
//        val listProduct = ArrayList<Product>()
//        for (i in name.indices){
//            val product = Product(name[i], img[i])
//            listProduct.add(product)
//        }
//        return listProduct
//    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.apply {
            replace(R.id.nav_host_fragment_activity_home, fragment)
            addToBackStack(null)
            commit()
        }
//        fragmentTransaction.replace(R.id.nav_host_fragment_activity_home, fragment)
//        fragmentTransaction.commit()
    }
}