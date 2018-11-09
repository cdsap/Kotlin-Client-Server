package com.kotlin.client.view.pairscreen


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.kotlin.client.R
import com.kotlin.core.entities.PairSymbol


interface PairListListener {
    fun onPairClicked(id: Long)
}

class PairSymbolAdapter(private val pairs: List<PairSymbol>,
                        val listener: PairListListener) : RecyclerView.Adapter<PairSymbolAdapter.PairViewHolder>() {


    override fun onBindViewHolder(holder: PairViewHolder, position: Int) =
            holder.bind(pairs[position])


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PairViewHolder {

        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_pair, parent, false)
        val holder = PairViewHolder(v)
        return holder
    }

    override fun getItemCount(): Int = pairs.size


    inner class PairViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textView: TextView
        var cardView: CardView

        init {
            textView = itemView.findViewById(R.id.pair)
            cardView = itemView.findViewById(R.id.cardView)
        }

        fun bind(data: PairSymbol) {
            textView.text = "${data.primarySymbol}-${data.secondarySymbol}"
            cardView.setOnClickListener {
                listener.onPairClicked(data.id)
            }

        }
    }
}