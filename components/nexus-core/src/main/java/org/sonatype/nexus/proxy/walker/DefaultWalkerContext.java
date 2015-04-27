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
package org.sonatype.nexus.proxy.walker;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

import org.sonatype.nexus.proxy.ResourceStoreRequest;
import org.sonatype.nexus.proxy.item.StorageItem;
import org.sonatype.nexus.proxy.repository.Repository;

public class DefaultWalkerContext
    implements WalkerContext
{
  public DefaultWalkerContext(final Repository store, final ResourceStoreRequest request) {
    this(store, request, null);
  }

  public DefaultWalkerContext(final Repository store, final ResourceStoreRequest request, final WalkerFilter filter) {
    this(store, request, filter, TraversalType.DEPTH_FIRST, false);
  }

  /**
   * @deprecated Use another ctor.
   */
  @Deprecated
  public DefaultWalkerContext(final Repository store, final ResourceStoreRequest request, final WalkerFilter filter,
                              boolean localOnly)
  {
    this(store, request, filter);
  }

  public DefaultWalkerContext(final Repository store,
                              final ResourceStoreRequest request,
                              @Nullable final WalkerFilter filter,
                              final TraversalType traversalType,
                              final boolean processCollections)
    {

  }

  @Override
  public TraversalType getTraversalType() {
    return null;
  }

  @Override
  public boolean isProcessCollections() {
    return false;
  }

  @Override
  public ResourceStoreRequest getResourceStoreRequest() {
    return null;
  }

  @Override
  public boolean isLocalOnly() {
    return false;
  }

  @Override
  public Map<String, Object> getContext() {
    return null;
  }

  @Override
  public List<WalkerProcessor> getProcessors() {
    return null;
  }

  @Override
  public void stop(final Throwable cause) {

  }

  @Override
  public boolean isStopped() {
    return false;
  }

  @Override
  public Throwable getStopCause() {
    return null;
  }

  @Override
  public WalkerFilter getFilter() {
    return null;
  }

  @Override
  public Repository getRepository() {
    return null;
  }

  @Override
  public WalkerThrottleController getThrottleController() {
    return null;
  }

  @Override
  public Comparator<StorageItem> getItemComparator() {
    return null;
  }
}
