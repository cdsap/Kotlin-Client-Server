package com.kotlin.client.di.homescreen

import com.kotlin.client.database.TradeDb
import com.kotlin.client.domain.GetTradesImpl
import com.kotlin.client.view.homescreen.HomeScreenPresenter
import com.kotlin.core.repository.TradesRepository
import com.kotlin.core.usecases.GetTrades
import dagger.Module
import dagger.Provides

@Module
class HomeScreenModule {
    @Provides
    fun providesPresenter(getTrades: GetTrades): HomeScreenPresenter {
        return HomeScreenPresenter(getTrades)
    }

    @Provides
    fun providesGetTrades(repository: TradesRepository<TradeDb>)
            : GetTrades = GetTradesImpl(repository)

}
