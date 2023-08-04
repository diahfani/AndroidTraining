package com.example.projecttraining2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ProductAdapter(private val listProductAdapter: ArrayList<Product>) : RecyclerView.Adapter<ProductAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img: ImageView = itemView.findViewById(R.id.img_product)
        val nameProduct: TextView = itemView.findViewById(R.id.name_product)

    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Product)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.product_layout, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listProductAdapter.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (nameProduct, img) = listProductAdapter[position]
        Glide.with(holder.itemView.context).load(img).into(holder.img)
        holder.nameProduct.text = nameProduct
        holder.itemView.setOnClickListener{
            onItemClickCallback.onItemClicked(listProductAdapter[holder.adapterPosition])
        }
    }
    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
}