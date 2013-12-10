/*
 * Sonatype Nexus (TM) Open Source Version
 * Copyright (c) 2007-2013 Sonatype, Inc.
 * All rights reserved. Includes the third-party code listed at http://links.sonatype.com/products/nexus/oss/attributions.
 *
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License Version 1.0,
 * which accompanies this distribution and is available at http://www.eclipse.org/legal/epl-v10.html.
 *
 * Sonatype Nexus (TM) Professional Version is available from Sonatype, Inc. "Sonatype" and "Sonatype Nexus" are trademarks
 * of Sonatype, Inc. Apache Maven is a trademark of the Apache Software Foundation. M2eclipse is a trademark of the
 * Eclipse Foundation. All other trademarks are the property of their respective owners.
 */

package org.sonatype.nexus.staticresources.internal;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.sonatype.nexus.internal.DevModeResources;
import org.sonatype.nexus.mime.MimeSupport;
import org.sonatype.nexus.plugins.rest.CacheControl;
import org.sonatype.nexus.plugins.rest.DefaultStaticResource;
import org.sonatype.nexus.plugins.rest.NexusResourceBundle;
import org.sonatype.nexus.plugins.rest.StaticResource;
import org.sonatype.nexus.staticresources.IndexPageRenderer;
import org.sonatype.nexus.web.ErrorStatusServletException;
import org.sonatype.nexus.web.WebUtils;

import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.google.common.base.Preconditions.checkNotNull;
import static javax.servlet.http.HttpServletResponse.SC_NOT_FOUND;
import static javax.servlet.http.HttpServletResponse.SC_NOT_MODIFIED;

/**
 * Provides access to static resources.
 *
 * @since 2.8
 */
@Singleton
@Named
public class StaticResourcesServlet
    extends HttpServlet
{
  private static final Logger log = LoggerFactory.getLogger(StaticResourcesServlet.class);

  private final List<NexusResourceBundle> nexusResourceBundles;

  private final MimeSupport mimeSupport;

  private final WebUtils webUtils;

  private final IndexPageRenderer indexPageRenderer;

  private final Map<String, StaticResource> staticResources;

  @Inject
  public StaticResourcesServlet(final List<NexusResourceBundle> nexusResourceBundles,
                                final MimeSupport mimeSupport,
                                final WebUtils webUtils,
                                final @Nullable IndexPageRenderer indexPageRenderer)
  {
    this.nexusResourceBundles = checkNotNull(nexusResourceBundles);
    this.mimeSupport = checkNotNull(mimeSupport);
    this.webUtils = checkNotNull(webUtils);
    this.indexPageRenderer = indexPageRenderer;
    this.staticResources = Maps.newHashMap();
    discoverResources();
  }

  private void discoverResources() {
    // log warnings if we find any overlapping resources
    if (!nexusResourceBundles.isEmpty()) {
      for (NexusResourceBundle bundle : nexusResourceBundles) {
        final List<StaticResource> resources = bundle.getContributedResouces();
        if (resources != null) {
          for (StaticResource resource : resources) {
            final String path = resource.getPath();
            log.trace("Serving static resource on path {} :: {}", path, resource);
            final StaticResource old = staticResources.put(path, resource);
            if (old != null) {
              log.warn("Overlapping static resources on path {}: old={}, new={}", path, old, resource);
            }
          }
        }
      }
    }

    // log the results
    if (DevModeResources.hasResourceLocations()) {
      log.info("DEV mode resources ENABLED; will override mounted ones if applicable");
    }
    log.info("Discovered and serving {} static resources", staticResources.size());
    if (log.isDebugEnabled()) {
      for (String path : staticResources.keySet()) {
        log.debug("  {}", path);
      }
    }
  }

  // service

  @Override
  protected void service(final HttpServletRequest request, final HttpServletResponse response)
      throws ServletException, IOException
  {
    webUtils.equipResponseWithStandardHeaders(response);
    super.service(request, response);
  }

  // GET

  @Override
  protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
      throws ServletException, IOException
  {
    final String requestPath = request.getPathInfo();
    log.debug("Requested resource {}", requestPath);
    // 0) see is index.html needed actually
    if ("".equals(requestPath) || "/".equals(requestPath)) {
      // redirect to index.html
      webUtils.sendTemporaryRedirect(response, webUtils.getAppRootUrl(request) + "index.html");
      return;
    }
    if ("/index.html".equals(requestPath)) {
      doGetIndex(request, response);
      return;
    }

    // locate it
    StaticResource staticResource = null;
    // 1) first "dev" resources if enabled (to override everything else)
    if (DevModeResources.hasResourceLocations()) {
      final File file = DevModeResources.getFileIfOnFileSystem(requestPath);
      if (file != null) {
        log.trace("Delivering DEV resource: {}", file.getAbsoluteFile());
        staticResource = new DevModeResource(requestPath, mimeSupport.guessMimeTypeFromPath(file.getName()), file);
      }
    }

    // 2) second, look at "ordinary" static resources, but only if devResource did not hit anything
    if (staticResource == null) {
      staticResource = staticResources.get(requestPath);
    }

    // 3) third, look into WAR embedded resources
    if (staticResource == null) {
      final URL resourceUrl = getServletContext().getResource(requestPath);
      if (resourceUrl != null) {
        staticResource = new DefaultStaticResource(resourceUrl, requestPath,
            mimeSupport.guessMimeTypeFromPath(requestPath));
      }
    }

    // deliver it, if we have anything
    if (staticResource != null) {
      doGetResource(request, response, staticResource);
    }
    else {
      throw new ErrorStatusServletException(SC_NOT_FOUND, "Not Found", "Resource not found");
    }
  }

  /**
   * Delegates to {@link IndexPageRenderer} to render index page.
   */
  private void doGetIndex(final HttpServletRequest request, final HttpServletResponse response)
      throws ServletException, IOException
  {
    if (indexPageRenderer != null) {
      indexPageRenderer.render(request, response, webUtils.getAppRootUrl(request));
    }
    else {
      throw new ErrorStatusServletException(SC_NOT_FOUND, "Not Found", "Index page not found");
    }
  }

  /**
   * Handles a file response, all the conditional request cases, and eventually the content serving of the file item.
   */
  private void doGetResource(final HttpServletRequest request,
                             final HttpServletResponse response,
                             final StaticResource resource) throws IOException
  {
    response.setHeader("Content-Type", resource.getContentType());
    response.setDateHeader("Last-Modified", resource.getLastModified());
    response.setHeader("Content-Length", String.valueOf(resource.getSize()));

    // cache-control
    if (resource instanceof CacheControl && ((CacheControl) resource).shouldCache()) {
      // default cache for 30 days
      response.setHeader("Cache-Control", "max-age=2592000");
    }
    else {
      // do not cache
      webUtils.addNoCacheResponseHeaders(response);
    }

    // honor if-modified-since GETs
    final long ifModifiedSince = request.getDateHeader("if-modified-since");
    // handle conditional GETs
    if (ifModifiedSince > -1 && resource.getLastModified() <= ifModifiedSince) {
      // this is a conditional GET using time-stamp, and resource is not modified
      response.setStatus(SC_NOT_MODIFIED);
    }
    else {
      // NEXUS-5023 disable IE for sniffing into response content
      response.setHeader("X-Content-Type-Options", "nosniff");
      // send the content only if needed (this method will be called for HEAD requests too)
      if ("GET".equalsIgnoreCase(request.getMethod())) {
        try (final InputStream in = resource.getInputStream()) {
          webUtils.sendContent(in, response);
        }
      }
    }
  }
}