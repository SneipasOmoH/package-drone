package de.dentrassi.pm.aspect.common.osgi;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Map;
import java.util.jar.JarInputStream;
import java.util.jar.Manifest;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import org.osgi.framework.Constants;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import de.dentrassi.pm.aspect.ChannelAspect;
import de.dentrassi.pm.common.XmlHelper;
import de.dentrassi.pm.meta.extract.Extractor;

public class OsgiExtractor implements Extractor
{
    public static final String KEY_CLASSIFIER = "classifier";

    public static final String KEY_VERSION = "version";

    public static final String KEY_NAME = "name";

    private final ChannelAspect aspect;

    private final XmlHelper xml;

    public OsgiExtractor ( final ChannelAspect aspect )
    {
        this.aspect = aspect;
        this.xml = new XmlHelper ();
    }

    @Override
    public ChannelAspect getAspect ()
    {
        return this.aspect;
    }

    @Override
    public void extractMetaData ( final Path file, final Map<String, String> metadata ) throws Exception
    {
        extractBundleInformation ( file, metadata );
        extractFeatureInformation ( file, metadata );
    }

    private void extractFeatureInformation ( final Path file, final Map<String, String> metadata ) throws Exception
    {
        Document fdoc;
        try ( ZipFile zf = new ZipFile ( file.toFile () ) )
        {
            final ZipEntry ze = zf.getEntry ( "feature.xml" );
            if ( ze == null )
            {
                return;
            }
            try ( InputStream stream = zf.getInputStream ( ze ) )
            {
                fdoc = this.xml.parse ( stream );
            }
        }
        catch ( final ZipException e )
        {
            // silently ignore
            return;
        }

        // process feature content
        final Element root = fdoc.getDocumentElement ();
        if ( !"feature".equals ( root.getNodeName () ) )
        {
            return;
        }

        final String id = root.getAttribute ( "id" );
        final String version = root.getAttribute ( KEY_VERSION );
        if ( id == null || version == null )
        {
            return;
        }

        metadata.put ( KEY_NAME, id );
        metadata.put ( KEY_VERSION, version );
        metadata.put ( KEY_CLASSIFIER, "eclipse.feature" );
    }

    private void extractBundleInformation ( final Path file, final Map<String, String> metadata ) throws IOException, FileNotFoundException
    {
        final Manifest mf;

        try ( final JarInputStream jarStream = new JarInputStream ( new FileInputStream ( file.toFile () ) ) )
        {
            mf = jarStream.getManifest ();
        }

        if ( mf == null )
        {
            return;
        }

        final String symbolicName = mf.getMainAttributes ().getValue ( Constants.BUNDLE_SYMBOLICNAME );
        final String version = mf.getMainAttributes ().getValue ( Constants.BUNDLE_VERSION );

        if ( symbolicName == null || version == null )
        {
            return;
        }

        metadata.put ( KEY_NAME, symbolicName );
        metadata.put ( KEY_VERSION, version );
        metadata.put ( KEY_CLASSIFIER, "bundle" );
    }
}
