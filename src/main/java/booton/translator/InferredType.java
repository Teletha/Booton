/*
 * Copyright (C) 2013 Nameless Production Committee
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://opensource.org/licenses/mit-license.php
 */
package booton.translator;

/**
 * @version 2013/08/31 21:55:20
 */
class InferredType {

    /** The actual type. */
    private Class type;

    /**
     * 
     */
    InferredType() {
    }

    /**
     * 
     */
    InferredType(Class type) {
        this.type = type;
    }

    /**
     * <p>
     * Set the inferred type.
     * </p>
     * 
     * @param type
     */
    void type(Class type) {
        this.type = type;
    }

    /**
     * Get the inferred type.
     * 
     * @return
     */
    Class type() {
        return type;
    }
}