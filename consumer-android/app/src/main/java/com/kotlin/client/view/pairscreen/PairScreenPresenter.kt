package com.kotlin.client.view.pairscreen

import com.kotlin.core.domain.entities.PairSymbol
import com.kotlin.core.domain.entities.usecases.GetPairs
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import javax.inject.Inject

class PairScreenPresenter @Inject constructor(private val getPairs: GetPairs) {

    lateinit var view: PairScreenPresenter.ScreenView

    fun initView(view: PairScreenPresenter.ScreenView) {
        this.view = view
    }

    suspend fun getData() {
        val result = GlobalScope.async { getPairs.get() }.await()
        view.load(result)
    }

    suspend fun refresh() {
        val result = GlobalScope.async { getPairs.sync() }.await()
        view.load(result)
    }

    interface ScreenView {
        fun load(pairs: List<PairSymbol>)
    }
}
