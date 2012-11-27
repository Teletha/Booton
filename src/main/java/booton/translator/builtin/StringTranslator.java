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
package booton.translator.builtin;

import booton.translator.Translator;

/**
 * @version 2009/08/08 10:49:18
 */
public class StringTranslator extends Translator<String> {

    public String String(String value) {
        return param(0);
    }

    /**
     * Javascript native String class have length property instead of length method. And Javascript
     * manages object as hash, so object can't have same name method or property. We must convert
     * invoking java.lang.String#length() method to the accessing length property.
     */
    public String length() {
        return that + ".length";
    }

    public String charAt(int index) {
        return that + ".charAt(" + param(0) + ")";
    }

    public String indexOf(String value) {
        return that + ".indexOf(" + param(0) + ")";
    }

    public String lastIndexOf(String value) {
        return that + ".lastIndexOf(" + param(0) + ")";
    }

    public String startsWith(String value) {
        return that + ".startsWith(" + param(0) + ")";
    }

    public String endsWith(String value) {
        return that + ".endsWith(" + param(0) + ")";
    }

    public String concat(String value) {
        return that + ".concat(" + param(0) + ")";
    }

    public String toLowerCase() {
        return that + ".toLowerCase()";
    }

    public String toUpperCase() {
        return that + ".toUpperCase()";
    }

    public String trim() {
        return that + ".trim()";
    }

    public String replace(char oldChar, char newChar) {
        return that + ".replace(" + regex(0, "g") + "," + param(1) + ")";
    }

    public String replace(CharSequence regex, CharSequence replace) {
        return that + ".replace(" + regex(0, "g") + "," + param(1) + ")";
    }

    public String replaceAll(String regex, String replacement) {
        return that + ".replace(" + regex(0, "g") + "," + param(1) + ")";
    }

    public String replaceFirst(String regex, String replacement) {
        return that + ".replace(" + regex(0) + "," + param(1) + ")";
    }

    public String codePointAt(int index) {
        return that + ".charCodeAt(" + param(0) + ")";
    }

    public String codePointBefore(int index) {
        return that + ".charCodeAt(" + param(0) + "-1)";
    }

    public String substring(int start) {
        return that + ".substring(" + param(0) + ")";
    }

    public String substring(int start, int end) {
        return that + ".substring(" + param(0) + "," + param(1) + ")";
    }

    public String valueOf(Object value) {
        return "new String(" + param(0) + ")";
    }
}
