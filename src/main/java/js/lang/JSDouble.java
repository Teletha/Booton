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
 * {@link Double} representation in Javascript runtime. This class doesn't provide all
 * functionalities.
 * </p>
 * 
 * @version 2014/03/24 16:16:31
 */
@JavaAPIProvider(Double.class)
class JSDouble extends JSNumber {

    /** The primitive double class. */
    public static final Class TYPE = Primitive.class;

    /**
     * Constructs a newly allocated {@code Double} object that represents the primitive
     * {@code double} argument.
     * 
     * @param value the value to be represented by the {@code Double}.
     */
    public JSDouble(double value) {
        super(value);
    }

    /**
     * Constructs a newly allocated {@code Double} object that represents the floating-point value
     * of type {@code double} represented by the string. The string is converted to a {@code double}
     * value as if by the {@code valueOf} method.
     * 
     * @param value a string to be converted to a {@code Double}.
     * @throws NumberFormatException if the string does not contain a parsable number.
     * @see java.lang.Double#valueOf(java.lang.String)
     */
    public JSDouble(String value) throws NumberFormatException {
        super(valueOf(value).doubleValue());
    }

    /**
     * Returns a hash code for this {@code Double} object. The result is the exclusive OR of the two
     * halves of the {@code long} integer bit representation, exactly as produced by the method
     * {@link #doubleToLongBits(double)}, of the primitive {@code double} value represented by this
     * {@code Double} object. That is, the hash code is the value of the expression: <blockquote>
     * {@code (int)(v^(v>>>32))} </blockquote> where {@code v} is defined by: <blockquote>
     * {@code long v = Double.doubleToLongBits(this.doubleValue());} </blockquote>
     * 
     * @return a {@code hash code} value for this object.
     */
    @Override
    public int hashCode() {
        return value.intValue();
    }

    /**
     * Returns a hash code for a {@code double} value; compatible with {@code Double.hashCode()}.
     * 
     * @param value the value to hash
     * @return a hash code value for a {@code double} value.
     * @since 1.8
     */
    public static int hashCode(double value) {
        return Double.valueOf(value).intValue();
    }

    /**
     * Returns {@code true} if the argument is a finite floating-point value; returns {@code false}
     * otherwise (for NaN and infinity arguments).
     *
     * @param d the {@code double} value to be tested
     * @return {@code true} if the argument is a finite floating-point value, {@code false}
     *         otherwise.
     * @since 1.8
     */
    public static boolean isFinite(double d) {
        return Math.abs(d) <= Double.MAX_VALUE;
    }

    /**
     * Returns {@code true} if the specified number is infinitely large in magnitude, {@code false}
     * otherwise.
     * 
     * @param value the value to be tested.
     * @return {@code true} if the value of the argument is positive infinity or negative infinity;
     *         {@code false} otherwise.
     */
    public static boolean isInfinite(double value) {
        return value == Double.POSITIVE_INFINITY || value == Double.NEGATIVE_INFINITY;
    }

    /**
     * Returns {@code true} if the specified number is a Not-a-Number (NaN) value, {@code false}
     * otherwise.
     * 
     * @param value the value to be tested.
     * @return {@code true} if the value of the argument is NaN; {@code false} otherwise.
     */
    public static boolean isNaN(double value) {
        return value != value;
    }

    /**
     * Compares the two specified {@code double} values. The sign of the integer value returned is
     * the same as that of the integer that would be returned by the call: <pre>
     *    new Double(d1).compareTo(new Double(d2))
     * </pre>
     * 
     * @param d1 the first {@code double} to compare
     * @param d2 the second {@code double} to compare
     * @return the value {@code 0} if {@code d1} is numerically equal to {@code d2}; a value less
     *         than {@code 0} if {@code d1} is numerically less than {@code d2}; and a value greater
     *         than {@code 0} if {@code d1} is numerically greater than {@code d2}.
     * @since 1.4
     */
    public static int compare(double d1, double d2) {
        if (d1 < d2) {
            return -1; // Neither val is NaN, thisVal is smaller
        }

        if (d1 > d2) {
            return 1; // Neither val is NaN, thisVal is larger
        }
        return 0;
    }

