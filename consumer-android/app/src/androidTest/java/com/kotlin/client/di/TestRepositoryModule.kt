package com.kotlin.client.di

import com.kotlin.core.entities.Trades
import com.kotlin.core.repository.PairsRepository
import com.kotlin.core.repository.TradesRepository
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import dagger.Module
import dagger.Provides


@Module
class TestRepositoryModule {

    @Provides
    fun providesTradeRepository(): TradesRepository = mock {
        on { getTradesPersisted(any()) }.thenReturn(Trades(emptyList()))
        on { getTradesRemote(any()) }.thenReturn(Trades(emptyList()))
    }


    @Provides
    fun providesPairRepository(): PairsRepository = mock {
        on { getPairs() }.thenReturn(listOf())
        on { syncPairs() }.thenReturn(listOf())
    }


}