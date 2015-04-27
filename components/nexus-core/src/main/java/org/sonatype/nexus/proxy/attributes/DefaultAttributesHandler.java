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
package org.sonatype.nexus.proxy.attributes;

import java.io.IOException;

import javax.inject.Named;
import javax.inject.Singleton;

import org.sonatype.nexus.proxy.item.ContentLocator;
import org.sonatype.nexus.proxy.item.RepositoryItemUid;
import org.sonatype.nexus.proxy.item.StorageItem;
import org.sonatype.sisu.goodies.common.ComponentSupport;

/**
 * The default implementation of AttributesHandler. Does not have any assumption regarding actual AttributeStorage it
 * uses. It uses {@link StorageItemInspector} and {@link StorageFileItemInspector} components for "expansion" of core
 * (and custom) attributes (those components might come from plugins too). This class also implements some
 * "optimizations" for attribute "lastRequested", by using coarser resolution for it (saving it very n-th hour or so).
 *
 * @author cstamas
 */
@Named
@Singleton
public class DefaultAttributesHandler
    extends ComponentSupport
    implements AttributesHandler
{
  @Override
  public AttributeStorage getAttributeStorage() {
    return null;
  }

  @Override
  public void fetchAttributes(final StorageItem item) throws IOException {

  }

  @Override
  public void storeAttributes(final StorageItem item, final ContentLocator content) throws IOException {

  }

  @Override
  public void storeAttributes(final StorageItem item) throws IOException {

  }

  @Override
  public boolean deleteAttributes(final RepositoryItemUid uid) throws IOException {
    return false;
  }

  @Override
  public void touchItemCheckedRemotely(final long timestamp, final StorageItem storageItem) throws IOException {

  }

  @Override
  public void touchItemLastRequested(final long timestamp, final StorageItem storageItem) throws IOException {

  }
}
