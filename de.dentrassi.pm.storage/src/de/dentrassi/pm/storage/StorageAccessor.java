/*******************************************************************************
 * Copyright (c) 2014 Jens Reimann.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Jens Reimann - initial API and implementation
 *******************************************************************************/
package de.dentrassi.pm.storage;

import java.util.Set;

import de.dentrassi.pm.common.ArtifactInformation;

public interface StorageAccessor
{
    public void updateChannel ( String channelId, String name );

    public void regenerateAll ( String channelId );

    public Set<ArtifactInformation> getArtifacts ( String channelId );
}