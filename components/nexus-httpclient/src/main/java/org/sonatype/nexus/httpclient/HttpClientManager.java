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

import org.sonatype.nexus.httpclient.config.HttpClientConfiguration;

import org.apache.http.client.HttpClient;

/**
 * HTTP-client manager.
 *
 * @since 3.0
 */
public interface HttpClientManager
{
  HttpClientConfiguration getConfiguration();

  void setConfiguration(HttpClientConfiguration configuration);

  // TODO: Revisit use where Provider<HttpClient> would be more appropriate instead of wider api manager exposes

  HttpClient create();

  // TODO: create with customizer
}
