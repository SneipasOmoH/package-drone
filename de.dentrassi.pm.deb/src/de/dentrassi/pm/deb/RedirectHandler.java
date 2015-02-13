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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RedirectHandler implements Handler
{

    private final HttpServletRequest request;

    public RedirectHandler ( final HttpServletRequest request )
    {
        this.request = request;
    }

    @Override
    public void process ( final OutputStream stream ) throws IOException
    {
    }

    @Override
    public void process ( final HttpServletResponse response ) throws IOException
    {
        response.sendRedirect ( this.request.getContextPath () + this.request.getPathInfo () + "/" );
    }

}
