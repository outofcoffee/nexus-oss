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
package org.sonatype.nexus.configuration.model;

import org.sonatype.nexus.configuration.ApplicationConfiguration;
import org.sonatype.nexus.configuration.ExternalConfiguration;
import org.sonatype.nexus.configuration.validator.ApplicationValidationResponse;
import org.sonatype.nexus.validation.ValidationResponse;

import org.codehaus.plexus.util.xml.Xpp3Dom;

import static com.google.common.base.Preconditions.checkNotNull;

public class CRepositoryCoreConfiguration
    extends AbstractCoreConfiguration<CRepository>
{
  private static final String REPOSITORY_ID_PATTERN = "^[a-zA-Z0-9_\\-\\.]+$";

  private final CRepository repositoryModel;

  private final CRepositoryExternalConfigurationHolderFactory<?> externalConfigurationFactory;

  private ExternalConfiguration<?> externalConfiguration;

  public CRepositoryCoreConfiguration(ApplicationConfiguration configuration, CRepository repositoryModel,
      CRepositoryExternalConfigurationHolderFactory<?> extFactory)
  {
    super(configuration);
    setOriginalConfiguration(repositoryModel);
    this.repositoryModel = checkNotNull(repositoryModel);
    this.externalConfigurationFactory = extFactory;
  }

  @Override
  protected void copyTransients(CRepository source, CRepository destination) {
    destination.setExternalConfiguration(source.getExternalConfiguration());

    destination.externalConfigurationImple = source.externalConfigurationImple;

    destination.defaultLocalStorageUrl = source.defaultLocalStorageUrl;

    // trick with RemoteStorage, which is an object, and XStream will not "overlap" it properly (ie. destionation !=
    // null but source == null)
    if (source.getRemoteStorage() == null) {
      destination.setRemoteStorage(null);
    }
  }

  public ExternalConfiguration<?> getExternalConfiguration() {
    if (externalConfiguration == null) {
      externalConfiguration = prepareExternalConfiguration(getOriginalConfiguration());
    }
    return externalConfiguration;
  }

  protected ExternalConfiguration<?> prepareExternalConfiguration(CRepository configuration) {
    if (externalConfigurationFactory == null) {
      return null;
    }

    // prepare the Xpp3Dom root node
    if (repositoryModel.getExternalConfiguration() == null) {
      // just put an elephant in South Africa to find it for sure ;)
      repositoryModel.setExternalConfiguration(new Xpp3Dom(DefaultCRepository.EXTERNAL_CONFIGURATION_NODE_NAME));
    }

    // set the holder
    if (repositoryModel.externalConfigurationImple == null) {
      // in 1st round, i intentionally choosed to make our lives bitter, and handle config manually
      // later we will see about it
      repositoryModel.externalConfigurationImple = externalConfigurationFactory
          .createExternalConfigurationHolder(repositoryModel);
    }

    return new DefaultExternalConfiguration<>(getApplicationConfiguration(),
        this, (AbstractXpp3DomExternalConfigurationHolder) repositoryModel.externalConfigurationImple);
  }

  @Override
  protected CRepository extractConfiguration(Configuration configuration) {
    // this is an exceptional situation, the "normal" way will not work, look at the constructor
    return null;
  }

  @Override
  public boolean isDirty() {
    return isThisDirty() || (getExternalConfiguration() != null && getExternalConfiguration().isDirty());
  }

  @Override
  public void validateChanges() {
    super.validateChanges();
    if (getExternalConfiguration() != null) {
      getExternalConfiguration().validateChanges();
    }
  }

  @Override
  public void commitChanges() {
    super.commitChanges();
    if (getExternalConfiguration() != null) {
      getExternalConfiguration().commitChanges();
    }
  }

  @Override
  public void rollbackChanges() {
    super.rollbackChanges();
    if (getExternalConfiguration() != null) {
      getExternalConfiguration().rollbackChanges();
    }
  }

  // ==

  @Override
  public ValidationResponse doValidateChanges(CRepository changedConfiguration) {
    return new ApplicationValidationResponse();
  }
}
