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

import org.sonatype.nexus.proxy.RequestContext;
import org.sonatype.nexus.proxy.ResourceStoreRequest;
import org.sonatype.nexus.proxy.attributes.Attributes;
import org.sonatype.nexus.proxy.repository.Repository;

/**
 * Abstract class encapsulating properties what all item kinds in Nexus share.
 */
public abstract class AbstractStorageItem
    implements StorageItem
{

  public Attributes getRepositoryItemAttributes() {
    return null;
  }

  public void upgrade() {
  }

  // ==

  /**
   * Default constructor, needed for XStream.
   * 
   * @deprecated This constructor is here for legacy reasons, do not use it!
   */
  @Deprecated
  private AbstractStorageItem() {
  }

  public AbstractStorageItem(final ResourceStoreRequest request, final boolean readable, final boolean writable) {
    this();
  }

  public AbstractStorageItem(Repository repository, ResourceStoreRequest request, boolean readable, boolean writable) {
    this(request, readable, writable);
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
  public void setRepositoryItemUid(final RepositoryItemUid repositoryItemUid) {

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
  public long getStoredLocally() {
    return 0;
  }

  @Override
  public void setStoredLocally(final long ts) {

  }

  @Override
  public long getRemoteChecked() {
    return 0;
  }

  @Override
  public void setRemoteChecked(final long ts) {

  }

  @Override
  public long getLastRequested() {
    return 0;
  }

  @Override
  public void setLastRequested(final long ts) {

  }

  @Override
  public boolean isVirtual() {
    return false;
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
  public String getParentPath() {
    return null;
  }

  @Override
  public int getPathDepth() {
    return 0;
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
