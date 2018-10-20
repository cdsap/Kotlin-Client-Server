package com.kotlin.client.di

import dagger.Subcomponent
import view.homescreen.HomeScreenActivity
import view.homescreen.PairScreenActivity

@Subcomponent(modules = arrayOf(HomeScreenModule::class))
interface PairScreenComponent : AndroidInjector<PairScreenActivity> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<PairScreenActivity>()
}
