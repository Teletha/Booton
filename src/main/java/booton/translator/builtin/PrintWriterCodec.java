/*
 * Copyright (C) 2016 Nameless Production Committee
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://opensource.org/licenses/mit-license.php
 */
package booton.translator.builtin;

import java.io.PrintWriter;
import java.io.Writer;

import booton.translator.Javascript;
import booton.translator.Translator;

/**
 * @version 2014/02/06 22:39:51
 */
public class PrintWriterCodec extends Translator<PrintWriter> {

    public String PrintWriter(Writer param0) {
        return "console";
    }

    /**
     * Closes the stream and releases any system resources associated with it. Closing a previously
     * closed stream has no effect.
     *
     * @see #checkError()
     */
    public String close() {
        return "";
    }

    /**
     * Terminates the current line by writing the line separator string. The line separator string
     * is defined by the system property <code>line.separator</code>, and is not necessarily a
     * single newline character (<code>'\n'</code>).
     */
    public String println() {
        return that + ".log()";
    }

    /**
     * Prints a boolean and then terminate the line. This method behaves as though it invokes
     * <code>{@link #print(boolean)}</code> and then <code>{@link #println()}</code>.
     * 
     * @param x The <code>boolean</code> to be printed
     */
    public String println(boolean x) {
        return that + ".log(" + param(0) + ")";
    }

    /**
     * Prints a character and then terminate the line. This method behaves as though it invokes
     * <code>{@link #print(char)}</code> and then <code>{@link #println()}</code>.
     * 
     * @param x The <code>char</code> to be printed.
     */
    public String println(char x) {
        return that + ".log(" + param(0) + ")";
    }

    /**
     * Prints an integer and then terminate the line. This method behaves as though it invokes
     * <code>{@link #print(int)}</code> and then <code>{@link #println()}</code>.
     * 
     * @param x The <code>int</code> to be printed.
     */
    public String println(int x) {
        return that + ".log(" + param(0) + ")";
    }

    /**
     * Prints a long and then terminate the line. This method behaves as though it invokes
     * <code>{@link #print(long)}</code> and then <code>{@link #println()}</code>.
     * 
     * @param x a The <code>long</code> to be printed.
     */
    public String println(long x) {
        return that + ".log(" + param(0) + ")";
    }

    /**
     * Prints a float and then terminate the line. This method behaves as though it invokes
     * <code>{@link #print(float)}</code> and then <code>{@link #println()}</code>.
     * 
     * @param x The <code>float</code> to be printed.
     */
    public String println(float x) {
        return that + ".log(" + param(0) + ")";
    }

    /**
     * Prints a double and then terminate the line. This method behaves as though it invokes
     * <code>{@link #print(double)}</code> and then <code>{@link #println()}</code>.
     * 
     * @param x The <code>double</code> to be printed.
     */
    public String println(double x) {
        return that + ".log(" + param(0) + ")";
    }

    /**
     * Prints an array of characters and then terminate the line. This method behaves as though it
     * invokes <code>{@link #print(char[])}</code> and then <code>{@link #println()}</code>.
     * 
     * @param x an array of chars to print.
     */
    public String println(char x[]) {
        return that + ".log(" + param(0) + ")";
    }

    /**
     * Prints a String and then terminate the line. This method behaves as though it invokes
     * <code>{@link #print(String)}</code> and then <code>{@link #println()}</code>.
     * 
     * @param x The <code>String</code> to be printed.
     */
    public String println(String x) {
        return that + ".log(" + param(0) + ")";
    }

    /**
     * Prints a string. If the argument is <code>null</code> then the string <code>"null"</code> is
     * printed. Otherwise, the string's characters are converted into bytes according to the
     * platform's default character encoding, and these bytes are written in exactly the manner of
     * the <code>{@link #write(int)}</code> method.
     * 
     * @param s The <code>String</code> to be printed
     */
    public String print(String s) {
        return that + ".log(" + param(0) + ")";
    }

    /**
     * Prints an Object and then terminate the line. This method calls at first String.valueOf(x) to
     * get the printed object's string value, then behaves as though it invokes
     * <code>{@link #print(String)}</code> and then <code>{@link #println()}</code>.
     * 
     * @param x The <code>Object</code> to be printed.
     */
    public String println(Object x) {
        return that + ".log(" + Javascript.writeMethodCode(String.class, "valueOf", Object.class, param(0)) + ")";
    }
}
