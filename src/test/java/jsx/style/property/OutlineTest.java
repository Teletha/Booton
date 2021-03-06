/*
 * Copyright (C) 2016 Nameless Production Committee
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://opensource.org/licenses/mit-license.php
 */
package jsx.style.property;

import static jsx.style.value.Color.*;

import org.junit.Test;

import jsx.style.StyleTester;

/**
 * @version 2014/11/15 10:33:17
 */
public class OutlineTest extends StyleTester {

    @Test
    public void width() throws Exception {
        ValidatableStyle style = writeStyle(() -> {
            outline.width(2, em);
        });

        assert style.property("outline-width", "2em");
    }

    @Test
    public void style2() throws Exception {
        ValidatableStyle style = writeStyle(() -> {
            outline.solid();
        });

        assert style.property("outline-style", "solid");
    }

    @Test
    public void color() throws Exception {
        ValidatableStyle style = writeStyle(() -> {
            outline.color(White);
        });

        assert style.property("outline-color", "white");
    }

    @Test
    public void chain() throws Exception {
        ValidatableStyle style = writeStyle(() -> {
            outline.solid().width(2, em).color(Black);
        });

        assert style.property("outline-style", "solid");
        assert style.property("outline-color", "black");
        assert style.property("outline-width", "2em");
    }
}
