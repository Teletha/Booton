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
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

/**
 * @version 2016/04/07 20:52:46
 */
class Signature {

    /** The declaring class. */
    private final GenericDeclaration declaration;

    /** The variable manager. */
    private final Map<String, Variable> variables = new HashMap();

    /** The variable declaring order. */
    final List types = new ArrayList();

    /**
     * 
     */
    Signature(String signatures, GenericDeclaration declaration) {
        this.declaration = declaration;

        if (signatures != null && signatures.length() != 0) {
            for (String signature : signatures.split(" ")) {
                types.add(parseType(signature, declaration));
            }
        }
    }

    /**
     * <p>
     * Parse generics expression.
     * </p>
     * 
     * @param signature
     * @param declaration
     * @return
     */
    private Type parseType(String signature, GenericDeclaration declaration) {
        if (signature.isEmpty()) {
            return Object.class;
        }

        for (int index = 0; index < signature.length(); index++) {
            char c = signature.charAt(index);

            if (c == ':') {
                Variable variable = get(signature.substring(0, index));
                variable.parse(split(signature.substring(index + 1), '&'));

                return variable;
            }

            if (c == '#') {
                return get(signature.substring(0, index));
            }

            if (c == '[') {
                return new Array(signature.substring(1), declaration);
            }

            if (c == '<') {
                return new Parameterized(signature
                        .substring(0, index), split(signature.substring(index + 1, signature.length() - 1), ','), declaration);
            }
        }
        return JSClass.forName(signature);
    }

    /**
     * <p>
     * Retrieve the named {@link TypeVariable} from cache.
     * </p>
     * 
     * @param name
     * @return
     */
    private Variable get(String name) {
        Variable variable = variables.get(name);

        if (variable == null) {
            variable = new Variable(name, declaration);
            variables.put(name, variable);
        }
        return variable;
    }

    /**
     * <p>
     * Split tokens.
     * </p>
     * 
     * @param signature
     * @param separator
     * @return
     */
    private String[] split(String signature, char separator) {
        int start = 0;
        int nest = 0;
        List<String> values = new ArrayList();

        for (int i = 0; i < signature.length(); i++) {
            char c = signature.charAt(i);

            if (c == '<') {
                nest++;
            } else if (c == '>') {
                nest--;
            } else if (c == separator && nest == 0) {
                values.add(signature.substring(start, i));

                start = i + 1;
            }
        }
        values.add(signature.substring(start));

        return values.toArray(new String[values.size()]);
    }

    /**
     * <p>
     * Helper method to convert {@link Type} to {@link Class}.
     * </p>
     * 
     * @param types
     * @return
     */
    static Class convert(Type type) {
        if (type instanceof Variable) {
            return convert(((Variable) type).bounds[0]);
        }

        if (type instanceof Parameterized) {
            return (Class) ((ParameterizedType) type).getRawType();
        }

        if (type instanceof Array) {
            return (Class) (Object) ((JSClass) (Object) convert(((GenericArrayType) type).getGenericComponentType())).getArrayClass();
        }
        return (Class) type;
    }

    /**
     * @version 2016/04/07 20:52:24
     */
    private class Variable<D extends GenericDeclaration> implements TypeVariable<D> {

        /** The variable name. */
        private final String name;

        /** The declaring class, constructor or method. */
        private final D declaration;

        /** The upper bound of types. */
        private Type[] bounds;

        /**
         * 
         */
        private Variable(String name, D declaration) {
            this.name = name;
            this.declaration = declaration;
        }

        private void parse(String[] uppers) {
            Type[] bounds = new Type[uppers.length];

            for (int i = 0; i < bounds.length; i++) {
                bounds[i] = parseType(uppers[i], declaration);
            }
            this.bounds = bounds;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Type[] getBounds() {
            return bounds;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public D getGenericDeclaration() {
            return declaration;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String getName() {
            return name;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public <T extends Annotation> T getAnnotation(Class<T> annotationClass) {
            // If this exception will be thrown, it is bug of this program. So we must rethrow the
            // wrapped error in here.
            throw new Error();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Annotation[] getAnnotations() {
            // If this exception will be thrown, it is bug of this program. So we must rethrow the
            // wrapped error in here.
            throw new Error();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public <T extends Annotation> T[] getAnnotationsByType(Class<T> annotationClass) {
            // If this exception will be thrown, it is bug of this program. So we must rethrow the
            // wrapped error in here.
            throw new Error();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public <T extends Annotation> T getDeclaredAnnotation(Class<T> annotationClass) {
            // If this exception will be thrown, it is bug of this program. So we must rethrow the
            // wrapped error in here.
            throw new Error();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public <T extends Annotation> T[] getDeclaredAnnotationsByType(Class<T> annotationClass) {
            // If this exception will be thrown, it is bug of this program. So we must rethrow the
            // wrapped error in here.
            throw new Error();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Annotation[] getDeclaredAnnotations() {
            // If this exception will be thrown, it is bug of this program. So we must rethrow the
            // wrapped error in here.
            throw new Error();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public AnnotatedType[] getAnnotatedBounds() {
            // If this exception will be thrown, it is bug of this program. So we must rethrow the
            // wrapped error in here.
            throw new Error();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + getOuterType().hashCode();
            result = prime * result + ((declaration == null) ? 0 : declaration.hashCode());
            result = prime * result + ((name == null) ? 0 : name.hashCode());
            return result;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Variable) {
                Variable other = (Variable) obj;

                return name.equals(other.name) && declaration.equals(other.declaration);
            }
            return false;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String toString() {
            // StringJoiner joiner = new StringJoiner(" & ", " extends ", "");
            //
            // for (Type bound : bounds) {
            // joiner.add(bound.toString());
            // }
            return name;
        }

        private Signature getOuterType() {
            return Signature.this;
        }
    }

    /**
     * @version 2013/09/08 9:57:17
     */
    private class Parameterized implements ParameterizedType {

        /** The arugument types. */
        private final List<Type> arguments;

        /** The raw type. */
        private final Type raw;

        /** The owner type. */
        private final Type owner;

        /**
         * @param arguments
         * @param raw
         * @param owner
         */
        private Parameterized(String raw, String[] parameters, GenericDeclaration declaration) {
            this.raw = JSClass.forName(raw);
            this.owner = null;
            this.arguments = new ArrayList();

            for (int i = 0; i < parameters.length; i++) {
                arguments.add(parseType(parameters[i], declaration));
            }
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Type[] getActualTypeArguments() {
            return arguments.toArray(new Type[arguments.size()]);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Type getRawType() {
            return raw;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Type getOwnerType() {
            return owner;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String toString() {
            StringJoiner joiner = new StringJoiner(", ", "<", ">");

            for (Type type : arguments) {
                joiner.add(type.toString());
            }
            return raw.getTypeName() + joiner.toString();
        }
    }

    /**
     * @version 2013/09/10 15:21:33
     */
    private class Array implements GenericArrayType {

        /** The component type. */
        private final Type component;

        /**
         * @param component
         */
        private Array(String signature, GenericDeclaration declaration) {
            this.component = parseType(signature, declaration);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Type getGenericComponentType() {
            return component;
        }
    }
}
