/*
 * Copyright (c) 2016 Cisco Systems, Inc. and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.opendaylight.mdsal.binding2.model.api.type.builder;

import com.google.common.annotations.Beta;
import org.opendaylight.mdsal.binding2.model.api.GeneratedProperty;
import org.opendaylight.mdsal.binding2.model.api.Type;

/**
 * Generated Property Builder is interface that contains methods to build and
 * instantiate Generated Property definition.
 *
 * @see GeneratedProperty
 */
@Beta
public interface GeneratedPropertyBuilder extends TypeMemberBuilder<GeneratedPropertyBuilder> {

    GeneratedPropertyBuilder setValue(String value);

    /**
     * Sets isReadOnly flag for property. If property is marked as read only it
     * is the same as set property in java as final.
     *
     * @param isReadOnly
     *            Read Only property flag.
     */
    GeneratedPropertyBuilder setReadOnly(final boolean isReadOnly);

    /**
     * Returns <code>new</code> <i>immutable</i> instance of Generated Property. <br>
     * The <code>definingType</code> param cannot be <code>null</code>. The
     * every member in Java MUST be declared and defined inside the scope of
     * <code>class</code> definition. In case that defining Type will be passed
     * as <code>null</code> reference the method SHOULD thrown
     * {@link IllegalArgumentException}.
     *
     * @param definingType
     *            Defining Type of Generated Property
     * @return <code>new</code> <i>immutable</i> instance of Generated Property.
     */
    GeneratedProperty toInstance(final Type definingType);
}
