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
package org.sonatype.nexus.proxy.repository;

import org.sonatype.nexus.proxy.IllegalOperationException;
import org.sonatype.nexus.proxy.ItemNotFoundException;
import org.sonatype.nexus.proxy.ResourceStoreRequest;
import org.sonatype.nexus.proxy.access.Action;
import org.sonatype.nexus.proxy.item.StorageItem;

/**
 * A Request Strategy that is able to drive request handling and/or modify the request itself during that. Ultimately,
 * instances of this class, when registered with a {@link Repository} instance are able to change how Nexus core
 * handles
 * the request.
 *
 * @author cstamas
 * @since 2.5
 */
public interface RequestStrategy
{
  void onHandle(Repository repository, ResourceStoreRequest request, Action action)
      throws ItemNotFoundException, IllegalOperationException;

  void onServing(Repository repository, ResourceStoreRequest request, StorageItem item)
      throws ItemNotFoundException, IllegalOperationException;

  void onRemoteAccess(ProxyRepository repository, ResourceStoreRequest request, StorageItem item)
      throws ItemNotFoundException, IllegalOperationException;
}
