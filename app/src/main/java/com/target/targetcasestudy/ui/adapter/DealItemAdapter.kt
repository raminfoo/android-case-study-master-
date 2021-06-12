package com.target.targetcasestudy.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.target.targetcasestudy.R
import com.target.targetcasestudy.databinding.DealListItemBinding
import com.target.targetcasestudy.network.data.DealsListData

class DealItemAdapter(
    val dealsList: List<DealsListData.Product>,
    private val clickListener: DealsListener
) :
    RecyclerView.Adapter<DealItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DealItemViewHolder {
        return DealItemViewHolder.from(parent)
    }

    override fun getItemCount(): Int {
        return dealsList.size
    }

    override fun onBindViewHolder(viewHolder: DealItemViewHolder, position: Int) {
        viewHolder.bind(dealsList[position])
        viewHolder.itemView.setOnClickListener {
            clickListener.onClick(dealsList[position])
        }
    }
}

class DealItemViewHolder(val binding: DealListItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: DealsListData.Product) {
        binding.dealList = item
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): DealItemViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = DealListItemBinding.inflate(layoutInflater, parent, false)
            return DealItemViewHolder(binding)
        }
    }

}


class DealsListener(val clickListener: (dealItem: DealsListData.Product) -> Unit) {
    fun onClick(dealItem: DealsListData.Product) = clickListener(dealItem)
}
