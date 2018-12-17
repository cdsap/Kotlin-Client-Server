package com.kotlin.client.view.tradescreen


import com.kotlin.core.entities.Trade
import com.kotlin.core.usecases.GetTrades
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import javax.inject.Inject

class TradesScreenPresenter @Inject constructor(private val getTrades: GetTrades) {

    lateinit var view: ScreenView

    fun initView(view: ScreenView) {
        this.view = view
    }

    suspend fun getData(id: Long) {
        val result = GlobalScope.async { getTrades.getTrades(id) }.await()
        view.load(result.trades)
    }

    suspend fun refreshData(id: Long) {
        val result = GlobalScope.async { getTrades.refreshTrades(id) }.await()
        view.load(result.trades)
    }

    interface ScreenView {
        fun load(result: List<Trade>)
    }
}
