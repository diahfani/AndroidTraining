package com.example.projecttraining2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class DiscountAdapter(private val listDiscountAdapter:ArrayList<Discount>) : RecyclerView.Adapter<DiscountAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: DiscountAdapter.OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClicked(data: Discount)
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img: ImageView = itemView.findViewById(R.id.img_discount)
        val nameProductDisc: TextView = itemView.findViewById(R.id.name_discount)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DiscountAdapter.ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.discount_layout, parent, false)
        return DiscountAdapter.ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: DiscountAdapter.ListViewHolder, position: Int) {
        val (nameProductDisc, img) = listDiscountAdapter[position]
        Glide.with(holder.itemView.context).load(img).into(holder.img)
        holder.nameProductDisc.text = nameProductDisc
        holder.itemView.setOnClickListener{
            onItemClickCallback.onItemClicked(listDiscountAdapter[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int = listDiscountAdapter.size
}