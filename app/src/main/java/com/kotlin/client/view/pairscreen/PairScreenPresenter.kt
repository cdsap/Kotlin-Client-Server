package com.kotlin.client.view.pairscreen

import com.kotlin.core.entities.PairSymbol
import com.kotlin.core.usecases.GetPairs
import javax.inject.Inject

class PairScreenPresenter @Inject constructor(private val getPairs: GetPairs) {

    lateinit var view: PairScreenPresenter.ScreenView

    fun initView(view: PairScreenPresenter.ScreenView) {
        this.view = view
    }

    suspend fun getData() {
       // val result = async(CommonPool) { getPairs.get() }.await()
       // view.load(result)
    }

    interface ScreenView {
        fun load(pairs: List<PairSymbol>)
    }
}
