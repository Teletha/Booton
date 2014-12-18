/*
 * Copyright (C) 2014 Nameless Production Committee
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://opensource.org/licenses/mit-license.php
 */
package jsx.style;

import java.util.HashMap;
import java.util.Map;

import js.dom.Element;

/**
 * @version 2014/12/17 12:03:37
 */
class StaticStyle implements Style {

    /** The static style pool. */
    static final Map<String, Style> pool = new HashMap();

    /** The style name. */
    private final String name;

    /**
     * @param name
     */
    StaticStyle(Style style) {
        this.name = StyleName.name(style);
    }

    /**
     * @param className
     */
    StaticStyle(String className) {
        this.name = className;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void declare() {
        // do nothing
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String intern() {
        return name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void applyTo(Element dom) {
        dom.addClass(name);
    }
}