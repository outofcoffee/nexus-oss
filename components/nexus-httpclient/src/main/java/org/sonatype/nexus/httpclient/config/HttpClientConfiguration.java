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
package org.sonatype.nexus.httpclient.config;

import org.sonatype.nexus.common.entity.Entity;

import com.google.common.base.Throwables;

/**
 * HTTP-client configuration.
 *
 * @since 3.0
 */
public class HttpClientConfiguration
    extends Entity
    implements Cloneable
{
  private ConnectionConfiguration connection;

  private ProxyConfiguration proxy;

  private AuthenticationConfiguration authentication;

  public ConnectionConfiguration getConnection() {
    return connection;
  }

  public void setConnection(final ConnectionConfiguration connection) {
    this.connection = connection;
  }

  public ProxyConfiguration getProxy() {
    return proxy;
  }

  public void setProxy(final ProxyConfiguration proxy) {
    this.proxy = proxy;
  }

  public AuthenticationConfiguration getAuthentication() {
    return authentication;
  }

  public void setAuthentication(final AuthenticationConfiguration authentication) {
    this.authentication = authentication;
  }

  public HttpClientConfiguration copy() {
    try {
      // FIXME: Address deep clone
      return (HttpClientConfiguration) clone();
    }
    catch (CloneNotSupportedException e) {
      throw Throwables.propagate(e);
    }
  }

  @Override
  public String toString() {
    return getClass().getSimpleName() + "{" +
        "connection=" + connection +
        ", proxy=" + proxy +
        ", authentication=" + authentication +
        '}';
  }
}
