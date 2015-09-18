/*
 * Copyright (C) 2015 Nameless Production Committee
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://opensource.org/licenses/mit-license.php
 */
package jsx.ui;

import static js.lang.Global.*;

import js.dom.CSSStyleDeclaration;
import js.dom.Element;
import js.lang.NativeArray;
import jsx.collection.DualList;
import jsx.style.Style;
import jsx.style.StyleName;

/**
 * @version 2014/10/07 12:49:29
 */
public class VirtualElement extends VirtualNode<Element> {

    /** The node name. */
    final String name;

    /** The attributes. */
    final DualList<String, String> attributes = new DualList();

    /** The class attributes. */
    public final NativeArray<Style> classList = new NativeArray();

    /** The The inline styles. */
    final DualList<String, String> inlines = new DualList();

    /** The items nodes. */
    final NativeArray<VirtualNode> items = new NativeArray();

    Object context;

    ContextualizedEventListeners contextualized;

    /** The parent widget. */
    Widget widget;

    /**
     * @param id
     * @param name
     * @param namespace
     */
    VirtualElement(int id, String name) {
        super(id);

        this.name = name;
    }

    /**
     * @param id
     * @param name
     * @param namespace
     */
    VirtualElement(int id, String name, Widget widget) {
        super(id);

        this.name = name;
        this.widget = widget;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    Element materialize() {
        if (name.startsWith("s:")) {
            dom = document.createElementNS("http://www.w3.org/2000/svg", name.substring(2));
        } else {
            dom = document.createElement(name);
        }

        // assign attributes
        for (int i = 0; i < attributes.size(); i++) {
            String name = attributes.key(i);
            String value = attributes.value(i);

            if (name.startsWith("xlink:")) {
                dom.attr("http://www.w3.org/1999/xlink", name.substring(6), value);
            } else {
                dom.attr(name, value);
            }
        }

        // assign classes and event listener
        for (int i = 0, length = classList.length(); i < length; i++) {
            Style style = classList.get(i);

            // style class
            dom.classList().add(style);

            // event listener
            if (widget != null) {
                widget.registerEventListener(style, dom, context);
            }

            // dom.addEventListener(listener.action.name, new NativeFunction<UIEvent>(event -> {
            // if (listener.action == UIAction.ClickRight) {
            // event.preventDefault();
            // }
            //
            // for (Observer observer : listener.observers) {
            // observer.accept(value == null ? event : value);
            // }
            // }));
        }

        // assign inline style
        int size = inlines.size();

        if (size != 0) {
            CSSStyleDeclaration style = dom.style();

            for (int i = 0; i < size; i++) {
                style.set(inlines.key(i), inlines.value(i));
            }
        }

        if (contextualized != null) {
            contextualized.assign(dom);
        }

        // assign children nodes
        for (int i = 0, length = items.length(); i < length; i++) {
            dom.append(items.get(i).materialize());
        }

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
    public void dispose() {
        super.dispose();

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
        builder.append("<").append(element.name);

        for (int i = 0; i < element.attributes.size(); i++) {
            builder.append(" ").append(element.attributes.key(i)).append("=\"").append(element.attributes.value(i)).append("\"");
        }

        if (element.classList.length() != 0) {
            builder.append(" class=\"");

            for (int i = 0; i < element.classList.length(); i++) {
                builder.append(StyleName.of(element.classList.get(i))).append(" ");
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
