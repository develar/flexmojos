package org.sonatype.flexmojos.plugin.air.packager;

import java.io.File;
import java.io.IOException;

import com.adobe.air.nai.DEBPackager;

public class FlexmojosDEBPackager
    extends DEBPackager
    implements IPackager
{

    private File naiDir;

    public FlexmojosDEBPackager( File naiDir, File runtimeDir )
    {
        super.setRuntimeDir( runtimeDir );
        this.naiDir = naiDir;
    }

    @Override
    protected File getNAISDKBinDir()
        throws IOException
    {
        return new File( naiDir, "bin" );
    }

    @Override
    protected File getNAISDKLibDir()
        throws IOException
    {
        return new File( naiDir, "lib" );
    }
    
}
