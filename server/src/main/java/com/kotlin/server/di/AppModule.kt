package com.kotlin.server.di

import com.googlecode.objectify.Objectify
import com.googlecode.objectify.ObjectifyService
import com.kotlin.core.usecases.GetTrades
import com.kotlin.core.usecases.SyncTrades
import com.kotlin.server.api.BxApi
import com.kotlin.server.database.PairStore
import com.kotlin.server.database.SymbolStore
import com.kotlin.server.database.TradeStore
import com.kotlin.server.domain.GetTradesImpl
import com.kotlin.server.domain.SyncTradesImpl
import com.kotlin.server.repository.GetTradesRepository
import com.kotlin.server.repository.GetTradesRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class AppModule {

    init {
        ObjectifyService.register(TradeStore::class.java,
                SymbolStore::class.java,
                PairStore::class.java)
    }

    @Provides
    fun providesGetTrades(
            repository: GetTradesRepository
    )
            : GetTrades = GetTradesImpl(repository)

    @Provides
    fun providesSyncTrades(
            repository: GetTradesRepository)
            : SyncTrades = SyncTradesImpl(repository)

    @Provides
    fun providesRestApi(): BxApi = BxApi()

    @Provides
    fun providesObjectifyService() = ObjectifyService.ofy()

    @Provides
    fun providesRepository(objectify: Objectify,
                           api: BxApi) = GetTradesRepositoryImpl(objectify, api)
}
}