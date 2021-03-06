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

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;
import org.junit.runner.RunWith;

import booton.soeur.ScriptRunner;

/**
 * @version 2015/02/28 20:23:10
 */
@RunWith(ScriptRunner.class)
public class NativeMapTest {

    @Test
    public void set() {
        NativeMap<String, String> map = new NativeMap();
        assert map.size() == 0;
        assert map.get("a") == null;

        map.set("a", "A");
        assert map.size() == 1;
        assert map.get("a").equals("A");

        map.set("b", "B");
        assert map.size() == 2;
        assert map.get("b").equals("B");

        map.set("a", "AA");
        assert map.size() == 2;
        assert map.get("a").equals("AA");
    }

    @Test
    public void nullKey() {
        NativeMap<String, String> map = new NativeMap();
        map.set(null, "A");
        assert map.size() == 1;
        assert map.get(null).equals("A");

        map.delete(null);
        assert map.get(null) == null;
    }

    @Test
    public void nullValue() {
        NativeMap<String, String> map = new NativeMap();
        assert map.has("a") == false;

        map.set("a", null);
        assert map.size() == 1;
        assert map.get("a") == null;
        assert map.has("a") == true;

        map.delete("a");
        assert map.get("a") == null;
        assert map.has("a") == false;
    }

    @Test
    public void clear() throws Exception {
        NativeMap<String, String> map = new NativeMap();
        map.set("a", null);
        map.set("b", null);
        assert map.size() == 2;

        map.clear();
        assert map.size() == 0;
    }

    @Test
    public void forEach() {
        NativeMap<Integer, Integer> map = new NativeMap();
        map.set(1, 10);
        map.set(2, 20);

        AtomicInteger sum = new AtomicInteger();
        map.forEach((value, key) -> sum.addAndGet(value));
        assert sum.get() == 30;
    }
}
