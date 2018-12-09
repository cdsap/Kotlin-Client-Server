package com.kotlin.client.domain.di

import com.kotlin.client.domain.GetPairsImpl
import com.kotlin.client.domain.GetTradesImpl
import com.kotlin.core.repository.PairsRepository
import com.kotlin.core.repository.TradesRepository
import com.kotlin.core.usecases.GetPairs
import com.kotlin.core.usecases.GetTrades
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun providesGetPairs(pairRepository: PairsRepository): GetPairs {
        return GetPairsImpl(pairRepository)
    }

    @Provides
    fun providesGetTrades(repository: TradesRepository)
            : GetTrades = GetTradesImpl(repository)
}