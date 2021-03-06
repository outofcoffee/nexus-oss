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
package org.sonatype.nexus.proxy.wastebasket;

import java.io.File;
import java.io.IOException;

import javax.inject.Inject;

import org.sonatype.nexus.common.dirs.ApplicationDirectories;
import org.sonatype.nexus.common.io.DirSupport;
import org.sonatype.sisu.goodies.common.ComponentSupport;

public abstract class AbstractRepositoryFolderCleaner
    extends ComponentSupport
    implements RepositoryFolderCleaner
{
  public static final String GLOBAL_TRASH_KEY = "trash";

  private ApplicationDirectories applicationDirectories;

  protected ApplicationDirectories getApplicationDirectories() {
    return applicationDirectories;
  }

  @Inject
  public void setApplicationDirectories(final ApplicationDirectories applicationDirectories) {
    this.applicationDirectories = applicationDirectories;
  }

  /**
   * Delete the file forever, or just keep it by renaming it (hence, will not be used anymore).
   *
   * @param file          file to be deleted
   * @param deleteForever if it's true, delete the file forever, if it's false, move the file to trash
   */
  protected void delete(final File file, final boolean deleteForever) throws IOException {
    File basketFile = new File(applicationDirectories.getWorkDirectory(GLOBAL_TRASH_KEY), file.getName());
    if (!deleteForever) {
      // move to trash
      DirSupport.moveIfExists(file.toPath(), basketFile.toPath());
    }
    else {
      DirSupport.deleteIfExists(file.toPath());
    }
  }
}
