package view.pairscreen

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kotlin.client.R
import com.kotlin.core.entities.PairSymbol
import view.BaseAdapter


class PairSymbolAdapter(private val trades: List<PairSymbol>) : BaseAdapter<PairSymbol>() {
    override fun getLayoutId(position: Int, obj: PairSymbol): Int {
        return R.layout.item_pair
    }

    override fun getViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder {
        return PairViewHolder(view, viewType)
    }


    inner class PairViewHolder(itemView: View, viewType: Int) : RecyclerView.ViewHolder(itemView), BaseAdapter.Binder<PairSymbol> {
        var textView: TextView

        init {
            textView = itemView.findViewById(R.id.pair)
        }

        override fun bind(data: PairSymbol) {
            textView.text = "${data.primarySymbol}-${data.secondarySymbol}"
        }


    }
}
