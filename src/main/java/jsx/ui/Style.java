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

import js.dom.UIEvent;

/**
 * @version 2015/10/05 2:11:42
 */
public interface Style extends Declarable, Location<UIEvent> {

    /**
     * <p>
     * Define the style declaration.
     * </p>
     */
    void style();

    /**
     * {@inheritDoc}
     */
    @Override
    default void declare() {
        StructureDescriptor.latestElement.classList.push(this);
    }

    /**
     * <p>
     * Combine this {@link Style} and the specified {@link Style}.
     * </p>
     * 
     * @param style A style to combine.
     * @return A combined {@link Style}.
     */
    default Style with(Style style) {
        return MultipleStyle.of(this, style);
    }
}