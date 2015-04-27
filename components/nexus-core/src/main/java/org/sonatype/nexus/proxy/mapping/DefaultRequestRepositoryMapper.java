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
package org.sonatype.nexus.proxy.mapping;

import java.util.List;
import java.util.Map;

import javax.inject.Named;
import javax.inject.Singleton;

import org.sonatype.nexus.configuration.AbstractLastingConfigurable;
import org.sonatype.nexus.configuration.ApplicationConfiguration;
import org.sonatype.nexus.configuration.CoreConfiguration;
import org.sonatype.nexus.configuration.model.CRepositoryGrouping;
import org.sonatype.nexus.proxy.NoSuchResourceStoreException;
import org.sonatype.nexus.proxy.ResourceStoreRequest;
import org.sonatype.nexus.proxy.repository.Repository;
import org.sonatype.sisu.goodies.eventbus.EventBus;

/**
 * The Class PathBasedRequestRepositoryMapper filters repositories to search using supplied list of filter expressions.
 * It is parametrized by java,util.Map, the contents: </p> <tt>
 * regexp1=repo1,repo2...
 * regexp2=repo3,repo4...
 * ...
 * </tt>
 * <p>
 * An example (with grouped Router and two repositories, one for central and one for inhouse in same group):
 * </p>
 * <tt>
 * /com/company/=inhouse
 * /org/apache/=central
 * </tt>
 *
 * @author cstamas
 */
@Singleton
@Named
public class DefaultRequestRepositoryMapper
    extends AbstractLastingConfigurable<CRepositoryGrouping>
    implements RequestRepositoryMapper
{
  public DefaultRequestRepositoryMapper(final String name, final EventBus eventBus,
                                        final ApplicationConfiguration applicationConfiguration)
  {
    super(name, eventBus, applicationConfiguration);
  }

  @Override
  protected CoreConfiguration<CRepositoryGrouping> wrapConfiguration(final Object configuration) {
    return null;
  }

  @Override
  public Map<String, RepositoryPathMapping> getMappings() {
    return null;
  }

  @Override
  public boolean addMapping(final RepositoryPathMapping mapping) {
    return false;
  }

  @Override
  public boolean removeMapping(final String id) {
    return false;
  }

  @Override
  public List<Repository> getMappedRepositories(final Repository repository, final ResourceStoreRequest request,
                                                final List<Repository> resolvedRepositories)
      throws NoSuchResourceStoreException
  {
    return null;
  }
}
