package org.sonatype.flexmojos.plugin.air.packager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;

//import com.adobe.air.ANEPackager;
import com.adobe.air.Listener;

public class FlexmojosANEPackager
    implements IPackager
{

    // private final ANEPackager packager;

    public FlexmojosANEPackager()
    {
        super();
        // this.packager = new ANEPackager();
    }

    public void setOutput( File output )
        throws FileNotFoundException, IOException
    {
        // this.packager.setOutput( output );
    }

    public void setDescriptor( File airDescriptor )
    {
        // //TODO is it safe?
        // this.packager.setExtensionDescriptor( airDescriptor );
    }

    public void setPrivateKey( PrivateKey key )
    {
        // this.packager.setPrivateKey( key );
    }

    public void setSignerCertificate( Certificate certificate )
        throws CertificateException
    {
        // this.packager.setSignerCertificate( certificate );
    }

    public void setCertificateChain( Certificate[] certificateChain )
        throws CertificateException
    {
        // this.packager.setCertificateChain( certificateChain );
    }

    public void setTimestampURL( String url )
    {
        // this.packager.setTimestampURL( url );
    }

    public void addSourceWithPath( File source, String path )
    {
        // this.packager.addSourceWithPath( source, path );
    }

    public void setListener( Listener listener )
    {
        // //no listener on ANE packager
    }

    public void createPackage()
        throws GeneralSecurityException, IOException
    {
        // this.packager.createPackage();
    }

    public void close()
    {
        // this.packager.close();
    }

}
