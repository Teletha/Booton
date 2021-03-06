/*
 * Copyright (C) 2016 Nameless Production Committee
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://opensource.org/licenses/mit-license.php
 */
package js.lang.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.junit.Test;
import org.junit.runner.RunWith;

import booton.soeur.ScriptRunner;
import booton.translator.annotation.PrimitiveMarker;
import booton.translator.annotation.StringMarker;

/**
 * @version 2013/09/21 23:36:58
 */
@RunWith(ScriptRunner.class)
public class ParameterAnnotationTest {

    @Test
    public void method() throws Exception {
        Method method = Annotated.class.getMethod("method", int.class, int.class, String.class);
        Annotation[][] annotations = method.getParameterAnnotations();
        assert annotations.length == 3;

        Annotation[] set = annotations[0];
        assert set.length == 1;
        assert set[0] instanceof PrimitiveMarker;

        PrimitiveMarker primitive = (PrimitiveMarker) set[0];
        assert primitive.intValue() == 2;

        set = annotations[1];
        assert set.length == 1;
        assert set[0] instanceof StringMarker;

        StringMarker string = (StringMarker) set[0];
        assert string.value().equals("string");

        set = annotations[2];
        assert set.length == 0;
    }

    @Test
    public void constructor() throws Exception {
        Constructor constructor = Annotated.class.getConstructor(int.class, int.class, String.class);
        Annotation[][] annotations = constructor.getParameterAnnotations();
        assert annotations.length == 3;

        Annotation[] set = annotations[0];
        assert set.length == 1;
        assert set[0] instanceof PrimitiveMarker;

        PrimitiveMarker primitive = (PrimitiveMarker) set[0];
        assert primitive.intValue() == 2;

        set = annotations[1];
        assert set.length == 1;
        assert set[0] instanceof StringMarker;

        StringMarker string = (StringMarker) set[0];
        assert string.value().equals("string");

        set = annotations[2];
        assert set.length == 0;
    }

    @Test
    public void variableArguments() throws Exception {
        Method method = Annotated.class.getMethod("var", int.class, int[].class);
        Annotation[][] annotations = method.getParameterAnnotations();
        assert annotations.length == 2;

        Annotation[] set = annotations[0];
        assert set.length == 0;

        set = annotations[1];
        assert set.length == 1;
        assert set[0] instanceof PrimitiveMarker;

        PrimitiveMarker primitive = (PrimitiveMarker) set[0];
        assert primitive.intValue() == 1000;
    }

    /**
     * @version 2013/09/13 16:17:19
     */
    @SuppressWarnings("unused")
    private static class Annotated {

        public Annotated(@PrimitiveMarker(intValue = 2) int first, @StringMarker("string") int second, String none) {
        }

        public void method(@PrimitiveMarker(intValue = 2) int first, @StringMarker("string") int second, String none) {
        }

        public void var(int first, @PrimitiveMarker(intValue = 1000) int... second) {
        }
    }
}
