package com.kotlin.client.di

import view.homescreen.HomeScreenPresenter
import com.kotlin.core.usecases.GetTrades
import dagger.Module
import dagger.Provides

@Module
interface MarketActivityModule {
    @Provides
    fun providesPresenter(getTrades: GetTrades): HomeScreenPresenter {
        return HomeScreenPresenter(getTrades)
    }
}
