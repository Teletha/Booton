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

/**
 * <p>
 * {@link Byte} representation in Javascript runtime. This class doesn't provide all
 * functionalities.
 * </p>
 * 
 * @version 2013/04/12 12:58:25
 */
@JavaAPIProvider(Byte.class)
class JSByte extends JSNumber {

    /** The primitive byte class. */
    public static final Class TYPE = Primitive.class;

    /**
     * Constructs a newly allocated {@code Byte} object that represents the specified {@code byte}
     * value.
     * 
     * @param value the value to be represented by the {@code Byte}.
     */
    public JSByte(byte value) {
        super(value);
    }

    /**
     * Constructs a newly allocated {@code Byte} object that represents the {@code byte} value
     * indicated by the {@code String} parameter. The string is converted to a {@code byte} value in
     * exactly the manner used by the {@code parseByte} method for radix 10.
     * 
     * @param value the {@code String} to be converted to a {@code Byte}
     * @throws NumberFormatException If the {@code String} does not contain a parsable {@code byte}.
     * @see java.lang.Byte#parseByte(java.lang.String, int)
     */
    public JSByte(String value) throws NumberFormatException {
        super(parseByte(value));
    }

    /**
     * Compares two {@code byte} values numerically. The value returned is identical to what would
     * be returned by: <pre>
     *    Byte.valueOf(x).compareTo(Byte.valueOf(y))
     * </pre>
     *
     * @param x the first {@code byte} to compare
     * @param y the second {@code byte} to compare
     * @return the value {@code 0} if {@code x == y}; a value less than {@code 0} if {@code x < y};
     *         and a value greater than {@code 0} if {@code x > y}
     * @since 1.7
     */
    public static int compare(byte x, byte y) {
        return x - y;
    }

    /**
     * Parses the string argument as a signed decimal {@code byte}. The characters in the string
     * must all be decimal digits, except that the first character may be an ASCII minus sign
     * {@code '-'} (<code>'&#92;u002D'</code>) to indicate a negative value or an ASCII plus sign
     * {@code '+'} (<code>'&#92;u002B'</code>) to indicate a positive value. The resulting
     * {@code byte} value is returned, exactly as if the argument and the radix 10 were given as
     * arguments to the {@link #parseByte(java.lang.String, int)} method.
     * 
     * @param value a {@code String} containing the {@code byte} representation to be parsed
     * @return the {@code byte} value represented by the argument in decimal
     * @throws NumberFormatException if the string does not contain a parsable {@code byte}.
     */
    public static byte parseByte(String value) throws NumberFormatException {
        return parseByte(value, 10);
    }

    /**
     * Parses the string argument as a signed {@code byte} in the radix specified by the second
     * argument. The characters in the string must all be digits, of the specified radix (as
     * determined by whether {@link java.lang.Character#digit(char, int)} returns a nonnegative
     * value) except that the first character may be an ASCII minus sign {@code '-'} (
     * <code>'&#92;u002D'</code>) to indicate a negative value or an ASCII plus sign {@code '+'} (
     * <code>'&#92;u002B'</code>) to indicate a positive value. The resulting {@code byte} value is
     * returned.
     * <p>
     * An exception of type {@code NumberFormatException} is thrown if any of the following
     * situations occurs:
     * <ul>
     * <li>The first argument is {@code null} or is a string of length zero.
     * <li>The radix is either smaller than {@link java.lang.Character#MIN_RADIX} or larger than
     * {@link java.lang.Character#MAX_RADIX}.
     * <li>Any character of the string is not a digit of the specified radix, except that the first
     * character may be a minus sign {@code '-'} (<code>'&#92;u002D'</code>) or plus sign
     * {@code '+'} (<code>'&#92;u002B'</code>) provided that the string is longer than length 1.
     * <li>The value represented by the string is not a value of type {@code byte}.
     * </ul>
     * 
     * @param value the {@code String} containing the {@code byte} representation to be parsed
     * @param radix the radix to be used while parsing {@code s}
     * @return the {@code byte} value represented by the string argument in the specified radix
     * @throws NumberFormatException If the string does not contain a parsable {@code byte}.
     */
    public static byte parseByte(String value, int radix) throws NumberFormatException {
        int parsed = Global.parseInt(value, radix);

        if (Global.isNaN(parsed)) {
            throw new NumberFormatException(value + " is not a number.");
        }
        return (byte) parsed;
    }

    /**
     * Returns a {@code Byte} object holding the value given by the specified {@code String}. The
     * argument is interpreted as representing a signed decimal {@code byte}, exactly as if the
     * argument were given to the {@link #parseByte(java.lang.String)} method. The result is a
     * {@code Byte} object that represents the {@code byte} value specified by the string.
     * <p>
     * In other words, this method returns a {@code Byte} object equal to the value of:
     * <blockquote> {@code new Byte(Byte.parseByte(s))} </blockquote>
     * 
     * @param value the string to be parsed
     * @return a {@code Byte} object holding the value represented by the string argument
     * @throws NumberFormatException If the {@code String} does not contain a parsable {@code byte}.
     */
    public static Byte valueOf(String value) throws NumberFormatException {
        return valueOf(value, 10);
    }

    /**
     * Returns a {@code Byte} object holding the value extracted from the specified {@code String}
     * when parsed with the radix given by the second argument. The first argument is interpreted as
     * representing a signed {@code byte} in the radix specified by the second argument, exactly as
     * if the argument were given to the {@link #parseByte(java.lang.String, int)} method. The
     * result is a {@code Byte} object that represents the {@code byte} value specified by the
     * string.
     * <p>
     * In other words, this method returns a {@code Byte} object equal to the value of:
     * <blockquote> {@code new Byte(Byte.parseByte(s, radix))} </blockquote>
     * 
     * @param value the string to be parsed
     * @param radix the radix to be used in interpreting {@code s}
     * @return a {@code Byte} object holding the value represented by the string argument in the
     *         specified radix.
     * @throws NumberFormatException If the {@code String} does not contain a parsable {@code byte}.
     */
    public static Byte valueOf(String value, int radix) throws NumberFormatException {
        return valueOf(parseByte(value, radix));
    }

    /**
     * Returns a {@code Byte} instance representing the specified {@code byte} value. If a new
     * {@code Byte} instance is not required, this method should generally be used in preference to
     * the constructor {@link #Byte(byte)}, as this method is likely to yield significantly better
     * space and time performance since all byte values are cached.
     * 
     * @param value a byte value.
     * @return a {@code Byte} instance representing {@code b}.
     * @since 1.5
     */
    public static Byte valueOf(byte value) {
        return (Byte) (Object) new JSByte(value);
    }

    /**
     * @version 2013/04/16 23:01:24
     */
    @JavaAPIProvider(byte.class)
    private static class Primitive {
    }
}
