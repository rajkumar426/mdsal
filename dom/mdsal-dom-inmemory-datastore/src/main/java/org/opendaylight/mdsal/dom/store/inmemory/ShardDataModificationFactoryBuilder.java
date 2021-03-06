/*
 * Copyright (c) 2016 Cisco Systems, Inc. and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.opendaylight.mdsal.dom.store.inmemory;

import com.google.common.base.Preconditions;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.opendaylight.mdsal.dom.api.DOMDataTreeIdentifier;
import org.opendaylight.yangtools.concepts.Builder;
import org.opendaylight.yangtools.yang.data.api.YangInstanceIdentifier;
import org.opendaylight.yangtools.yang.data.api.YangInstanceIdentifier.PathArgument;

class ShardDataModificationFactoryBuilder extends ModificationContextNodeBuilder
        implements Builder<ShardDataModificationFactory> {

    private final Map<DOMDataTreeIdentifier, ForeignShardModificationContext> childShards = new HashMap<>();
    private final DOMDataTreeIdentifier root;

    ShardDataModificationFactoryBuilder(final DOMDataTreeIdentifier root) {
        this.root = Preconditions.checkNotNull(root);
    }

    void addSubshard(final ForeignShardModificationContext value) {
        WriteableSubshardBoundaryNode leafNode = WriteableSubshardBoundaryNode.from(value);
        putNode(value.getIdentifier().getRootIdentifier(), leafNode);
    }

    void addSubshard(final DOMDataTreeIdentifier prefix, final ForeignShardModificationContext value) {
        childShards.put(prefix, value);
    }

    private void putNode(final YangInstanceIdentifier key, final WriteableSubshardBoundaryNode subshardNode) {
        final Iterator<PathArgument> toBoundary = toRelative(key).getPathArguments().iterator();
        if (toBoundary.hasNext()) {
            ModificationContextNodeBuilder current = this;
            while (true) {
                final PathArgument nextArg = toBoundary.next();
                if (!toBoundary.hasNext()) {
                    current.addBoundary(nextArg, subshardNode);
                    break;
                }

                current = getInterior(nextArg);
            }
        }
    }

    @Override
    public ShardDataModificationFactory build() {
        return new ShardDataModificationFactory(root, buildChildren(), childShards);
    }

    private YangInstanceIdentifier toRelative(final YangInstanceIdentifier key) {
        return key.relativeTo(root.getRootIdentifier()).get();
    }
}