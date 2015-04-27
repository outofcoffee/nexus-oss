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
package org.sonatype.nexus.proxy.cache;

import java.util.Collection;

/**
 * The Class EhCacheCache is a thin wrapper around EHCache just to make things going.
 *
 * @author cstamas
 */
public class EhCachePathCache
    implements PathCache
{
  @Override
  public boolean contains(final String path) {
    return false;
  }

  @Override
  public boolean isExpired(final String path) {
    return false;
  }

  @Override
  public long getExpirationTime(final String path) {
    return 0;
  }

  @Override
  public boolean remove(final String path) {
    return false;
  }

  @Override
  public boolean removeWithParents(final String path) {
    return false;
  }

  @Override
  public boolean removeWithChildren(final String path) {
    return false;
  }

  @Override
  public boolean purge() {
    return false;
  }

  @Override
  public void put(final String path, final Object element) {

  }

  @Override
  public void put(final String path, final Object element, final int expirationSeconds) {

  }

  @Override
  public CacheStatistics getStatistics() {
    return null;
  }

  @Override
  public Collection<String> listKeysInCache() {
    return null;
  }
}
