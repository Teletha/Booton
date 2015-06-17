/*
 * Copyright (C) 2015 Nameless Production Committee
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://opensource.org/licenses/mit-license.php
 */
package booton.translator;

/**
 * @version 2013/08/23 23:35:09
 */
abstract class Operand {

    /** The flag for operand duplication. */
    boolean duplicated = false;

    /**
     * @param type
     * @return
     */
    final Operand cast(Class type) {
        Operand operand = castActual(type);
        operand.duplicated = duplicated;

        return operand;
    }

    /**
     * <p>
     * Internal API.
     * </p>
     * 
     * @param type
     * @return
     */
    Operand castActual(Class type) {
        return this;
    }

    /**
     * <p>
     * Invert operand value if we can.
     * </p>
     * 
     * @return A inverted operand.
     */
    Operand invert() {
        return this;
    }

    int computeMultiplicity() {
        return 1;
    }

    /**
     * <p>
     * Enclose this operand.
     * </p>
     * 
     * @return A disclosed operand.
     */
    Operand encolose() {
        return new OperandEnclose(this);
    }

    /**
     * <p>
     * Disclose the outmost parenthesis if we can.
     * </p>
     * 
     * @return A disclosed operand.
     */
    Operand disclose() {
        return this;
    }

    /**
     * <p>
     * Infer the type of this {@link Operand}.
     * </p>
     * 
     * @return
     */
    abstract InferredType infer();

    /**
     * @return
     */
    boolean isLarge() {
        return infer().type() == long.class || infer().type() == double.class;
    }
}
