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
package org.sonatype.nexus.httpclient;

import java.util.Arrays;

/**
 * Connection configuration.
 *
 * @since 3.0
 */
public class ConnectionConfiguration
{
  private int connectionTimeout;

  private int maximumRetries;

  private String querySuffix;

  private String userAgentSuffix;

  private ProxyConfiguration httpProxy;

  private ProxyConfiguration httpsProxy;

  private String[] nonProxyHosts;

  public int getConnectionTimeout() {
    return connectionTimeout;
  }

  public void setConnectionTimeout(final int connectionTimeout) {
    this.connectionTimeout = connectionTimeout;
  }

  public int getMaximumRetries() {
    return maximumRetries;
  }

  public void setMaximumRetries(final int maximumRetries) {
    this.maximumRetries = maximumRetries;
  }

  public String getQuerySuffix() {
    return querySuffix;
  }

  public void setQuerySuffix(final String querySuffix) {
    this.querySuffix = querySuffix;
  }

  public String getUserAgentSuffix() {
    return userAgentSuffix;
  }

  public void setUserAgentSuffix(final String userAgentSuffix) {
    this.userAgentSuffix = userAgentSuffix;
  }

  public ProxyConfiguration getHttpProxy() {
    return httpProxy;
  }

  public void setHttpProxy(final ProxyConfiguration httpProxy) {
    this.httpProxy = httpProxy;
  }

  public ProxyConfiguration getHttpsProxy() {
    return httpsProxy;
  }

  public void setHttpsProxy(final ProxyConfiguration httpsProxy) {
    this.httpsProxy = httpsProxy;
  }

  public String[] getNonProxyHosts() {
    return nonProxyHosts;
  }

  public void setNonProxyHosts(final String[] nonProxyHosts) {
    this.nonProxyHosts = nonProxyHosts;
  }

  @Override
  public String toString() {
    return getClass().getSimpleName() + "{" +
        "connectionTimeout=" + connectionTimeout +
        ", maximumRetries=" + maximumRetries +
        ", querySuffix='" + querySuffix + '\'' +
        ", userAgentSuffix='" + userAgentSuffix + '\'' +
        ", httpProxy=" + httpProxy +
        ", httpsProxy=" + httpsProxy +
        ", nonProxyHosts=" + Arrays.toString(nonProxyHosts) +
        '}';
  }
}
