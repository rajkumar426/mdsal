/*
 * Copyright (c) 2016 Cisco Systems, Inc. and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.opendaylight.mdsal.binding2.spec;

import com.google.common.annotations.Beta;

/**
 * Represents instantiable data object such as input / output, data tree items.
 *
 * @param <T> Final interface which is instantiable and extends this interface
 */

@Beta
public interface Instantiable<T extends Instantiable<T>> {

    // REPLACES: DataObject#getImplementedInterface()
    Class<T> implementedInterface();

}
