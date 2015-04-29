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
package org.sonatype.nexus;

import javax.crypto.Cipher;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.sonatype.nexus.events.EventSubscriberHost;
import org.sonatype.nexus.events.NexusInitializedEvent;
import org.sonatype.nexus.events.NexusStartedEvent;
import org.sonatype.nexus.events.NexusStoppedEvent;
import org.sonatype.nexus.events.NexusStoppingEvent;
import org.sonatype.nexus.internal.orient.OrientBootstrap;
import org.sonatype.nexus.security.SecuritySystem;
import org.sonatype.sisu.goodies.eventbus.EventBus;
import org.sonatype.sisu.goodies.lifecycle.LifecycleSupport;

import com.google.common.annotations.VisibleForTesting;
import org.eclipse.sisu.bean.BeanManager;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Boots Nexus application and critical components.
 *
 * @since 2.7
 */
@Singleton
@Named("NxApplication")
public class NxApplication
    extends LifecycleSupport
{
  private final EventBus eventBus;

  private final ApplicationStatusSource applicationStatusSource;

  private final SecuritySystem securitySystem;

  private final EventSubscriberHost eventSubscriberHost;

  private final OrientBootstrap orientBootstrap;

  private final BeanManager beanManager;

  @Inject
  public NxApplication(final EventBus eventBus,
                       final ApplicationStatusSource applicationStatusSource,
                       final SecuritySystem securitySystem,
                       final EventSubscriberHost eventSubscriberHost,
                       final OrientBootstrap orientBootstrap,
                       final BeanManager beanManager)
  {
    this.eventBus = checkNotNull(eventBus);
    this.applicationStatusSource = checkNotNull(applicationStatusSource);
    this.securitySystem = checkNotNull(securitySystem);
    this.eventSubscriberHost = checkNotNull(eventSubscriberHost);
    this.orientBootstrap = checkNotNull(orientBootstrap);
    this.beanManager = checkNotNull(beanManager);

    logInitialized();
  }

  @VisibleForTesting
  protected void logInitialized() {
    final StringBuilder sysInfoLog = new StringBuilder();
    sysInfoLog.append("\n-------------------------------------------------\n\n");
    sysInfoLog.append("Initializing ").append(getNexusNameForLogs());
    sysInfoLog.append("\n\n-------------------------------------------------");
    log.info(sysInfoLog.toString());
  }

  @VisibleForTesting
  protected final String getNexusNameForLogs() {
    final StringBuilder msg = new StringBuilder();
    msg.append("Sonatype Nexus ").append(applicationStatusSource.getSystemStatus().getEditionShort());
    msg.append(" ").append(applicationStatusSource.getSystemStatus().getVersion());
    return msg.toString();
  }

  @Override
  protected void doStart() throws Exception {
    if (Cipher.getMaxAllowedKeyLength("AES") == Integer.MAX_VALUE) {
      log.info("Unlimited strength JCE policy detected");
    }

    // register core and plugin contributed subscribers, start dispatching events to them
    eventSubscriberHost.start();

    applicationStatusSource.setState(SystemState.STOPPED);

    // HACK: Must start database services manually
    orientBootstrap.start();

    eventBus.post(new NexusInitializedEvent(this));

    applicationStatusSource.getSystemStatus().setState(SystemState.STARTING);

    securitySystem.start();

    applicationStatusSource.getSystemStatus().setState(SystemState.STARTED);

    log.info("Started {}", getNexusNameForLogs());
    eventBus.post(new NexusStartedEvent(this));
  }

  @Override
  protected void doStop() throws Exception {
    applicationStatusSource.getSystemStatus().setState(SystemState.STOPPING);

    // Due to no dependency mechanism in NX for components, we need to fire off a hint about shutdown first
    eventBus.post(new NexusStoppingEvent(this));

    // kill services + notify
    eventBus.post(new NexusStoppedEvent(this));
    eventSubscriberHost.stop();

    securitySystem.stop();

    // HACK: Must stop database services manually
    orientBootstrap.stop();

    // dispose of JSR-250
    beanManager.unmanage();

    applicationStatusSource.getSystemStatus().setState(SystemState.STOPPED);
    log.info("Stopped {}", getNexusNameForLogs());
  }
}
