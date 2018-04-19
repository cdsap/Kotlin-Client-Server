package com.kotlin.server.di

import com.googlecode.objectify.Objectify
import com.googlecode.objectify.ObjectifyService
import com.kotlin.core.usecases.GetTrades
import com.kotlin.core.usecases.SyncTrades
import com.kotlin.server.api.BxApi
import com.kotlin.server.database.TradeStore
import com.kotlin.server.domain.GetTradesImpl
import com.kotlin.server.domain.SyncTradesImpl
import com.kotlin.server.repository.GetTradesLocalRepository
import com.kotlin.server.repository.GetTradesRemoteRepository
import com.kotlin.server.repository.GetTradesRepository
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class AppModule {

    init {
        ObjectifyService.register(TradeStore::class.java)
    }

    @Provides
    fun providesGetTrades(
            @Named("local") localRepository: GetTradesRepository,
            @Named("remote") remoteRepository: GetTradesRepository
    )
            : GetTrades = GetTradesImpl(localRepository, remoteRepository)

    @Provides
    fun providesSyncTrades(
            @Named("local") localRepository: GetTradesRepository,
            @Named("remote") remoteRepository: GetTradesRepository)
            : SyncTrades = SyncTradesImpl(localRepository, remoteRepository)

    @Provides
    @Named("local")
    fun providesLocalRepository(objectify: Objectify): GetTradesRepository =
            GetTradesLocalRepository(objectify)

    @Provides
    @Named("remote")
    fun providesRemoteRepository(api: BxApi): GetTradesRepository =
            GetTradesRemoteRepository(api)

    @Provides
    fun providesRestApi(): BxApi = BxApi()

    @Provides
    fun providesObjectifyService() = ObjectifyService.ofy()
}