    // /**
    // * Returns a representation of the specified floating-point value according to the IEEE 754
    // * floating-point "double format" bit layout.
    // * <p>
    // * Bit 63 (the bit that is selected by the mask {@code 0x8000000000000000L}) represents the
    // sign
    // * of the floating-point number. Bits 62-52 (the bits that are selected by the mask
    // * {@code 0x7ff0000000000000L}) represent the exponent. Bits 51-0 (the bits that are selected
    // by
    // * the mask {@code 0x000fffffffffffffL}) represent the significand (sometimes called the
    // * mantissa) of the floating-point number.
    // * <p>
    // * If the argument is positive infinity, the result is {@code 0x7ff0000000000000L}.
    // * <p>
    // * If the argument is negative infinity, the result is {@code 0xfff0000000000000L}.
    // * <p>
    // * If the argument is NaN, the result is {@code 0x7ff8000000000000L}.
    // * <p>
    // * In all cases, the result is a {@code long} integer that, when given to the
    // * {@link #longBitsToDouble(long)} method, will produce a floating-point value the same as the
    // * argument to {@code doubleToLongBits} (except all NaN values are collapsed to a single
    // * "canonical" NaN value).
    // *
    // * @param value a {@code double} precision floating-point number.
    // * @return the bits that represent the floating-point number.
    // */
    // public static long doubleToLongBits(double value) {
    // long result = doubleToRawLongBits(value);
    // // Check for NaN based on values of bit fields, maximum
    // // exponent and nonzero significand.
    // if (((result & DoubleConsts.EXP_BIT_MASK) == DoubleConsts.EXP_BIT_MASK) && (result &
    // DoubleConsts.SIGNIF_BIT_MASK) != 0L)
    // result = 0x7ff8000000000000L;
    // return result;
    // }

    // /**
    // * Returns a representation of the specified floating-point value according to the IEEE 754
    // * floating-point "double format" bit layout, preserving Not-a-Number (NaN) values.
    // * <p>
    // * Bit 63 (the bit that is selected by the mask {@code 0x8000000000000000L}) represents the
    // sign
    // * of the floating-point number. Bits 62-52 (the bits that are selected by the mask
    // * {@code 0x7ff0000000000000L}) represent the exponent. Bits 51-0 (the bits that are selected
    // by
    // * the mask {@code 0x000fffffffffffffL}) represent the significand (sometimes called the
    // * mantissa) of the floating-point number.
    // * <p>
    // * If the argument is positive infinity, the result is {@code 0x7ff0000000000000L}.
    // * <p>
    // * If the argument is negative infinity, the result is {@code 0xfff0000000000000L}.
    // * <p>
    // * If the argument is NaN, the result is the {@code long} integer representing the actual NaN
    // * value. Unlike the {@code doubleToLongBits} method, {@code doubleToRawLongBits} does not
    // * collapse all the bit patterns encoding a NaN to a single "canonical" NaN value.
    // * <p>
    // * In all cases, the result is a {@code long} integer that, when given to the
    // * {@link #longBitsToDouble(long)} method, will produce a floating-point value the same as the
    // * argument to {@code doubleToRawLongBits}.
    // *
    // * @param value a {@code double} precision floating-point number.
    // * @return the bits that represent the floating-point number.
    // * @since 1.3
    // */
    // public static long doubleToRawLongBits(double value) {
    // boolean negative = value < 0;
    //
    // if (negative) {
    // value *= -1;
    // }
    //
    // double exp = Math.floor(Math.log(value) / Math.log(2));
    // double frac = Math.floor(value * Math.pow(2, 52 - exp));
    // exp += 1023;
    //
    // StringBuilder builder = new StringBuilder();
    // builder.append(negative ? "1" : "0");
    //
    // String mix = "0000000000".concat(Long.toBinaryString((long) exp));
    // mix = mix.substring(mix.length() - 11);
    //
    // builder.append(mix).append(Long.toBinaryString((long) frac).substring(1));
    // return Long.parseLong(builder.toString(), 2);
    // }

