/*
 * Sonatype Nexus (TM) Open Source Version
 * Copyright (c) 2007-2014 Sonatype, Inc.
 * All rights reserved. Includes the third-party code listed at http://links.sonatype.com/products/nexus/oss/attributions.
 *
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License Version 1.0,
 * which accompanies this distribution and is available at http://www.eclipse.org/legal/epl-v10.html.
 *
 * Sonatype Nexus (TM) Professional Version is available from Sonatype, Inc. "Sonatype" and "Sonatype Nexus" are trademarks
 * of Sonatype, Inc. Apache Maven is a trademark of the Apache Software Foundation. M2eclipse is a trademark of the
 * Eclipse Foundation. All other trademarks are the property of their respective owners.
 */
package org.sonatype.nexus.plugins.ruby;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.sonatype.nexus.events.EventSubscriber;
import org.sonatype.nexus.proxy.events.NexusStoppedEvent;
import org.sonatype.nexus.ruby.DefaultRubygemsGateway;

import com.google.common.eventbus.Subscribe;
import org.jruby.embed.ScriptingContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Factory for Ruby backed helpers that also hooks into NX eventing and closes the gateway on system stop.
 *
 * @since 2.11
 */
@Singleton
@Named
public class NexusRubygemsGateway
    extends DefaultRubygemsGateway implements EventSubscriber
{
  private final Logger log = LoggerFactory.getLogger(getClass());

  @Inject
  public NexusRubygemsGateway(final ScriptingContainer container) {
    super(container);
    log.debug("Creating RubygemsGateway");
  }

  @Subscribe
  public void on(final NexusStoppedEvent event) {
    log.debug("Terminating RubygemsGateway");
    terminate();
  }
}