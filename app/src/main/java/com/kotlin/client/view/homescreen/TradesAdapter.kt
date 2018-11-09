package com.kotlin.client.view.homescreen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kotlin.client.R
import com.kotlin.core.entities.Trade
import kotlinx.android.synthetic.main.item_trade.view.*
import view.BaseAdapter


class TradesAdapter(private val trades: List<Trade>) : BaseAdapter<Trade>() {
    override fun getLayoutId(position: Int, obj: Trade): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindTrade(trade: Trade) {
            with(trade) {
                itemView.date.text = trade_date
                itemView.amount.text = "$amount"
                itemView.type.text = trade_type
            }
        }
    }

}
