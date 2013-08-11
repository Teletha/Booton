/*
 * Copyright (C) 2013 Nameless Production Committee
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://opensource.org/licenses/mit-license.php
 */
package booton.translator.flow;

import org.junit.Test;

import booton.translator.Param;
import booton.translator.ScriptTester;
import booton.translator.Scriptable;

/**
 * @version 2013/08/09 9:44:11
 */
@SuppressWarnings("unused")
public class WhileTest extends ScriptTester {

    @Test
    public void Normal() {
        test(new Scriptable() {

            public int act(@Param(from = 0, to = 5) int value) {
                while (value < 3) {
                    value++;
                }

                return value;
            }
        });
    }

    @Test
    public void Break() {
        test(new Scriptable() {

            public int act(@Param(from = 0, to = 5) int value) {
                while (value < 3) {
                    value++;

                    if (value == 1) {
                        break;
                    }
                }
                return value;
            }
        });
    }

    @Test
    public void InfiniteBreak() {
        test(new Scriptable() {

            public int act(@Param(from = 0, to = 5) int value) {
                while (true) {
                    value++;

                    if (value == 10) {
                        break;
                    }
                }
                return value;
            }
        });
    }

    @Test
    public void MultipuleBreaks() {
        test(new Scriptable() {

            public int act(int value) {
                while (value < 5) {
                    value++;

                    if (value == 1) {
                        break;
                    }

                    if (value == 7) {
                        break;
                    }
                }
                return value;
            }
        });
    }

    @Test
    public void Continue() {
        test(new Scriptable() {

            public int act(@Param(from = 0, to = 5) int value) {
                while (value < 3) {
                    value += 2;

                    if (value == 3) {
                        continue;
                    }
                    value += 2;
                }
                return value;
            }
        });
    }

    @Test
    public void Return() {
        test(new Scriptable() {

            public int act(@Param(from = 0, to = 5) int value) {
                while (value < 3) {
                    value++;

                    if (value == 3) {
                        return value;
                    }
                }
                return value;
            }
        });
    }

    @Test
    public void Mix() {
        test(new Scriptable() {

            public int act(@Param(from = 0, to = 5) int value) {
                while (value < 5) {
                    value++;

                    if (value == 3)
                        return value;
                    else if (value == 2)
                        break;
                    else
                        value++;
                }
                return value + 1;
            }
        });
    }

    @Test
    public void Nest() {
        test(new Scriptable() {

            public int act(@Param(from = 0, to = 10) int value) {
                while (value < 30) {
                    value += 10;

                    while (30 < value) {
                        value--;
                    }
                }
                return value;
            }
        });
    }

    @Test
    public void LabeledBreak() {
        test(new Scriptable() {

            public int act(@Param(from = 1, to = 10) int value) {
                root: while (value < 100) {
                    value *= 2;

                    while (50 < value) {
                        value--;

                        if (value == 70) {
                            break root;
                        }
                    }
                    value *= 2;
                }
                return value;
            }
        });
    }

    @Test
    public void InIf() {
        test(new Scriptable() {

            public int act(@Param(from = 0, to = 5) int value) {
                while (value < 3) {
                    value++;
                }

                return value;
            }
        });
    }

    @Test
    public void logical() {
        test(new Scriptable() {

            public int act(@Param(from = 0, to = 5) int value) {
                while (value % 2 != 0 && value != 1) {
                    value++;
                }
                return value;
            }
        });
    }
}
