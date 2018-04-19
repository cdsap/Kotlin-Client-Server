package com.kotlin.client.presenter

import com.kotlin.core.entities.Trade
import com.kotlin.core.usecases.GetTrades
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.async
import javax.inject.Inject

class HomeScreenPresenter @Inject constructor(private val getTrades: GetTrades) {

    lateinit var view: ScreenView

    fun initView(view: ScreenView) {
        this.view = view
    }

    suspend fun getData(id: Long) {
        val result = async(CommonPool) { getTrades.getTrades(id) }.await()
        view.load(result.trades)
    }

    interface ScreenView {
        fun load(result: List<Trade>)
    }
}
