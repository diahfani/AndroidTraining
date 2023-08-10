package com.example.projecttraining2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.projecttraining2.data.IsLogin
import com.example.projecttraining2.databinding.ActivityHomeBinding
import com.example.projecttraining2.ui.wishlist.WishlistFragment
import com.example.projecttraining2.ui.home.HomeFragment
import com.example.projecttraining2.ui.profile.ProfileFragment

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
                R.id.navigation_dashboard -> replaceFragment(WishlistFragment())
                R.id.navigation_notifications -> replaceFragment(ProfileFragment())

                else ->  {

                }
            }
            true
        }

    }


    fun logout(view: View){
        val userPreference = UserPreference(view.context)
        val getlogin = userPreference.getLogin()
        val setlogin = IsLogin()
        setlogin.isLogin = false
        if (getlogin.isLogin) {
            userPreference.logout()
            startActivity(Intent(view.context, MainActivity::class.java))
            finish()
        }
    }
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