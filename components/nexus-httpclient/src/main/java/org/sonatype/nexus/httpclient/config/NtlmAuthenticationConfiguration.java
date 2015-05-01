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

import org.sonatype.nexus.common.text.Strings2;

/**
 * NTLM authentication configuration.
 *
 * @since 3.0
 */
public class NtlmAuthenticationConfiguration
  extends AuthenticationConfiguration
{
  public static final String TYPE = "ntml";

  private String username;

  private String password;

  private String host;

  private String domain;

  public NtlmAuthenticationConfiguration() {
    super(TYPE);
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(final String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(final String password) {
    this.password = password;
  }

  public String getHost() {
    return host;
  }

  public void setHost(final String host) {
    this.host = host;
  }

  public String getDomain() {
    return domain;
  }

  public void setDomain(final String domain) {
    this.domain = domain;
  }

  @Override
  public String toString() {
    return getClass().getSimpleName() + "{" +
        "username='" + username + '\'' +
        ", password='" + Strings2.mask(password) + '\'' +
        ", host='" + host + '\'' +
        ", domain='" + domain + '\'' +
        '}';
  }
}
