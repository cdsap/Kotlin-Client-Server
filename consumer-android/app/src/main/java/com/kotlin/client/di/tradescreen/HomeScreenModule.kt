package com.kotlin.client.di.tradescreen

import com.kotlin.client.view.tradescreen.TradesScreenPresenter
import com.kotlin.core.usecases.GetTrades
import dagger.Module
import dagger.Provides

@Module
class HomeScreenModule {
    @Provides
    fun providesPresenter(getTrades: GetTrades): TradesScreenPresenter {
        return TradesScreenPresenter(getTrades)
    }
}
