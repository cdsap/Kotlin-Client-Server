package com.kotlin.client.di.homescreen

import dagger.Subcomponent
import dagger.android.AndroidInjector
import view.homescreen.HomeScreenActivity

@Subcomponent(modules = arrayOf(HomeScreenModule::class))
interface HomeScreenComponent : AndroidInjector<HomeScreenActivity> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<HomeScreenActivity>()
}
