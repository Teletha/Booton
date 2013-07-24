/*
 * Copyright (C) 2013 Nameless Production Committee
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://opensource.org/licenses/mit-license.php
 */
package booton.css.property;

import java.util.ArrayList;
import java.util.List;

import booton.css.value.Color;
import booton.css.value.Numeric;

/**
 * @version 2013/07/23 13:23:08
 */
class BorderSet extends Border {

    /** The descriptor set. */
    private final List<Border> descriptors = new ArrayList();

    /**
     * 
     */
    protected BorderSet() {
    }

    /**
     * @param name
     */
    protected BorderSet(String name) {
        super(name);
    }

    /**
     * <p>
     * Create descriptor set.
     * </p>
     * 
     * @param descriptors
     */
    protected final BorderSet add(Border... descriptors) {
        for (Border descriptor : descriptors) {
            this.descriptors.add(descriptor);
        }
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Border radius(Numeric size) {
        for (Border descriptor : descriptors) {
            descriptor.radius(size);
        }
        return chain();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Numeric width() {
        return descriptors.get(0).width();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Border width(Numeric size) {
        for (Border descriptor : descriptors) {
            descriptor.width(size);
        }
        return chain();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Color color() {
        return descriptors.get(0).color();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Border color(Color color) {
        for (Border descriptor : descriptors) {
            descriptor.color(color);
        }
        return chain();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BorderStyle style() {
        return descriptors.get(0).style();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Border style(BorderStyle style) {
        for (Border descriptor : descriptors) {
            descriptor.style(style);
        }
        return chain();
    }
}