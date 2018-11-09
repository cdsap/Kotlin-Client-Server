package com.kotlin.client.di

import android.app.Activity
import com.kotlin.client.di.homescreen.HomeScreenComponent
import com.kotlin.client.di.pairscreen.PairScreenComponent
import dagger.Binds
import dagger.Module
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import view.homescreen.HomeScreenActivity
import view.pairscreen.PairScreenActivity

//
//@Module
//abstract class ActivityBuilder {
//
//    @Binds
//    @IntoMap
//    @ActivityKey(HomeScreenActivity::class)
//    abstract fun bindHomeScreenActivity(builder: HomeScreenComponent.Builder): AndroidInjector.Factory<out Activity>
//
//    @Binds
//    @IntoMap
//    @ActivityKey(PairScreenActivity::class)
//    abstract fun bindPairScreenActivity(builder: PairScreenComponent.Builder): AndroidInjector.Factory<out Activity>
//}