package com.kotlin.server.service;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0002\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lcom/kotlin/server/service/GetTradesService;", "", "getTrades", "Lcom/kotlin/core/usecases/GetTrades;", "(Lcom/kotlin/core/usecases/GetTrades;)V", "Lcom/kotlin/core/entities/Trades;", "pair", "", "getTradesByPair", "", "Lcom/kotlin/core/entities/Market;", "server"})
public final class GetTradesService {
    private final com.kotlin.core.usecases.GetTrades getTrades = null;
    
    @org.jetbrains.annotations.NotNull()
    public final com.kotlin.core.entities.Trades getTrades(@org.jetbrains.annotations.NotNull()
    java.lang.String pair) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.kotlin.core.entities.Market> getTradesByPair() {
        return null;
    }
    
    @javax.inject.Inject()
    public GetTradesService(@org.jetbrains.annotations.NotNull()
    com.kotlin.core.usecases.GetTrades getTrades) {
        super();
    }
}