package com.kotlin.server.di;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.kotlin.core.repository.PairsRepository;
import com.kotlin.core.repository.SyncRepository;
import com.kotlin.core.repository.TradesRepository;
import com.kotlin.core.usecases.GetTrades;
import com.kotlin.core.usecases.SyncPairs;
import com.kotlin.core.usecases.SyncTrades;
import com.kotlin.server.endpoint.DroidconEndPoint;
import com.kotlin.server.endpoint.DroidconEndPoint_MembersInjector;
import com.kotlin.server.repository.di.RepositoryModule;
import com.kotlin.server.repository.di.RepositoryModule_ProvidesObjectifyServiceFactory;
import com.kotlin.server.repository.di.RepositoryModule_ProvidesPairRepositoryFactory;
import com.kotlin.server.repository.di.RepositoryModule_ProvidesRestApiFactory;
import com.kotlin.server.repository.di.RepositoryModule_ProvidesSyncRepositoryFactory;
import com.kotlin.server.repository.di.RepositoryModule_ProvidesTradesRepositoryFactory;
import com.kotlin.server.repository.domain.di.DomainModule;
import com.kotlin.server.repository.domain.di.DomainModule_ProvideGetTradesFactory;
import com.kotlin.server.repository.domain.di.DomainModule_ProvideSyncPairsFactory;
import com.kotlin.server.repository.domain.di.DomainModule_ProvideSyncTradesFactory;
import com.kotlin.server.service.GetTradesService;
import com.kotlin.server.service.SyncTradesService;
import dagger.internal.Preconditions;
import javax.annotation.Generated;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class DaggerInjector implements Injector {
  private DomainModule domainModule;

  private RepositoryModule repositoryModule;

  private DaggerInjector(Builder builder) {
    initialize(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  public static Injector create() {
    return new Builder().build();
  }

  private TradesRepository getTradesRepository() {
    return RepositoryModule_ProvidesTradesRepositoryFactory.proxyProvidesTradesRepository(
        repositoryModule,
        RepositoryModule_ProvidesObjectifyServiceFactory.proxyProvidesObjectifyService(
            repositoryModule),
        RepositoryModule_ProvidesRestApiFactory.proxyProvidesRestApi(repositoryModule));
  }

  private PairsRepository getPairsRepository() {
    return RepositoryModule_ProvidesPairRepositoryFactory.proxyProvidesPairRepository(
        repositoryModule,
        RepositoryModule_ProvidesObjectifyServiceFactory.proxyProvidesObjectifyService(
            repositoryModule),
        RepositoryModule_ProvidesRestApiFactory.proxyProvidesRestApi(repositoryModule));
  }

  private SyncTrades getSyncTrades() {
    return DomainModule_ProvideSyncTradesFactory.proxyProvideSyncTrades(
        domainModule, getTradesRepository(), getPairsRepository());
  }

  private SyncTradesService getSyncTradesService() {
    return new SyncTradesService(getSyncTrades());
  }

  private SyncRepository getSyncRepository() {
    return RepositoryModule_ProvidesSyncRepositoryFactory.proxyProvidesSyncRepository(
        repositoryModule,
        RepositoryModule_ProvidesObjectifyServiceFactory.proxyProvidesObjectifyService(
            repositoryModule),
        RepositoryModule_ProvidesRestApiFactory.proxyProvidesRestApi(repositoryModule));
  }

  private SyncPairs getSyncPairs2() {
    return DomainModule_ProvideSyncPairsFactory.proxyProvideSyncPairs(
        domainModule, getSyncRepository());
  }

  private com.kotlin.server.service.SyncPairs getSyncPairs() {
    return new com.kotlin.server.service.SyncPairs(getSyncPairs2());
  }

  private GetTrades getGetTrades() {
    return DomainModule_ProvideGetTradesFactory.proxyProvideGetTrades(
        domainModule, getTradesRepository(), getPairsRepository());
  }

  private GetTradesService getGetTradesService() {
    return new GetTradesService(getGetTrades());
  }

  @SuppressWarnings("unchecked")
  private void initialize(final Builder builder) {
    this.domainModule = builder.domainModule;
    this.repositoryModule = builder.repositoryModule;
  }

  @Override
  public void inject(DroidconEndPoint droidconEndPoint) {
    injectDroidconEndPoint(droidconEndPoint);
  }

  @CanIgnoreReturnValue
  private DroidconEndPoint injectDroidconEndPoint(DroidconEndPoint instance) {
    DroidconEndPoint_MembersInjector.injectSyncService(instance, getSyncTradesService());
    DroidconEndPoint_MembersInjector.injectSyncService2(instance, getSyncPairs());
    DroidconEndPoint_MembersInjector.injectGetTradesService(instance, getGetTradesService());
    return instance;
  }

  public static final class Builder {
    private RepositoryModule repositoryModule;

    private DomainModule domainModule;

    private Builder() {}

    public Injector build() {
      if (repositoryModule == null) {
        this.repositoryModule = new RepositoryModule();
      }
      if (domainModule == null) {
        this.domainModule = new DomainModule();
      }
      return new DaggerInjector(this);
    }

    public Builder repositoryModule(RepositoryModule repositoryModule) {
      this.repositoryModule = Preconditions.checkNotNull(repositoryModule);
      return this;
    }

    public Builder domainModule(DomainModule domainModule) {
      this.domainModule = Preconditions.checkNotNull(domainModule);
      return this;
    }
  }
}
