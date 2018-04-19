package com.kotlin.client.homescreen

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kotlin.client.R
import com.kotlin.core.entities.Trade
import kotlinx.android.synthetic.main.item.view.*


class TradesAdapter(private val trades: List<Trade>) : RecyclerView.Adapter<TradesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindFeedModel(trades[position])
    }

    override fun getItemCount() = trades.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindFeedModel(feedModel: Trade) {
            with(feedModel) {
                itemView.date.text = trade_date
                itemView.amount.text = "$amount"
                itemView.type.text = trade_type
            }
        }
    }

}
