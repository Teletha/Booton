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

import org.junit.Ignore;
import org.junit.Test;

import booton.translator.Debuggable;
import booton.translator.Param;
import booton.translator.ScriptTester;
import booton.translator.Scriptable;

/**
 * @version 2013/01/11 16:57:25
 */
@SuppressWarnings("unused")
public class TryTest extends ScriptTester {

    /**
     * @version 2013/04/10 14:51:57
     */
    private static class Throw {

        private static int error(int value) {
            if (value == 1) {
                throw new Error();
            }
            return value + 10;
        }

        private static int exception(int value) throws Exception {
            if (value == 2) {
                throw new Exception();
            }
            return value + 10;
        }
    }

    @Test
    public void TryCatch() {
        test(new Scriptable() {

            @Debuggable
            public int act(int value) {
                try {
                    return Throw.error(value);
                } catch (Error e) {
                    return -1;
                }
            }
        });
    }

    @Test
    public void TryCatchAfter() {
        test(new Scriptable() {

            @Debuggable
            public int act(int value) {
                try {
                    value = Throw.error(value);
                } catch (Error e) {
                    value += 2;
                }
                return value;
            }
        });
    }

    @Test
    public void TryCatchAfter2() {
        test(new Scriptable() {

            @Debuggable
            public int act(int value) {
                try {
                    value = Throw.error(value);
                    value = Throw.error(value);
                } catch (Error e) {
                    value += 2;
                }
                return value;
            }
        });
    }

    @Test
    public void TryEmptyCatch() {
        test(new Scriptable() {

            @Debuggable
            public int act(int value) {
                try {
                    value = Throw.error(value);
                } catch (Error e) {
                    // do nothing
                }
                return value;
            }
        });
    }

    @Test
    public void TryMultipleCatch() {
        test(new Scriptable() {

            @Debuggable
            public int act(int value) {
                try {
                    value = Throw.error(value);
                    value = Throw.exception(value);

                    return value + 100;
                } catch (Exception e) {
                    return 2;
                } catch (Error e) {
                    return 3;
                }
            }
        });
    }

    @Test
    public void TryMultipleCatchAfter() {
        test(new Scriptable() {

            @Debuggable
            public int act(int value) {
                try {
                    value = Throw.error(value);
                    value = Throw.exception(value);
                } catch (Exception e) {
                    value = 20;
                } catch (Error e) {
                    value = 30;
                }
                return value;
            }
        });
    }

    @Test
    public void TryCatchInCatch() {
        test(new Scriptable() {

            @Debuggable
            public int act(@Param(from = 0, to = 10) int value) {
                try {
                    if (value % 2 == 0) {
                        throw new Error();
                    }
                    return value + 1;
                } catch (Error e) {
                    try {
                        if (value % 3 == 0) {
                            throw new Error();
                        }
                        return value + 2;
                    } catch (Error e2) {
                        return value + 3;
                    }
                }
            }
        });
    }

    @Test
    public void TryCatchInTry() {
        test(new Scriptable() {

            @Debuggable
            public int act(@Param(from = 0, to = 10) int value) {
                try {
                    value = Throw.exception(value);

                    try {
                        value = Throw.error(value);
                    } catch (Error e) {
                        return value + 1;
                    }
                    return value + 2;
                } catch (Exception e) {
                    return value + 3;
                }
            }
        });
    }

    @Test
    @Ignore
    public void TryCatchFromDepth() {
        test(new Scriptable() {

            @Debuggable
            public int act(@Param(from = 0, to = 10) int value) {
                try {
                    return compute(value);
                } catch (Exception e) {
                    return value + 10;
                }
            }

            private int compute(int value) throws Exception {
                if (value % 2 == 0) {
                    throw new Exception();
                }
                return value;
            }
        });
    }

    @Test
    @Ignore
    public void TryCatchWithFrameFull() {
        test(new Scriptable() {

            @Debuggable
            public int act(int value) {
                for (int i = 0; i < 1; i++) {
                    value++;
                }

                try {
                    return value;
                } catch (Exception e) {
                    return 10; // unexecutable
                }

            }
        });
    }

    @Test
    @Ignore
    public void TryCatchSequencial() {
        test(new Scriptable() {

            @Debuggable
            public int act(@Param(from = 0, to = 3) int value) {
                try {
                    if (value == 0) {
                        throw new Error();
                    }
                } catch (Error e) {
                    return -1;
                }

                try {
                    if (value == 3) {
                        throw new Error();
                    }
                } catch (Error e) {
                    return -1;
                }
                return value;
            }
        });
    }

    @Test
    @Ignore
    public void TryFinally() {
        test(new Scriptable() {

            @Debuggable
            public int act(int value) {
                try {
                    value = error(value);
                } finally {
                    value++;
                }
                return value;
            }

            @Debuggable
            private int error(int value) {
                if (value == 1) {
                    throw new Error();
                }
                return value + 10;
            }
        });
    }

    @Test
    @Ignore
    public void TryEmptyFinally() {
        test(new Scriptable() {

            @Debuggable
            public int act(int value) {
                try {
                    value = error(value);
                } finally {
                    // do nothing
                }
                return value;
            }

            @Debuggable
            private int error(int value) {
                if (value == 1) {
                    throw new Error();
                }
                return value + 10;
            }
        });
    }

    @Test
    @Ignore
    public void EmptyTryFinally() {
        test(new Scriptable() {

            @Debuggable
            public int act(int value) {
                try {
                    // do nothing
                } finally {
                    value = error(value);
                }
                return value;
            }

            @Debuggable
            private int error(int value) {
                if (value == 1) {
                    throw new Error();
                }
                return value + 10;
            }
        });
    }

    @Test
    @Ignore
    public void TryCatchFinally() {
        test(new Scriptable() {

            @Debuggable
            public int act(int value) {
                try {
                    if (value == 0) {
                        throw new Error();
                    }
                    value++;
                } catch (Error e) {
                    value--;
                } finally {
                    value = value + 10;
                }
                return value;
            }
        });
    }
}
