package com.example.projecttraining2

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.inputmethod.InputBinding
import android.widget.Button
import android.widget.TableLayout
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.example.projecttraining2.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var btnCreateAccount: Button
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        btnCreateAccount = binding.btnCreateAccount
        btnCreateAccount.setOnClickListener(this)

        val dotsIndicator = findViewById<DotsIndicator>(R.id.dots_indicator)
        val viewPager = findViewById<ViewPager2>(R.id.viewPager)
        val adapter = OnBoardingViewPagerAdapter(this, this)
        viewPager.adapter = adapter
        dotsIndicator.attachTo(viewPager)

        val userPref = UserPreference(this)
        val login = userPref.getLogin()
        val onboardHasShown = userPref.getOnboard()

        if (!onboardHasShown){
            setContentView(view)
            userPref.setOnboard()
        } else if (login.isLogin) {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }  else {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

    }

    override fun onClick(p0: View?) {
        when(p0?.id) {
            R.id.btn_create_account -> {
                val moveIntent = Intent(this@MainActivity, LoginActivity::class.java)
                startActivity(moveIntent)
            }
        }

    }

}