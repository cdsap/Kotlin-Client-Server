package com.kotlin.server.repository.di

import com.google.api.client.util.store.DataStore
import com.google.api.client.util.store.DataStoreFactory
import com.google.appengine.api.utils.SystemProperty
import com.google.cloud.datastore.DatastoreOptions
import com.googlecode.objectify.Objectify
import com.googlecode.objectify.ObjectifyFactory
import com.googlecode.objectify.ObjectifyService
import com.kotlin.core.repository.PairsRepository
import com.kotlin.core.repository.SyncRepository
import com.kotlin.core.repository.TradesRepository
import com.kotlin.server.repository.GetTradesRepositoryImpl
import com.kotlin.server.repository.PairRepositoryImpl
import com.kotlin.server.repository.SyncPairsRepositoryImpl
import com.kotlin.server.repository.api.BxApi
import com.kotlin.server.repository.database.PairStore
import com.kotlin.server.repository.database.TradeStore
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {
    init {
        initData()
    }

    @Provides
    fun providesTradesRepository(objectify: Objectify,
                                 api: BxApi): TradesRepository = GetTradesRepositoryImpl(objectify, api)

    @Provides
    fun providesPairRepository(objectify: Objectify,
                               api: BxApi): PairsRepository = PairRepositoryImpl(objectify, api)

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
        return ObjectifyService.ofy()
    }

    private fun initData() {
        if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Production) {
            ObjectifyService.init()
        } else {
            val dataStore = DatastoreOptions.newBuilder()
                    .setHost("http://localhost:8081")
                    .setProjectId("kotlin-client-server")
                    .build()
                    .service
            ObjectifyService.init(ObjectifyFactory(dataStore))
        }
        ObjectifyService.begin()
        ObjectifyService.register(TradeStore::class.java)
        ObjectifyService.register(PairStore::class.java)
    }
}

