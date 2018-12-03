package com.kotlin.server.service;

import com.kotlin.core.usecases.GetTrades;
import dagger.internal.Factory;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class GetTradesService_Factory implements Factory<GetTradesService> {
  private final Provider<GetTrades> getTradesProvider;

  public GetTradesService_Factory(Provider<GetTrades> getTradesProvider) {
    this.getTradesProvider = getTradesProvider;
  }

  @Override
  public GetTradesService get() {
    return new GetTradesService(getTradesProvider.get());
  }

  public static GetTradesService_Factory create(Provider<GetTrades> getTradesProvider) {
    return new GetTradesService_Factory(getTradesProvider);
  }
}
