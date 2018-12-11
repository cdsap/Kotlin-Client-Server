package com.kotlin.client.repository.di

import com.google.gson.GsonBuilder
import com.kotlin.client.api.OverallDesrializer
import com.kotlin.client.api.Trades2Serial
import com.kotlin.client.database.AppDatabase
import com.kotlin.client.database.DbInterface
import com.kotlin.client.repository.GetTradesRepositoryImpl
import com.kotlin.client.repository.PairRepositoryImpl
import com.kotlin.client.repository.SyncPairRepositoryImpl
import com.kotlin.client.repository.api.BxApi
import com.kotlin.client.repository.api.MarketOverall
import com.kotlin.core.entities.Trades
import com.kotlin.core.repository.PairsRepository
import com.kotlin.core.repository.SyncRepository
import com.kotlin.core.repository.TradesRepository
import dagger.Module
import dagger.Provides
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@Module
class RepositoryModule {

    @Provides
    fun providesTradeRepository(db: DbInterface,
                                api: BxApi): TradesRepository =
            GetTradesRepositoryImpl(db, api)

    @Provides
    fun providesPairRepository(db: DbInterface,
                               api: BxApi): PairsRepository =
            PairRepositoryImpl(db)

    @Provides
    fun providesSyncPairRepository(db: DbInterface,
                                   api: BxApi): SyncRepository =
            SyncPairRepositoryImpl(db, api)

    @Provides
    fun providesRestApi(retrofit: Retrofit): BxApi = BxApi(retrofit)


    @Provides
    fun providesRetrofit(@Named("URL") url: String): Retrofit {
        return Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(createGsonConverterPair())
                .build()
    }

    @Provides
    fun providesDbInterface(appDatabase: AppDatabase): DbInterface {
        return appDatabase.dbInterface()
    }



    private fun createGsonConverterPair(): Converter.Factory {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.registerTypeAdapter(MarketOverall::class.java, OverallDesrializer())
        gsonBuilder.registerTypeAdapter(Trades::class.java, Trades2Serial())
        val gson = gsonBuilder.create()
        return GsonConverterFactory.create(gson)
    }
}