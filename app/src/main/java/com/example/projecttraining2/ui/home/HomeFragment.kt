package com.example.projecttraining2.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projecttraining2.DetailActivity
import com.example.projecttraining2.Discount
import com.example.projecttraining2.DiscountAdapter
import com.example.projecttraining2.Product
import com.example.projecttraining2.ProductAdapter
import com.example.projecttraining2.R
import com.example.projecttraining2.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var rcView: RecyclerView
    private lateinit var rcViewDiscount: RecyclerView
    private val listproduct = ArrayList<Product>()
    private val listDiscount = ArrayList<Discount>()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        _binding = FragmentHomeBinding.inflate(layoutInflater)
        rcView = root.findViewById(R.id.recView1)
        rcView.setHasFixedSize(true)
        rcViewDiscount = root.findViewById(R.id.rcViewDiscount)
        rcViewDiscount.setHasFixedSize(true)
        listproduct.addAll(getListProduct())
        listDiscount.addAll(getListDiscount())
        showRecyclerListProduct()
        showRecyclerListDiscount()

//        val textView: TextView = binding.textHome
//        homeViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
        return root


    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        _binding = FragmentHomeBinding.inflate(layoutInflater)
//        rcView = view.findViewById(R.id.recView1)
//        rcView.setHasFixedSize(true)
//        list.addAll(getListProduct())
//        showRecyclerList()
//    }

    private fun showRecyclerListProduct() {
        rcView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, true)
        val productAdapter = ProductAdapter(listproduct)
        rcView.adapter = productAdapter
        productAdapter.setOnItemClickCallback(object : ProductAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Product) {
                val moveWithObjectIntent = Intent(context, DetailActivity::class.java)
                moveWithObjectIntent.putExtra(DetailActivity.EXTRA_ITEM, data)
                startActivity(moveWithObjectIntent)
            }
        })
    }

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