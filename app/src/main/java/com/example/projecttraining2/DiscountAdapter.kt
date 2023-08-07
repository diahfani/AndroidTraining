package com.example.projecttraining2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.projecttraining2.data.response.FakeStoreAPIResponseItem


class DiscountAdapter(val listDiscountAdapter:List<FakeStoreAPIResponseItem>?) : RecyclerView.Adapter<DiscountAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: DiscountAdapter.OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClicked(data: Discount)
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(get: FakeStoreAPIResponseItem?) {
            val imgDiscount: ImageView = itemView.findViewById(R.id.img_discount)
            val nameDiscount: TextView = itemView.findViewById(R.id.name_discount)
            nameDiscount.text = get?.title
            Glide.with(itemView.context).load(get?.image).into(imgDiscount)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DiscountAdapter.ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.discount_layout, parent, false)
        return DiscountAdapter.ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: DiscountAdapter.ListViewHolder, position: Int) {
        holder.bind(listDiscountAdapter?.get(position))
    }

    override fun getItemCount(): Int = listDiscountAdapter?.size ?: 0
}