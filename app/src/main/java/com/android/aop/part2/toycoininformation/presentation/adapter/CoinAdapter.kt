package com.android.aop.part2.toycoininformation.presentation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.aop.part2.toycoininformation.domain.model.Coin
import com.android.aop.part2.toycoininformation.presentation.adapter.viewholder.CoinViewHolder

class CoinAdapter (val onItemClickListener: (Coin) -> Unit) : RecyclerView.Adapter<CoinViewHolder>() {

    private val coinList = mutableListOf<Coin>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder =
        CoinViewHolder(parent)

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        holder.bind(coinList[position], onItemClickListener)
    }

    override fun getItemCount(): Int =
        coinList.size

    fun addAll(list: List<Coin>) {
        coinList.clear()
        coinList.addAll(list)
        notifyDataSetChanged()
    }
}