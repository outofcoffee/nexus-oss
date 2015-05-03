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
package org.sonatype.nexus.repository.httpclient;

import javax.inject.Named;
import javax.inject.Singleton;

import org.sonatype.sisu.goodies.common.ComponentSupport;

// FIXME: Remove/adjust to nexus-httpclient api

@Named
@Singleton
public class HttpClientFactoryImpl
    extends ComponentSupport
    //implements HttpClientFactory
{
  //private org.sonatype.nexus.httpclient.HttpClientFactory httpClientFactory;
  //
  //@Inject
  //public HttpClientFactoryImpl(final org.sonatype.nexus.httpclient.HttpClientFactory httpClientFactory) {
  //  this.httpClientFactory = checkNotNull(httpClientFactory);
  //}

  //@Override
  //public HttpClient create(final HttpClientConfig config) {
  //  //return httpClientFactory.create(new Customizer()
  //  //{
  //  //  @Override
  //  //  public void customize(final HttpClientBuilder builder) {
  //  //    applyConfiguration(builder, config);
  //  //    applyRedirectStrategy(builder);
  //  //  }
  //  //});
  //  return null;
  //}

  //private void applyConfiguration(final HttpClientBuilder builder, final HttpClientConfig config) {
  //  // connection/socket timeouts
  //  int timeout = 20000; // default 20 seconds
  //  if (config.getConnection() != null && config.getConnection().getTimeout() != null) {
  //    timeout = config.getConnection().getTimeout() * 1000;
  //  }
  //  builder.getSocketConfigBuilder().setSoTimeout(timeout);
  //  builder.getRequestConfigBuilder().setConnectTimeout(timeout);
  //  builder.getRequestConfigBuilder().setSocketTimeout(timeout);
  //
  //  // obey the given retries count and apply it to client.
  //  int retries = 3; // default 3 retries
  //  if (config.getConnection() != null && config.getConnection().getRetries() != null) {
  //    retries = config.getConnection().getRetries();
  //  }
  //  builder.getHttpClientBuilder().setRetryHandler(new StandardHttpRequestRetryHandler(retries, false));
  //
  //  applyAuthenticationConfig(builder, config.getAuthentication(), null);
  //  applyProxyConfig(builder, config);
  //
  //  // Apply optional context-specific user-agent suffix
  //  if (config.getConnection() != null) {
  //    String userAgentSuffix = config.getConnection().getUserAgentCustomisation();
  //    String customizedUserAgent = null;
  //    if (!Strings.nullToEmpty(userAgentSuffix).isEmpty()) {
  //      customizedUserAgent = builder.getUserAgent() + " " + userAgentSuffix;
  //      builder.setUserAgent(customizedUserAgent);
  //    }
  //    String urlParameters = config.getConnection().getUrlParameters();
  //    if (Strings.nullToEmpty(urlParameters).isEmpty()) {
  //      urlParameters = null;
  //    }
  //    applyRequestExecutor(builder, customizedUserAgent, urlParameters);
  //  }
  //  if (config.getConnection() != null && Boolean.TRUE.equals(config.getConnection().getUseTrustStore())) {
  //    builder.getHttpClientBuilder().addInterceptorFirst(new HttpRequestInterceptor()
  //    {
  //      @Override
  //      public void process(final HttpRequest request, final HttpContext context) {
  //        context.setAttribute(SSLContextSelector.USE_TRUST_STORE, Boolean.TRUE);
  //      }
  //    });
  //  }
  //}

  //private void applyAuthenticationConfig(final HttpClientBuilder builder,
  //                                       final AuthenticationConfig config,
  //                                       final HttpHost httpHost)
  //{
  //  if (config != null) {
  //    String authScope = "target";
  //    if (httpHost != null) {
  //      authScope = httpHost.toHostString() + " proxy"; // FIXME: Why "proxy" here?
  //    }
  //
  //    log.debug("{} authentication setup using {}", authScope, config);
  //
  //    Credentials credentials = checkNotNull(config.getCredentials(), "credentials");
  //    List<String> authSchemes = checkNotNull(config.getPreferredAuthSchemes(), "authentication schemes");
  //
  //    if (httpHost != null) {
  //      builder.setCredentials(new AuthScope(httpHost), credentials);
  //      builder.getRequestConfigBuilder().setProxyPreferredAuthSchemes(authSchemes);
  //    }
  //    else {
  //      builder.setCredentials(AuthScope.ANY, credentials);
  //      builder.getRequestConfigBuilder().setTargetPreferredAuthSchemes(authSchemes);
  //    }
  //  }
  //}

  //@VisibleForTesting
  //public void applyProxyConfig(final HttpClientBuilder builder, final HttpClientConfig config) {
  //  if (config.getProxy() != null && config.getProxy().getHttp() != null) {
  //    Map<String, HttpHost> proxies = Maps.newHashMap();
  //
  //    HttpProxyConfig httpProxyConfig = config.getProxy().getHttp();
  //    HttpHost httpProxy = new HttpHost(httpProxyConfig.getHostname(), httpProxyConfig.getPort());
  //    applyAuthenticationConfig(builder, httpProxyConfig.getAuthentication(), httpProxy);
  //
  //    log.debug("http proxy setup with host '{}'", httpProxyConfig.getHostname());
  //    proxies.put("http", httpProxy);
  //    proxies.put("https", httpProxy);
  //
  //    if (config.getProxy().getHttps() != null) {
  //      HttpProxyConfig httpsProxyConfig = config.getProxy().getHttps();
  //      HttpHost httpsProxy = new HttpHost(httpsProxyConfig.getHostname(), httpsProxyConfig.getPort());
  //      applyAuthenticationConfig(builder, httpsProxyConfig.getAuthentication(), httpsProxy);
  //      log.debug("https proxy setup with host '{}'", httpsProxy.getHostName());
  //      proxies.put("https", httpsProxy);
  //    }
  //
  //    final Set<Pattern> nonProxyHostPatterns = Sets.newHashSet();
  //    if (config.getProxy().getNonProxyHosts() != null) {
  //      for (String nonProxyHostRegex : config.getProxy().getNonProxyHosts()) {
  //        try {
  //          nonProxyHostPatterns.add(Pattern.compile(nonProxyHostRegex, Pattern.CASE_INSENSITIVE));
  //        }
  //        catch (PatternSyntaxException e) {
  //          log.warn("Invalid non proxy host regex: {}", nonProxyHostRegex, e);
  //        }
  //      }
  //    }
  //
  //    builder.getHttpClientBuilder().setRoutePlanner(
  //        new NexusHttpRoutePlanner(proxies, nonProxyHostPatterns, DefaultSchemePortResolver.INSTANCE)
  //    );
  //  }
  //}

  //private void applyRedirectStrategy(final HttpClientBuilder builder) {
  //  builder.getHttpClientBuilder().setRedirectStrategy(new NexusRedirectStrategy());
  //}
  //
  //private void applyRequestExecutor(final HttpClientBuilder builder, final String userAgent, final String urlParameters) {
  //  if (userAgent != null || urlParameters != null) {
  //    builder.getHttpClientBuilder().setRequestExecutor(new HttpRequestExecutor()
  //    {
  //      @Override
  //      public void preProcess(final HttpRequest request, final HttpProcessor processor, final HttpContext ctx)
  //          throws HttpException, IOException
  //      {
  //        if (userAgent != null) {
  //          // NEXUS-7575: In case of HTTP Proxy tunnel, add generic UA while performing CONNECT
  //          if (!request.containsHeader(HTTP.USER_AGENT)) {
  //            request.addHeader(new BasicHeader(HTTP.USER_AGENT, userAgent));
  //          }
  //        }
  //        super.preProcess(request, processor, ctx);
  //      }
  //
  //      @Override
  //      public HttpResponse execute(final HttpRequest request,
  //                                  final HttpClientConnection conn,
  //                                  final HttpContext context)
  //          throws IOException, HttpException
  //      {
  //        HttpResponse response = null;
  //        if (urlParameters != null) {
  //          HttpRequestWrapper wrappedRequest = HttpRequestWrapper.wrap(request);
  //          URI uri = wrappedRequest.getURI();
  //          if (uri != null) {
  //            String uriString = uri.toASCIIString();
  //            try {
  //              wrappedRequest.setURI(new URI(uriString + (uri.getQuery() == null ? "?" : "&") + urlParameters));
  //            }
  //            catch (URISyntaxException e) {
  //              throw new HttpException("Unable to add url parameters " + urlParameters + " to " + uriString, e);
  //            }
  //            response = super.execute(wrappedRequest, conn, context);
  //          }
  //        }
  //        if (response == null) {
  //          response = super.execute(request, conn, context);
  //        }
  //        return response;
  //      }
  //    });
  //  }
  //}
}
