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
package de.dentrassi.pm.deb;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import de.dentrassi.pm.deb.servlet.Helper;

public class ContentHandler implements Handler
{
    private final URL resource;

    private final Map<String, Object> model;

    public ContentHandler ( final URL resource, final Map<String, Object> model )
    {
        this.resource = resource;
        this.model = model;
    }

    @Override
    public void process ( final OutputStream stream ) throws IOException
    {
    }

    @Override
    public void process ( final HttpServletResponse response ) throws IOException
    {
        Helper.render ( response, this.resource, this.model );
    }

}
