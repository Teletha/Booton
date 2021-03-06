/*
 * Copyright (C) 2016 Nameless Production Committee
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://opensource.org/licenses/mit-license.php
 */
package js.lang.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Executable;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.GenericSignatureFormatError;
import java.lang.reflect.MalformedParameterizedTypeException;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import booton.translator.JavaAPIProvider;
import js.lang.NativeArray;
import js.lang.NativeObject;

/**
 * @version 2015/08/08 14:28:02
 */
@JavaAPIProvider(Executable.class)
class Parameterizable extends JSAccessibleObject implements GenericDeclaration {

    /** The annotation definition for parameters. */
    private final NativeObject annotations;

    /** The cache for parameter {@link Annotation}. */
    private NativeArray<List<Annotation>> parameterAnnotations;

    /** The cache for parameter {@link Class}. */
    private List<Class> parameters;

    /** The cache for parameter {@link Type}. */
    private List<Type> parameterTypes;

    /** The cache for exception {@link Class}. */
    private List<Class> exceptions;

    /** The cache for exception {@link Type}. */
    private List<Type> exceptionTypes;

    /**
     * @param name
     * @param nameJS
     * @param owner
     * @param metadata
     * @param indexForAnnotation
     */
    public Parameterizable(String name, String nameJS, Class owner, NativeArray<?> metadata, int indexForAnnotation) {
        super(name, nameJS, owner, metadata, indexForAnnotation);

        annotations = metadata.get(indexForAnnotation + 1, new NativeObject());
    }

    /**
     * Returns an array of {@code Class} objects that represent the formal parameter types, in
     * declaration order, of the method represented by this {@code Method} object. Returns an array
     * of length 0 if the underlying method takes no parameters.
     * 
     * @return the parameter types for the method this object represents
     */
    public final Class<?>[] getParameterTypes() {
        if (parameters == null) {
            parameters = convert(getGenericParameterTypes());
        }
        return parameters.toArray(new Class[parameters.size()]);
    }

    /**
     * Returns the number of formal parameters (whether explicitly declared or implicitly declared
     * or neither) for the executable represented by this object.
     *
     * @since 1.8
     * @return The number of formal parameters for the executable this object represents
     */
    public int getParameterCount() {
        return getParameterTypes().length;
    }

    /**
     * Returns an array of {@code Type} objects that represent the formal parameter types, in
     * declaration order, of the method represented by this {@code Method} object. Returns an array
     * of length 0 if the underlying method takes no parameters.
     * <p>
     * If a formal parameter type is a parameterized type, the {@code Type} object returned for it
     * must accurately reflect the actual type parameters used in the source code.
     * <p>
     * If a formal parameter type is a type variable or a parameterized type, it is created.
     * Otherwise, it is resolved.
     * 
     * @return an array of Types that represent the formal parameter types of the underlying method,
     *         in declaration order
     * @throws GenericSignatureFormatError if the generic method signature does not conform to the
     *             format specified in <cite>The Java&trade; Virtual Machine Specification</cite>
     * @throws TypeNotPresentException if any of the parameter types of the underlying method refers
     *             to a non-existent type declaration
     * @throws MalformedParameterizedTypeException if any of the underlying method's parameter types
     *             refer to a parameterized type that cannot be instantiated for any reason
     * @since 1.5
     */
    public final Type[] getGenericParameterTypes() {
        if (parameterTypes == null) {
            parameterTypes = new Signature(metadata.get(2, ""), owner).types;
            metadata.deleteProperty(2);
        }
        return parameterTypes.toArray(new Type[parameterTypes.size()]);
    }

    /**
     * Returns an array of {@code Class} objects that represent the types of exceptions declared to
     * be thrown by the underlying constructor represented by this {@code Constructor} object.
     * Returns an array of length 0 if the constructor declares no exceptions in its {@code throws}
     * clause.
     * 
     * @return the exception types declared as being thrown by the constructor this object
     *         represents
     */
    public final Class<?>[] getExceptionTypes() {
        if (exceptions == null) {
            exceptions = convert(getGenericExceptionTypes());
        }
        return exceptions.toArray(new Class[exceptions.size()]);
    }

    /**
     * Returns an array of {@code Type} objects that represent the exceptions declared to be thrown
     * by this {@code Constructor} object. Returns an array of length 0 if the underlying method
     * declares no exceptions in its {@code throws} clause.
     * <p>
     * If an exception type is a type variable or a parameterized type, it is created. Otherwise, it
     * is resolved.
     * 
     * @return an array of Types that represent the exception types thrown by the underlying method
     * @throws GenericSignatureFormatError if the generic method signature does not conform to the
     *             format specified in <cite>The Java&trade; Virtual Machine Specification</cite>
     * @throws TypeNotPresentException if the underlying method's {@code throws} clause refers to a
     *             non-existent type declaration
     * @throws MalformedParameterizedTypeException if the underlying method's {@code throws} clause
     *             refers to a parameterized type that cannot be instantiated for any reason
     * @since 1.5
     */
    public final Type[] getGenericExceptionTypes() {
        if (exceptionTypes == null) {
            exceptionTypes = new Signature(metadata.get(3, ""), owner).types;
            metadata.deleteProperty(3);
        }
        return exceptionTypes.toArray(new Type[exceptionTypes.size()]);
    }

    /**
     * Returns an array of arrays that represent the annotations on the formal parameters, in
     * declaration order, of the method represented by this {@code Method} object. (Returns an array
     * of length zero if the underlying method is parameterless. If the method has one or more
     * parameters, a nested array of length zero is returned for each parameter with no
     * annotations.) The annotation objects contained in the returned arrays are serializable. The
     * caller of this method is free to modify the returned arrays; it will have no effect on the
     * arrays returned to other callers.
     * 
     * @return an array of arrays that represent the annotations on the formal parameters, in
     *         declaration order, of the method represented by this Method object
     * @since 1.5
     */
    public final Annotation[][] getParameterAnnotations() {
        if (parameterAnnotations == null) {
            parameterAnnotations = new NativeArray(getParameterTypes().length);

            for (String index : annotations.keys()) {
                List<Annotation> container = new ArrayList();
                NativeObject definition = annotations.getProperty(index, new NativeObject());

                for (String name : definition.keys()) {
                    Class type = JSClass.forName(name);

                    container.add((Annotation) Proxy
                            .newProxyInstance(null, new Class[] {type}, new AnnotationProxy(type, definition.getProperty(name))));
                }
                parameterAnnotations.setProperty(index, container);
            }
        }

        Annotation[][] annotations = new Annotation[parameterAnnotations.length()][];

        for (int i = 0; i < annotations.length; i++) {
            List<Annotation> container = parameterAnnotations.get(i);

            if (container == null) {
                annotations[i] = new Annotation[0];
            } else {
                annotations[i] = container.toArray(new Annotation[0]);
            }
        }
        return annotations;
    }

    boolean equalParamTypes(Class<?>[] params1, Class<?>[] params2) {
        if (params1.length == params2.length) {
            for (int i = 0; i < params1.length; i++) {
                if (params1[i] != params2[i]) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
