package com.example.projecttraining2.ui.wishlist

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.example.projecttraining2.App
import com.example.projecttraining2.DetailActivity
import com.example.projecttraining2.Wishlist
import com.example.projecttraining2.WishlistAdapter
import com.example.projecttraining2.WishlistDetailActivity
import com.example.projecttraining2.WishlistRoomDb
import com.example.projecttraining2.databinding.FragmentWishlistBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WishlistFragment : Fragment() {

    private var _binding: FragmentWishlistBinding? = null
    private lateinit var wishlistAdapter: WishlistAdapter
    private lateinit var rvWishlist: RecyclerView
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWishlistBinding.inflate(inflater, container, false)
        val root: View = binding.root
        rvWishlist = binding.rcViewWishlist
        rvWishlist.layoutManager = GridLayoutManager(context, 2)
        wishlistAdapter = WishlistAdapter(emptyList())
        rvWishlist.adapter = wishlistAdapter
//        val textView: TextView = binding.searchView
//        textView.text = "Belum ada wishlist"
//        dashboardViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        wishlistAdapter.setOnItemClickCallback(object : WishlistAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Wishlist) {
                val moveWithObjIntent = Intent(context, WishlistDetailActivity::class.java)
                moveWithObjIntent.putExtra("Detail Wishlist", data)
                startActivity(moveWithObjIntent)
            }
        })

        lifecycleScope.launch {
            val wishlist = withContext(Dispatchers.IO) {
                WishlistRoomDb.getDatabase(requireContext()).wishlistDao().getAllWishlist()
            }
            wishlistAdapter.updateData(wishlist)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}