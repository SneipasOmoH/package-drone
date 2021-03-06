/*******************************************************************************
 * Copyright (c) 2014, 2015 IBH SYSTEMS GmbH.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBH SYSTEMS GmbH - initial API and implementation
 *******************************************************************************/
package de.dentrassi.pm.storage.web.tags;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import de.dentrassi.pm.common.Severity;
import de.dentrassi.pm.common.SimpleArtifactInformation;
import de.dentrassi.pm.storage.Channel;

public class Functions
{
    public static String channel ( final Channel channel )
    {
        if ( channel == null )
        {
            return null;
        }

        if ( channel.getName () == null )
        {
            return channel.getId ();
        }
        else
        {
            return String.format ( "%s (%s)", channel.getName (), channel.getId () );
        }
    }

    public static List<SimpleArtifactInformation> nameSorted ( final Collection<SimpleArtifactInformation> artifacts )
    {
        if ( artifacts == null )
        {
            return null;
        }

        final List<SimpleArtifactInformation> result = new ArrayList<> ( artifacts );

        Collections.sort ( result, SimpleArtifactInformation.NAME_COMPARATOR );

        return result;
    }

    public static String severity ( final Severity severity )
    {
        return severityWithDefault ( severity, "default" );
    }

    public static String severityWithDefault ( final Severity severity, final String defaultValue )
    {
        if ( severity == null )
        {
            return defaultValue;
        }

        switch ( severity )
        {
            case INFO:
                return "info";
            case WARNING:
                return "warning";
            case ERROR:
                return "danger";
            default:
                return defaultValue;
        }
    }
}
