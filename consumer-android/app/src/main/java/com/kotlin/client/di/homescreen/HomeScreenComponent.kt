package com.kotlin.client.di.homescreen

import com.kotlin.client.view.homescreen.TradesActivity
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent(modules = arrayOf(HomeScreenModule::class))
interface HomeScreenComponent : AndroidInjector<TradesActivity> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<TradesActivity>()
}
