package com.android.aop.part2.toycoininformation.presentation.adapter.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.aop.part2.toycoininformation.R
import com.android.aop.part2.toycoininformation.databinding.ItemCoinBinding
import com.android.aop.part2.toycoininformation.domain.model.Coin

class CoinViewHolder  (parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_coin, parent, false)
) {
    private val binding = ItemCoinBinding.bind(itemView)

    fun bind(
        item: Coin,
        onItemClick : (Coin) -> Unit
    ) {
        binding.item = item

        itemView.setOnClickListener {
            onItemClick(item)
        }
    }

}