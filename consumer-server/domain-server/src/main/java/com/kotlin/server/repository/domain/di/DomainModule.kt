package com.kotlin.server.repository.domain.di

import com.kotlin.core.repository.PairsRepository
import com.kotlin.core.repository.TradesRepository
import com.kotlin.core.usecases.GetPairs
import com.kotlin.core.usecases.GetTrades
import com.kotlin.core.usecases.SyncTrades
import com.kotlin.core.usecases.impl.GetPairsImpl
import com.kotlin.core.usecases.impl.GetTradesImpl
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

}