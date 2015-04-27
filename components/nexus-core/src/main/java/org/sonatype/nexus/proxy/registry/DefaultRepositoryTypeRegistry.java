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
package org.sonatype.nexus.proxy.registry;

import java.util.Map;
import java.util.Set;

import javax.inject.Named;
import javax.inject.Singleton;

import org.sonatype.nexus.proxy.repository.Repository;

@Singleton
@Named
public class DefaultRepositoryTypeRegistry
    implements RepositoryTypeRegistry
{
  @Override
  public Set<RepositoryTypeDescriptor> getRegisteredRepositoryTypeDescriptors() {
    return null;
  }

  @Override
  public boolean registerRepositoryTypeDescriptors(final RepositoryTypeDescriptor d) {
    return false;
  }

  @Override
  public boolean unregisterRepositoryTypeDescriptors(final RepositoryTypeDescriptor d) {
    return false;
  }

  @Override
  public Set<Class<? extends Repository>> getRepositoryRoles() {
    return null;
  }

  @Override
  public Set<String> getExistingRepositoryHints(final Class<? extends Repository> role) {
    return null;
  }

  @Override
  public RepositoryTypeDescriptor getRepositoryTypeDescriptor(final Class<? extends Repository> role,
                                                              final String hint)
  {
    return null;
  }

  @Override
  public RepositoryTypeDescriptor getRepositoryTypeDescriptor(final String role, final String hint) {
    return null;
  }

  @Override
  public Map<String, ContentClass> getContentClasses() {
    return null;
  }

  @Override
  public Set<String> getCompatibleContentClasses(final ContentClass contentClass) {
    return null;
  }

  @Override
  public ContentClass getRepositoryContentClass(final Class<? extends Repository> role, final String hint) {
    return null;
  }
}
