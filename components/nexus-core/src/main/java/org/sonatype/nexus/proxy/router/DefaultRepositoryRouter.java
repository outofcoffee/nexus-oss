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
package org.sonatype.nexus.proxy.router;

import java.io.InputStream;
import java.util.Collection;
import java.util.Map;

import javax.inject.Named;
import javax.inject.Singleton;

import org.sonatype.nexus.configuration.AbstractLastingConfigurable;
import org.sonatype.nexus.configuration.ApplicationConfiguration;
import org.sonatype.nexus.configuration.CoreConfiguration;
import org.sonatype.nexus.configuration.model.CRouting;
import org.sonatype.nexus.proxy.AccessDeniedException;
import org.sonatype.nexus.proxy.IllegalOperationException;
import org.sonatype.nexus.proxy.ItemNotFoundException;
import org.sonatype.nexus.proxy.ResourceStoreRequest;
import org.sonatype.nexus.proxy.StorageException;
import org.sonatype.nexus.proxy.access.Action;
import org.sonatype.nexus.proxy.item.StorageItem;
import org.sonatype.nexus.proxy.item.StorageLinkItem;
import org.sonatype.nexus.proxy.storage.UnsupportedStorageOperationException;
import org.sonatype.sisu.goodies.eventbus.EventBus;

/**
 * The simplest re-implementation for RepositoryRouter that does only routing.
 *
 * @author cstamas
 */
@Singleton
@Named
public class DefaultRepositoryRouter
    extends AbstractLastingConfigurable<CRouting>
    implements RepositoryRouter
{
  public DefaultRepositoryRouter(final String name, final EventBus eventBus,
                                 final ApplicationConfiguration applicationConfiguration)
  {
    super(name, eventBus, applicationConfiguration);
  }

  @Override
  protected CoreConfiguration<CRouting> wrapConfiguration(final Object configuration) {
    return null;
  }

  @Override
  public boolean isFollowLinks() {
    return false;
  }

  @Override
  public void setFollowLinks(final boolean follow) {

  }

  @Override
  public StorageItem dereferenceLink(final StorageLinkItem link)
      throws AccessDeniedException, ItemNotFoundException, IllegalOperationException, StorageException
  {
    return null;
  }

  @Override
  public StorageItem dereferenceLink(final StorageLinkItem link, final boolean localOnly, final boolean remoteOnly)
      throws AccessDeniedException, ItemNotFoundException, IllegalOperationException, StorageException
  {
    return null;
  }

  @Override
  public RequestRoute getRequestRouteForRequest(final ResourceStoreRequest request) throws ItemNotFoundException {
    return null;
  }

  @Override
  public boolean authorizePath(final ResourceStoreRequest request, final Action action) {
    return false;
  }

  @Override
  public StorageItem retrieveItem(final ResourceStoreRequest request)
      throws ItemNotFoundException, IllegalOperationException, StorageException, AccessDeniedException
  {
    return null;
  }

  @Override
  public void copyItem(final ResourceStoreRequest from, final ResourceStoreRequest to)
      throws UnsupportedStorageOperationException, ItemNotFoundException, IllegalOperationException, StorageException,
             AccessDeniedException
  {

  }

  @Override
  public void moveItem(final ResourceStoreRequest from, final ResourceStoreRequest to)
      throws UnsupportedStorageOperationException, ItemNotFoundException, IllegalOperationException, StorageException,
             AccessDeniedException
  {

  }

  @Override
  public void deleteItem(final ResourceStoreRequest request)
      throws UnsupportedStorageOperationException, ItemNotFoundException, IllegalOperationException, StorageException,
             AccessDeniedException
  {

  }

  @Override
  public void storeItem(final ResourceStoreRequest request, final InputStream is,
                        final Map<String, String> userAttributes)
      throws UnsupportedStorageOperationException, ItemNotFoundException, IllegalOperationException, StorageException,
             AccessDeniedException
  {

  }

  @Override
  public void createCollection(final ResourceStoreRequest request, final Map<String, String> userAttributes)
      throws UnsupportedStorageOperationException, ItemNotFoundException, IllegalOperationException, StorageException,
             AccessDeniedException
  {

  }

  @Override
  public Collection<StorageItem> list(final ResourceStoreRequest request)
      throws ItemNotFoundException, IllegalOperationException, StorageException, AccessDeniedException
  {
    return null;
  }
}
