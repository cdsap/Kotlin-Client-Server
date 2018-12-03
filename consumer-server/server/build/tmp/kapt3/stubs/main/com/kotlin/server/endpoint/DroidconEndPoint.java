package com.kotlin.server.endpoint;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0015\u001a\u00020\u00162\b\b\u0001\u0010\u0017\u001a\u00020\u0018H\u0007J\u000e\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001aH\u0007J\b\u0010\u001c\u001a\u00020\u001dH\u0007J\b\u0010\u001e\u001a\u00020\u001dH\u0007R\u001e\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001e\u0010\t\u001a\u00020\n8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001e\u0010\u000f\u001a\u00020\u00108\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014\u00a8\u0006\u001f"}, d2 = {"Lcom/kotlin/server/endpoint/DroidconEndPoint;", "", "()V", "getTradesService", "Lcom/kotlin/server/service/GetTradesService;", "getGetTradesService", "()Lcom/kotlin/server/service/GetTradesService;", "setGetTradesService", "(Lcom/kotlin/server/service/GetTradesService;)V", "syncService", "Lcom/kotlin/server/service/SyncTradesService;", "getSyncService", "()Lcom/kotlin/server/service/SyncTradesService;", "setSyncService", "(Lcom/kotlin/server/service/SyncTradesService;)V", "syncService2", "Lcom/kotlin/server/service/SyncPairs;", "getSyncService2", "()Lcom/kotlin/server/service/SyncPairs;", "setSyncService2", "(Lcom/kotlin/server/service/SyncPairs;)V", "getTrades", "Lcom/kotlin/core/entities/Trades;", "id", "", "getTradesByPair", "", "Lcom/kotlin/core/entities/Market;", "sync", "", "sync2", "server"})
@com.google.api.server.spi.config.Api(name = "droidcon", version = "v1")
public final class DroidconEndPoint {
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Inject()
    public com.kotlin.server.service.SyncTradesService syncService;
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Inject()
    public com.kotlin.server.service.SyncPairs syncService2;
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Inject()
    public com.kotlin.server.service.GetTradesService getTradesService;
    
    @org.jetbrains.annotations.NotNull()
    public final com.kotlin.server.service.SyncTradesService getSyncService() {
        return null;
    }
    
    public final void setSyncService(@org.jetbrains.annotations.NotNull()
    com.kotlin.server.service.SyncTradesService p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.kotlin.server.service.SyncPairs getSyncService2() {
        return null;
    }
    
    public final void setSyncService2(@org.jetbrains.annotations.NotNull()
    com.kotlin.server.service.SyncPairs p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.kotlin.server.service.GetTradesService getGetTradesService() {
        return null;
    }
    
    public final void setGetTradesService(@org.jetbrains.annotations.NotNull()
    com.kotlin.server.service.GetTradesService p0) {
    }
    
    @com.google.api.server.spi.config.ApiMethod(name = "sync", httpMethod = "GET", path = "sync/")
    public final void sync() {
    }
    
    @com.google.api.server.spi.config.ApiMethod(name = "syncPairs", httpMethod = "GET", path = "syncPairs/")
    public final void sync2() {
    }
    
    @org.jetbrains.annotations.NotNull()
    @com.google.api.server.spi.config.ApiMethod(name = "trades", httpMethod = "GET", path = "trades/{pair}")
    public final com.kotlin.core.entities.Trades getTrades(@org.jetbrains.annotations.NotNull()
    @javax.inject.Named(value = "pair")
    java.lang.String id) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @com.google.api.server.spi.config.ApiMethod(name = "trades", httpMethod = "GET", path = "trades/")
    public final java.util.List<com.kotlin.core.entities.Market> getTradesByPair() {
        return null;
    }
    
    public DroidconEndPoint() {
        super();
    }
}