package com.kotlin.server.di


import com.kotlin.server.repository.di.RepositoryModule
import com.kotlin.server.repository.domain.di.DomainModule
import dagger.Module

@Module(includes = arrayOf(
        RepositoryModule::class,
        DomainModule::class))
class AppModule {
}
