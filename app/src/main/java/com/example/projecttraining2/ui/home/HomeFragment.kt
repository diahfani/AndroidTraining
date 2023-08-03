package com.example.projecttraining2.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projecttraining2.Product
import com.example.projecttraining2.ProductAdapter
import com.example.projecttraining2.R
import com.example.projecttraining2.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var rcView: RecyclerView
    private val list = ArrayList<Product>()
    private lateinit var pAdapter: ProductAdapter

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
        list.addAll(getListProduct())
        showRecyclerList()

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

    private fun showRecyclerList() {
        rcView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, true)
        val productAdapter = ProductAdapter(list)
        rcView.adapter = productAdapter
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}