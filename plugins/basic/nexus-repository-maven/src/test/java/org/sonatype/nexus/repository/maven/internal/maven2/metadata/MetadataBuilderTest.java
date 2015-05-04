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
package org.sonatype.nexus.repository.maven.internal.maven2.metadata;

import org.sonatype.nexus.repository.maven.internal.maven2.Maven2MavenPathParser;
import org.sonatype.sisu.litmus.testsupport.TestSupport;

import org.apache.maven.artifact.repository.metadata.Metadata;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;

/**
 * UT for {@link MetadataUpdater}
 *
 * @since 3.0
 */
public class MetadataBuilderTest
    extends TestSupport
{
  private final Maven2MavenPathParser mavenPathParser = new Maven2MavenPathParser();

  private MetadataBuilder testSubject;

  @Before
  public void prepare() {
    this.testSubject = new MetadataBuilder();
  }

  @Test(expected = IllegalStateException.class)
  public void wrongEnterA() {
    testSubject.onEnterArtifactId("foo");
  }

  @Test(expected = IllegalStateException.class)
  public void wrongEnterV() {
    testSubject.onEnterBaseVersion("foo");
  }

  @Test(expected = IllegalStateException.class)
  public void wrongEnterGV() {
    testSubject.onEnterGroupId("foo"); // good
    testSubject.onEnterBaseVersion("foo"); // fail
  }

  @Test(expected = IllegalStateException.class)
  public void wrongEnterGANoV() {
    testSubject.onEnterGroupId("junit"); // good
    testSubject.onEnterArtifactId("junit"); // good
    testSubject.addArtifactVersion(mavenPathParser.parsePath("/junit/junit/4.12/junit-4.12.pom")); // fail, no V
  }

  @Test(expected = IllegalStateException.class)
  public void contextGAVMismatch() {
    testSubject.onEnterGroupId("foo"); // good
    testSubject.onEnterArtifactId("bar"); // good
    testSubject.onEnterBaseVersion("1.0"); // good
    testSubject.addArtifactVersion(mavenPathParser.parsePath("/junit/junit/4.12/junit-4.12.pom")); // fail, GAV mismatch
  }

  @Test
  public void simpleRelease() {
    testSubject.onEnterGroupId("group");
    testSubject.onEnterArtifactId("artifact");
    testSubject.onEnterBaseVersion("1.0");
    testSubject.addArtifactVersion(mavenPathParser.parsePath("/group/artifact/1.0/artifact-1.0.pom"));
    testSubject.addPlugin("prefix", "artifact", "name");
    final MavenMetadata vmd = testSubject.onExitBaseVersion();
    assertThat(vmd, nullValue());

    final MavenMetadata amd = testSubject.onExitArtifactId();
    assertThat(amd, notNullValue());
    assertThat(amd.getGroupId(), equalTo("group"));
    assertThat(amd.getArtifactId(), equalTo("artifact"));
    assertThat(amd.getBaseVersions(), notNullValue());
    assertThat(amd.getBaseVersions().getVersions(), hasSize(1));

    final MavenMetadata gmd = testSubject.onExitGroupId();
    assertThat(gmd, notNullValue());
    assertThat(gmd.getGroupId(), equalTo("group"));
    assertThat(gmd.getArtifactId(), nullValue());
    assertThat(gmd.getPlugins(), hasSize(1));
  }

  @Test
  public void simpleSnapshot() {
    testSubject.onEnterGroupId("group");
    testSubject.onEnterArtifactId("artifact");
    testSubject.onEnterBaseVersion("1.0-SNAPSHOT");
    testSubject.addArtifactVersion(
        mavenPathParser.parsePath("/group/artifact/1.0-SNAPSHOT/artifact-1.0-20150430.121212-1.pom"));
    testSubject.addPlugin("prefix", "artifact", "name");
    final MavenMetadata vmd = testSubject.onExitBaseVersion();
    assertThat(vmd, notNullValue());
    assertThat(vmd.getGroupId(), equalTo("group"));
    assertThat(vmd.getArtifactId(), equalTo("artifact"));
    assertThat(vmd.getVersion(), equalTo("1.0-SNAPSHOT"));
    assertThat(vmd.getSnapshots(), notNullValue());
    assertThat(vmd.getSnapshots().getSnapshotTimestamp(), equalTo(new DateTime("2015-04-30T12:12:12Z").getMillis()));
    assertThat(vmd.getSnapshots().getSnapshotBuildNumber(), equalTo(1));
    assertThat(vmd.getSnapshots().getSnapshots(), hasSize(1));

    final MavenMetadata amd = testSubject.onExitArtifactId();
    assertThat(amd, notNullValue());
    assertThat(amd.getGroupId(), equalTo("group"));
    assertThat(amd.getArtifactId(), equalTo("artifact"));
    assertThat(amd.getBaseVersions(), notNullValue());
    assertThat(amd.getBaseVersions().getVersions(), hasSize(1));
    assertThat(amd.getBaseVersions().getLatest(), equalTo("1.0-SNAPSHOT"));
    assertThat(amd.getBaseVersions().getRelease(), nullValue());
    assertThat(amd.getBaseVersions().getVersions(), contains("1.0-SNAPSHOT"));

    final MavenMetadata gmd = testSubject.onExitGroupId();
    assertThat(gmd, notNullValue());
    assertThat(gmd.getGroupId(), equalTo("group"));
    assertThat(gmd.getPlugins(), hasSize(1));
  }
}