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

import org.sonatype.nexus.proxy.repository.Repository;
import org.sonatype.sisu.goodies.common.FormatTemplate;
import org.sonatype.sisu.goodies.common.SimpleFormat;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Thrown if the requested item is not found.
 *
 * @author cstamas
 */
public class ItemNotFoundException
    extends Exception
{
  private static final long serialVersionUID = -4964273361722823796L;

  public static ItemNotFoundReason reasonFor(final ResourceStoreRequest request, final String message,
                                             final Object... params)
  {
    return new ItemNotFoundReason(SimpleFormat.template(message, params), request);
  }

  public static ItemNotFoundInRepositoryReason reasonFor(final ResourceStoreRequest request,
                                                         final Repository repository, final String message,
                                                         final Object... params)
  {
    return new ItemNotFoundInRepositoryReason(SimpleFormat.template(message, params), request, repository);
  }


  public static class ItemNotFoundReason
  {
    private final FormatTemplate message;

    private final ResourceStoreRequest resourceStoreRequest;

    public ItemNotFoundReason(final FormatTemplate message, final ResourceStoreRequest resourceStoreRequest) {
      this.message = checkNotNull(message);
      this.resourceStoreRequest = checkNotNull(resourceStoreRequest).cloneAndDetach();
    }

    public String getMessage() {
      return message.toString();
    }

    public ResourceStoreRequest getResourceStoreRequest() {
      return resourceStoreRequest;
    }
  }

  public static class ItemNotFoundInRepositoryReason
      extends ItemNotFoundReason
  {
    private final Repository repository;

    public ItemNotFoundInRepositoryReason(final FormatTemplate message,
                                          final ResourceStoreRequest resourceStoreRequest,
                                          final Repository repository)
    {
      super(message, resourceStoreRequest);
      this.repository = checkNotNull(repository);
    }

    public Repository getRepository() {
      return repository;
    }
  }

  private final ItemNotFoundReason reason;

  public ItemNotFoundException(final ItemNotFoundReason reason) {
    this(reason, null);
  }

  public ItemNotFoundException(final ItemNotFoundReason reason, final Throwable cause) {
    super(reason.getMessage(), cause);
    this.reason = reason;
  }

  public ItemNotFoundReason getReason() {
    return reason;
  }
}