    /**
     * Returns a new {@code double} initialized to the value represented by the specified
     * {@code String}, as performed by the {@code valueOf} method of class {@code Double}.
     * 
     * @param s the string to be parsed.
     * @return the {@code double} value represented by the string argument.
     * @throws NullPointerException if the string is null
     * @throws NumberFormatException if the string does not contain a parsable {@code double}.
     * @see java.lang.Double#valueOf(String)
     * @since 1.2
     */
    public static double parseDouble(String s) throws NumberFormatException {
        return Global.parseFloat(s);
    }

    /**
     * Returns a representation of the specified floating-point value according to the IEEE 754
     * floating-point "double format" bit layout.
     * <p>
     * Bit 63 (the bit that is selected by the mask {@code 0x8000000000000000L}) represents the sign
     * of the floating-point number. Bits 62-52 (the bits that are selected by the mask
     * {@code 0x7ff0000000000000L}) represent the exponent. Bits 51-0 (the bits that are selected by
     * the mask {@code 0x000fffffffffffffL}) represent the significand (sometimes called the
     * mantissa) of the floating-point number.
     * <p>
     * If the argument is positive infinity, the result is {@code 0x7ff0000000000000L}.
     * <p>
     * If the argument is negative infinity, the result is {@code 0xfff0000000000000L}.
     * <p>
     * If the argument is NaN, the result is {@code 0x7ff8000000000000L}.
     * <p>
     * In all cases, the result is a {@code long} integer that, when given to the
     * {@link #longBitsToDouble(long)} method, will produce a floating-point value the same as the
     * argument to {@code doubleToLongBits} (except all NaN values are collapsed to a single
     * "canonical" NaN value).
     * 
     * @param value a {@code double} precision floating-point number.
     * @return the bits that represent the floating-point number.
     */
    public static long doubleToLongBits(double value) {
        long result = doubleToRawLongBits(value);

        if (((result & 0x7FF0000000000000L) == 0x7FF0000000000000L) && (result & 0x000FFFFFFFFFFFFFL) != 0L) {
            result = 0x7ff8000000000000L;
        }
        return result;
    }

    /**
     * Returns a representation of the specified floating-point value according to the IEEE 754
     * floating-point "double format" bit layout, preserving Not-a-Number (NaN) values.
     * <p>
     * Bit 63 (the bit that is selected by the mask {@code 0x8000000000000000L}) represents the sign
     * of the floating-point number. Bits 62-52 (the bits that are selected by the mask
     * {@code 0x7ff0000000000000L}) represent the exponent. Bits 51-0 (the bits that are selected by
     * the mask {@code 0x000fffffffffffffL}) represent the significand (sometimes called the
     * mantissa) of the floating-point number.
     * <p>
     * If the argument is positive infinity, the result is {@code 0x7ff0000000000000L}.
     * <p>
     * If the argument is negative infinity, the result is {@code 0xfff0000000000000L}.
     * <p>
     * If the argument is NaN, the result is the {@code long} integer representing the actual NaN
     * value. Unlike the {@code doubleToLongBits} method, {@code doubleToRawLongBits} does not
     * collapse all the bit patterns encoding a NaN to a single "canonical" NaN value.
     * <p>
     * In all cases, the result is a {@code long} integer that, when given to the
     * {@link #longBitsToDouble(long)} method, will produce a floating-point value the same as the
     * argument to {@code doubleToRawLongBits}.
     * 
     * @param value a {@code double} precision floating-point number.
     * @return the bits that represent the floating-point number.
     * @since 1.3
     */
    public static long doubleToRawLongBits(double value) {
        // If this exception will be thrown, it is bug of this program. So we must rethrow the
        // wrapped error in here.
        throw new Error();
    }

