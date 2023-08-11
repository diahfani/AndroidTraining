package com.example.projecttraining2


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class WishlistDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wishlist_detail)

        val wishlistProduct = intent.getParcelableExtra<Wishlist>("Detail Wishlist")
        val title : TextView = findViewById(R.id.titleWishlist)
        val price : TextView = findViewById(R.id.priceDetail)
        val image : ImageView = findViewById(R.id.detailImageWishlist)
        val desc : TextView = findViewById(R.id.descriptionWishlist)

        if (wishlistProduct != null) {
            title.text = wishlistProduct.title
            price.text = wishlistProduct.price
            Glide.with(this).load(wishlistProduct.image).into(image)
            desc.text = wishlistProduct.description
        } else {
            Log.d("wishlist detail", "cannot get detail")
        }

        val backBtn : ImageView = findViewById(R.id.back)
        backBtn.setOnClickListener {
            finish()
        }

        val removeBtn : Button = findViewById(R.id.btnRemoveWishlist)
        removeBtn.setOnClickListener {
        if(wishlistProduct != null){
            lifecycleScope.launch {
                withContext(Dispatchers.IO){
                    WishlistRoomDb.getDatabase(this@WishlistDetailActivity).wishlistDao().deleteWishlist(wishlistProduct)
                }
            }
            Toast.makeText(this, "Wishlist berhasil dihapus", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }
        }

    }
}