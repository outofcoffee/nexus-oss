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

import java.util.Arrays;

/**
 * Proxy configuration.
 *
 * @since 3.0
 */
public class ProxyConfiguration
    implements Cloneable
{
  private ProxyServerConfiguration http;

  private ProxyServerConfiguration https;

  private String[] nonProxyHosts;

  public ProxyServerConfiguration getHttp() {
    return http;
  }

  public void setHttp(final ProxyServerConfiguration http) {
    this.http = http;
  }

  public ProxyServerConfiguration getHttps() {
    return https;
  }

  public void setHttps(final ProxyServerConfiguration https) {
    this.https = https;
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
        "http=" + http +
        ", https=" + https +
        ", nonProxyHosts=" + Arrays.toString(nonProxyHosts) +
        '}';
  }
}
