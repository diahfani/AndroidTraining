package com.example.projecttraining2

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.projecttraining2.data.response.FakeStoreAPIResponseItem
import com.example.projecttraining2.data.retrofit.ApiConfig
import com.example.projecttraining2.databinding.ActivityDetailBinding
import com.example.projecttraining2.databinding.ActivityHomeBinding
import com.example.projecttraining2.ui.home.HomeFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Integer.parseInt

class DetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_ITEM = "extra_item"
    }

    private lateinit var binding: ActivityDetailBinding
    private  var name: TextView? = null
    private  var img : ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)

        setContentView(binding.root)

        // GET DATA FROM PREVIOUS ACTIVITY

        val id: String? = intent.getStringExtra(EXTRA_ITEM)

        getDetailProduct(id)
        val name: TextView = binding.detailText
        val img: ImageView = binding.detailImage

//        val item = if (Build.VERSION.SDK_INT >= 33) {
//            intent.getParcelableExtra<FakeStoreAPIResponseItem>(EXTRA_ITEM, FakeStoreAPIResponseItem::class.java)
//        } else {
//            @Suppress("DEPRECATION")
//            intent.getParcelableExtra<FakeStoreAPIResponseItem>(EXTRA_ITEM)
//        }
//
//        if (item != null) {
//            name.text = item.title
//            Glide.with(this).load(item.image).into(img)
//        }

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
                        name.text = responseBody.title
                        Glide.with(this@DetailActivity).load(responseBody.image).into(img)
//                        val name: TextView = binding.detailText
//                        val img: ImageView = binding.detailImage
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