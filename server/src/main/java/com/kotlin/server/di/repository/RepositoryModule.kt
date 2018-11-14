package com.kotlin.server.di.repository

import com.googlecode.objectify.Objectify
import com.kotlin.core.repository.PairsRepository
import com.kotlin.core.repository.TradesRepository
import com.kotlin.server.api.BxApi
import com.kotlin.server.database.TradeStore
import com.kotlin.server.repository.GetTradesRepositoryImpl
import com.kotlin.server.repository.PairRepositoryImpl
import com.kotlin.server.repository.SyncPairsRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {
    @Provides
    fun providesTradesRepository(objectify: Objectify,
                                 api: BxApi): TradesRepository<TradeStore> = GetTradesRepositoryImpl(objectify, api)

    @Provides
    fun providesPairRepository(objectify: Objectify,
                               api: BxApi): PairsRepository = PairRepositoryImpl(objectify)

    @Provides
    fun providesSyncPairRepository(objectify: Objectify,
                                   api: BxApi) = SyncPairsRepositoryImpl(objectify, api)

}