package com.oksama.guitargravity

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.oksama.guitargravity.databinding.ItemCardGuitarBinding

class ListGuitarAdapter(private val listGuitar: ArrayList<Guitar>) : RecyclerView.Adapter<ListGuitarAdapter.ListViewHolder>() {

    class ListViewHolder(var binding: ItemCardGuitarBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClicked(data: Guitar)
    }

    fun setOnClickCallback(onItemClickCallback :OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemCardGuitarBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int = listGuitar.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, picture, maker, price, description, isFav) = listGuitar[position]

        holder.binding.tvCardGuitarName.text = name
        Glide.with(holder.itemView.context).load(picture).into(holder.binding.ivGuitar)
        holder.binding.tvMaker.text = maker
        holder.binding.tvPrice.text = price

        holder.binding.btnReadMore.setOnClickListener{
            onItemClickCallback.onItemClicked(listGuitar[holder.adapterPosition])
        }
    }
}