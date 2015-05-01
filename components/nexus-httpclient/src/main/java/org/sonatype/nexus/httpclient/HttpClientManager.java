package org.sonatype.nexus.httpclient;

import org.sonatype.nexus.httpclient.config.HttpClientConfiguration;

/**
 * ???
 *
 * @since 3.0
 */
public interface HttpClientManager
{
  HttpClientConfiguration getConfiguration();

  void setConfiguration(HttpClientConfiguration configuration);

  // create
}
