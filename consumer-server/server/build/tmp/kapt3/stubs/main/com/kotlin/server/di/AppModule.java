package com.kotlin.server.di;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/kotlin/server/di/AppModule;", "", "()V", "server"})
@dagger.Module(includes = {com.kotlin.server.repository.di.RepositoryModule.class, com.kotlin.server.repository.domain.di.DomainModule.class})
public final class AppModule {
    
    public AppModule() {
        super();
    }
}