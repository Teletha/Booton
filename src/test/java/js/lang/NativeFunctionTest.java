/*
 * Copyright (C) 2016 Nameless Production Committee
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://opensource.org/licenses/mit-license.php
 */
package js.lang;

import org.junit.Test;
import org.junit.runner.RunWith;

import booton.soeur.ScriptRunner;

/**
 * @version 2013/08/19 1:31:37
 */
@RunWith(ScriptRunner.class)
public class NativeFunctionTest {

    private Character ayase = new Character("あやせ");

    private Character kuroneko = new Character("黒猫");

    @Test
    public void apply() throws Exception {
        NativeFunction<Character> say = new NativeFunction(ayase);
        assert say.apply(ayase, "通報しますよ！").equals("あやせ「通報しますよ！」");
    }

    @Test
    public void context() throws Exception {
        NativeFunction<Character> say = new NativeFunction(ayase);
        assert say.apply(kuroneko, "バカ言わないで頂戴").equals("黒猫「バカ言わないで頂戴」");
    }

    @Test
    public void bindContext() throws Exception {
        NativeFunction<Character> say = new NativeFunction(ayase).bind(kuroneko);
        assert say.apply(ayase, "通報しますよ！").equals("黒猫「通報しますよ！」");
    }

    @Test
    public void bindParameter() throws Exception {
        NativeFunction<Character> say = new NativeFunction(ayase).bind(ayase, "ば、バカじゃないの");
        assert say.apply(ayase, "通報しますよ！").equals("あやせ「ば、バカじゃないの」");
    }

    @Test
    public void bindBoth() throws Exception {
        NativeFunction<Character> say = new NativeFunction(ayase).bind(kuroneko, "ば、バカじゃないの");
        assert say.apply(ayase, "通報しますよ！").equals("黒猫「ば、バカじゃないの」");
    }

    /**
     * @version 2013/08/19 1:32:22
     */
    private static class Character {

        private String name;

        /**
         * @param name
         */
        private Character(String name) {
            this.name = name;
        }

        /**
         */
        @SuppressWarnings("unused")
        private String say(String message) {
            return name + "「" + message + "」";
        }
    }
}
