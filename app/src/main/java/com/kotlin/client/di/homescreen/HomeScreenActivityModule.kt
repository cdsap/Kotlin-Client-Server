package com.kotlin.client.di.homescreen

import com.kotlin.client.di.ActivityScope
import com.kotlin.client.view.homescreen.TradesActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class HomeScreenActivityModule {
    @ActivityScope
    @ContributesAndroidInjector(modules = [HomeScreenModule::class])
    abstract fun contributeHomeScreenActivity() : TradesActivity
}