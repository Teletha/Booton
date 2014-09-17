/*
 * Copyright (C) 2014 Nameless Production Committee
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://opensource.org/licenses/mit-license.php
 */
package jsx.ui.virtual;

import js.dom.Node;

/**
 * @version 2014/09/04 23:21:40
 */
abstract class VirtualNode<D extends Node> implements Materializer<D> {

    /**
     * <p>
     * The real DOM node.
     * </p>
     * <p>
     * To tell the truth, we DON'T want Virtual DOM to have Real DOM. However, if virtual DOM don't
     * have it, a patch will scan or search the position of Real DOM by any index or ID information
     * at every time to apply a diff patch. Its cost is slightly expensive than this way.
     * </p>
     * <p>
     * Only the latest Virtual DOM has the Real DOM, and other Virtual DOM discards its reference
     * immediately.
     * </p>
     */
    public D real;

    /** The node identifier. */
    public final int id;

    /**
     * 
     */
    protected VirtualNode(int id) {
        this.id = id;
    }
}
