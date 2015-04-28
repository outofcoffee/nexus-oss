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

package org.sonatype.nexus.configuration.validator;

import java.util.Random;

import javax.inject.Named;
import javax.inject.Singleton;

import org.sonatype.nexus.configuration.model.CHttpProxySettings;
import org.sonatype.nexus.configuration.model.CRemoteConnectionSettings;
import org.sonatype.nexus.configuration.model.CRemoteHttpProxySettings;
import org.sonatype.nexus.configuration.model.CRemoteProxySettings;
import org.sonatype.nexus.configuration.model.CRouting;
import org.sonatype.nexus.configuration.model.CSmtpConfiguration;
import org.sonatype.nexus.configuration.model.Configuration;
import org.sonatype.nexus.validation.ValidationMessage;
import org.sonatype.nexus.validation.ValidationResponse;

import org.codehaus.plexus.util.StringUtils;

/**
 * Default {@link ApplicationConfigurationValidator}.
 */
@Singleton
@Named
public class DefaultApplicationConfigurationValidator
    implements ApplicationConfigurationValidator
{
  private final Random rand = new Random(System.currentTimeMillis());

  private static final String REPOSITORY_ID_PATTERN = "^[a-zA-Z0-9_\\-\\.]+$";

  private String generateId() {
    return Long.toHexString(System.nanoTime() + rand.nextInt(2008));
  }

  public ValidationResponse validateModel(Configuration model) {
    ApplicationValidationResponse response = new ApplicationValidationResponse();
    ApplicationValidationContext context = response.getContext();

    // global conn settings
    if (model.getGlobalConnectionSettings() == null) {
      model.setGlobalConnectionSettings(new CRemoteConnectionSettings());
      response.addWarning("Global connection settings block, which is mandatory, was missing. Reset with defaults.");
      response.setModified(true);
    }

    // remote proxy settings (optional)
    final CRemoteProxySettings rps = model.getRemoteProxySettings();
    if (rps != null) {
      if (rps.getHttpProxySettings() != null) {
        response.append(validateRemoteHttpProxySettings(context, rps.getHttpProxySettings()));
      }
      if (rps.getHttpsProxySettings() != null) {
        response.append(validateRemoteHttpProxySettings(context, rps.getHttpsProxySettings()));
      }
    }

    // nexus built-in http proxy
    if (model.getHttpProxy() != null) {
      response.append(validateHttpProxySettings(context, model.getHttpProxy()));
    }
    else {
      model.setHttpProxy(new CHttpProxySettings());
      response.addWarning("The HTTP Proxy section was missing from configuration, defaulted it.");
      response.setModified(true);
    }

    // routing
    if (model.getRouting() == null) {
      model.setRouting(new CRouting());
      response.addWarning("The routing section was missing from configuration, defaulted it.");
      response.setModified(true);
    }

    // check existing reposes and check their realms
    context.addExistingRepositoryIds();

    //List<CRepository> reposes = model.getRepositories();
    //for (CRepository repo : reposes) {
    //  response.append(validateRepository(context, repo));
    //}
    //
    //// check groups (optional section)
    //if (model.getRepositoryGrouping() != null) {
    //  response.append(validateRepositoryGrouping(context, model.getRepositoryGrouping()));
    //}
    //
    //// check repo targets (optional section)
    //if (model.getRepositoryTargets() != null) {
    //  List<CRepositoryTarget> targets = model.getRepositoryTargets();
    //
    //  for (CRepositoryTarget target : targets) {
    //    response.append(validateRepositoryTarget(context, target));
    //  }
    //}

    response.append(validateSmtpConfiguration(context, model.getSmtpConfiguration()));

    return response;
  }

  @Override
  public ValidationResponse validateHttpProxySettings(ApplicationValidationContext ctx, CHttpProxySettings settings) {
    ApplicationValidationResponse response = new ApplicationValidationResponse(ctx);

    if (settings.getPort() < 80) {
      settings.setPort(8082);
      response.addWarning("The HTTP Proxy port is below 80? Settings defaulted.");
      response.setModified(true);
    }

    if (!CHttpProxySettings.PROXY_POLICY_PASS_THRU.equals(settings.getProxyPolicy())
        && !CHttpProxySettings.PROXY_POLICY_STRICT.equals(settings.getProxyPolicy())) {
      response.addError("The HTTP Proxy policy settings is invalid: '" + settings.getProxyPolicy()
          + "'. Valid policies are '" + CHttpProxySettings.PROXY_POLICY_STRICT + "' and '"
          + CHttpProxySettings.PROXY_POLICY_PASS_THRU + "'.");
    }

    return response;
  }

  @Override
  public ValidationResponse validateRemoteHttpProxySettings(ApplicationValidationContext ctx,
                                                            CRemoteHttpProxySettings settings)
  {
    ApplicationValidationResponse response = new ApplicationValidationResponse(ctx);

    if (settings.getProxyPort() < 1 || settings.getProxyPort() > 65535) {
      response.addError("The proxy port must be an integer between 1 and 65535!");
    }

    return response;
  }

  @Override
  public ValidationResponse validateSmtpConfiguration(ApplicationValidationContext ctx, CSmtpConfiguration settings) {
    ApplicationValidationResponse response = new ApplicationValidationResponse(ctx);

    if (StringUtils.isEmpty(settings.getHostname())) {
      ValidationMessage msg = new ValidationMessage("host", "SMTP Host is empty.");
      response.addError(msg);
    }

    if (settings.getPort() < 0) {
      ValidationMessage msg = new ValidationMessage("port", "SMTP Port is invalid.  Enter a port greater than 0.");
      response.addError(msg);
    }

    if (StringUtils.isEmpty(settings.getSystemEmailAddress())) {
      ValidationMessage msg = new ValidationMessage("systemEmailAddress", "System Email Address is empty.");
      response.addError(msg);
    }

    return response;
  }
}
