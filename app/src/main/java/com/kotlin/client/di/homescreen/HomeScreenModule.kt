package com.kotlin.client.di.homescreen

import com.kotlin.client.domain.GetTradesImpl
import com.kotlin.client.repository.GetTradesRepository
import view.homescreen.HomeScreenPresenter
import com.kotlin.core.usecases.GetTrades
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class HomeScreenModule {
    @Provides
    fun providesPresenter(getTrades: GetTrades): HomeScreenPresenter {
        return HomeScreenPresenter(getTrades)
    }

    @Provides
    fun providesGetTrades(repository: GetTradesRepository)
            : GetTrades = GetTradesImpl(repository)

}
