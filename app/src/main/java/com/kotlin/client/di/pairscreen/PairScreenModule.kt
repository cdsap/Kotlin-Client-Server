package com.kotlin.client.di.pairscreen

import view.pairscreen.PairScreenPresenter
import com.kotlin.core.usecases.GetPairs
import dagger.Module
import dagger.Provides

@Module
public class PairScreenModule {
    @Provides
    fun providesPairPresenter(getPairs: GetPairs): PairScreenPresenter {
        return PairScreenPresenter(getPairs)
    }
}