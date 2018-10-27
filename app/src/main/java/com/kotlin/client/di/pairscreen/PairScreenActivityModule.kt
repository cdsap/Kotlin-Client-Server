package com.kotlin.client.di.pairscreen

import com.kotlin.client.di.ActivityScope
import dagger.Module
import dagger.android.ContributesAndroidInjector
import view.homescreen.HomeScreenActivity
import view.pairscreen.PairScreenActivity

@Module
abstract class PairScreenActivityModule {
    @ActivityScope
    @ContributesAndroidInjector(modules = [PairScreenModule::class])
    abstract fun contributePairScreenActivity(): PairScreenActivity
}