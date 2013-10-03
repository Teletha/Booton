/*
 * Copyright (C) 2013 Nameless Production Committee
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://opensource.org/licenses/mit-license.php
 */
package kiss;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;

import booton.sample.Person;
import booton.sample.beans.Collections;
import booton.sample.beans.Primitives;
import booton.translator.ScriptRunner;

/**
 * @version 2013/10/02 11:28:35
 */
@RunWith(ScriptRunner.class)
public class ReadWriteTest {

    @Test
    public void bean() {
        Person person = I.make(Person.class);
        person.setAge(16);
        person.setLastName("Yuigahama");
        person.setFirstName("Yui");

        Person other = writeThenRead(person);

        assert other.getAge() == 16;
        assert other.getFirstName().equals("Yui");
        assert other.getLastName().equals("Yuigahama");
    }

    @Test
    public void primitives() throws Exception {
        Primitives primitives = I.make(Primitives.class);
        primitives.setIntValue(10);
        primitives.setBooleanValue(true);
        primitives.setByteValue((byte) 2);
        primitives.setCharValue('a');
        primitives.setDoubleValue(9.3443456345634);
        primitives.setFloatValue(0.533f);
        primitives.setLongValue(3020103);
        primitives.setShortValue((short) 19);

        Primitives other = writeThenRead(primitives);

        assert other.getByteValue() == 2;
        assert other.getCharValue() == 'a';
        assert other.getDoubleValue() == 9.3443456345634;
        assert other.getFloatValue() == 0.533f;
        assert other.getIntValue() == 10;
        assert other.getLongValue() == 3020103;
        assert other.getShortValue() == 19;
    }

    @Test
    public void list() throws Exception {
        List<String> list = new ArrayList();
        list.add("one");
        list.add("two");
        list.add("three");

        Collections collections = I.make(Collections.class);
        collections.setList(list);

        Collections other = writeThenRead(collections);
        assert other.getList().size() == 3;
        assert other.getList().get(0).equals("one");
        assert other.getList().get(1).equals("two");
        assert other.getList().get(2).equals("three");
    }

    /**
     * <p>
     * Helper method to write and read model.
     * </p>
     * 
     * @param model
     * @return
     */
    private <T> T writeThenRead(T model) {
        StringBuilder builder = new StringBuilder();
        I.write(model, builder, true);
        T other = (T) I.read(builder, I.make(model.getClass()));

        assert other != model;
        return other;
    }
}
