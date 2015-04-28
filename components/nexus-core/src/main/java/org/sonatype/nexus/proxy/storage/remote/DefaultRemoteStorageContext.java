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
package org.sonatype.nexus.proxy.storage.remote;

import org.sonatype.nexus.proxy.repository.RemoteAuthenticationSettings;
import org.sonatype.nexus.proxy.repository.RemoteConnectionSettings;
import org.sonatype.nexus.proxy.repository.RemoteProxySettings;

public class DefaultRemoteStorageContext
    implements RemoteStorageContext
{
  public DefaultRemoteStorageContext() {

  }

  @Override
  public RemoteConnectionSettings getRemoteConnectionSettings() {
    return null;
  }

  @Override
  public void setRemoteConnectionSettings(final RemoteConnectionSettings settings) {

  }

  @Override
  public RemoteAuthenticationSettings getRemoteAuthenticationSettings() {
    return null;
  }

  @Override
  public void setRemoteAuthenticationSettings(final RemoteAuthenticationSettings settings) {

  }

  @Override
  public RemoteProxySettings getRemoteProxySettings() {
    return null;
  }

  @Override
  public void setRemoteProxySettings(final RemoteProxySettings settings) {

  }
}
