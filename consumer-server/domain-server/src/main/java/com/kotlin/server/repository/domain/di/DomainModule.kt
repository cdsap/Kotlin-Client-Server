package com.kotlin.server.repository.domain.di

import com.kotlin.core.domain.entities.repository.PairsRepository
import com.kotlin.core.domain.entities.repository.SyncRepository
import com.kotlin.core.domain.entities.repository.TradesRepository
import com.kotlin.core.domain.entities.usecases.GetMarket
import com.kotlin.core.domain.entities.usecases.GetPairs
import com.kotlin.core.domain.entities.usecases.GetTrades
import com.kotlin.core.domain.entities.usecases.SyncTrades
import com.kotlin.core.domain.entities.usecases.impl.GetPairsImpl
import com.kotlin.core.domain.entities.usecases.impl.GetTradesImpl
import com.kotlin.server.repository.domain.GetMarketImpl
import com.kotlin.server.repository.domain.SyncTradesImpl
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideGetTrades(tradesRepository: TradesRepository):
            GetTrades = GetTradesImpl(tradesRepository)

    @Provides
    fun providePairRepository(pairRepository: PairsRepository):
            GetPairs = GetPairsImpl(pairRepository)

    @Provides
    fun provideSyncTrades(tradesRepository: TradesRepository,
                          pairRepository: PairsRepository)
            : SyncTrades = SyncTradesImpl(tradesRepository, pairRepository)

    @Provides
    fun providesGetMarket(syncRepository: SyncRepository): GetMarket = GetMarketImpl(syncRepository)
}