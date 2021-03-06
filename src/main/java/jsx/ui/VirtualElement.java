/*
 * Copyright (C) 2016 Nameless Production Committee
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://opensource.org/licenses/mit-license.php
 */
package jsx.ui;

import static js.lang.Global.*;

import js.dom.Element;
import js.lang.NativeArray;
import jsx.collection.DualList;
import jsx.style.Style;
import jsx.ui.flux.Interactive;

/**
 * @version 2015/10/04 22:16:29
 */
public class VirtualElement extends VirtualNode<Element> {

    /** The namespace uri. */
    final String ns;

    /** The node name. */
    final String name;

    /** The attributes. */
    public final DualList<String, String> attributes = new DualList();

    /** The class attributes. */
    public final NativeArray<Style> classList = new NativeArray();

    /** The items nodes. */
    public final NativeArray<VirtualNode> items = new NativeArray();

    final Object context;

    /** The parent widget. */
    Widget widget;

    /**
     * @param id
     * @param name
     * @param namespace
     */
    VirtualElement(int id, String name, Object context) {
        super(id);

        this.ns = StructureDSL.HTML;
        this.name = name;
        this.context = context;
    }

    /**
     * @param id
     * @param name
     * @param namespace
     */
    VirtualElement(int id, String ns, String name, Object context, Widget widget) {
        super(id);

        this.ns = ns;
        this.name = name;
        this.context = context;
        this.widget = widget;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    Element materialize() {
        WidgetLog.MaterializeElement.start();

        // create DOM
        dom = document.createElementNS(ns, name);

        // assign context object
        dom.property(Interactive.class.getName(), context);

        // assign attributes
        for (int i = 0, length = attributes.size(); i < length; i++) {
            dom.attr(attributes.key(i), attributes.value(i));
        }

        // assign classes
        for (int i = 0, length = classList.length(); i < length; i++) {
            dom.add(classList.get(i));
        }

        // assign children nodes
        for (int i = 0, length = items.length(); i < length; i++) {
            dom.append(items.get(i).materialize());
        }

        WidgetLog.MaterializeElement.stop();

        // API definition
        return dom;
    }

    /**
     * <p>
     * Find index for the specified node.
     * </p>
     * 
     * @param node A target node to search.
     * @return A index of the specified node.
     */
    final int indexOf(VirtualNode node) {
        for (int index = 0, length = items.length(); index < length; index++) {
            if (items.get(index).id == node.id) {
                return index;
            }
        }
        return -1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void vandalize() {
        super.vandalize();

        // discard children reference
        for (int i = items.length() - 1; 0 <= i; i--) {
            items.remove(i).dispose();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return format(this, 1);
    }

    /**
     * <p>
     * Create human-readable element structure.
     * </p>
     * 
     * @param element A target element to format.
     * @param indentSize A size of indent.
     * @return A string expression of human-readable element structure.
     */
    private static String format(VirtualElement element, int indentSize) {
        StringBuilder builder = new StringBuilder();
        builder.append("<").append(element.name).append(" vid=\"").append(element.id).append("\"");

        for (int i = 0; i < element.attributes.size(); i++) {
            builder.append(" ").append(element.attributes.key(i)).append("=\"").append(element.attributes.value(i)).append("\"");
        }

        if (element.classList.length() != 0) {
            builder.append(" class=\"");

            for (int i = 0; i < element.classList.length(); i++) {
                builder.append(element.classList.get(i).name()).append(" ");
            }
            builder.append("\"");
        }

        boolean hasChild = element.items.length() != 0;

        if (hasChild) {
            builder.append(">");
        }

        for (int i = 0; i < element.items.length(); i++) {
            builder.append("\r\n").append(indent(indentSize));

            VirtualNode item = element.items.get(i);

            if (item instanceof VirtualElement) {
                builder.append(format((VirtualElement) item, indentSize + 1));
            } else {
                builder.append(item);
            }
        }

        if (hasChild) {
            builder.append("\r\n").append(indent(indentSize - 1)).append("</").append(element.name).append(">");
        } else {
            builder.append("/>");
        }
        return builder.toString();
    }

    /**
     * <p>
     * Compute indent expression.
     * </p>
     * 
     * @param size A size of indent.
     * @return A space expression for indent.
     */
    private static String indent(int size) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < size; i++) {
            builder.append("  ");
        }
        return builder.toString();
    }
}
