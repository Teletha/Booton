/*
 * Copyright (C) 2016 Nameless Production Committee
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://opensource.org/licenses/mit-license.php
 */
package booton.translator;

import org.junit.Test;

/**
 * @version 2013/01/21 15:36:41
 */
public class ScriptBufferTest {

    @Test
    public void optimize() throws Exception {
        ScriptWriter buffer = new ScriptWriter();
        buffer.append(",");
        assert buffer.toString().length() == 1;

        buffer.optimize();
        assert buffer.toString().length() == 0;
    }

    @Test
    public void end() throws Exception {
        ScriptWriter writer = new ScriptWriter();
        writer.append("a , ");
        assert writer.toString().length() == 4;

        writer.append("}");
        assert writer.toString().length() == 2;
    }
}
