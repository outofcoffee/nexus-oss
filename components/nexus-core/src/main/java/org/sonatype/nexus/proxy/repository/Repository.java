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
package org.sonatype.nexus.proxy.repository;

import org.sonatype.nexus.proxy.registry.ContentClass;

/**
 * Repository interface used by Proximity. It is an extension of ResourceStore iface, allowing to make direct
 * RepositoryItemUid based requests which bypasses AccessManager. Also, defines some properties.
 *
 * @author cstamas
 */
public interface Repository
{
  /**
   * Returns the ID of the resourceStore.
   *
   * @return the id
   */
  String getId();

  /**
   * Sets the ID of the resourceStore. It must be unique type-wide (Router vs Repository).
   *
   * @param id the ID of the repo.
   */
  void setId(String id);

  /**
   * Gets repository human name.
   */
  String getName();

  /**
   * Sets repository human name.
   */
  void setName(String name);

  /**
   * This is the "type"/kind of the repository. It tells some minimal info about the repo working (not content,
   * neither implementation).
   */
  RepositoryKind getRepositoryKind();

  /**
   * This is the "class" of the repository content. It is used in grouping, only same content reposes may be grouped.
   */
  ContentClass getRepositoryContentClass();


  /**
   * Returns the facet of Repository, if available, otherwise it returns null.
   *
   * @return the facet requested, otherwise null.
   */
  <F> F adaptToFacet(Class<F> t);


  /**
   * Gets local status.
   */
  LocalStatus getLocalStatus();

  /**
   * Sets local status.
   *
   * @param val the val
   */
  void setLocalStatus(LocalStatus val);


  // ==================================================
  // Behaviour

  RequestStrategy registerRequestStrategy(String key, RequestStrategy strategy);

  RequestStrategy unregisterRequestStrategy(String key);

  /**
   * Tells whether the resource store is exposed as Nexus content or not.
   */
  boolean isExposed();

  /**
   * Sets the exposed flag.
   */
  void setExposed(boolean val);

}
