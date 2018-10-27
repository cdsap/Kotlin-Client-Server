package com.kotlin.client.di

import com.kotlin.client.di.homescreen.HomeScreenActivityModule
import com.kotlin.client.di.homescreen.HomeScreenModule
import com.kotlin.client.di.pairscreen.PairScreenActivityModule
import com.kotlin.client.di.pairscreen.PairScreenModule
import dagger.Module
import view.pairscreen.PairScreenActivity

@Module(includes = arrayOf(
        HomeScreenActivityModule::class,
        PairScreenActivityModule::class
))
internal abstract class ContributeActivityModule