/*
 * Copyright (C) 2016 Nameless Production Committee
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://opensource.org/licenses/mit-license.php
 */
package booton.translator.operator;

import org.junit.Test;

import booton.soeur.ScriptTester;
import booton.soeur.Scriptable;

/**
 * @version 2013/02/17 15:09:01
 */
@SuppressWarnings("unused")
public class FloatIncrementTest extends ScriptTester {

    @Test
    public void incrementFieldInMethodCall() throws Exception {
        test(new Scriptable() {

            private float index = 0;

            public float act() {
                return call(index++);
            }

            private float call(float value) {
                return index + value * 10;
            }
        });
    }

    @Test
    public void decrementFieldInMethodCall() throws Exception {
        test(new Scriptable() {

            private float index = 0;

            public float act() {
                return call(index--);
            }

            private float call(float value) {
                return index + value * 10;
            }
        });
    }

    @Test
    public void preincrementFieldInMethodCall() throws Exception {
        test(new Scriptable() {

            private float index = 0;

            public float act() {
                return call(++index);
            }

            private float call(float value) {
                return index + value * 10;
            }
        });
    }

    @Test
    public void predecrementFieldInMethodCall() throws Exception {
        test(new Scriptable() {

            private float index = 0;

            public float act() {
                return call(--index);
            }

            private float call(float value) {
                return index + value * 10;
            }
        });
    }

    @Test
    public void incrementFieldInFieldAccess() throws Exception {
        test(new Scriptable() {

            private float index = 1;

            private float count = 2;

            public float act() {
                index = count++;

                return count + index * 10;
            }
        });
    }

    @Test
    public void decrementFieldInFieldAccess() throws Exception {
        test(new Scriptable() {

            private float index = 1;

            private float count = 2;

            public float act() {
                index = count--;

                return count + index * 10;
            }
        });
    }

    @Test
    public void preincrementFieldInFieldAccess() throws Exception {
        test(new Scriptable() {

            private float index = 1;

            private float count = 2;

            public float act() {
                index = ++count;

                return count + index * 10;
            }
        });
    }

    @Test
    public void predecrementFieldInFieldAccess() throws Exception {
        test(new Scriptable() {

            private float index = 1;

            private float count = 2;

            public float act() {
                index = --count;

                return count + index * 10;
            }
        });
    }
}
