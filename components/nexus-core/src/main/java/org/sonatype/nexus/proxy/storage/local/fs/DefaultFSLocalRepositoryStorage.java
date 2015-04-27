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
package org.sonatype.nexus.proxy.storage.local.fs;

import java.io.File;
import java.net.URL;
import java.util.Collection;

import javax.inject.Named;
import javax.inject.Singleton;

import org.sonatype.nexus.proxy.ItemNotFoundException;
import org.sonatype.nexus.proxy.LocalStorageException;
import org.sonatype.nexus.proxy.ResourceStoreRequest;
import org.sonatype.nexus.proxy.item.AbstractStorageItem;
import org.sonatype.nexus.proxy.item.StorageItem;
import org.sonatype.nexus.proxy.repository.Repository;
import org.sonatype.nexus.proxy.storage.UnsupportedStorageOperationException;
import org.sonatype.nexus.proxy.storage.local.LocalRepositoryStorage;

/**
 * LocalRepositoryStorage that uses plain File System (relies on {@link File}) to implement it's functionality.
 *
 * @author cstamas
 */
@Singleton
@Named(DefaultFSLocalRepositoryStorage.PROVIDER_STRING)
public class DefaultFSLocalRepositoryStorage
    implements LocalRepositoryStorage
{
  public static final String PROVIDER_STRING = "file";

  @Override
  public String getProviderId() {
    return null;
  }

  @Override
  public void validateStorageUrl(final String url) throws LocalStorageException {

  }

  @Override
  public boolean isReachable(final Repository repository, final ResourceStoreRequest request)
      throws LocalStorageException
  {
    return false;
  }

  @Override
  public URL getAbsoluteUrlFromBase(final Repository repository, final ResourceStoreRequest request)
      throws LocalStorageException
  {
    return null;
  }

  @Override
  public boolean containsItem(final Repository repository, final ResourceStoreRequest request)
      throws LocalStorageException
  {
    return false;
  }

  @Override
  public AbstractStorageItem retrieveItem(final Repository repository, final ResourceStoreRequest request)
      throws ItemNotFoundException, LocalStorageException
  {
    return null;
  }

  @Override
  public void storeItem(final Repository repository, final StorageItem item)
      throws UnsupportedStorageOperationException, LocalStorageException
  {

  }

  @Override
  public void deleteItem(final Repository repository, final ResourceStoreRequest request)
      throws ItemNotFoundException, UnsupportedStorageOperationException, LocalStorageException
  {

  }

  @Override
  public void shredItem(final Repository repository, final ResourceStoreRequest request)
      throws ItemNotFoundException, UnsupportedStorageOperationException, LocalStorageException
  {

  }

  @Override
  public void moveItem(final Repository repository, final ResourceStoreRequest from, final ResourceStoreRequest to)
      throws ItemNotFoundException, UnsupportedStorageOperationException, LocalStorageException
  {

  }

  @Override
  public Collection<StorageItem> listItems(final Repository repository, final ResourceStoreRequest request)
      throws ItemNotFoundException, LocalStorageException
  {
    return null;
  }
}
