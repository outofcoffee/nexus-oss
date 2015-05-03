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
package org.sonatype.nexus.internal.httpclient;

import org.sonatype.nexus.httpclient.config.HttpClientConfiguration;
import org.sonatype.nexus.orient.OClassNameBuilder;
import org.sonatype.nexus.orient.entity.SingletonEntityAdapter;

import com.orientechnologies.orient.core.metadata.schema.OClass;
import com.orientechnologies.orient.core.record.impl.ODocument;

/**
 * {@link HttpClientConfiguration} entity-adapter.
 *
 * @since 3.0
 */
public class HttpClientConfigurationEntityAdapter
  extends SingletonEntityAdapter<HttpClientConfiguration>
{
  public static final String DB_CLASS = new OClassNameBuilder()
      .type(HttpClientConfiguration.class)
      .build();

  public HttpClientConfigurationEntityAdapter() {
    super(DB_CLASS);
  }

  @Override
  protected void defineType(final OClass type) {
    // TODO:
  }

  @Override
  protected HttpClientConfiguration newEntity() {
    return new HttpClientConfiguration();
  }

  @Override
  protected void readFields(final ODocument document, final HttpClientConfiguration entity) {
    // TODO:
  }

  @Override
  protected void writeFields(final ODocument document, final HttpClientConfiguration entity) {
    // TODO:
  }
}
