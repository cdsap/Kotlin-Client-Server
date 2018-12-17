package com.kotlin.client.di.pairscreen

import com.kotlin.client.di.ActivityScope
import com.kotlin.client.view.pairscreen.PairScreenActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class PairScreenActivityModule {
    @ActivityScope
    @ContributesAndroidInjector(modules = [PairScreenModule::class])
    abstract fun contributePairScreenActivity(): PairScreenActivity
}