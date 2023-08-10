package com.example.projecttraining2

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.ListAdapter
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.projecttraining2.data.response.FakeStoreAPIResponse
import com.example.projecttraining2.data.response.FakeStoreAPIResponseItem
import com.example.projecttraining2.databinding.ProductLayoutBinding

class ProductAdapter(val data: List<FakeStoreAPIResponseItem>?) : RecyclerView.Adapter<ProductAdapter.MyViewHolder>(){

    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClicked(data: FakeStoreAPIResponseItem)
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(get: FakeStoreAPIResponseItem?) {
            val imgPhoto: ImageView = itemView.findViewById(R.id.img_product)
            val nameProduct: TextView = itemView.findViewById(R.id.name_product)
            val price: TextView = itemView.findViewById(R.id.price)
            nameProduct.text = get?.title
            Glide.with(itemView.context).load(get?.image).into(imgPhoto)
            price.text = get?.price.toString()
        }
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductAdapter.MyViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.product_layout, parent, false)
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: ProductAdapter.MyViewHolder, position: Int) {
        holder.bind(data?.get(position))
        holder.itemView.setOnClickListener{
            data?.get(position)?.let { it1 -> onItemClickCallback.onItemClicked(it1) }
        }
    }

    override fun getItemCount(): Int = data?.size ?: 0


    //    companion object{
//        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<FakeStoreAPIResponseItem>() {
//            override fun areItemsTheSame(
//                oldItem: FakeStoreAPIResponseItem,
//                newItem: FakeStoreAPIResponseItem
//            ): Boolean {
//                return oldItem == newItem
//            }
//
//            override fun areContentsTheSame(
//                oldItem: FakeStoreAPIResponseItem,
//                newItem: FakeStoreAPIResponseItem
//            ): Boolean {
//                return oldItem == newItem
//            }
//        }
//    }
//
//    class MyViewHolder(private val binding: ProductLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
//        fun bind(product: FakeStoreAPIResponseItem){
//            val img: ImageView =itemView.findViewById(R.id.img_product)
//            binding.nameProduct.text = product.title
//            Glide.with(itemView.context).load(product.image).into(img)
//
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
//        val binding = ProductLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        return MyViewHolder(binding)
//    }
//
//    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        val product = getItem(position)
//        holder.bind(product)
//    }



}

//class ProductAdapter(private val listProductAdapter: ArrayList<Product>) : RecyclerView.Adapter<ProductAdapter.ListViewHolder>() {
//
//    private lateinit var onItemClickCallback: OnItemClickCallback
//
//    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val img: ImageView = itemView.findViewById(R.id.img_product)
//        val nameProduct: TextView = itemView.findViewById(R.id.name_product)
//
//    }
//
//    interface OnItemClickCallback {
//        fun onItemClicked(data: Product)
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
//        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.product_layout, parent, false)
//        return ListViewHolder(view)
//    }
//
//    override fun getItemCount(): Int = listProductAdapter.size
//
//    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
//        val (nameProduct, img) = listProductAdapter[position]
//        Glide.with(holder.itemView.context).load(img).into(holder.img)
//        holder.nameProduct.text = nameProduct
//        holder.itemView.setOnClickListener{
//            onItemClickCallback.onItemClicked(listProductAdapter[holder.adapterPosition])
//        }
//    }
//    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
//        this.onItemClickCallback = onItemClickCallback
//    }
//}