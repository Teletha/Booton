/*
 * Copyright (C) 2009 Nameless Production Committee.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package booton.translator;

import org.junit.Test;

/**
 * @version 2009/08/18 18:50:34
 */
public class FieldTest extends ScriptTranslatorTestcase {

    @Test
    public void IntField() {
        assertScript(new IntField());
    }

    /**
     * @version 2009/08/18 18:54:24
     */
    private static class IntField implements ScriptForInt {

        private int field = 10;

        public int execute(int value) {
            return field;
        }
    }

    @Test
    public void IntFieldWithExpresison() {
        assertScript(new IntFieldWithExpresison());
    }

    /**
     * @version 2009/08/18 18:54:24
     */
    private static class IntFieldWithExpresison implements ScriptForInt {

        private int field = 10;

        public int execute(int value) {
            return field + value;
        }
    }

    @Test
    public void LongField() {
        assertScript(new LongField());
    }

    /**
     * @version 2009/08/18 18:54:24
     */
    private static class LongField implements ScriptForLong {

        private long field = 9876543210L;

        public long execute(long value) {
            return field;
        }
    }

    @Test
    public void FloatField() {
        assertScript(new FloatField());
    }

    /**
     * @version 2009/08/18 18:54:24
     */
    private static class FloatField implements ScriptForFloat {

        private float field = 3.1415f;

        public float execute(float value) {
            return field;
        }
    }

    @Test
    public void DoubleField() {
        assertScript(new DoubleField());
    }

    /**
     * @version 2009/08/18 18:54:24
     */
    private static class DoubleField implements ScriptForDouble {

        private double field = 3.14159265358979323846264338327950288419716939937510d;

        public double execute(double value) {
            return field;
        }
    }

    @Test
    public void BooleanField() {
        assertScript(new BooleanField());
    }

    /**
     * @version 2009/08/18 18:54:24
     */
    private static class BooleanField implements ScriptForBoolean {

        private boolean field = false;

        public boolean execute(boolean value) {
            return field;
        }
    }

    @Test
    public void Extend() {
        assertScript(new ExtendChild());
    }

    /**
     * @version 2009/09/01 2:51:35
     */
    private static class ExtendBase {

        public int field = 10;
    }

    /**
     * @version 2009/08/18 19:19:52
     */
    private static class ExtendChild extends ExtendBase implements ScriptForInt {

        public int execute(int value) {
            return value + field;
        }
    }

    @Test
    public void Override() {
        assertScript(new OverrideChild());
    }

    /**
     * @version 2009/09/01 2:51:35
     */
    private static class OverrideBase {

        @SuppressWarnings("unused")
        protected int field = 10;
    }

    /**
     * @version 2009/08/18 19:19:52
     */
    private static class OverrideChild extends OverrideBase implements ScriptForInt {

        protected int field = 5;

        public int execute(int value) {
            return value + this.field;
        }
    }

    @Test
    public void Super() {
        assertScript(new SuperChild());
    }

    /**
     * @version 2009/09/01 2:51:35
     */
    private static class SuperBase {

        protected int field = 10;
    }

    /**
     * @version 2009/08/18 19:19:52
     */
    private static class SuperChild extends SuperBase implements ScriptForInt {

        protected int field = 5;

        public int execute(int value) {
            return value + this.field + super.field;
        }
    }
}
