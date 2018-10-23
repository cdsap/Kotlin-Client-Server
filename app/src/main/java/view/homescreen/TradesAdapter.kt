package view.homescreen

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kotlin.client.R
import com.kotlin.core.entities.Trade
import kotlinx.android.synthetic.main.item.view.*
import view.BaseAdapter


class TradesAdapter(private val trades: List<Trade>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

//    override fun onBindViewHolder(holder: BaseHolder, position: Int) {
//        holder.bindItem(trades[position])
//    }


    override fun getItemCount() = trades.size

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
