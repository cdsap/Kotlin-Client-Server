package com.kotlin.server.service;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\u0005\u001a\u00020\u0006R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0007"}, d2 = {"Lcom/kotlin/server/service/SyncTradesService;", "", "syncTrades", "Lcom/kotlin/core/usecases/SyncTrades;", "(Lcom/kotlin/core/usecases/SyncTrades;)V", "sync", "", "server"})
public final class SyncTradesService {
    private final com.kotlin.core.usecases.SyncTrades syncTrades = null;
    
    public final void sync() {
    }
    
    @javax.inject.Inject()
    public SyncTradesService(@org.jetbrains.annotations.NotNull()
    com.kotlin.core.usecases.SyncTrades syncTrades) {
        super();
    }
}