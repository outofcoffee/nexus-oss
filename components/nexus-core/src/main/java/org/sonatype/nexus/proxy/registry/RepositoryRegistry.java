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

import org.sonatype.nexus.proxy.NoSuchRepositoryException;
import org.sonatype.nexus.proxy.repository.Repository;

/**
 * The Interface RepositoryRegistry. It holds the Repositories.
 *
 * @author cstamas
 */
public interface RepositoryRegistry
{
  List<Repository> getRepositories();

  <T> List<T> getRepositoriesWithFacet(Class<T> f);

  Repository getRepository(String repoId)
      throws NoSuchRepositoryException;

  <T> T getRepositoryWithFacet(String repoId, Class<T> f)
      throws NoSuchRepositoryException;

  boolean repositoryIdExists(String repositoryId);
}
