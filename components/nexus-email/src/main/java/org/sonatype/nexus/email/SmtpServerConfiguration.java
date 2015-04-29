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
package org.sonatype.nexus.email;

import org.sonatype.nexus.common.text.Strings2;

/**
 * SMTP-server configuration.
 *
 * @since 3.0
 */
public class SmtpServerConfiguration
  implements Cloneable
{
  private SmtpProtocol protocol;

  private String host;

  private int port;

  private String username;

  private String password;

  private String fromAddress;

  private String subjectPrefix;

  public SmtpProtocol getProtocol() {
    return protocol;
  }

  public void setProtocol(final SmtpProtocol protocol) {
    this.protocol = protocol;
  }

  public String getHost() {
    return host;
  }

  public void setHost(final String host) {
    this.host = host;
  }

  public int getPort() {
    return port;
  }

  public void setPort(final int port) {
    this.port = port;
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

  public String getFromAddress() {
    return fromAddress;
  }

  public void setFromAddress(final String fromAddress) {
    this.fromAddress = fromAddress;
  }

  public String getSubjectPrefix() {
    return subjectPrefix;
  }

  public void setSubjectPrefix(final String subjectPrefix) {
    this.subjectPrefix = subjectPrefix;
  }

  @Override
  public String toString() {
    return getClass().getSimpleName() + "{" +
        "protocol=" + protocol +
        ", host='" + host + '\'' +
        ", port=" + port +
        ", username='" + username + '\'' +
        ", password='" + Strings2.mask(password) + '\'' +
        ", fromAddress='" + fromAddress + '\'' +
        ", subjectPrefix='" + subjectPrefix + '\'' +
        '}';
  }
}
