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
package org.sonatype.nexus.proxy.wastebasket;

import java.io.IOException;

import javax.inject.Named;
import javax.inject.Singleton;

import org.sonatype.nexus.proxy.LocalStorageException;
import org.sonatype.nexus.proxy.ResourceStoreRequest;
import org.sonatype.nexus.proxy.repository.Repository;
import org.sonatype.nexus.proxy.storage.local.LocalRepositoryStorage;
import org.sonatype.sisu.goodies.common.ComponentSupport;

@Named
@Singleton
public class DefaultWastebasket
    extends ComponentSupport
    implements Wastebasket
{
  @Override
  public DeleteOperation getDeleteOperation() {
    return null;
  }

  @Override
  public void setDeleteOperation(final DeleteOperation deleteOperation) {

  }

  @Override
  public Long getTotalSize() {
    return null;
  }

  @Override
  public void purgeAll() throws IOException {

  }

  @Override
  public void purgeAll(final long age) throws IOException {

  }

  @Override
  public Long getSize(final Repository repository) {
    return null;
  }

  @Override
  public void purge(final Repository repository) throws IOException {

  }

  @Override
  public void purge(final Repository repository, final long age) throws IOException {

  }

  @Override
  public void delete(final LocalRepositoryStorage ls, final Repository repository, final ResourceStoreRequest request)
      throws LocalStorageException
  {

  }

  @Override
  public boolean undelete(final LocalRepositoryStorage ls, final Repository repository,
                          final ResourceStoreRequest request)
      throws LocalStorageException
  {
    return false;
  }
}
