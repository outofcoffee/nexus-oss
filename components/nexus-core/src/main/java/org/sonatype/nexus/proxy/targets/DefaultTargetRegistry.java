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
package org.sonatype.nexus.proxy.targets;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.inject.Named;
import javax.inject.Singleton;

import org.sonatype.nexus.configuration.AbstractLastingConfigurable;
import org.sonatype.nexus.configuration.ApplicationConfiguration;
import org.sonatype.nexus.configuration.CoreConfiguration;
import org.sonatype.nexus.configuration.model.CRepositoryTarget;
import org.sonatype.nexus.proxy.registry.ContentClass;
import org.sonatype.nexus.proxy.repository.Repository;
import org.sonatype.sisu.goodies.eventbus.EventBus;

/**
 * The default implementation of target registry.
 * 
 * @author cstamas
 */
@Singleton
@Named
public class DefaultTargetRegistry
    extends AbstractLastingConfigurable<List<CRepositoryTarget>>
    implements TargetRegistry
{
  public DefaultTargetRegistry(final String name, final EventBus eventBus,
                               final ApplicationConfiguration applicationConfiguration)
  {
    super(name, eventBus, applicationConfiguration);
  }

  @Override
  protected CoreConfiguration<List<CRepositoryTarget>> wrapConfiguration(final Object configuration) {
    return null;
  }

  @Override
  public Collection<Target> getRepositoryTargets() {
    return null;
  }

  @Override
  public Target getRepositoryTarget(final String id) {
    return null;
  }

  @Override
  public boolean addRepositoryTarget(final Target target) {
    return false;
  }

  @Override
  public boolean removeRepositoryTarget(final String id) {
    return false;
  }

  @Override
  public Set<Target> getTargetsForContentClass(final ContentClass contentClass) {
    return null;
  }

  @Override
  public Set<Target> getTargetsForContentClassPath(final ContentClass contentClass, final String path) {
    return null;
  }

  @Override
  public TargetSet getTargetsForRepositoryPath(final Repository repository, final String path) {
    return null;
  }

  @Override
  public boolean hasAnyApplicableTarget(final Repository repository) {
    return false;
  }
}
