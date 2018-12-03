package com.kotlin.server.service;

import dagger.internal.Factory;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class SyncPairs_Factory implements Factory<SyncPairs> {
  private final Provider<com.kotlin.core.usecases.SyncPairs> syncPairsProvider;

  public SyncPairs_Factory(Provider<com.kotlin.core.usecases.SyncPairs> syncPairsProvider) {
    this.syncPairsProvider = syncPairsProvider;
  }

  @Override
  public SyncPairs get() {
    return new SyncPairs(syncPairsProvider.get());
  }

  public static SyncPairs_Factory create(
      Provider<com.kotlin.core.usecases.SyncPairs> syncPairsProvider) {
    return new SyncPairs_Factory(syncPairsProvider);
  }
}
