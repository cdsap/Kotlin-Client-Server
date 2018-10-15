package com.kotlin.server.di

import com.googlecode.objectify.Objectify
import com.googlecode.objectify.ObjectifyService
import com.googlecode.objectify.Ref
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
        ObjectifyService.register(TradeStore::class.java)
        ObjectifyService.register(SymbolStore::class.java)
        ObjectifyService.register(PairStore::class.java)
        initData()
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
                           api: BxApi): GetTradesRepository = GetTradesRepositoryImpl(objectify, api)

    private fun initData() {
        //if (ObjectifyService.ofy().load().type(SymbolStore::class.java)
        //                .count() == 0) {
        ObjectifyService.ofy().save().entity(SymbolStore("THB")).now()
        ObjectifyService.ofy().save().entity(SymbolStore("BTC")).now()
        ObjectifyService.ofy().save().entity(SymbolStore("OMG")).now()
        ObjectifyService.ofy().save().entity(SymbolStore("XRP")).now()
        ObjectifyService.ofy().save().entity(SymbolStore("ETH")).now()

        ObjectifyService.ofy().save().entity(PairStore(
                1,
                Ref.create(ObjectifyService.ofy().load().type(SymbolStore::class.java).id("THB").safe()),
                Ref.create(ObjectifyService.ofy().load().type(SymbolStore::class.java).id("BTC").safe())))

        ObjectifyService.ofy().save().entity(PairStore(
                25,
                Ref.create(ObjectifyService.ofy().load().type(SymbolStore::class.java).id("THB").safe()),
                Ref.create(ObjectifyService.ofy().load().type(SymbolStore::class.java).id("XRP").safe())))

        ObjectifyService.ofy().save().entity(PairStore(
                26,
                Ref.create(ObjectifyService.ofy().load().type(SymbolStore::class.java).id("THB").safe()),
                Ref.create(ObjectifyService.ofy().load().type(SymbolStore::class.java).id("OMG").safe())))

        ObjectifyService.ofy().save().entity(PairStore(
                21,
                Ref.create(ObjectifyService.ofy().load().type(SymbolStore::class.java).id("THB").safe()),
                Ref.create(ObjectifyService.ofy().load().type(SymbolStore::class.java).id("ETH").safe())))

    }
}
