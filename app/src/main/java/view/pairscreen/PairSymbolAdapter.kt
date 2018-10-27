package view.pairscreen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kotlin.client.R
import com.kotlin.core.entities.PairSymbol
import com.kotlin.core.entities.Trade
import kotlinx.android.synthetic.main.item_pair.view.*
import kotlinx.android.synthetic.main.item_trade.view.*
import view.BaseAdapter


class PairSymbolAdapter(private val trades: List<PairSymbol>) : BaseAdapter<PairSymbol>() {
    override fun getLayoutId(position: Int, obj: PairSymbol): Int {
        return 1
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindTrade(pairSymbol: PairSymbol) {
            with(pairSymbol) {
                itemView.pair.text = "${pairSymbol.primarySymbol}-${pairSymbol.secondarySymbol}"
               // itemView.rate.text = "${pairSymbol.rate}"
            }
        }
    }
}