    /**
     * Returns the {@code double} value corresponding to a given bit representation. The argument is
     * considered to be a representation of a floating-point value according to the IEEE 754
     * floating-point "double format" bit layout.
     * <p>
     * If the argument is {@code 0x7ff0000000000000L}, the result is positive infinity.
     * <p>
     * If the argument is {@code 0xfff0000000000000L}, the result is negative infinity.
     * <p>
     * If the argument is any value in the range {@code 0x7ff0000000000001L} through
     * {@code 0x7fffffffffffffffL} or in the range {@code 0xfff0000000000001L} through
     * {@code 0xffffffffffffffffL}, the result is a NaN. No IEEE 754 floating-point operation
     * provided by Java can distinguish between two NaN values of the same type with different bit
     * patterns. Distinct values of NaN are only distinguishable by use of the
     * {@code Double.doubleToRawLongBits} method.
     * <p>
     * In all other cases, let <i>s</i>, <i>e</i>, and <i>m</i> be three values that can be computed
     * from the argument: <blockquote> <pre>{@code
     * int s = ((bits >> 63) == 0) ? 1 : -1;
     * int e = (int)((bits >> 52) & 0x7ffL);
     * long m = (e == 0) ?
     *                 (bits & 0xfffffffffffffL) << 1 :
     *                 (bits & 0xfffffffffffffL) | 0x10000000000000L;
     * }</pre> </blockquote> Then the floating-point result equals the value of the mathematical
     * expression <i>s</i>&middot;<i>m</i>&middot;2<sup><i>e</i>-1075</sup>.
     * <p>
     * Note that this method may not be able to return a {@code double} NaN with exactly same bit
     * pattern as the {@code long} argument. IEEE 754 distinguishes between two kinds of NaNs, quiet
     * NaNs and <i>signaling NaNs</i>. The differences between the two kinds of NaN are generally
     * not visible in Java. Arithmetic operations on signaling NaNs turn them into quiet NaNs with a
     * different, but often similar, bit pattern. However, on some processors merely copying a
     * signaling NaN also performs that conversion. In particular, copying a signaling NaN to return
     * it to the calling method may perform this conversion. So {@code longBitsToDouble} may not be
     * able to return a {@code double} with a signaling NaN bit pattern. Consequently, for some
     * {@code long} values, {@code doubleToRawLongBits(longBitsToDouble(start))} may <i>not</i>
     * equal {@code start}. Moreover, which particular bit patterns represent signaling NaNs is
     * platform dependent; although all NaN bit patterns, quiet or signaling, must be in the NaN
     * range identified above.
     * 
     * @param bits any {@code long} integer.
     * @return the {@code double} floating-point value with the same bit pattern.
     */
    public static double longBitsToDouble(long bits) {
        return 0;
    }

