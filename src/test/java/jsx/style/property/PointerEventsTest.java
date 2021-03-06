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

import org.junit.Test;

import jsx.style.StyleTester;

/**
 * @version 2014/11/13 14:37:14
 */
public class PointerEventsTest extends StyleTester {

    @Test
    public void none() {
        ValidatableStyle parsed = writeStyle(() -> {
            pointerEvents.none();
        });
        assert parsed.property("pointer-events", "none");
    }
}
