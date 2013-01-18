/*
 * Copyright (C) 2013 Nameless Production Committee
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://opensource.org/licenses/mit-license.php
 */
package booton.translator.js;

import java.lang.reflect.Constructor;

/**
 * <p>
 * {@link Constructor} representation in Javascript runtime. This class doesn't provide all
 * functionalities.
 * </p>
 * 
 * @version 2013/01/17 20:45:34
 */
public class JSConstructor<T> extends JSAnnotatedElement {

    /** The declared class definition in runtime. */
    private NativeObject clazz;

    /** The constructor function in runtime. */
    private final NativeFunction function;

    /**
     * <p>
     * Create native constructor.
     * </p>
     * 
     * @param name
     * @param clazz
     * @param function
     * @param annotations
     */
    JSConstructor(String name, NativeObject clazz, NativeFunction function, NativeArray<NativeArray> annotations) {
        super(name, annotations);

        this.clazz = clazz;
        this.function = function;
    }

    /**
     * <p>
     * Uses the constructor represented by this Constructor object to create and initialize a new
     * instance of the constructor's declaring class, with the specified initialization parameters.
     * Individual parameters are automatically unwrapped to match primitive formal parameters, and
     * both primitive and reference parameters are subject to method invocation conversions as
     * necessary.
     * </p>
     * 
     * @param parameters Array of objects to be passed as arguments to the constructor call; values
     *            of primitive types are wrapped in a wrapper object of the appropriate type (e.g. a
     *            float in a Float).
     * @return A new object created by calling the constructor this object represents.
     */
    public T newInstance(Object... parameters) {
        // create new instance
        Object instance = clazz.create();

        // invoke function
        function.apply(instance, parameters);

        // API definition
        return (T) instance;
    }
}
