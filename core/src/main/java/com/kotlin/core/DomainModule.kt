package com.kotlin.core

import com.kotlin.core.repository.PairsRepository
import com.kotlin.core.repository.TradesRepository
import com.kotlin.core.usecases.GetPairs
import com.kotlin.core.usecases.GetTrades
import com.kotlin.core.usecases.impl.GetPairsImpl
import com.kotlin.core.usecases.impl.GetTradesImpl
import dagger.Module
import dagger.Provides

@Module
class DomainModule {
    @Provides
    fun providesGetPairs(pairRepository: PairsRepository): GetPairs {
        return GetPairsImpl(pairRepository)
    }

    @Provides
    fun providesGetTrades(repository: TradesRepository, pairRepository: PairsRepository)
            : GetTrades = GetTradesImpl(repository, pairRepository)

}