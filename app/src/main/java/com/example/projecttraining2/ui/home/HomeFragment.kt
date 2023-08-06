package com.example.projecttraining2.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.graphics.createBitmap
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.projecttraining2.DetailActivity
import com.example.projecttraining2.Discount
import com.example.projecttraining2.DiscountAdapter
import com.example.projecttraining2.Product
import com.example.projecttraining2.ProductAdapter
import com.example.projecttraining2.R
import com.example.projecttraining2.data.response.FakeStoreAPIResponse
import com.example.projecttraining2.data.response.FakeStoreAPIResponseItem
import com.example.projecttraining2.data.retrofit.ApiConfig
import com.example.projecttraining2.databinding.FragmentHomeBinding
import com.example.projecttraining2.databinding.ProductLayoutBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var bindProduct : ProductLayoutBinding
    private lateinit var rcView: RecyclerView
    private lateinit var rcViewDiscount: RecyclerView
//    private val listproduct = ArrayList<Product>()
    private val listDiscount = ArrayList<Discount>()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    companion object {
        private const val TAG = "HomeFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        bindProduct = ProductLayoutBinding.inflate(layoutInflater)
        _binding = FragmentHomeBinding.inflate(layoutInflater)
        rcView = root.findViewById(R.id.recView1)
        rcView.setHasFixedSize(true)
        rcViewDiscount = root.findViewById(R.id.rcViewDiscount)
        rcViewDiscount.setHasFixedSize(true)
//        listproduct.addAll(getListProduct())
        listDiscount.addAll(getListDiscount())
//        showRecyclerListProduct()
        showRecyclerListDiscount()

        // coba retrofit
        rcView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, true)
        val itemDecoration = DividerItemDecoration(context, LinearLayoutManager(context).orientation)
        rcView.addItemDecoration(itemDecoration)

        getProduct()



//        val textView: TextView = binding.textHome
//        homeViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
        return root


    }

    private fun getProduct() {
        val client = ApiConfig.getApiService().getProducts()
        client.enqueue(object : Callback<List<FakeStoreAPIResponseItem>> {

            override fun onResponse(
                call: Call<List<FakeStoreAPIResponseItem>>,
                response: Response<List<FakeStoreAPIResponseItem>>
            ) {
                if (response.isSuccessful){
//                    for(i in response.body()!!.iterator()) {
//                        Log.e(TAG, i.title.toString())
//                    }
                    val responseBody = response.body()

                    if (responseBody != null) {
                        rcView.adapter = ProductAdapter(responseBody)
                    }
                } else {
                    Log.e(TAG, "onFailure : $response.message}")
                }
            }

            override fun onFailure(call: Call<List<FakeStoreAPIResponseItem>>, t: Throwable) {
                Log.e(TAG, "onFailure : ${t.message}")
            }
        })
    }

    private fun setProductsData(products: List<FakeStoreAPIResponseItem>) {
        val adapter = ProductAdapter(products)
//        for (i in products) {
//            Log.e(TAG, i.title.toString())
//            bindProduct.nameProduct.text = i?.title
//            Glide.with(View(context)).load(i?.image).into(bindProduct.imgProduct)
//        }
//        adapter.submitList(products)
        binding.recView1.adapter = adapter

    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        _binding = FragmentHomeBinding.inflate(layoutInflater)
//        rcView = view.findViewById(R.id.recView1)
//        rcView.setHasFixedSize(true)
//        list.addAll(getListProduct())
//        showRecyclerList()
//    }

//    private fun showRecyclerListProduct() {
//        rcView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, true)
//        val productAdapter = ProductAdapter(listproduct)
//        rcView.adapter = productAdapter
//        productAdapter.setOnItemClickCallback(object : ProductAdapter.OnItemClickCallback{
//            override fun onItemClicked(data: Product) {
//                val moveWithObjectIntent = Intent(context, DetailActivity::class.java)
//                moveWithObjectIntent.putExtra(DetailActivity.EXTRA_ITEM, data)
//                startActivity(moveWithObjectIntent)
//            }
//        })
//    }

    private fun showRecyclerListDiscount() {
        rcViewDiscount.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, true)
        val discountadapter = DiscountAdapter(listDiscount)
        rcViewDiscount.adapter = discountadapter
    }

    private fun getListProduct(): ArrayList<Product> {
        val name = resources.getStringArray(R.array.name_product)
        val img = resources.getStringArray(R.array.img_product)
        val listProduct = ArrayList<Product>()
        for (i in name.indices){
            val product = Product(name[i], img[i])
            listProduct.add(product)
        }
        return listProduct
    }

    private fun getListDiscount(): ArrayList<Discount> {
        val name = resources.getStringArray(R.array.discount_product)
        val img = resources.getStringArray(R.array.img_discount)
        val listdiscount = ArrayList<Discount>()
        for (i in name.indices){
            val discount = Discount(name[i], img[i])
            listdiscount.add(discount)
        }
        return listdiscount
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}