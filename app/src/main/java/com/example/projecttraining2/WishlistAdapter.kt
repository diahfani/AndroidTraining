package com.example.projecttraining2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.projecttraining2.data.response.FakeStoreAPIResponseItem


class WishlistAdapter(private var wishlist: List<Wishlist>) : RecyclerView.Adapter<WishlistAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClicked(data: Wishlist)
    }


    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)  {
        fun bind(get: Wishlist){
            val nameWl: TextView = itemView.findViewById(R.id.name_product)
            val imgWl : ImageView = itemView.findViewById(R.id.img_product)
            val price: TextView = itemView.findViewById(R.id.price)
            nameWl.text = get?.title
            Glide.with(itemView.context).load(get?.image).into(imgWl)
            price.text = get?.price.toString()
        }
    }

    fun setOnItemClickCallback(onItemClickCallback: WishlistAdapter.OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun updateData(newWishlist: List<Wishlist>){
        wishlist = newWishlist
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WishlistAdapter.ListViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.product_layout, parent, false)
        return WishlistAdapter.ListViewHolder(v)
    }

    override fun onBindViewHolder(holder: WishlistAdapter.ListViewHolder, position: Int) {
        holder.bind(wishlist[position])
        holder.itemView.setOnClickListener{
            wishlist[position]?.let { it1 -> onItemClickCallback.onItemClicked(it1) }
        }
    }

    override fun getItemCount(): Int = wishlist?.size ?: 0
}