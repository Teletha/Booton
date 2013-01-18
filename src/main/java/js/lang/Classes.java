/*
 * Copyright (C) 2013 Nameless Production Committee
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://opensource.org/licenses/mit-license.php
 */
package js.lang;

import java.util.Collections;
import java.util.List;

import booton.translator.Translator;

/**
 * @version 2013/01/18 21:54:32
 */
public class Classes {

    /**
     * <p>
     * Find all sub class of the specified class.
     * </p>
     * 
     * @param type A type to search.
     * @return A list of found classes.
     */
    public static <T> List<Class<? extends T>> find(Class<T> type) {
        return Collections.EMPTY_LIST;
    }

    /**
     * @version 2013/01/18 22:41:25
     */
    @SuppressWarnings("unused")
    private static class Coder extends Translator<Classes> {

        /**
         * <p>
         * Find all sub class of the specified class.
         * </p>
         * 
         * @param type A type to search.
         * @return A list of found classes.
         */
        public String find(Class param0) {
            return "boot.find(" + param(0) + ")";
        }
    }
}
