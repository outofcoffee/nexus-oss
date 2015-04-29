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
package org.sonatype.nexus.internal.email;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;
import javax.inject.Singleton;

import org.sonatype.nexus.common.stateguard.Guarded;
import org.sonatype.nexus.common.stateguard.StateGuardLifecycleSupport;
import org.sonatype.nexus.email.EmailConfiguration;
import org.sonatype.nexus.email.EmailConfigurationStore;
import org.sonatype.nexus.email.EmailManager;
import org.sonatype.sisu.goodies.common.Mutex;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.sonatype.nexus.common.stateguard.StateGuardLifecycleSupport.State.STARTED;

/**
 * Default {@link EmailManager}.
 *
 * @since 3.0
 */
@Named
@Singleton
public class EmailManagerImpl
  extends StateGuardLifecycleSupport
  implements EmailManager
{
  private final EmailConfigurationStore store;

  private final Provider<EmailConfiguration> defaults;

  private final Mutex lock = new Mutex();

  private EmailConfiguration configuration;

  @Inject
  public EmailManagerImpl(final EmailConfigurationStore store,
                          @Named("initial") final Provider<EmailConfiguration> defaults)
  {
    this.store = checkNotNull(store);
    this.defaults = checkNotNull(defaults);
  }

  // FIXME: Need to sort out changes to sisu-mailer or replacement

  //
  // Configuration
  //

  /**
   * Load configuration from store, or use defaults.
   */
  private EmailConfiguration loadConfiguration() {
    EmailConfiguration model = store.load();

    // use defaults if no configuration was loaded from the store
    if (model == null) {
      model = defaults.get();

      // default config must not be null
      checkNotNull(model);

      log.info("Using default configuration: {}", model);
    }
    else {
      log.info("Loaded configuration: {}", model);
    }

    return model;
  }

  /**
   * Return configuration, loading if needed.
   *
   * The result model should be considered _immutable_ unless copied.
   */
  private EmailConfiguration getConfigurationInternal() {
    synchronized (lock) {
      if (configuration == null) {
        configuration = loadConfiguration();
      }
      return configuration;
    }
  }

  @Override
  @Guarded(by = STARTED)
  public EmailConfiguration getConfiguration() {
    return getConfigurationInternal().copy();
  }

  @Override
  @Guarded(by = STARTED)
  public void setConfiguration(final EmailConfiguration configuration) {
    checkNotNull(configuration);

    EmailConfiguration model = configuration.copy();
    // TODO: Validate configuration before saving?  Or leave to ext.direct?

    log.info("Saving configuration: {}", model);
    synchronized (lock) {
      store.save(model);
      this.configuration = model;
    }
  }

  // FIXME: Need to sort out changes to sisu-mailer or replacement

  //
  // Mail Sending
  //

  //@Override
  //@Guarded(by = STARTED)
  //public MailRequest createRequest(final String subject, final String body) {
  //  checkNotNull(subject);
  //  checkNotNull(body);
  //
  //  String mailId = "NX" + System.currentTimeMillis();
  //  MailRequest request = new MailRequest(mailId, DefaultMailType.DEFAULT_TYPE_ID);
  //
  //  // TODO: Add subject-prefix if configured
  //  request.getBodyContext().put(MailType.SUBJECT_KEY, subject);
  //
  //  request.getBodyContext().put(MailType.BODY_KEY, body);
  //
  //  // TODO: Add from-address, or do this on send?
  //
  //  // TODO: Add custom headers?
  //
  //  return request;
  //}
  //
  //@Override
  //@Guarded(by = STARTED)
  //public MailRequestStatus send(final MailRequest request) {
  //  checkNotNull(request);
  //
  //  EmailConfiguration model = getConfigurationInternal();
  //  MailRequestStatus status;
  //  if (model.isEnabled()) {
  //    log.debug("Sending email: {}", request);
  //    status = emailer.sendMail(request);
  //  }
  //  else {
  //    // generate synthetic status if disabled
  //    status = new MailRequestStatus(request);
  //    status.setErrorCause(new Exception("Email sending disabled"));
  //  }
  //  return status;
  //}
  //
  //@Override
  //@Guarded(by = STARTED)
  //public boolean sendVerification(final SmtpServerConfiguration configuration, final String address) {
  //  checkNotNull(configuration);
  //  checkNotNull(address);
  //
  //  // TODO: construct fresh emailer and send verification email
  //
  //  return false;
  //}

  // HACK: For ref this is what previous impl did... but this pollutes the default emailer configuration

  //public boolean sendSmtpConfigurationTest(final SmtpConfiguration config, final String email) throws EmailerException {
  //final EmailerConfiguration emailerConfiguration = new NexusEmailerConfiguration(customizers);
  //emailerConfiguration.setDebug(config.isDebugMode());
  //emailerConfiguration.setMailHost(config.getHostname());
  //emailerConfiguration.setMailPort(config.getPort());
  //emailerConfiguration.setUsername(config.getUsername());
  //emailerConfiguration.setPassword(config.getPassword());
  //emailerConfiguration.setSsl(config.isSslEnabled());
  //emailerConfiguration.setTls(config.isTlsEnabled());
  //
  //emailer.configure(emailerConfiguration);
  //
  //MailRequest request = new MailRequest(NEXUS_MAIL_ID, DefaultMailType.DEFAULT_TYPE_ID);
  //request.setFrom(new Address(config.getSystemEmailAddress(), "Nexus Repository Manager"));
  //request.getToAddresses().add(new Address(email));
  //request.getBodyContext().put(DefaultMailType.SUBJECT_KEY, "Nexus: SMTP Configuration validation.");
  //
  //StringBuilder body = new StringBuilder();
  //body.append("Your current SMTP configuration is valid!");
  //
  //request.getBodyContext().put(DefaultMailType.BODY_KEY, body.toString());
  //
  //MailRequestStatus status = emailer.sendSyncedMail(request);
  //
  //if (status.getErrorCause() != null) {
  //  log.error("Unable to send e-mail", status.getErrorCause());
  //  throw new EmailerException("Unable to send e-mail", status.getErrorCause());
  //}
  //
  //return status.isSent();
  //}
}
