package com.kotlin.server.di

import com.kotlin.server.endpoint.DroidconEndPoint
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = arrayOf(AppModule::class))
interface Injector {

    fun inject(droidconEndPoint: DroidconEndPoint)
}
