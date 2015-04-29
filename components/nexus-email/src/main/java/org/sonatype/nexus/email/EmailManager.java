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
package org.sonatype.nexus.email;

/**
 * Email manager.
 *
 * @since 3.0
 */
public interface EmailManager
{
  /**
   * Returns copy of current email configuration.
   */
  EmailConfiguration getConfiguration();

  /**
   * Installs new email configuration.
   */
  void setConfiguration(EmailConfiguration configuration);

  ///**
  // * Create default email request.
  // */
  //MailRequest createRequest(String subject, String body);
  //
  ///**
  // * Send email.
  // */
  //MailRequestStatus send(MailRequest request);
  //
  ///**
  // * Send verification email.
  // */
  //boolean sendVerification(SmtpServerConfiguration configuration, String address);
}
