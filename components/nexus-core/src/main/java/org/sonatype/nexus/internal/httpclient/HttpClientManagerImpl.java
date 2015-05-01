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

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;
import javax.inject.Singleton;

import org.sonatype.nexus.httpclient.HttpClientConfigurationStore;
import org.sonatype.nexus.httpclient.HttpClientManager;
import org.sonatype.nexus.httpclient.config.HttpClientConfiguration;
import org.sonatype.sisu.goodies.common.ComponentSupport;
import org.sonatype.sisu.goodies.common.Mutex;

import org.apache.http.client.HttpClient;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Default {@link HttpClientManager}.
 *
 * @since 3.0
 */
@Named
@Singleton
public class HttpClientManagerImpl
  extends ComponentSupport
  implements HttpClientManager
{
  private final HttpClientConfigurationStore store;

  private final Provider<HttpClientConfiguration> defaults;

  private final Mutex lock = new Mutex();

  private HttpClientConfiguration configuration;

  @Inject
  public HttpClientManagerImpl(final HttpClientConfigurationStore store,
                               @Named("initial") final Provider<HttpClientConfiguration> defaults)
  {
    this.store = checkNotNull(store);
    log.debug("Store: {}", store);
    this.defaults = checkNotNull(defaults);
    log.debug("Defaults: {}", defaults);
  }

  //
  // Configuration
  //

  /**
   * Load configuration from store, or use defaults.
   */
  private HttpClientConfiguration loadConfiguration() {
    HttpClientConfiguration model = store.load();

    // use defaults if no configuration was loaded from the store
    if (model == null) {
      model = defaults.get();

      // default config must not be null
      checkNotNull(model);

      log.info("Using default configuration: {}", model);
    }
    else {
      log.info("Loaded configuration: {}", model);
    }

    return model;
  }

  /**
   * Return configuration, loading if needed.
   *
   * The result model should be considered _immutable_ unless copied.
   */
  private HttpClientConfiguration getConfigurationInternal() {
    synchronized (lock) {
      if (configuration == null) {
        configuration = loadConfiguration();
      }
      return configuration;
    }
  }

  @Override
  public HttpClientConfiguration getConfiguration() {
    return getConfigurationInternal().copy();
  }

  @Override
  public void setConfiguration(final HttpClientConfiguration configuration) {
    checkNotNull(configuration);

    HttpClientConfiguration model = configuration.copy();
    log.info("Saving configuration: {}", model);
    synchronized (lock) {
      store.save(model);
      this.configuration = model;
    }
  }

  //
  // Instance creation
  //

  @Override
  public HttpClient create() {
    return null;
  }
}