    /**
     * Returns a hexadecimal string representation of the {@code double} argument. All characters
     * mentioned below are ASCII characters.
     * <ul>
     * <li>If the argument is NaN, the result is the string "{@code NaN}".
     * <li>Otherwise, the result is a string that represents the sign and magnitude of the argument.
     * If the sign is negative, the first character of the result is '{@code -}' (
     * {@code '\u005Cu002D'}); if the sign is positive, no sign character appears in the result. As
     * for the magnitude <i>m</i>:
     * <ul>
     * <li>If <i>m</i> is infinity, it is represented by the string {@code "Infinity"}; thus,
     * positive infinity produces the result {@code "Infinity"} and negative infinity produces the
     * result {@code "-Infinity"}.
     * <li>If <i>m</i> is zero, it is represented by the string {@code "0x0.0p0"}; thus, negative
     * zero produces the result {@code "-0x0.0p0"} and positive zero produces the result
     * {@code "0x0.0p0"}.
     * <li>If <i>m</i> is a {@code double} value with a normalized representation, substrings are
     * used to represent the significand and exponent fields. The significand is represented by the
     * characters {@code "0x1."} followed by a lowercase hexadecimal representation of the rest of
     * the significand as a fraction. Trailing zeros in the hexadecimal representation are removed
     * unless all the digits are zero, in which case a single zero is used. Next, the exponent is
     * represented by {@code "p"} followed by a decimal string of the unbiased exponent as if
     * produced by a call to {@link Integer#toString(int) Integer.toString} on the exponent value.
     * <li>If <i>m</i> is a {@code double} value with a subnormal representation, the significand is
     * represented by the characters {@code "0x0."} followed by a hexadecimal representation of the
     * rest of the significand as a fraction. Trailing zeros in the hexadecimal representation are
     * removed. Next, the exponent is represented by {@code "p-1022"}. Note that there must be at
     * least one nonzero digit in a subnormal significand.
     * </ul>
     * </ul>
     * <table border> <caption>Examples</caption>
     * <tr>
     * <th>Floating-point Value</th>
     * <th>Hexadecimal String</th>
     * <tr>
     * <td>{@code 1.0}</td>
     * <td>{@code 0x1.0p0}</td>
     * <tr>
     * <td>{@code -1.0}</td>
     * <td>{@code -0x1.0p0}</td>
     * <tr>
     * <td>{@code 2.0}</td>
     * <td>{@code 0x1.0p1}</td>
     * <tr>
     * <td>{@code 3.0}</td>
     * <td>{@code 0x1.8p1}</td>
     * <tr>
     * <td>{@code 0.5}</td>
     * <td>{@code 0x1.0p-1}</td>
     * <tr>
     * <td>{@code 0.25}</td>
     * <td>{@code 0x1.0p-2}</td>
     * <tr>
     * <td>{@code Double.MAX_VALUE}</td>
     * <td>{@code 0x1.fffffffffffffp1023}</td>
     * <tr>
     * <td>{@code Minimum Normal Value}</td>
     * <td>{@code 0x1.0p-1022}</td>
     * <tr>
     * <td>{@code Maximum Subnormal Value}</td>
     * <td>{@code 0x0.fffffffffffffp-1022}</td>
     * <tr>
     * <td>{@code Double.MIN_VALUE}</td>
     * <td>{@code 0x0.0000000000001p-1022}</td>
     * </table>
     * 
     * @param d the {@code double} to be converted.
     * @return a hex string representation of the argument.
     * @since 1.5
     * @author Joseph D. Darcy
     */
    public static String toHexString(double d) {
        /*
         * Modeled after the "a" conversion specifier in C99, section 7.19.6.1; however, the output
         * of this method is more tightly specified.
         */
        if (!isFinite(d))
            // For infinity and NaN, use the decimal output.
            return Double.toString(d);
        else {
            // Initialized to maximum size of output.
            StringBuilder answer = new StringBuilder(24);

            if (Math.copySign(1.0, d) == -1.0) // value is negative,
                answer.append("-"); // so append sign info

            answer.append("0x");

            d = Math.abs(d);

            if (d == 0.0) {
                answer.append("0.0p0");
            } else {
                boolean subnormal = (d < 2.2250738585072014E-308);

                // Isolate significand bits and OR in a high-order bit
                // so that the string representation has a known
                // length.
                long signifBits = (Double.doubleToLongBits(d) & 0x000FFFFFFFFFFFFFL) | 0x1000000000000000L;

                // Subnormal values have a 0 implicit bit; normal
                // values have a 1 implicit bit.
                answer.append(subnormal ? "0." : "1.");

                // Isolate the low-order 13 digits of the hex
                // representation. If all the digits are zero,
                // replace with a single 0; otherwise, remove all
                // trailing zeros.
                String signif = Long.toHexString(signifBits).substring(3, 16);
                answer.append(signif.equals("0000000000000") ? // 13 zeros
                        "0" : signif.replaceFirst("0{1,12}$", ""));

                answer.append('p');
                // If the value is subnormal, use the E_min exponent
                // value for double; otherwise, extract and report d's
                // exponent (the representation of a subnormal uses
                // E_min -1).
                answer.append(subnormal ? -1022 : Math.getExponent(d));
            }
            return answer.toString();
        }
    }

