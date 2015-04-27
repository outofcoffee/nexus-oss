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

import java.util.List;

import javax.inject.Named;
import javax.inject.Singleton;

import org.sonatype.nexus.proxy.NoSuchRepositoryException;
import org.sonatype.nexus.proxy.repository.GroupRepository;
import org.sonatype.nexus.proxy.repository.Repository;
import org.sonatype.sisu.goodies.common.ComponentSupport;

/**
 * Repository registry. It holds handles to registered repositories and sorts them properly. This class is used to get
 * a
 * grip on repositories.
 * <p>
 * Getting reposes from here and changing repo attributes like group, id and rank have no effect on repo registry! For
 * that kind of change, you have to: 1) get repository, 2) remove repository from registry, 3) change repo attributes
 * and 4) add repository.
 * <p>
 * ProximityEvents: this component just "concentrates" the repositiry events of all known repositories by it. It can be
 * used as single point to access all repository events. TODO this is not a good place to keep group repository
 * management code
 *
 * @author cstamas
 */
@Singleton
@Named
public class DefaultRepositoryRegistry
    extends ComponentSupport
    implements RepositoryRegistry
{
  @Override
  public void addRepository(final Repository repository) {

  }

  @Override
  public void removeRepository(final String repoId) throws NoSuchRepositoryException {

  }

  @Override
  public void removeRepositorySilently(final String repoId) throws NoSuchRepositoryException {

  }

  @Override
  public List<Repository> getRepositories() {
    return null;
  }

  @Override
  public <T> List<T> getRepositoriesWithFacet(final Class<T> f) {
    return null;
  }

  @Override
  public Repository getRepository(final String repoId) throws NoSuchRepositoryException {
    return null;
  }

  @Override
  public <T> T getRepositoryWithFacet(final String repoId, final Class<T> f) throws NoSuchRepositoryException {
    return null;
  }

  @Override
  public boolean repositoryIdExists(final String repositoryId) {
    return false;
  }

  @Override
  public List<String> getGroupsOfRepository(final String repositoryId) {
    return null;
  }

  @Override
  public List<GroupRepository> getGroupsOfRepository(final Repository repository) {
    return null;
  }
}
