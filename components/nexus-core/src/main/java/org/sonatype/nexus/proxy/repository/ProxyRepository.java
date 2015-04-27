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

import org.sonatype.nexus.proxy.RemoteStorageException;
import org.sonatype.nexus.proxy.storage.remote.RemoteStorageContext;

/**
 * A proxy repository is what it's name says :)
 *
 * @author cstamas
 */
public interface ProxyRepository
    extends Repository
{
  /**
   * Gets proxy mode.
   */
  ProxyMode getProxyMode();

  /**
   * Returns the remote URL of this repository, if any.
   *
   * @return remote url of this repository, null otherwise.
   */
  String getRemoteUrl();

  /**
   * Sets the remote url.
   *
   * @param url the new remote url
   */
  void setRemoteUrl(String url)
      throws RemoteStorageException;

  /**
   * Returns repository specific remote connection context.
   */
  RemoteStorageContext getRemoteStorageContext();
}
