package com.kotlin.server.repository.di

import com.googlecode.objectify.Objectify
import com.googlecode.objectify.ObjectifyFactory
import com.googlecode.objectify.ObjectifyService
import com.googlecode.objectify.ObjectifyService.factory
import com.googlecode.objectify.Ref
import com.kotlin.core.repository.PairsRepository
import com.kotlin.core.repository.SyncRepository
import com.kotlin.core.repository.TradesRepository
import com.kotlin.server.repository.GetTradesRepositoryImpl
import com.kotlin.server.repository.PairRepositoryImpl
import com.kotlin.server.repository.SyncPairsRepositoryImpl
import com.kotlin.server.repository.api.BxApi
import com.kotlin.server.repository.database.PairStore
import com.kotlin.server.repository.database.SymbolStore
import com.kotlin.server.repository.database.TradeStore
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {
    init {
        System.out.println("211ssss1")
        initData()
     //   ObjectifyService.init()


    }

    @Provides
    fun providesTradesRepository(objectify: ObjectifyFactory,
                                 api: BxApi): TradesRepository = GetTradesRepositoryImpl(objectify.ofy(), api)

    @Provides
    fun providesPairRepository(objectify: ObjectifyFactory,
                               api: BxApi): PairsRepository = PairRepositoryImpl(objectify.ofy())

    @Provides
    fun providesSyncPairRepository(objectify: ObjectifyFactory,
                                   api: BxApi) = SyncPairsRepositoryImpl(objectify.ofy(), api)

    @Provides
    fun providesSyncRepository(objectify: ObjectifyFactory,
                               api: BxApi): SyncRepository = SyncPairsRepositoryImpl(objectify.ofy(), api)

    @Provides
    fun providesRestApi(): BxApi = BxApi()

    @Provides
    fun providesObjectifyService(): ObjectifyFactory {
        System.out.println("21www11")

        ObjectifyService.init()
        ObjectifyService.begin()


        System.out.println("2111")
        factory().register(TradeStore::class.java)
        System.out.println("211vvvvvvv1")

        factory().register(SymbolStore::class.java)
        System.out.println("211ww1")
        factory().register(PairStore::class.java)
        System.out.println("211ssss1")
//        initData()
        System.out.println("2111")



        ObjectifyService.ofy().save().entity(SymbolStore("THB")).now()
        ObjectifyService.ofy().save().entity(SymbolStore("BTC")).now()
        ObjectifyService.ofy().save().entity(SymbolStore("OMG")).now()
        ObjectifyService.ofy().save().entity(SymbolStore("XRP")).now()
        ObjectifyService.ofy().save().entity(SymbolStore("ETH")).now()

        ObjectifyService.ofy().save().entity(PairStore(
                1,
                Ref.create(ObjectifyService.factory().ofy().load().type(SymbolStore::class.java).id("THB").safe()),
                Ref.create(ObjectifyService.factory().ofy().load().type(SymbolStore::class.java).id("BTC").safe())))

        ObjectifyService.factory().ofy().save().entity(PairStore(
                25,
                Ref.create(ObjectifyService.factory().ofy().load().type(SymbolStore::class.java).id("THB").safe()),
                Ref.create(ObjectifyService.factory().ofy().load().type(SymbolStore::class.java).id("XRP").safe())))

        ObjectifyService.factory().ofy().save().entity(PairStore(
                26,
                Ref.create(ObjectifyService.factory().ofy().load().type(SymbolStore::class.java).id("THB").safe()),
                Ref.create(ObjectifyService.factory().ofy().load().type(SymbolStore::class.java).id("OMG").safe())))

        ObjectifyService.factory().ofy().save().entity(PairStore(
                21,
                Ref.create(ObjectifyService.factory().ofy().load().type(SymbolStore::class.java).id("THB").safe()),
                Ref.create(ObjectifyService.factory().ofy().load().type(SymbolStore::class.java).id("ETH").safe())))
        //      ofy().factory()
//                    Ref.create(ObjectifyService.factory().ofy().load().type(SymbolStore::class.java).id("ETH").safe())))

        return ObjectifyService.factory()
    }

    private fun initData() {
        //if (ObjectifyService.factory().ofy().load().type(SymbolStore::class.java)
        //                .count() == 0) {

    }


}

