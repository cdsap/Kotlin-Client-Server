package com.kotlin.client.di.tradescreen

import com.kotlin.client.di.ActivityScope
import com.kotlin.client.view.tradescreen.TradesActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class HomeScreenActivityModule {
    @ActivityScope
    @ContributesAndroidInjector(modules = [HomeScreenModule::class])
    abstract fun contributeHomeScreenActivity() : TradesActivity
}