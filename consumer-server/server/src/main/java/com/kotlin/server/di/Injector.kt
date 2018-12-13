package com.kotlin.server.di

import com.kotlin.server.Init
import com.kotlin.server.endpoint.EndPoint
import com.kotlin.server.repository.di.RepositoryModule
import com.kotlin.server.repository.domain.di.DomainModule
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = (arrayOf(
        RepositoryModule::class,
        DomainModule::class)))
interface Injector {

    fun inject(endPoint: EndPoint)

    fun inject(init: Init)
}
