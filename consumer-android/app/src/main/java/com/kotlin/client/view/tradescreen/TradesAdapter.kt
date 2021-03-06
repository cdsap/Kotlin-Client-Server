package com.kotlin.client.view.tradescreen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kotlin.client.R
import com.kotlin.core.domain.entities.Trade

class TradesAdapter(private val trades: List<Trade>) :
        RecyclerView.Adapter<TradesAdapter.TradesViewHolder>() {

    override fun onBindViewHolder(holder: TradesAdapter.TradesViewHolder, position: Int) {
        holder.bind(trades[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TradesAdapter.TradesViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_trade, parent, false)
        return TradesViewHolder(v)
    }

    override fun getItemCount(): Int = trades.size


    inner class TradesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var date: TextView = itemView.findViewById(R.id.date)
        var amount: TextView = itemView.findViewById(R.id.amount)
        var type: TextView = itemView.findViewById(R.id.type)

        fun bind(data: Trade) {
            date.text = data.trade_date
            amount.text = "${data.amount}"
            type.text = data.trade_type
        }
    }
}
