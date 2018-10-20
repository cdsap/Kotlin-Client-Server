package com.kotlin.client.di

import view.pairscreen.PairScreenPresenter
import com.kotlin.core.usecases.GetPairs
import dagger.Module
import dagger.Provides
import view.homescreen.PairScreenActivity

@Module
interface PairScreenModule {
    @Provides
    fun providesPairPresenter(getPairs: GetPairs): PairScreenPresenter {
        return PairScreenPresenter(getPairs)
    }
}