package view.pairscreen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kotlin.client.R
import com.kotlin.core.entities.PairSymbol


class PairSymbolAdapter(private val pairs: List<PairSymbol>) : RecyclerView.Adapter<PairSymbolAdapter.PairViewHolder>() {


    override fun onBindViewHolder(holder: PairViewHolder, position: Int) =
            holder.bind(pairs[position])


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PairViewHolder {

        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_pair, parent, false)
        return PairViewHolder(v)

    }

    override fun getItemCount(): Int = pairs.size


    inner class PairViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textView: TextView

        init {
            textView = itemView.findViewById(R.id.pair)
        }

        fun bind(data: PairSymbol) {
            textView.text = "${data.primarySymbol}-${data.secondarySymbol}"
        }


    }
}