    /**
     * Returns a string representation of the {@code double} argument. All characters mentioned
     * below are ASCII characters.
     * <ul>
     * <li>If the argument is NaN, the result is the string "{@code NaN}".
     * <li>Otherwise, the result is a string that represents the sign and magnitude (absolute value)
     * of the argument. If the sign is negative, the first character of the result is '{@code -}' (
     * <code>'&#92;u002D'</code>); if the sign is positive, no sign character appears in the result.
     * As for the magnitude <i>m</i>:
     * <ul>
     * <li>If <i>m</i> is infinity, it is represented by the characters {@code "Infinity"}; thus,
     * positive infinity produces the result {@code "Infinity"} and negative infinity produces the
     * result {@code "-Infinity"}.
     * <li>If <i>m</i> is zero, it is represented by the characters {@code "0.0"}; thus, negative
     * zero produces the result {@code "-0.0"} and positive zero produces the result {@code "0.0"}.
     * <li>If <i>m</i> is greater than or equal to 10<sup>-3</sup> but less than 10<sup>7</sup>,
     * then it is represented as the integer part of <i>m</i>, in decimal form with no leading
     * zeroes, followed by '{@code .}' (<code>'&#92;u002E'</code>), followed by one or more decimal
     * digits representing the fractional part of <i>m</i>.
     * <li>If <i>m</i> is less than 10<sup>-3</sup> or greater than or equal to 10<sup>7</sup>, then
     * it is represented in so-called "computerized scientific notation." Let <i>n</i> be the unique
     * integer such that 10<sup><i>n</i></sup> &le; <i>m</i> {@literal <} 10<sup><i>n</i>+1</sup>;
     * then let <i>a</i> be the mathematically exact quotient of <i>m</i> and 10<sup><i>n</i></sup>
     * so that 1 &le; <i>a</i> {@literal <} 10. The magnitude is then represented as the integer
     * part of <i>a</i>, as a single decimal digit, followed by '{@code .}' (
     * <code>'&#92;u002E'</code>), followed by decimal digits representing the fractional part of
     * <i>a</i>, followed by the letter '{@code E}' (<code>'&#92;u0045'</code>), followed by a
     * representation of <i>n</i> as a decimal integer, as produced by the method
     * {@link Integer#toString(int)}.
     * </ul>
     * </ul>
     * How many digits must be printed for the fractional part of <i>m</i> or <i>a</i>? There must
     * be at least one digit to represent the fractional part, and beyond that as many, but only as
     * many, more digits as are needed to uniquely distinguish the argument value from adjacent
     * values of type {@code double}. That is, suppose that <i>x</i> is the exact mathematical value
     * represented by the decimal representation produced by this method for a finite nonzero
     * argument <i>d</i>. Then <i>d</i> must be the {@code double} value nearest to <i>x</i>; or if
     * two {@code double} values are equally close to <i>x</i>, then <i>d</i> must be one of them
     * and the least significant bit of the significand of <i>d</i> must be {@code 0}.
     * <p>
     * To create localized string representations of a floating-point value, use subclasses of
     * {@link java.text.NumberFormat}.
     * 
     * @param d the {@code double} to be converted.
     * @return a string representation of the argument.
     */
    public static String toString(double d) {
        return valueOf(d).toString();
    }

