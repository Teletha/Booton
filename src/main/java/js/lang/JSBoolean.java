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

import booton.translator.JavaAPIProvider;
import booton.translator.JavascriptNative;
import booton.translator.JavascriptNativeProperty;

/**
 * <p>
 * {@link Boolean} representation in Javascript runtime. This class doesn't provide all
 * functionalities.
 * </p>
 * 
 * @version 2013/04/12 12:58:25
 */
@JavaAPIProvider(Boolean.class)
class JSBoolean implements JavascriptNative {

    /**
     * The {@code Boolean} object corresponding to the primitive value {@code true}.
     */
    public static final Boolean TRUE = new Boolean(true);

    /**
     * The {@code Boolean} object corresponding to the primitive value {@code false}.
     */
    public static final Boolean FALSE = new Boolean(false);

    /** The primitive boolean class. */
    public static final Class TYPE = Primitive.class;

    /** The actual value. */
    private final boolean value;

    /**
     * Allocates a {@code Boolean} object representing the {@code value} argument.
     * <p>
     * <b>Note: It is rarely appropriate to use this constructor. Unless a <i>new</i> instance is
     * required, the static factory {@link #valueOf(boolean)} is generally a better choice. It is
     * likely to yield significantly better space and time performance.</b>
     * 
     * @param value the value of the {@code Boolean}.
     */
    public JSBoolean(boolean value) {
        this.value = value;
    }

    /**
     * Allocates a {@code Boolean} object representing the value {@code true} if the string argument
     * is not {@code null} and is equal, ignoring case, to the string {@code "true"}. Otherwise,
     * allocate a {@code Boolean} object representing the value {@code false}. Examples:
     * <p>
     * {@code new Boolean("True")} produces a {@code Boolean} object that represents {@code true}.<br>
     * {@code new Boolean("yes")} produces a {@code Boolean} object that represents {@code false}.
     * 
     * @param value the string to be converted to a {@code Boolean}.
     */
    public JSBoolean(String value) {
        this(toBoolean(value));
    }

    /**
     * Returns the value of this {@code Boolean} object as a boolean primitive.
     * 
     * @return the primitive {@code boolean} value of this object.
     */
    public boolean booleanValue() {
        return value;
    }

    /**
     * <p>
     * Returns the primitive value of this object.
     * </p>
     * <p>
     * JavaScript calls the valueOf method to convert an object to a primitive value. You rarely
     * need to invoke the valueOf method yourself; JavaScript automatically invokes it when
     * encountering an object where a primitive value is expected.
     * </p>
     * <p>
     * You can create a function to be called in place of the default valueOf method. Your function
     * must take no arguments.
     * </p>
     * 
     * @return A primitive value.
     */
    @JavascriptNativeProperty
    public boolean valueOf() {
        return value;
    }

    /**
     * Returns a {@code String} object representing this Boolean's value. If this object represents
     * the value {@code true}, a string equal to {@code "true"} is returned. Otherwise, a string
     * equal to {@code "false"} is returned.
     * 
     * @return a string representation of this object.
     */
    public String toString() {
        return value ? "true" : "false";
    }

    /**
     * Returns {@code true} if and only if the system property named by the argument exists and is
     * equal to the string {@code "true"}. (Beginning with version 1.0.2 of the
     * Java<small><sup>TM</sup></small> platform, the test of this string is case insensitive.) A
     * system property is accessible through {@code getProperty}, a method defined by the
     * {@code System} class.
     * <p>
     * If there is no property with the specified name, or if the specified name is empty or null,
     * then {@code false} is returned.
     * 
     * @param name the system property name.
     * @return the {@code boolean} value of the system property.
     * @throws SecurityException for the same reasons as {@link System#getProperty(String)
     *             System.getProperty}
     * @see java.lang.System#getProperty(java.lang.String)
     * @see java.lang.System#getProperty(java.lang.String, java.lang.String)
     */
    public static boolean getBoolean(String name) {
        boolean result = false;

        try {
            result = parseBoolean(System.getProperty(name));
        } catch (IllegalArgumentException | NullPointerException e) {
        }
        return result;
    }

    /**
     * Parses the string argument as a boolean. The {@code boolean} returned represents the value
     * {@code true} if the string argument is not {@code null} and is equal, ignoring case, to the
     * string {@code "true"}.
     * <p>
     * Example: {@code Boolean.parseBoolean("True")} returns {@code true}.<br>
     * Example: {@code Boolean.parseBoolean("yes")} returns {@code false}.
     * 
     * @param value the {@code String} containing the boolean representation to be parsed
     * @return the boolean represented by the string argument
     * @since 1.5
     */
    public static boolean parseBoolean(String value) {
        return toBoolean(value);
    }

    /**
     * Returns a {@code Boolean} with a value represented by the specified string. The
     * {@code Boolean} returned represents a true value if the string argument is not {@code null}
     * and is equal, ignoring case, to the string {@code "true"}.
     * 
     * @param value a string.
     * @return the {@code Boolean} value represented by the string.
     */
    public static Boolean valueOf(String value) {
        return valueOf(parseBoolean(value));
    }

    /**
     * Returns a {@code Boolean} instance representing the specified {@code boolean} value. If the
     * specified {@code boolean} value is {@code true}, this method returns {@code Boolean.TRUE}; if
     * it is {@code false}, this method returns {@code Boolean.FALSE}. If a new {@code Boolean}
     * instance is not required, this method should generally be used in preference to the
     * constructor {@link #Boolean(boolean)}, as this method is likely to yield significantly better
     * space and time performance.
     * 
     * @param value a boolean value.
     * @return a {@code Boolean} instance representing {@code b}.
     * @since 1.4
     */
    public static Boolean valueOf(boolean value) {
        return value ? TRUE : FALSE;
    }

    /**
     * Returns a {@code String} object representing the specified boolean. If the specified boolean
     * is {@code true}, then the string {@code "true"} will be returned, otherwise the string
     * {@code "false"} will be returned.
     * 
     * @param value the boolean to be converted
     * @return the string representation of the specified {@code boolean}
     * @since 1.4
     */
    public static String toString(boolean value) {
        return value ? "true" : "false";
    }

    /**
     * <p>
     * Helper method to parse value.
     * </p>
     * 
     * @param name
     * @return
     */
    private static boolean toBoolean(String name) {
        return name != null && name.equalsIgnoreCase("true");
    }

    /**
     * @version 2013/04/16 23:01:24
     */
    @JavaAPIProvider(boolean.class)
    private static class Primitive {
    }
}
