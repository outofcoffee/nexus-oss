/*
 * Sonatype Nexus (TM) Open Source Version
 * Copyright (c) 2008-2015 Sonatype, Inc.
 * All rights reserved. Includes the third-party code listed at http://links.sonatype.com/products/nexus/oss/attributions.
 *
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License Version 1.0,
 * which accompanies this distribution and is available at http://www.eclipse.org/legal/epl-v10.html.
 *
 * Sonatype Nexus (TM) Professional Version is available from Sonatype, Inc. "Sonatype" and "Sonatype Nexus" are trademarks
 * of Sonatype, Inc. Apache Maven is a trademark of the Apache Software Foundation. M2eclipse is a trademark of the
 * Eclipse Foundation. All other trademarks are the property of their respective owners.
 */
package org.sonatype.nexus.internal.httpclient;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.sonatype.nexus.common.property.SystemPropertiesHelper;
import org.sonatype.nexus.events.NexusStoppedEvent;
import org.sonatype.nexus.httpclient.HttpClientBuilder;
import org.sonatype.nexus.httpclient.HttpClientFactory;
import org.sonatype.sisu.goodies.common.ComponentSupport;
import org.sonatype.sisu.goodies.eventbus.EventBus;

import com.google.common.eventbus.Subscribe;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.CookieSpecs;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Default implementation of {@link HttpClientFactory}.
 *
 * @since 2.2
 */
@Singleton
@Named
public class HttpClientFactoryImpl
    extends ComponentSupport
    implements HttpClientFactory
{
  /**
   * Key for customizing default (and max) keep alive duration when remote server does not state anything, or states
   * some unreal high value. Value is milliseconds.
   */
  private static final String KEEP_ALIVE_MAX_DURATION_KEY = "nexus.apacheHttpClient4x.keepAliveMaxDuration";

  /**
   * Default keep alive max duration: 30 seconds.
   */
  private static final long KEEP_ALIVE_MAX_DURATION_DEFAULT = TimeUnit.SECONDS.toMillis(30);


  /**
   * Key for customizing connection pool timeout. In other words, how long should a HTTP request execution be blocked
   * when pool is depleted, for a connection. Value is milliseconds.
   */
  private static final String CONNECTION_POOL_TIMEOUT_KEY = "nexus.apacheHttpClient4x.connectionPoolTimeout";

  /**
   * Default pool timeout: 30 seconds.
   */
  private static final int CONNECTION_POOL_TIMEOUT_DEFAULT = (int) TimeUnit.SECONDS.toMillis(30);

  /**
   * The low level core event bus.
   */
  private final EventBus eventBus;

  /**
   * Shared client connection manager.
   */
  private final SharedHttpClientConnectionManager sharedConnectionManager;

  @Inject
  public HttpClientFactoryImpl(final EventBus eventBus,
                               final SharedHttpClientConnectionManager clientConnectionManager)
  {
    this.sharedConnectionManager = checkNotNull(clientConnectionManager);

    this.eventBus = checkNotNull(eventBus);
    this.eventBus.register(this);
  }

  /**
   * Performs a clean shutdown on this component, it kills the evicting thread and shuts down the shared connection
   * manager. Multiple invocation of this method is safe, it will not do anything.
   */
  public synchronized void shutdown() {
    //evictingThread.interrupt();
    //sharedConnectionManager._shutdown();
    eventBus.unregister(this);
    log.info("Stopped");
  }

  @Subscribe
  public void onEvent(final NexusStoppedEvent event) {
    shutdown();
  }

  @Override
  protected void finalize() throws Throwable {
    try {
      shutdown();
    }
    finally {
      super.finalize();
    }
  }

  @Override
  public HttpClient create() {
    //RemoteStorageContext context = globalRemoteStorageContextProvider.get();
    //Builder builder = prepare(new RemoteStorageContextCustomizer(context));

    // FIXME: Why is this here and not general?
    //boolean reuseConnections = reuseConnectionsNeeded(context);
    //if (!reuseConnections) {
    //  builder.getHttpClientBuilder().setConnectionReuseStrategy(new NoConnectionReuseStrategy());
    //}

    //return builder.build();
    return null;
  }

  //@VisibleForTesting
  //boolean reuseConnectionsNeeded(final RemoteStorageContext context) {
  //  // return true if any of the auth is NTLM based, as NTLM must have keep-alive to work
  //  if (context != null) {
  //    if (context.getRemoteAuthenticationSettings() instanceof NtlmRemoteAuthenticationSettings) {
  //      return true;
  //    }
  //    if (context.getRemoteProxySettings() != null) {
  //      if (context.getRemoteProxySettings().getHttpProxySettings() != null &&
  //          context.getRemoteProxySettings().getHttpProxySettings()
  //              .getProxyAuthentication() instanceof NtlmRemoteAuthenticationSettings) {
  //        return true;
  //      }
  //      if (context.getRemoteProxySettings().getHttpsProxySettings() != null &&
  //          context.getRemoteProxySettings().getHttpsProxySettings()
  //              .getProxyAuthentication() instanceof NtlmRemoteAuthenticationSettings) {
  //        return true;
  //      }
  //    }
  //  }
  //  return false;
  //}

  @Override
  public HttpClient create(final Customizer customizer) {
    return prepare(customizer).build();
  }

  @Override
  public HttpClientBuilder prepare(final Customizer customizer) {
    final HttpClientBuilder builder = new HttpClientBuilder();

    // dependencies
    builder.getHttpClientBuilder().setConnectionManager(sharedConnectionManager);

    // configurable defaults
    int poolTimeout = SystemPropertiesHelper.getInteger(CONNECTION_POOL_TIMEOUT_KEY, CONNECTION_POOL_TIMEOUT_DEFAULT);
    builder.getRequestConfigBuilder().setConnectionRequestTimeout(poolTimeout);

    long keepAliveDuration = SystemPropertiesHelper.getLong(KEEP_ALIVE_MAX_DURATION_KEY, KEEP_ALIVE_MAX_DURATION_DEFAULT);
    builder.getHttpClientBuilder().setKeepAliveStrategy(new NexusConnectionKeepAliveStrategy(keepAliveDuration));

    // defaults
    builder.getConnectionConfigBuilder().setBufferSize(8 * 1024);
    builder.getRequestConfigBuilder().setCookieSpec(CookieSpecs.IGNORE_COOKIES);
    builder.getRequestConfigBuilder().setExpectContinueEnabled(false);
    builder.getRequestConfigBuilder().setStaleConnectionCheckEnabled(false);

    // Apply default user-agent
    final String userAgent = null; // getUserAgent();
    builder.setUserAgent(userAgent);

    customizer.customize(builder);

    return builder;
  }
}
