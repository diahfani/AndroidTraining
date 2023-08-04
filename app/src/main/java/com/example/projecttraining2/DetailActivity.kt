package com.example.projecttraining2

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.projecttraining2.databinding.ActivityDetailBinding
import com.example.projecttraining2.databinding.ActivityHomeBinding

class DetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_ITEM = "extra_item"
    }

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)

        setContentView(binding.root)

        // GET DATA FROM PREVIOUS ACTIVITY
        val name: TextView = binding.detailText
        val img: ImageView = binding.detailImage
        val item = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<Product>(EXTRA_ITEM, Product::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Product>(EXTRA_ITEM)
        }

        if (item != null) {
            name.text = item.name
            Glide.with(this).load(item.photo).into(img)
        }

        // button back
        val backImg : ImageView = findViewById(R.id.back)
        backImg.setOnClickListener {
            finish()
        }
    }
}