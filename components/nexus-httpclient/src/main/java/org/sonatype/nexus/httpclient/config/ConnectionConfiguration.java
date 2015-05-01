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

/**
 * Connection configuration.
 *
 * @since 3.0
 */
public class ConnectionConfiguration
    implements Cloneable
{
  private int timeout;

  private int maximumRetries;

  private String userAgent;

  // TODO: queryString?  This may be more harmful than helpful, omitting for now

  private AuthenticationConfiguration authentication;

  public int getTimeout() {
    return timeout;
  }

  public void setTimeout(final int timeout) {
    this.timeout = timeout;
  }

  public int getMaximumRetries() {
    return maximumRetries;
  }

  public void setMaximumRetries(final int maximumRetries) {
    this.maximumRetries = maximumRetries;
  }

  public String getUserAgent() {
    return userAgent;
  }

  public void setUserAgent(final String userAgent) {
    this.userAgent = userAgent;
  }

  public AuthenticationConfiguration getAuthentication() {
    return authentication;
  }

  public void setAuthentication(final AuthenticationConfiguration authentication) {
    this.authentication = authentication;
  }

  @Override
  public String toString() {
    return getClass().getSimpleName() + "{" +
        "timeout=" + timeout +
        ", maximumRetries=" + maximumRetries +
        ", userAgent='" + userAgent + '\'' +
        ", authentication=" + authentication +
        '}';
  }
}
