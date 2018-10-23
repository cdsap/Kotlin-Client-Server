package com.kotlin.client.di.pairscreen

import dagger.Subcomponent
import view.pairscreen.PairScreenActivity
import dagger.android.AndroidInjector


@Subcomponent(modules = arrayOf(PairScreenModule::class))
interface PairScreenComponent : AndroidInjector<PairScreenActivity> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<PairScreenActivity>()
}
