/*
 * Copyright (C) 2014 Nameless Production Committee
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://opensource.org/licenses/mit-license.php
 */
package booton.virtual;

import org.junit.Test;

/**
 * @version 2014/08/28 22:49:27
 */
public class VNodeTest {

    @Test
    public void diff() throws Exception {
        VNode left = new VNode("div");
        VNode right = new VNode("text");

        Patch patch = Diff.diff(left, right);
    }
}