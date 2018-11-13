package com.kotlin.client.di.pairscreen

import com.kotlin.client.domain.GetPairsImpl
import com.kotlin.client.repository.PairRepository
import com.kotlin.client.view.pairscreen.PairScreenPresenter
import com.kotlin.core.usecases.GetPairs
import dagger.Module
import dagger.Provides

@Module
public class PairScreenModule {
    @Provides
    fun providesPairPresenter(getPairs: GetPairs): PairScreenPresenter {
        return PairScreenPresenter(getPairs)
    }

    @Provides
    fun providesGetPairs(pairRepository: PairRepository): GetPairs {
        return GetPairsImpl(pairRepository)
    }
}