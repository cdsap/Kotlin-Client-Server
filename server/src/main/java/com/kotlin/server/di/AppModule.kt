package com.kotlin.server.di

import com.googlecode.objectify.Objectify
import com.googlecode.objectify.ObjectifyService
import com.googlecode.objectify.Ref
import com.kotlin.core.repository.PairsRepository
import com.kotlin.core.repository.SyncRepository
import com.kotlin.core.repository.TradesRepository
import com.kotlin.core.usecases.GetTrades
import com.kotlin.core.usecases.SyncPairs
import com.kotlin.core.usecases.SyncTrades
import com.kotlin.server.api.BxApi
import com.kotlin.server.database.PairStore
import com.kotlin.server.database.SymbolStore
import com.kotlin.server.database.TradeStore
import com.kotlin.server.di.repository.RepositoryModule
import com.kotlin.server.domain.GetTradesImpl
import com.kotlin.server.domain.SyncPairsImpl
import com.kotlin.server.domain.SyncTradesImpl
import com.kotlin.server.repository.*
import dagger.Module
import dagger.Provides

@Module(includes = arrayOf(RepositoryModule::class))
class AppModule {

    @Provides
    fun providesGetTrades(
            repository: TradesRepository<TradeStore>,
            pairRepository: PairsRepository
    )
            : GetTrades = GetTradesImpl(repository, pairRepository)

    @Provides
    fun providesSyncPrairs(
            repository: SyncRepository): SyncPairs = SyncPairsImpl(repository)


    @Provides
    fun providesSyncTrades(
            tradesRepository: TradesRepository<TradeStore>,
            pairRepository: PairsRepository)
            : SyncTrades = SyncTradesImpl(tradesRepository,
            pairRepository)

}
