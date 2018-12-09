package com.kotlin.client.di

import com.kotlin.client.di.homescreen.HomeScreenActivityModule
import com.kotlin.client.di.pairscreen.PairScreenActivityModule
import dagger.Module

@Module(includes = [HomeScreenActivityModule::class, PairScreenActivityModule::class])
internal abstract class ContributeActivityModule