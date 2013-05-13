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

import booton.translator.JavaAPIProvider;

/**
 * <p>
 * {@link Float} representation in Javascript runtime. This class doesn't provide all
 * functionalities.
 * </p>
 * 
 * @version 2013/04/12 12:58:25
 */
@JavaAPIProvider(Float.class)
class JSFloat extends JSNumber {

    /** The primitive float class. */
    public static final Class TYPE = Primitive.class;

    /**
     * @param value
     */
    private JSFloat(double value) {
        super(value);
    }

    /**
     * Returns a new {@code float} initialized to the value represented by the specified
     * {@code String}, as performed by the {@code valueOf} method of class {@code Float}.
     * 
     * @param value the string to be parsed.
     * @return the {@code float} value represented by the string argument.
     * @throws NullPointerException if the string is null
     * @throws NumberFormatException if the string does not contain a parsable {@code float}.
     * @see java.lang.Float#valueOf(String)
     * @since 1.2
     */
    public static float parseFloat(String value) throws NumberFormatException {
        if (value == null) {
            throw new NullPointerException();
        }

        float parsed = Global.parseFloat(value);

        if (Global.isNaN(parsed)) {
            throw new NumberFormatException(value + " is not a number.");
        }
        return parsed;
    }

    /**
     * Returns a {@code Float} object holding the {@code float} value represented by the argument
     * string {@code s}.
     * <p>
     * If {@code s} is {@code null}, then a {@code NullPointerException} is thrown.
     * <p>
     * Leading and trailing whitespace characters in {@code s} are ignored. Whitespace is removed as
     * if by the {@link String#trim} method; that is, both ASCII space and control characters are
     * removed. The rest of {@code s} should constitute a <i>FloatValue</i> as described by the
     * lexical syntax rules: <blockquote>
     * <dl>
     * <dt><i>FloatValue:</i>
     * <dd><i>Sign<sub>opt</sub></i> {@code NaN}
     * <dd><i>Sign<sub>opt</sub></i> {@code Infinity}
     * <dd><i>Sign<sub>opt</sub> FloatingPointLiteral</i>
     * <dd><i>Sign<sub>opt</sub> HexFloatingPointLiteral</i>
     * <dd><i>SignedInteger</i>
     * </dl>
     * <p>
     * <dl>
     * <dt><i>HexFloatingPointLiteral</i>:
     * <dd><i>HexSignificand BinaryExponent FloatTypeSuffix<sub>opt</sub></i>
     * </dl>
     * <p>
     * <dl>
     * <dt><i>HexSignificand:</i>
     * <dd><i>HexNumeral</i>
     * <dd><i>HexNumeral</i> {@code .}
     * <dd>{@code 0x} <i>HexDigits<sub>opt</sub> </i>{@code .}<i> HexDigits</i>
     * <dd>{@code 0X}<i> HexDigits<sub>opt</sub> </i>{@code .} <i>HexDigits</i>
     * </dl>
     * <p>
     * <dl>
     * <dt><i>BinaryExponent:</i>
     * <dd><i>BinaryExponentIndicator SignedInteger</i>
     * </dl>
     * <p>
     * <dl>
     * <dt><i>BinaryExponentIndicator:</i>
     * <dd>{@code p}
     * <dd>{@code P}
     * </dl>
     * </blockquote> where <i>Sign</i>, <i>FloatingPointLiteral</i>, <i>HexNumeral</i>,
     * <i>HexDigits</i>, <i>SignedInteger</i> and <i>FloatTypeSuffix</i> are as defined in the
     * lexical structure sections of <cite>The Java&trade; Language Specification</cite>, except
     * that underscores are not accepted between digits. If {@code s} does not have the form of a
     * <i>FloatValue</i>, then a {@code NumberFormatException} is thrown. Otherwise, {@code s} is
     * regarded as representing an exact decimal value in the usual
     * "computerized scientific notation" or as an exact hexadecimal value; this exact numerical
     * value is then conceptually converted to an "infinitely precise" binary value that is then
     * rounded to type {@code float} by the usual round-to-nearest rule of IEEE 754 floating-point
     * arithmetic, which includes preserving the sign of a zero value. Note that the
     * round-to-nearest rule also implies overflow and underflow behaviour; if the exact value of
     * {@code s} is large enough in magnitude (greater than or equal to ({@link #MAX_VALUE} +
     * {@link Math#ulp(float) ulp(MAX_VALUE)}/2), rounding to {@code float} will result in an
     * infinity and if the exact value of {@code s} is small enough in magnitude (less than or equal
     * to {@link #MIN_VALUE}/2), rounding to float will result in a zero. Finally, after rounding a
     * {@code Float} object representing this {@code float} value is returned.
     * <p>
     * To interpret localized string representations of a floating-point value, use subclasses of
     * {@link java.text.NumberFormat}.
     * <p>
     * Note that trailing format specifiers, specifiers that determine the type of a floating-point
     * literal ({@code 1.0f} is a {@code float} value; {@code 1.0d} is a {@code double} value), do
     * <em>not</em> influence the results of this method. In other words, the numerical value of the
     * input string is converted directly to the target floating-point type. In general, the
     * two-step sequence of conversions, string to {@code double} followed by {@code double} to
     * {@code float}, is <em>not</em> equivalent to converting a string directly to {@code float}.
     * For example, if first converted to an intermediate {@code double} and then to {@code float},
     * the string<br>
     * {@code "1.00000017881393421514957253748434595763683319091796875001d"}<br>
     * results in the {@code float} value {@code 1.0000002f}; if the string is converted directly to
     * {@code float}, <code>1.000000<b>1</b>f</code> results.
     * <p>
     * To avoid calling this method on an invalid string and having a {@code NumberFormatException}
     * be thrown, the documentation for {@link Double#valueOf Double.valueOf} lists a regular
     * expression which can be used to screen the input.
     * 
     * @param value the string to be parsed.
     * @return a {@code Float} object holding the value represented by the {@code String} argument.
     * @throws NumberFormatException if the string does not contain a parsable number.
     */
    public static Float valueOf(String value) throws NumberFormatException {
        return valueOf(parseFloat(value));
    }

    /**
     * Returns a {@code Float} instance representing the specified {@code float} value. If a new
     * {@code Float} instance is not required, this method should generally be used in preference to
     * the constructor {@link #Float(float)}, as this method is likely to yield significantly better
     * space and time performance by caching frequently requested values.
     * 
     * @param value a float value.
     * @return a {@code Float} instance representing {@code f}.
     * @since 1.5
     */
    public static Float valueOf(float value) {
        return (Float) (Object) new JSFloat(value);
    }

    /**
     * @version 2013/04/16 23:01:24
     */
    @JavaAPIProvider(float.class)
    private static class Primitive {
    }
}