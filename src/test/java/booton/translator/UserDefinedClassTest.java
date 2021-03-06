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

import booton.sample.Person;
import booton.soeur.ScriptTester;
import booton.soeur.Scriptable;

/**
 * @version 2012/11/30 15:33:32
 */
@SuppressWarnings("unused")
public class UserDefinedClassTest extends ScriptTester {

    @Test
    public void UserClass() {
        test(new Scriptable() {

            int act(int value) {
                Person user = new Person();
                user.setAge(value);

                return user.getAge();
            }
        });
    }

    @Test
    public void UserClassToString() {
        test(new Scriptable() {

            String act(String value) {
                Person user = new Person();
                user.setAge(17);
                user.setName(value);

                return user.toString();
            }
        });
    }
}
