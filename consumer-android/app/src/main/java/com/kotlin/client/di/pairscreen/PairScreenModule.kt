package com.kotlin.client.di.pairscreen

import com.kotlin.client.view.pairscreen.PairScreenPresenter
import com.kotlin.core.domain.entities.usecases.GetPairs
import dagger.Module
import dagger.Provides

@Module
class PairScreenModule {
    @Provides
    fun providesPairPresenter(getPairs: GetPairs): PairScreenPresenter {
        return PairScreenPresenter(getPairs)
    }
}