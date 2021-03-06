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
package de.dentrassi.pm.aspect.recipe;

import de.dentrassi.osgi.web.LinkTarget;
import de.dentrassi.pm.storage.Channel;

/**
 * A recipe which will initially configure a channel
 */
public interface Recipe
{
    public LinkTarget setup ( Channel channel );
}
