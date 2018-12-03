package com.kotlin.server.repository.domain.di

import com.kotlin.core.repository.PairsRepository
import com.kotlin.core.repository.SyncRepository
import com.kotlin.core.repository.TradesRepository
import com.kotlin.core.usecases.GetTrades
import com.kotlin.core.usecases.SyncPairs
import com.kotlin.core.usecases.SyncTrades
import com.kotlin.server.repository.domain.GetTradesImpl
import com.kotlin.server.repository.domain.SyncPairsImpl
import com.kotlin.server.repository.domain.SyncTradesImpl
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideGetTrades(tradesRepository: TradesRepository,
                         pairsRepository: PairsRepository):
            GetTrades = GetTradesImpl(tradesRepository, pairsRepository)

    @Provides
    fun provideSyncPairs(syncRepository: SyncRepository):
            SyncPairs = SyncPairsImpl(syncRepository)

    @Provides
    fun provideSyncTrades(tradesRepository: TradesRepository,
                          pairRepository: PairsRepository)
            : SyncTrades = SyncTradesImpl(tradesRepository, pairRepository)


}