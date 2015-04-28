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
package org.sonatype.nexus.proxy;

public class RequestContext
{
  public RequestContext(RequestContext parent) {
  }

  public Object get(Object key, boolean fallBackToParent) {
    return null;
  }

  public boolean containsKey(Object key, boolean fallBackToParent) {
    return false;
  }

  public Object get(Object key) {
    return get(key, true);
  }

  public boolean containsKey(Object key) {
    return containsKey(key, true);
  }

  public Object put(final String key, final Object value) {
    return null;
  }

  @Deprecated
  public Object put(final Object key, final Object value) {
    return null;
  }

  public Object remove(final Object key) {
    return null;
  }
}
