/*******************************************************************************
 * Copyright (c) 2015 Jens Reimann.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Jens Reimann - initial API and implementation
 *******************************************************************************/
package de.dentrassi.pm.deb.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.eclipse.scada.utils.str.ExtendedPropertiesReplacer;
import org.eclipse.scada.utils.str.StringReplacer;

import com.google.common.io.CharStreams;

public class Helper
{

    public static void render ( final HttpServletResponse response, final URL url, final Map<String, Object> model ) throws IOException
    {
        final PrintWriter w = response.getWriter ();
        response.setContentType ( "text/html" );
        w.write ( StringReplacer.replace ( loadResource ( url ), new ExtendedPropertiesReplacer ( model ), StringReplacer.DEFAULT_PATTERN, true ) );
    }

    private static String loadResource ( final URL url ) throws IOException
    {
        try ( InputStream is = url.openStream (); Reader r = new InputStreamReader ( is, StandardCharsets.UTF_8 ) )
        {
            return CharStreams.toString ( r );
        }
    }

}
