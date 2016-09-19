/*
 * Copyright (C) 2016 Nameless Production Committee
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://opensource.org/licenses/mit-license.php
 */
package js.lang.invoke;

import java.lang.invoke.MethodHandleInfo;
import java.lang.invoke.SerializedLambda;
import java.util.Objects;

import booton.translator.JavaAPIProvider;

/**
 * @version 2013/10/26 11:31:59
 */
@JavaAPIProvider(SerializedLambda.class)
class JSSerializedLambda {

    private final Class<?> capturingClass;

    private final String functionalInterfaceClass;

    private final String functionalInterfaceMethodName;

    private final String functionalInterfaceMethodSignature;

    private final String implClass;

    private final String implMethodName;

    private final String implMethodSignature;

    private final int implMethodKind;

    private final String instantiatedMethodType;

    private final Object[] capturedArgs;

    /**
     * Create a {@code SerializedLambda} from the low-level information present at the lambda
     * factory site.
     * 
     * @param capturingClass The class in which the lambda expression appears
     * @param functionalInterfaceClass Name, in slash-delimited form, of static type of the returned
     *            lambda object
     * @param functionalInterfaceMethodName Name of the functional interface method for the present
     *            at the lambda factory site
     * @param functionalInterfaceMethodSignature Signature of the functional interface method
     *            present at the lambda factory site
     * @param implMethodKind Method handle kind for the implementation method
     * @param implClass Name, in slash-delimited form, for the class holding the implementation
     *            method
     * @param implMethodName Name of the implementation method
     * @param implMethodSignature Signature of the implementation method
     * @param instantiatedMethodType The signature of the primary functional interface method after
     *            type variables are substituted with their instantiation from the capture site
     * @param capturedArgs The dynamic arguments to the lambda factory site, which represent
     *            variables captured by the lambda
     */
    public JSSerializedLambda(Class<?> capturingClass, String functionalInterfaceClass, String functionalInterfaceMethodName, String functionalInterfaceMethodSignature, int implMethodKind, String implClass, String implMethodName, String implMethodSignature, String instantiatedMethodType, Object[] capturedArgs) {
        this.capturingClass = capturingClass;
        this.functionalInterfaceClass = functionalInterfaceClass;
        this.functionalInterfaceMethodName = functionalInterfaceMethodName;
        this.functionalInterfaceMethodSignature = functionalInterfaceMethodSignature;
        this.implMethodKind = implMethodKind;
        this.implClass = implClass;
        this.implMethodName = implMethodName;
        this.implMethodSignature = implMethodSignature;
        this.instantiatedMethodType = instantiatedMethodType;
        this.capturedArgs = Objects.requireNonNull(capturedArgs).clone();
    }

    /**
     * Get the name of the class that captured this lambda.
     * 
     * @return the name of the class that captured this lambda
     */
    public String getCapturingClass() {
        return capturingClass.getName().replace('.', '/');
    }

    /**
     * Get the name of the invoked type to which this lambda has been converted
     * 
     * @return the name of the functional interface class to which this lambda has been converted
     */
    public String getFunctionalInterfaceClass() {
        return functionalInterfaceClass;
    }

    /**
     * Get the name of the primary method for the functional interface to which this lambda has been
     * converted.
     * 
     * @return the name of the primary methods of the functional interface
     */
    public String getFunctionalInterfaceMethodName() {
        return functionalInterfaceMethodName;
    }

    /**
     * Get the signature of the primary method for the functional interface to which this lambda has
     * been converted.
     * 
     * @return the signature of the primary method of the functional interface
     */
    public String getFunctionalInterfaceMethodSignature() {
        return functionalInterfaceMethodSignature;
    }

    /**
     * Get the name of the class containing the implementation method.
     * 
     * @return the name of the class containing the implementation method
     */
    public String getImplClass() {
        return implClass;
    }

    /**
     * Get the name of the implementation method.
     * 
     * @return the name of the implementation method
     */
    public String getImplMethodName() {
        return implMethodName;
    }

    /**
     * Get the signature of the implementation method.
     * 
     * @return the signature of the implementation method
     */
    public String getImplMethodSignature() {
        return implMethodSignature;
    }

    /**
     * Get the method handle kind (see {@link MethodHandleInfo}) of the implementation method.
     * 
     * @return the method handle kind of the implementation method
     */
    public int getImplMethodKind() {
        return implMethodKind;
    }

    /**
     * Get the signature of the primary functional interface method after type variables are
     * substituted with their instantiation from the capture site.
     * 
     * @return the signature of the primary functional interface method after type variable
     *         processing
     */
    public final String getInstantiatedMethodType() {
        return instantiatedMethodType;
    }

    /**
     * Get the count of dynamic arguments to the lambda capture site.
     * 
     * @return the count of dynamic arguments to the lambda capture site
     */
    public int getCapturedArgCount() {
        return capturedArgs.length;
    }

    /**
     * Get a dynamic argument to the lambda capture site.
     * 
     * @param i the argument to capture
     * @return a dynamic argument to the lambda capture site
     */
    public Object getCapturedArg(int i) {
        return capturedArgs[i];
    }
}
