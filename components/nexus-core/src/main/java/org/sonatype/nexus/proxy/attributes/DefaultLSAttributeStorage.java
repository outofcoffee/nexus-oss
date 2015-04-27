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

import javax.enterprise.inject.Typed;
import javax.inject.Named;
import javax.inject.Singleton;

import org.sonatype.nexus.proxy.item.RepositoryItemUid;

/**
 * AttributeStorage implementation that uses LocalRepositoryStorage of repositories to store attributes "along" the
 * artifacts (well, not along but in same storage but hidden).
 *
 * @author cstamas
 */
@Typed(AttributeStorage.class)
@Named("ls")
@Singleton
public class DefaultLSAttributeStorage
    implements AttributeStorage
{
  @Override
  public Attributes getAttributes(final RepositoryItemUid uid) throws IOException {
    return null;
  }

  @Override
  public void putAttributes(final RepositoryItemUid uid, final Attributes attributes) throws IOException {

  }

  @Override
  public boolean deleteAttributes(final RepositoryItemUid uid) throws IOException {
    return false;
  }
}
