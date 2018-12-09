package com.kotlin.client.view.pairscreen

import com.kotlin.core.entities.PairSymbol
import com.kotlin.core.usecases.GetPairs
import com.kotlin.core.usecases.SyncTrades
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import javax.inject.Inject

class PairScreenPresenter @Inject constructor(private val getPairs: GetPairs,
                                              private val syncPairs: SyncTrades) {

    lateinit var view: PairScreenPresenter.ScreenView

    fun initView(view: PairScreenPresenter.ScreenView) {
        this.view = view
    }

    suspend fun getData() {

        val result = GlobalScope.async { getPairs.get() }.await()
        view.load(result)
    }

    suspend fun refresh() {
        val result = GlobalScope.async { syncPairs.syncTrades() }.await()
        //  view.load(result)
    }

    interface ScreenView {
        fun load(pairs: List<PairSymbol>)
    }
}
