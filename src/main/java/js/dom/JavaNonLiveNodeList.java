/*
 * Copyright (C) 2016 Nameless Production Committee
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://opensource.org/licenses/mit-license.php
 */
package js.dom;

import java.util.ArrayList;
import java.util.List;

import kiss.XML;

/**
 * @version 2014/01/22 21:35:39
 */
class JavaNonLiveNodeList extends NodeList {

    /** The item container. */
    private final List<Node> elements = new ArrayList();

    /**
     * @param node
     */
    JavaNonLiveNodeList(Node node) {
        elements.add(node);
    }

    /**
     * @param elements
     */
    JavaNonLiveNodeList(XML elements) {
        for (XML element : elements) {
            this.elements.add(EmulateElement.convert(element));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int length() {
        return elements.size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Node item(int index) {
        return elements.get(index);
    }
}
