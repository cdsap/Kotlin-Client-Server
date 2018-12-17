package com.kotlin.client.di.pairscreen

import com.kotlin.client.view.pairscreen.PairScreenActivity
import dagger.Subcomponent
import dagger.android.AndroidInjector


@Subcomponent(modules = [PairScreenModule::class])
interface PairScreenComponent : AndroidInjector<PairScreenActivity> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<PairScreenActivity>()
}
