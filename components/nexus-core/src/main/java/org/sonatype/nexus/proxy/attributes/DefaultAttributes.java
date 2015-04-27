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
package org.sonatype.nexus.proxy.attributes;

import java.util.Map;

/**
 * Default implementation of Attributes.
 * 
 * @author cstamas
 * @since 2.0
 */
public class DefaultAttributes
    implements Attributes
{
  @Override
  public boolean containsKey(final String key) {
    return false;
  }

  @Override
  public String get(final String key) {
    return null;
  }

  @Override
  public String put(final String key, final String value) {
    return null;
  }

  @Override
  public String remove(final String key) {
    return null;
  }

  @Override
  public void putAll(final Map<? extends String, ? extends String> map) {

  }

  @Override
  public void overlayAttributes(final Attributes repositoryItemAttributes) {

  }

  @Override
  public int getGeneration() {
    return 0;
  }

  @Override
  public void setGeneration(final int value) {

  }

  @Override
  public void incrementGeneration() {

  }

  @Override
  public String getPath() {
    return null;
  }

  @Override
  public void setPath(final String value) {

  }

  @Override
  public boolean isReadable() {
    return false;
  }

  @Override
  public void setReadable(final boolean value) {

  }

  @Override
  public boolean isWritable() {
    return false;
  }

  @Override
  public void setWritable(final boolean value) {

  }

  @Override
  public String getRepositoryId() {
    return null;
  }

  @Override
  public void setRepositoryId(final String value) {

  }

  @Override
  public long getCreated() {
    return 0;
  }

  @Override
  public void setCreated(final long value) {

  }

  @Override
  public long getModified() {
    return 0;
  }

  @Override
  public void setModified(final long value) {

  }

  @Override
  public long getStoredLocally() {
    return 0;
  }

  @Override
  public void setStoredLocally(final long value) {

  }

  @Override
  public long getCheckedRemotely() {
    return 0;
  }

  @Override
  public void setCheckedRemotely(final long value) {

  }

  @Override
  public long getLastRequested() {
    return 0;
  }

  @Override
  public void setLastRequested(final long value) {

  }

  @Override
  public boolean isExpired() {
    return false;
  }

  @Override
  public void setExpired(final boolean value) {

  }

  @Override
  public String getRemoteUrl() {
    return null;
  }

  @Override
  public void setRemoteUrl(final String value) {

  }

  @Override
  public Map<String, String> asMap() {
    return null;
  }
}