    /**
     * Returns a {@code Double} object holding the {@code double} value represented by the argument
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
     * regarded as representing an exact decimal value in the usual "computerized scientific
     * notation" or as an exact hexadecimal value; this exact numerical value is then conceptually
     * converted to an "infinitely precise" binary value that is then rounded to type {@code double}
     * by the usual round-to-nearest rule of IEEE 754 floating-point arithmetic, which includes
     * preserving the sign of a zero value. Note that the round-to-nearest rule also implies
     * overflow and underflow behaviour; if the exact value of {@code s} is large enough in
     * magnitude (greater than or equal to ({@link #MAX_VALUE} + {@link Math#ulp(double)
     * ulp(MAX_VALUE)}/2), rounding to {@code double} will result in an infinity and if the exact
     * value of {@code s} is small enough in magnitude (less than or equal to {@link #MIN_VALUE}/2),
     * rounding to float will result in a zero. Finally, after rounding a {@code Double} object
     * representing this {@code double} value is returned.
     * <p>
     * To interpret localized string representations of a floating-point value, use subclasses of
     * {@link java.text.NumberFormat}.
     * <p>
     * Note that trailing format specifiers, specifiers that determine the type of a floating-point
     * literal ({@code 1.0f} is a {@code float} value; {@code 1.0d} is a {@code double} value), do
     * <em>not</em> influence the results of this method. In other words, the numerical value of the
     * input string is converted directly to the target floating-point type. The two-step sequence
     * of conversions, string to {@code float} followed by {@code float} to {@code double}, is
     * <em>not</em> equivalent to converting a string directly to {@code double}. For example, the
     * {@code float} literal {@code 0.1f} is equal to the {@code double} value
     * {@code 0.10000000149011612}; the {@code float} literal {@code 0.1f} represents a different
     * numerical value than the {@code double} literal {@code 0.1}. (The numerical value 0.1 cannot
     * be exactly represented in a binary floating-point number.)
     * <p>
     * To avoid calling this method on an invalid string and having a {@code NumberFormatException}
     * be thrown, the regular expression below can be used to screen the input string: <code>
     * <pre>
     *  final String Digits     = "(\\p{Digit}+)";
     *  final String HexDigits  = "(\\p{XDigit}+)";
     *  // an exponent is 'e' or 'E' followed by an optionally
     *  // signed decimal integer.
     *  final String Exp        = "[eE][+-]?"+Digits;
     *  final String fpRegex    =
     *      ("[\\x00-\\x20]*"+  // Optional leading "whitespace"
     *       "[+-]?(" + // Optional sign character
     *       "NaN|" +           // "NaN" string
     *       "Infinity|" +      // "Infinity" string
     * 
     *       // A decimal floating-point string representing a finite positive
     *       // number without a leading sign has at most five basic pieces:
     *       // Digits . Digits ExponentPart FloatTypeSuffix
     *       //
     *       // Since this method allows integer-only strings as input
     *       // in addition to strings of floating-point literals, the
     *       // two sub-patterns below are simplifications of the grammar
     *       // productions from section 3.10.2 of
     *       // <cite>The Java&trade; Language Specification</cite>.
     * 
     *       // Digits ._opt Digits_opt ExponentPart_opt FloatTypeSuffix_opt
     *       "((("+Digits+"(\\.)?("+Digits+"?)("+Exp+")?)|"+
     * 
     *       // . Digits ExponentPart_opt FloatTypeSuffix_opt
     *       "(\\.("+Digits+")("+Exp+")?)|"+
     * 
     *       // Hexadecimal strings
     *       "((" +
     *        // 0[xX] HexDigits ._opt BinaryExponent FloatTypeSuffix_opt
     *        "(0[xX]" + HexDigits + "(\\.)?)|" +
     * 
     *        // 0[xX] HexDigits_opt . HexDigits BinaryExponent FloatTypeSuffix_opt
     *        "(0[xX]" + HexDigits + "?(\\.)" + HexDigits + ")" +
     * 
     *        ")[pP][+-]?" + Digits + "))" +
     *       "[fFdD]?))" +
     *       "[\\x00-\\x20]*");// Optional trailing "whitespace"
     * 
     *  if (Pattern.matches(fpRegex, myString))
     *      Double.valueOf(myString); // Will not throw NumberFormatException
     *  else {
     *      // Perform suitable alternative action
     *  }
     * </pre> </code>
     * 
     * @param value the string to be parsed.
     * @return a {@code Double} object holding the value represented by the {@code String} argument.
     * @throws NumberFormatException if the string does not contain a parsable number.
     */
    public static Double valueOf(String value) throws NumberFormatException {
        return valueOf(Global.parseFloat(value));
    }

    /**
     * Returns a {@code Double} instance representing the specified {@code double} value. If a new
     * {@code Double} instance is not required, this method should generally be used in preference
     * to the constructor {@link #Double(double)}, as this method is likely to yield significantly
     * better space and time performance by caching frequently requested values.
     * 
     * @param value a double value.
     * @return a {@code Double} instance representing {@code d}.
     * @since 1.5
     */
    public static Double valueOf(double value) {
        return (Double) (Object) new JSDouble(value);
    }

    /**
     * @version 2013/04/16 23:01:24
     */
    @JavaAPIProvider(double.class)
    private static class Primitive {
    }
}
