package com.kotlin.client.di

import com.kotlin.core.domain.entities.repository.PairsRepository
import com.kotlin.core.domain.entities.repository.TradesRepository
import com.kotlin.core.domain.entities.usecases.GetPairs
import com.kotlin.core.domain.entities.usecases.GetTrades
import com.kotlin.core.domain.entities.usecases.impl.GetPairsImpl
import com.kotlin.core.domain.entities.usecases.impl.GetTradesImpl
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