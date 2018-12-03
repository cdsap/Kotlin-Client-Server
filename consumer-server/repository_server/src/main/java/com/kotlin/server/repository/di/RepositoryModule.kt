package com.kotlin.server.repository.di

import com.googlecode.objectify.Objectify
import com.googlecode.objectify.ObjectifyService
import com.kotlin.core.repository.PairsRepository
import com.kotlin.core.repository.SyncRepository
import com.kotlin.core.repository.TradesRepository
import com.kotlin.server.repository.GetTradesRepositoryImpl
import com.kotlin.server.repository.PairRepositoryImpl
import com.kotlin.server.repository.SyncPairsRepositoryImpl
import com.kotlin.server.repository.api.BxApi
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {
    init {
        System.out.println("211ssss1")
     //   ObjectifyService.init()


    }

    @Provides
    fun providesTradesRepository(objectify: Objectify,
                                 api: BxApi): TradesRepository = GetTradesRepositoryImpl(objectify, api)

    @Provides
    fun providesPairRepository(objectify: Objectify,
                               api: BxApi): PairsRepository = PairRepositoryImpl(objectify)

    @Provides
    fun providesSyncPairRepository(objectify: Objectify,
                                   api: BxApi) = SyncPairsRepositoryImpl(objectify, api)

    @Provides
    fun providesSyncRepository(objectify: Objectify,
                               api: BxApi): SyncRepository = SyncPairsRepositoryImpl(objectify, api)

    @Provides
    fun providesRestApi(): BxApi = BxApi()

    @Provides
    fun providesObjectifyService(): Objectify {
        ObjectifyService.init()
        return ObjectifyService.ofy()
    }

}

