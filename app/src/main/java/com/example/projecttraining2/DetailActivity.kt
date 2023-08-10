package com.example.projecttraining2

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.projecttraining2.data.response.FakeStoreAPIResponseItem
import com.example.projecttraining2.data.retrofit.ApiConfig
import com.example.projecttraining2.databinding.ActivityDetailBinding
import com.example.projecttraining2.databinding.ActivityHomeBinding
import com.example.projecttraining2.ui.home.HomeFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Integer.parseInt

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
        val id: String? = intent.getStringExtra(EXTRA_ITEM)

        getDetailProduct(id)
        // button back
        val backImg : ImageView = findViewById(R.id.back)
        backImg.setOnClickListener {
            finish()
        }
    }



    private fun getDetailProduct(id: String?) {
        val client = ApiConfig.getApiService().getProductsByID(id)
        client.enqueue(object : Callback<FakeStoreAPIResponseItem>{
            override fun onResponse(
                call: Call<FakeStoreAPIResponseItem>,
                response: Response<FakeStoreAPIResponseItem>
            ) {
                if (response.isSuccessful){
                    val responseBody = response.body()
                    if(responseBody!= null){
                        Log.e("DetailActivity", responseBody.title!!)
                        val name: TextView = binding.detailText
                        val img: ImageView = binding.detailImage
                        val price: TextView = binding.priceDetail
                        val desc: TextView = binding.descriptionDiscount
                        name.text = responseBody.title
                        Glide.with(this@DetailActivity).load(responseBody.image).into(img)
                        price.text = "$${responseBody.price.toString()}"
                        desc.text = responseBody.description.toString()

                        val btnWishlist : Button = binding.btnWishlist
                        btnWishlist.setOnClickListener {
                            val newWishlistItem = Wishlist(
                                title = responseBody.title.toString(),
                                description = responseBody.description.toString(),
                                image = responseBody.image.toString(),
                                price = responseBody.price.toString()
                            )
                            lifecycleScope.launch {
                                withContext(Dispatchers.IO){
                                    WishlistRoomDb.getDatabase(this@DetailActivity).wishlistDao().insertWishlist(newWishlistItem)
                                }
                            }
                            Toast.makeText(this@DetailActivity, "Berhasil menambahkan Wishlist", Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    Log.e("DetailActivity", "onFailure : $response.message}")
                }
            }

            override fun onFailure(call: Call<FakeStoreAPIResponseItem>, t: Throwable) {
                Log.e("DetailActivity", "onFailure : ${t.message}")
            }

        })
    }
}