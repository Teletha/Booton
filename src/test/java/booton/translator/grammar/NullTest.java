/*
 * Copyright (C) 2016 Nameless Production Committee
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://opensource.org/licenses/mit-license.php
 */
package booton.translator.grammar;

import org.junit.Test;

import booton.soeur.ScriptTester;
import booton.soeur.Scriptable;


/**
 * @version 2012/12/01 3:32:08
 */
@SuppressWarnings("unused")
public class NullTest extends ScriptTester {

    @Test
    public void Null() {
        test(new Scriptable() {

            Object act() {
                return null;
            }
        });
    }
}
