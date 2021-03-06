/*******************************************************************************
 * Copyright (c) 2015 IBH SYSTEMS GmbH.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBH SYSTEMS GmbH - initial API and implementation
 *******************************************************************************/
package de.dentrassi.osgi.web.controller.validator;

import de.dentrassi.osgi.web.controller.binding.BindingError;

public interface ValidationContext
{
    public void error ( String errorMessage );

    public void error ( BindingError error );

    public void error ( String path, String errorMessage );

    public void error ( String path, BindingError error );

    public void setMarker ( String marker );
}
