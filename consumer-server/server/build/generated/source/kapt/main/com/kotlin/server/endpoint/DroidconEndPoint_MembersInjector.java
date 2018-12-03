package com.kotlin.server.endpoint;

import com.kotlin.server.service.GetTradesService;
import com.kotlin.server.service.SyncPairs;
import com.kotlin.server.service.SyncTradesService;
import dagger.MembersInjector;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class DroidconEndPoint_MembersInjector implements MembersInjector<DroidconEndPoint> {
  private final Provider<SyncTradesService> syncServiceProvider;

  private final Provider<SyncPairs> syncService2Provider;

  private final Provider<GetTradesService> getTradesServiceProvider;

  public DroidconEndPoint_MembersInjector(
      Provider<SyncTradesService> syncServiceProvider,
      Provider<SyncPairs> syncService2Provider,
      Provider<GetTradesService> getTradesServiceProvider) {
    this.syncServiceProvider = syncServiceProvider;
    this.syncService2Provider = syncService2Provider;
    this.getTradesServiceProvider = getTradesServiceProvider;
  }

  public static MembersInjector<DroidconEndPoint> create(
      Provider<SyncTradesService> syncServiceProvider,
      Provider<SyncPairs> syncService2Provider,
      Provider<GetTradesService> getTradesServiceProvider) {
    return new DroidconEndPoint_MembersInjector(
        syncServiceProvider, syncService2Provider, getTradesServiceProvider);
  }

  @Override
  public void injectMembers(DroidconEndPoint instance) {
    injectSyncService(instance, syncServiceProvider.get());
    injectSyncService2(instance, syncService2Provider.get());
    injectGetTradesService(instance, getTradesServiceProvider.get());
  }

  public static void injectSyncService(DroidconEndPoint instance, SyncTradesService syncService) {
    instance.syncService = syncService;
  }

  public static void injectSyncService2(DroidconEndPoint instance, SyncPairs syncService2) {
    instance.syncService2 = syncService2;
  }

  public static void injectGetTradesService(
      DroidconEndPoint instance, GetTradesService getTradesService) {
    instance.getTradesService = getTradesService;
  }
}
