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
package org.sonatype.nexus.proxy.item;

import java.io.IOException;
import java.io.InputStream;

import org.sonatype.nexus.proxy.RequestContext;
import org.sonatype.nexus.proxy.ResourceStoreRequest;
import org.sonatype.nexus.proxy.repository.Repository;

/**
 * Default implementation of {@link StorageFileItem}.
 */
public class DefaultStorageFileItem
    implements StorageFileItem
{
  public DefaultStorageFileItem(Repository repository, ResourceStoreRequest request, boolean canRead,
                                boolean canWrite, ContentLocator contentLocator)
  {
  }

  @Override
  public long getLength() {
    return 0;
  }

  @Override
  public String getMimeType() {
    return null;
  }

  @Override
  public boolean isReusableStream() {
    return false;
  }

  @Override
  public InputStream getInputStream() throws IOException {
    return null;
  }

  @Override
  public void setContentLocator(final ContentLocator locator) {

  }

  @Override
  public ContentLocator getContentLocator() {
    return null;
  }

  @Override
  public String getContentGeneratorId() {
    return null;
  }

  @Override
  public void setContentGeneratorId(final String contentGeneratorId) {

  }

  @Override
  public boolean isContentGenerated() {
    return false;
  }

  @Override
  public ResourceStoreRequest getResourceStoreRequest() {
    return null;
  }

  @Override
  public RepositoryItemUid getRepositoryItemUid() {
    return null;
  }


  @Override
  public String getRepositoryId() {
    return null;
  }

  @Override
  public long getCreated() {
    return 0;
  }

  @Override
  public long getModified() {
    return 0;
  }

  @Override
  public long getLastRequested() {
    return 0;
  }

  @Override
  public boolean isReadable() {
    return false;
  }

  @Override
  public boolean isWritable() {
    return false;
  }

  @Override
  public boolean isExpired() {
    return false;
  }

  @Override
  public void setExpired(final boolean expired) {

  }

  @Override
  public String getPath() {
    return null;
  }

  @Override
  public String getName() {
    return null;
  }

  @Override
  public String getRemoteUrl() {
    return null;
  }

  @Override
  public RequestContext getItemContext() {
    return null;
  }
}
