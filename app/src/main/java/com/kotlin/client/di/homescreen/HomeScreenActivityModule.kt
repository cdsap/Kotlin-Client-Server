package com.kotlin.client.di.homescreen

import com.kotlin.client.di.ActivityScope
import dagger.Module
import dagger.android.ContributesAndroidInjector
import view.homescreen.HomeScreenActivity

@Module
abstract class HomeScreenActivityModule {
    @ActivityScope
    @ContributesAndroidInjector(modules = [HomeScreenModule::class])
    abstract fun contributeHomeScreenActivity() : HomeScreenActivity
}