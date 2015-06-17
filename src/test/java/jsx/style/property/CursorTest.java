/*
 * Copyright (C) 2015 Nameless Production Committee
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://opensource.org/licenses/mit-license.php
 */
package jsx.style.property;

import jsx.style.StyleTester;

import org.junit.Test;

/**
 * @version 2014/11/13 14:24:48
 */
public class CursorTest extends StyleTester {

    @Test
    public void rgb() {
        ValidatableStyle parsed = style(() -> {
            cursor.help();
        });
        assert parsed.property("cursor", "help");

        parsed = style(() -> {
            cursor.alias();
        });
        assert parsed.property("cursor", "alias");

        parsed = style(() -> {
            cursor.copy();
        });
        assert parsed.property("cursor", "copy");
    }
}
