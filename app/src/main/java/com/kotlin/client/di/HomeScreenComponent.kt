package com.kotlin.client.di

import dagger.Subcomponent
import view.homescreen.HomeScreenActivity

@Subcomponent(modules = arrayOf(HomeScreenModule::class))
interface HomeScreenComponent : AndroidInjector<HomeScreenActivity> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<HomeScreenActivity>()
}
