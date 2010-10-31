package org.sonatype.flexmojos.plugin.compiler;

import java.io.File;

public class Manifest {
  private String uri;

  private String[] includes;
  private String[] excludes;

  private ManifestClassName[] names;

  private File outputFile;

  public ManifestClassName[] getNames() {
    return names;
  }

  public String getURI() {
    return uri;
  }

  public String[] getIncludes() {
    return includes;
  }

  public String[] getExcludes() {
    return excludes;
  }

  public File getOutputFile() {
    return outputFile;
  }
}
