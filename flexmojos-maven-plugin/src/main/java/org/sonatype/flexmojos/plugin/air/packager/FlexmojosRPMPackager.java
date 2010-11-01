package org.sonatype.flexmojos.plugin.air.packager;

import java.io.File;
import java.io.IOException;

import com.adobe.air.nai.RPMPackager;

public class FlexmojosRPMPackager
    extends RPMPackager
    implements IPackager
{

    private File naiDir;

    public FlexmojosRPMPackager( File naiDir, File runtimeDir )
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
