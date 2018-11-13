package com.kotlin.client.di.homescreen

import com.kotlin.client.view.homescreen.HomeScreenActivity
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent(modules = arrayOf(HomeScreenModule::class))
interface HomeScreenComponent : AndroidInjector<HomeScreenActivity> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<HomeScreenActivity>()
}
