package com.kotlin.server.repository.di


import com.google.appengine.api.utils.SystemProperty
import com.google.cloud.datastore.DatastoreOptions
import com.google.gson.GsonBuilder
import com.googlecode.objectify.Objectify
import com.googlecode.objectify.ObjectifyFactory
import com.googlecode.objectify.ObjectifyService
import com.kotlin.core.domain.entities.Trades
import com.kotlin.core.network.TradesDeserializer
import com.kotlin.core.domain.entities.repository.PairsRepository
import com.kotlin.core.domain.entities.repository.SyncRepository
import com.kotlin.core.domain.entities.repository.TradesRepository
import com.kotlin.server.repository.GetTradesRepositoryImpl
import com.kotlin.server.repository.PairRepositoryImpl
import com.kotlin.server.repository.SyncPairsRepositoryImpl
import com.kotlin.server.repository.api.BxApiImpl
import com.kotlin.server.repository.api.PairsDeserializer
import com.kotlin.server.repository.api.entities.PairsInfo
import com.kotlin.server.repository.api.patch.CallFactoryWrapper
import com.kotlin.server.repository.database.DbImpl
import com.kotlin.server.repository.database.DbInterface
import com.kotlin.server.repository.database.PairStore
import com.kotlin.server.repository.database.TradeStore
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class RepositoryModule {
    init {
        initData()
    }

    @Provides
    fun providesDbInterface(objectify: Objectify): DbInterface = DbImpl(objectify)

    @Provides
    fun providesRetrofit(gsonConverterFactory: GsonConverterFactory): Retrofit =
            Retrofit.Builder()
                    .baseUrl(URL)
                    .addConverterFactory(gsonConverterFactory)
                    .callFactory(CallFactoryWrapper())
                    .build()


    @Provides
    fun providesGsonConverter(): GsonConverterFactory {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.registerTypeAdapter(PairsInfo::class.java, PairsDeserializer())
        gsonBuilder.registerTypeAdapter(Trades::class.java, TradesDeserializer())
        val gson = gsonBuilder.create()
        return GsonConverterFactory.create(gson)
    }

    @Provides
    fun providesTradesRepository(dbInterface: DbInterface,
                                 api: BxApiImpl): TradesRepository =
            GetTradesRepositoryImpl(dbInterface, api)

    @Provides
    fun providesPairRepository(dbInterface: DbInterface,
                               api: BxApiImpl): PairsRepository =
            PairRepositoryImpl(dbInterface, api)

    @Provides
    fun providesSyncPairRepository(dbInterface: DbInterface,
                                   api: BxApiImpl): SyncRepository = SyncPairsRepositoryImpl(dbInterface, api)

    @Provides
    fun providesRestApi(retrofit: Retrofit): BxApiImpl = BxApiImpl(retrofit)

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

    companion object {
        const val URL = "https://bx.in.th"
    }
}

