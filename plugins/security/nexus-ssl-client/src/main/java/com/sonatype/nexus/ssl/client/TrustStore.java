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
package com.sonatype.nexus.ssl.client;

import java.util.Collection;

import com.sonatype.nexus.ssl.client.internal.TrustStoreImpl;
import com.sonatype.nexus.ssl.model.TrustStoreKey;

import com.google.inject.ImplementedBy;

/**
 * Trust Store Nexus Client Subsystem.
 *
 * @since ssl 1.0
 */
@ImplementedBy(TrustStoreImpl.class)
public interface TrustStore
{

  Certificate create();

  Collection<Certificate> get();

  Certificate get(String id);

  boolean isEnabledFor(TrustStoreKey key);

  TrustStore enableFor(TrustStoreKey key);

  TrustStore disableFor(TrustStoreKey key);

}
