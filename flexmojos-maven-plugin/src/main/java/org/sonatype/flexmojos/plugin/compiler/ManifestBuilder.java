package org.sonatype.flexmojos.plugin.compiler;

import org.codehaus.plexus.util.DirectoryScanner;
import org.codehaus.plexus.util.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

public class ManifestBuilder
{
    private Manifest manifest;

    private DirectoryScanner scanner;

    public ManifestBuilder(Manifest manifest)
    {
        this.manifest = manifest;

        configureDirectoryScanner();
    }

    public String generateFileName()
        throws URISyntaxException
    {
        URI uri = new URI( manifest.getURI() );
        StringBuilder filename = new StringBuilder();
        if ( uri.isAbsolute() )
        {
            filename.append( uri.getHost() );
        }
        if ( uri.getPath() != null )
        {
            filename.append( uri.getPath().replace('/', '-') );
        }
        filename.append("-manifest.xml");
        
        return filename.toString();
    }

    private void configureDirectoryScanner()
    {
        scanner = new DirectoryScanner();
        scanner.addDefaultExcludes();

        if ( manifest.getIncludes() != null )
        {
            String[] patterns = prepareScannerPatterns( manifest.getIncludes() );
            scanner.setIncludes( patterns );
        }
        if ( manifest.getExcludes() != null )
        {
            String[] patterns = prepareScannerPatterns( manifest.getExcludes() );
            scanner.setExcludes( patterns );
        }
    }

    private String[] prepareScannerPatterns( String[] patterns )
    {
        String[] scannerPatterns = new String[patterns.length * 2];
        for ( int i = 0, j = 0; i < patterns.length; i++, j += 2 )
        {
            final String scannerPatern = patterns[i].replace( '.', File.separatorChar );
            scannerPatterns[j] = scannerPatern + ".as";
            scannerPatterns[j + 1] = scannerPatern + ".mxml";
        }

        return scannerPatterns;
    }

  public void build(List<String> baseDirectories, File output) throws IOException {
    StringBuilder stringBuilder = new StringBuilder("<componentPackage>");
    for (String sourcePath : baseDirectories) {
      File sourceFile = new File(sourcePath);
      if (sourceFile.exists()) {
        build(sourceFile, stringBuilder);
      }
    }
    stringBuilder.append("\n</componentPackage>");

    //noinspection ResultOfMethodCallIgnored
    output.getParentFile().mkdirs();
    FileUtils.fileWrite(output.getPath(), stringBuilder.toString());
  }

    private void build( File baseDirectory, StringBuilder stringBuilder )
    {
        scanner.setBasedir( baseDirectory );
        scanner.scan();

        ol: for ( String fileName : scanner.getIncludedFiles() )
        {
            File parent = new File( fileName ).getParentFile();
            while ( parent != null )
            {
                parent = parent.getParentFile();
            }

            String className = fileName.replace( File.separatorChar, '.' );
            className = className.substring( 0, className.lastIndexOf( '.' ) );

            String localName = className.substring( className.lastIndexOf( '.' ) + 1 );

            String id = null;
            if ( manifest.getNames() != null )
            {
                for ( ManifestClassName name : manifest.getNames() )
                {
                    if ( name.getId().equals( localName ) )
                    {
                        continue ol;
                    }
                    else if ( name.getName().equals( localName ) )
                    {
                        id = name.getId();
                        break;
                    }
                }
            }

            if ( localName.endsWith( "Impl" ) )
            {
                id = localName.substring( 0, localName.length() - 4 );
            }

            stringBuilder.append( "\n\t<component" );
            if ( id != null )
            {
                stringBuilder.append( " id=\"" ).append( id ).append( "\"" );
            }
            stringBuilder.append( " class=\"" ).append( className ).append( "\"/>" );
        }
    }
}
