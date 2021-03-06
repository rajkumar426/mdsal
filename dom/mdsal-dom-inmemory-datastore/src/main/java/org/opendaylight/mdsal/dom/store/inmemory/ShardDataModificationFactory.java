/*
 * Copyright (c) 2016 Cisco Systems, Inc. and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.opendaylight.mdsal.dom.store.inmemory;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import java.util.Map;
import org.opendaylight.mdsal.dom.api.DOMDataTreeIdentifier;
import org.opendaylight.yangtools.yang.data.api.YangInstanceIdentifier.PathArgument;
import org.opendaylight.yangtools.yang.data.api.schema.tree.CursorAwareDataTreeSnapshot;

final class ShardDataModificationFactory {
    private final Map<DOMDataTreeIdentifier, ForeignShardModificationContext> childShards;
    private final Map<PathArgument, WriteableModificationNode> children;
    private final DOMDataTreeIdentifier root;

    ShardDataModificationFactory(final DOMDataTreeIdentifier root,
        final Map<PathArgument, WriteableModificationNode> children,
        final Map<DOMDataTreeIdentifier, ForeignShardModificationContext> childShards) {
        this.root = Preconditions.checkNotNull(root);
        this.children = ImmutableMap.copyOf(children);
        this.childShards = ImmutableMap.copyOf(childShards);
    }

    @VisibleForTesting
    Map<PathArgument, WriteableModificationNode> getChildren() {
        return children;
    }

    @VisibleForTesting
    Map<DOMDataTreeIdentifier, ForeignShardModificationContext> getChildShards() {
        return childShards;
    }

    ShardDataModification createModification(final CursorAwareDataTreeSnapshot snapshot) {
        return new ShardDataModification(new ShardRootModificationContext(root, snapshot), children, childShards);
    }
}
