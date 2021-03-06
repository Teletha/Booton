/*
 * Copyright (C) 2016 Nameless Production Committee
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://opensource.org/licenses/mit-license.php
 */
package booton.translator.flow;

import org.junit.Test;

import booton.soeur.Param;
import booton.soeur.ScriptTester;
import booton.soeur.Scriptable;

/**
 * @version 2014/06/28 18:12:01
 */
@SuppressWarnings("unused")
public class WhileTest extends ScriptTester {

    @Test
    public void normal() {
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
    public void withBreak() {
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
    public void multipuleBreaks() {
        test(new Scriptable() {

            public int act(int value) {
                while (value < 5) {
                    value++;

                    if (value == 1) {
                        break;
                    }

                    if (value == 0) {
                        break;
                    }
                }
                return value;
            }
        });
    }

    @Test
    public void withContinue() {
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
    public void withReturn() {
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
    public void mix() {
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
    public void nest() {
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
    public void labeledBreak() {
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
    public void inIf() {
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
    public void afterIf() {
        test(new Scriptable() {

            public int act(int value) {
                if (value % 2 == 0) {
                    value += 100;
                }

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

    @Test
    public void assignWithMethod() {
        test(new Scriptable() {

            public int act(@Param(from = 0, to = 5) int value) {
                while ((value = test(value)) < 10) {
                    value++;
                }
                return value;
            }

            private int test(int value) {
                return value + 2;
            }
        });
    }

    @Test
    public void oneLiner() {
        test(new Scriptable() {

            public int act(int value) {
                // @formatter:off
                while (value < 0) {value+=2;}
                // @formatter:on

                return value;
            }
        });
    }

    @Test
    public void oneLinerNest() {
        test(new Scriptable() {

            public int act(int value) {
                // @formatter:off
                while (value < 0) {if (value % 2==0) {value +=3;} else {value+= 5;}};
                // @formatter:on

                return value;
            }
        });
    }

    @Test
    public void sequentialWithComplexCondition() throws Exception {
        test(new Scriptable() {

            public int act(int value) {
                while (0 < value && value < 5) {
                    value++;
                }

                while (value < 1) {
                    value++;
                }
                return value;
            }
        });
    }

    @Test
    public void infiniteBreak() {
        test(new Scriptable() {

            public int act(int value) {
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
    public void infiniteFisrtBreak() {
        test(new Scriptable() {

            public int act(int value) {
                while (true) {
                    value++;

                    if (value == 5 || value == 6) {
                        break;
                    }

                    value++;
                }
                return value;
            }
        });
    }

    @Test
    public void infiniteStatementBreakWithFollow() {
        test(new Scriptable() {

            public int act(int value) {
                while (true) {
                    value++;

                    if (value == 10) {
                        value += 2;
                        break;
                    }
                }
                value += 3;
                return value;
            }
        });
    }

    @Test
    public void infiniteMultipleStatementBreak() {
        test(new Scriptable() {

            public int act(int value) {
                while (true) {
                    value++;

                    if (value == 0) {
                        break;
                    }

                    if (value % 2 == 0) {
                        value++;
                        break;
                    }
                }
                return value;
            }
        });
    }

    @Test
    public void infiniteMultipleStatementBreaks() {
        test(new Scriptable() {

            public int act(int value) {
                while (true) {
                    value++;

                    if (value == 0) {
                        value += 2;
                        break;
                    }

                    if (value % 2 == 0) {
                        value += 3;
                        break;
                    }
                }
                return value;
            }
        });
    }

    @Test
    public void infiniteNonConditionalSingleBackedge() {
        test(new Scriptable() {

            public int act(int value) {
                while (true) {
                    value++;

                    if (value == 0) {
                        value += 2;
                        break;
                    }

                    if (value % 2 == 0) {
                        value += 3;
                        break;
                    }

                    value += 2;
                }
                return value;
            }
        });
    }

    @Test
    public void infiniteBreakInNestedIf() {
        test(new Scriptable() {

            public int act(int value) {
                while (true) {
                    value++;

                    if (value == 0) {
                        break;
                    }

                    if (value % 2 == 0) {
                        value++;

                        if (value % 3 == 0) {
                            break;
                        }
                    }
                }
                return value;
            }
        });
    }

    @Test
    public void infiniteBreakAndContinue() {
        test(new Scriptable() {

            public int act(int value) {
                while (true) {
                    value++;

                    if (value % 5 == 0) {
                        value += 5;
                        break;
                    }

                    if (value % 3 == 0) {
                        continue;
                    }

                    if (value % 2 == 0) {
                        value += 2;
                        break;
                    }
                }
                return value;
            }
        });
    }

    @Test
    public void inifinitContinue() throws Exception {
        test(new Scriptable() {

            int act(@Param(from = 0, to = 10) int value) {
                while (true) {
                    value++;

                    if (value % 6 == 0) {
                        continue;
                    }

                    if (value % 3 == 0) {
                        return value;
                    }
                }
            }
        });
    }

    @Test
    public void inifinitContinueWithShorthandIf() throws Exception {
        test(new Scriptable() {

            int act(@Param(from = 0, to = 10) int value) {
                while (true) {
                    value++;

                    if (value % 6 == 0) continue;

                    if (value % 3 == 0) {
                        return value;
                    }
                }
            }
        });
    }

    @Test
    public void inifinitContinueWithShorthandIfInAnotherIf() throws Exception {
        test(new Scriptable() {

            int act(@Param(from = 0, to = 20) int value) {
                while (true) {
                    value++;

                    if (value % 2 == 0) {
                        value++;

                        if (value % 3 == 0) continue;
                    }

                    if (value % 7 == 0) {
                        return value;
                    }
                }
            }
        });
    }

    @Test
    public void infiniteNest() throws Exception {
        test(new Scriptable() {

            int act(int value) {
                while (true) {
                    while (true) {
                        value++;

                        if (value % 3 == 0) {
                            break;
                        }

                        if (value % 5 == 0) {
                            break;
                        }
                    }

                    value++;

                    if (value % 2 == 0) {
                        return value;
                    }
                }
            }
        });
    }

    @Test
    public void infiniteNestBreakAndContinue() throws Exception {
        test(new Scriptable() {

            int act(@Param(from = 0, to = 10) int value) {
                int a = 0;

                while (true) {
                    while (true) {
                        value++;

                        if (value % 3 == 0) {
                            break;
                        }

                        if (value % 4 == 0) {
                            a++;
                            continue;
                        }

                        if (value % 5 == 0) {
                            break;
                        }
                    }

                    a++;

                    if (value % 2 == 0) {
                        break;
                    }
                }
                return a;
            }
        });
    }
}
