package com.kotlin.client.di.pairscreen

import com.kotlin.client.view.pairscreen.PairScreenPresenter
import com.kotlin.core.usecases.GetPairs
import com.kotlin.core.usecases.SyncTrades
import dagger.Module
import dagger.Provides

@Module
class PairScreenModule {
    @Provides
    fun providesPairPresenter(getPairs: GetPairs, syncTrades: SyncTrades): PairScreenPresenter {
        return PairScreenPresenter(getPairs, syncTrades)
    }
}