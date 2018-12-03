package com.kotlin.server.service;

import com.kotlin.core.usecases.SyncTrades;
import dagger.internal.Factory;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class SyncTradesService_Factory implements Factory<SyncTradesService> {
  private final Provider<SyncTrades> syncTradesProvider;

  public SyncTradesService_Factory(Provider<SyncTrades> syncTradesProvider) {
    this.syncTradesProvider = syncTradesProvider;
  }

  @Override
  public SyncTradesService get() {
    return new SyncTradesService(syncTradesProvider.get());
  }

  public static SyncTradesService_Factory create(Provider<SyncTrades> syncTradesProvider) {
    return new SyncTradesService_Factory(syncTradesProvider);
  }
}
