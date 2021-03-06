/*
 * Copyright (C) 2016 Nameless Production Committee
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://opensource.org/licenses/mit-license.php
 */
package jsx.style.value;

import org.junit.Test;

import jsx.style.StyleTester;

/**
 * @version 2014/11/13 14:08:05
 */
public class CalcTest extends StyleTester {

    private static final Numeric one = new Numeric(1, px);

    private static final Numeric two = new Numeric(2, em);

    @Test
    public void none() throws Exception {
        ValidatableStyle style = writeStyle(() -> {
            display.width(one);
        });
        assert style.property("width", "1px");
    }

    @Test
    public void add() throws Exception {
        ValidatableStyle style = writeStyle(() -> {
            display.width(one.add(two));
        });
        assert style.property("width", "calc(1px + 2em)", "-webkit-calc(1px + 2em)");
    }

    @Test
    public void inPrefixedProperty() throws Exception {
        ValidatableStyle style = writeStyle(() -> {
            transform.translateY(one.add(two));
        });
        assert style.property("-webkit-transform", "translateY(-webkit-calc(1px + 2em))");
        assert style.property("transform", "translateY(calc(1px + 2em))");
    }

    @Test
    public void noneInPrefixedProperty() throws Exception {
        ValidatableStyle style = writeStyle(() -> {
            transform.translateY(one);
        });
        assert style.property("-webkit-transform", "translateY(1px)");
        assert style.property("transform", "translateY(1px)");
    }
}
