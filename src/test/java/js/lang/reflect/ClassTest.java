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

import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;

import booton.soeur.ScriptRunner;
import booton.translator.JavascriptNative;
import booton.translator.JavascriptNativeProperty;

/**
 * @version 2014/09/09 22:32:12
 */
@RunWith(ScriptRunner.class)
public class ClassTest {

    private static final Cloneable anonymous = new Cloneable() {
    };

    private static final Class anonymousClass = anonymous.getClass();

    @Test
    public void modifierPrivate() throws Exception {
        int modifier = Private.class.getModifiers();
        assert Modifier.isPrivate(modifier);
    }

    @Test
    public void modifierPackage() throws Exception {
        int modifier = Package.class.getModifiers();
        assert !Modifier.isPrivate(modifier);
        assert !Modifier.isProtected(modifier);
        assert !Modifier.isPublic(modifier);
    }

    @Test
    public void modifierProtected() throws Exception {
        int modifier = Protected.class.getModifiers();
        assert Modifier.isProtected(modifier);
    }

    @Test
    public void modifierPublic() throws Exception {
        int modifier = Public.class.getModifiers();
        assert Modifier.isPublic(modifier);
    }

    @Test
    public void modifierAbstract() throws Exception {
        assert Modifier.isAbstract(Package.class.getModifiers());
        assert !Modifier.isAbstract(Public.class.getModifiers());
    }

    @Test
    public void modifierInterface() throws Exception {
        assert Modifier.isInterface(Protected.class.getModifiers());
        assert !Modifier.isInterface(Public.class.getModifiers());
    }

    /**
     * @version 2013/08/03 0:25:14
     */
    private static class Private {
    }

    /**
     * @version 2013/08/03 0:25:14
     */
    static abstract class Package {
    }

    /**
     * @version 2013/08/03 0:25:14
     */
    protected static interface Protected {
    }

    /**
     * @version 2013/08/03 0:25:14
     */
    public static class Public {
    }

    @Test
    public void getSuperclass() throws Exception {
        assert ExtendedClass.class.getSuperclass() == SuperClass.class;
        assert SuperClass.class.getSuperclass() == Object.class;
        assert Object.class.getSuperclass() == null;

        assert Interface.class.getSuperclass() == null;
        assert ExtendedInterface.class.getSuperclass() == null;
        assert int.class.getSuperclass() == null;
    }

    @Test
    public void getGenericSuperclass() throws Exception {
        assert ExtendedClass.class.getGenericSuperclass() == SuperClass.class;
        assert SuperClass.class.getGenericSuperclass() == Object.class;
        assert Object.class.getGenericSuperclass() == null;

        assert Interface.class.getGenericSuperclass() == null;
        assert ExtendedInterface.class.getGenericSuperclass() == null;
        assert int.class.getGenericSuperclass() == null;
    }

    @Test
    public void getInterfaces() throws Exception {
        assert ExtendedClass.class.getInterfaces().length == 0;
        assert SuperClass.class.getInterfaces().length == 0;
        assert Object.class.getInterfaces().length == 0;
        assert Interface.class.getInterfaces().length == 0;

        Class[] interfaces = ExtendedInterface.class.getInterfaces();
        assert interfaces.length == 1;
        assert interfaces[0] == Interface.class;

        interfaces = ImplementdClass.class.getInterfaces();
        assert interfaces.length == 1;
        assert interfaces[0] == ExtendedInterface.class;

        interfaces = ImplementdExtendedClass.class.getInterfaces();
        assert interfaces.length == 1;
        assert interfaces[0] == ExtendedInterface.class;
    }

    @Test
    public void getGenericInterfaces() throws Exception {
        assert ExtendedClass.class.getGenericInterfaces().length == 0;
        assert SuperClass.class.getGenericInterfaces().length == 0;
        assert Object.class.getGenericInterfaces().length == 0;
        assert Interface.class.getGenericInterfaces().length == 0;

        Type[] interfaces = ExtendedInterface.class.getGenericInterfaces();
        assert interfaces.length == 1;
        assert interfaces[0] == Interface.class;

        interfaces = ImplementdClass.class.getGenericInterfaces();
        assert interfaces.length == 1;
        assert interfaces[0] == ExtendedInterface.class;

        interfaces = ImplementdExtendedClass.class.getGenericInterfaces();
        assert interfaces.length == 1;
        assert interfaces[0] == ExtendedInterface.class;
    }

    /**
     * @version 2013/09/03 19:53:20
     */
    @SuppressWarnings("unused")
    private static interface Interface {

        int interfaceStaticField = 10;

        /**
         * 
         */
        void interfaceMethod();
    }

    /**
     * @version 2013/09/03 20:10:51
     */
    @SuppressWarnings("unused")
    private static interface ExtendedInterface extends Interface {

        int extendedInterfaceStaticField = 100;

        /**
         * 
         */
        void extendedInterfaceMethod();
    }

    /**
     * @version 2013/09/03 21:44:04
     */
    private static class SuperClass {
    }

    /**
     * @version 2013/09/03 21:44:08
     */
    private static class ExtendedClass extends SuperClass {
    }

    /**
     * @version 2013/09/03 21:40:21
     */
    private static class ImplementdClass implements ExtendedInterface {

        /**
         * {@inheritDoc}
         */
        @Override
        public void interfaceMethod() {
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void extendedInterfaceMethod() {
        }
    }

    /**
     * @version 2013/09/03 21:40:21
     */
    private static class ImplementdExtendedClass extends ExtendedClass implements ExtendedInterface {

        /**
         * {@inheritDoc}
         */
        @Override
        public void interfaceMethod() {
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void extendedInterfaceMethod() {
        }
    }

    @Test
    public void getMethod() throws Exception {
        Method method = Methods.class.getMethod("publicMethod");
        assert method != null;

        method = Methods.class.getMethod("publicMethod", (Class[]) null);
        assert method != null;
    }

    @Test
    public void getMethodForAlias() throws Exception {
        Method method = Methods.class.getMethod("getClass");
        assert method != null;
    }

    @Test(expected = NullPointerException.class)
    public void getMethodNullName() throws Exception {
        Methods.class.getMethod(null);
    }

    @Test(expected = NoSuchMethodException.class)
    public void getIvalidMethod() throws Exception {
        Methods.class.getMethod("protectedMethod");
    }

    @Test
    public void getMethods() throws Exception {
        Method[] methods = Methods.class.getMethods();
        assert methods != null;
        assert methods.length == 10;

        // extends
        methods = ExtendedMethods.class.getMethods();
        assert methods != null;
        assert methods.length == 12;

        // check defensive copy
        methods[0] = methods[1];
        assert methods[0] == methods[1];

        methods = ExtendedMethods.class.getMethods();
        assert methods[0] != methods[1];

        // implements
        methods = ImplementdClass.class.getMethods();
        assert methods != null;
        assert methods.length == 11;

        // interface
        methods = Interface.class.getMethods();
        assert methods != null;
        assert methods.length == 1;

        // extended interface
        methods = ExtendedInterface.class.getMethods();
        assert methods != null;
        assert methods.length == 2;
    }

    @Test
    public void getDeclaredMethod() throws Exception {
        Method method = Methods.class.getDeclaredMethod("protectedMethod");
        assert method != null;

        method = Methods.class.getDeclaredMethod("packageMethod");
        assert method != null;

        method = Methods.class.getDeclaredMethod("packageMethod", (Class[]) null);
        assert method != null;

        method = Methods.class.getDeclaredMethod("privateMethod", int.class);
        assert method != null;
    }

    @Test(expected = NullPointerException.class)
    public void getDeclaredMethodNullName() throws Exception {
        Methods.class.getDeclaredMethod(null);
    }

    @Test(expected = NoSuchMethodException.class)
    public void getDeclaredInvalidMethod() throws Exception {
        Methods.class.getDeclaredMethod("invalid");
    }

    @Test
    public void getDeclaredMethods() throws Exception {
        Method[] methods = Methods.class.getDeclaredMethods();
        assert methods != null;
        assert methods.length == 4;

        // extends
        methods = ExtendedMethods.class.getDeclaredMethods();
        assert methods != null;
        assert methods.length == 4;

        // check defensive copy
        methods[0] = methods[1];
        assert methods[0] == methods[1];

        methods = ExtendedMethods.class.getDeclaredMethods();
        assert methods[0] != methods[1];

        // implements
        methods = ImplementdClass.class.getDeclaredMethods();
        assert methods != null;
        assert methods.length == 2;

        // interface
        methods = Interface.class.getDeclaredMethods();
        assert methods != null;
        assert methods.length == 1;

        // extended interface
        methods = ExtendedInterface.class.getDeclaredMethods();
        assert methods != null;
        assert methods.length == 1;
    }

    @Test
    public void nativeMethod() throws Exception {
        Method[] methods = NativeInterface.class.getMethods();
        assert methods.length == 1;
    }

    /**
     * @version 2013/12/28 10:07:33
     */
    private interface NativeInterface extends JavascriptNative {

        @JavascriptNativeProperty
        void some();
    }

    /**
     * @version 2013/09/03 15:04:55
     */
    private static class Methods {

        /**
         * 
         */
        public native void publicMethod();

        /**
         * 
         */
        protected native void protectedMethod();

        /**
         * 
         */
        native void packageMethod();

        /**
         * 
         */
        private native void privateMethod(int value);
    }

    /**
     * @version 2013/09/03 15:14:17
     */
    private static class ExtendedMethods extends Methods {

        /**
         * 
         */
        public native void extendedMethod();

        /**
         * {@inheritDoc}
         */
        @Override
        public void publicMethod() {
            super.publicMethod();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void protectedMethod() {
            super.protectedMethod();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        void packageMethod() {
            super.packageMethod();
        }
    }

    @Test
    public void getField() throws Exception {
        Field field = Fields.class.getField("publicField");
        assert field != null;

        field = Fields.class.getField("staticPublicField");
        assert field != null;
    }

    @Test(expected = NullPointerException.class)
    public void getFieldNullName() throws Exception {
        Methods.class.getField(null);
    }

    @Test(expected = NoSuchFieldException.class)
    public void getInvalidField() throws Exception {
        Methods.class.getField("protectedField");
    }

    @Test
    public void getFields() throws Exception {
        Field[] fields = Fields.class.getFields();
        assert fields != null;
        assert fields.length == 2;

        // extends
        fields = ExtendedFields.class.getFields();
        assert fields != null;
        assert fields.length == 4;

        // check defensive copy
        fields[0] = fields[1];
        assert fields[0] == fields[1];

        fields = ExtendedFields.class.getDeclaredFields();
        assert fields[0] != fields[1];

        // interface
        fields = Interface.class.getFields();
        assert fields != null;
        assert fields.length == 1;

        // extended interface
        fields = ExtendedInterface.class.getFields();
        assert fields != null;
        assert fields.length == 2;
    }

    @Test
    public void getDeclaredField() throws Exception {
        Field field = Fields.class.getDeclaredField("publicField");
        assert field != null;

        field = Fields.class.getDeclaredField("protectedField");
        assert field != null;

        field = Fields.class.getDeclaredField("packageField");
        assert field != null;

        field = Fields.class.getDeclaredField("privateField");
        assert field != null;

        field = Fields.class.getDeclaredField("staticPublicField");
        assert field != null;

        field = Fields.class.getDeclaredField("staticProtectedField");
        assert field != null;
    }

    @Test(expected = NullPointerException.class)
    public void getDeclaredFieldNullName() throws Exception {
        Methods.class.getDeclaredField(null);
    }

    @Test(expected = NoSuchFieldException.class)
    public void getDeclaredInvalidField() throws Exception {
        Methods.class.getDeclaredField("invalid");
    }

    @Test
    public void getDeclaredFields() throws Exception {
        Field[] fields = Fields.class.getDeclaredFields();
        assert fields != null;
        assert fields.length == 6;

        // extends
        fields = ExtendedFields.class.getDeclaredFields();
        assert fields != null;
        assert fields.length == 2;

        // check defensive copy
        fields[0] = fields[1];
        assert fields[0] == fields[1];

        fields = ExtendedFields.class.getDeclaredFields();
        assert fields[0] != fields[1];

        // interface
        fields = Interface.class.getDeclaredFields();
        assert fields != null;
        assert fields.length == 1;

        // extended interface
        fields = ExtendedInterface.class.getDeclaredFields();
        assert fields != null;
        assert fields.length == 1;
    }

    /**
     * @version 2013/09/03 15:04:55
     */
    @SuppressWarnings("unused")
    private static class Fields {

        public static int staticPublicField;

        protected static int staticProtectedField;

        public int publicField;

        protected int protectedField;

        int packageField;

        private int privateField;
    }

    /**
     * @version 2013/09/03 15:14:17
     */
    @SuppressWarnings("unused")
    private static class ExtendedFields extends Fields {

        public int extendedField;

        public int protectedField;
    }

    @Test
    public void getConstructor() throws Exception {
        Constructor constructor = Constructors.class.getConstructor();
        assert constructor != null;
    }

    @Test(expected = NoSuchMethodException.class)
    public void getIvalidConstructor() throws Exception {
        Constructors.class.getConstructor(int.class);
    }

    @Test
    public void getConstructors() throws Exception {
        Constructor[] constructors = Constructors.class.getConstructors();
        assert constructors != null;
        assert constructors.length == 1;

        // extends
        constructors = ExtendedConstructors.class.getConstructors();
        assert constructors != null;
        assert constructors.length == 1;

        // check defensive copy
        constructors[0] = null;
        assert constructors[0] == null;

        constructors = ExtendedConstructors.class.getConstructors();
        assert constructors[0] != null;

        // interface
        constructors = Interface.class.getConstructors();
        assert constructors != null;
        assert constructors.length == 0;

        // extended interface
        constructors = ExtendedInterface.class.getConstructors();
        assert constructors != null;
        assert constructors.length == 0;
    }

    @Test
    public void getDeclaredConstructor() throws Exception {
        Constructor constructor = Constructors.class.getDeclaredConstructor(int.class);
        assert constructor != null;

        constructor = Constructors.class.getDeclaredConstructor(double.class);
        assert constructor != null;

        constructor = Constructors.class.getDeclaredConstructor(boolean.class);
        assert constructor != null;

        constructor = Constructors.class.getDeclaredConstructor();
        assert constructor != null;
    }

    @Test(expected = NoSuchMethodException.class)
    public void getIvalidDeclaredConstructor() throws Exception {
        Constructors.class.getDeclaredConstructor(int.class, double.class, float.class);
    }

    @Test
    public void getDeclaredConstructors() throws Exception {
        Constructor[] constructors = Constructors.class.getDeclaredConstructors();
        assert constructors != null;
        assert constructors.length == 4;

        // extends
        constructors = ExtendedConstructors.class.getDeclaredConstructors();
        assert constructors != null;
        assert constructors.length == 2;

        // check defensive copy
        constructors[0] = constructors[1];
        assert constructors[0] == constructors[1];

        constructors = ExtendedConstructors.class.getDeclaredConstructors();
        assert constructors[0] != constructors[1];

        // interface
        constructors = Interface.class.getDeclaredConstructors();
        assert constructors != null;
        assert constructors.length == 0;

        // extended interface
        constructors = ExtendedInterface.class.getDeclaredConstructors();
        assert constructors != null;
        assert constructors.length == 0;
    }

    /**
     * @version 2013/09/04 16:57:20
     */
    @SuppressWarnings("unused")
    private static class Constructors {

        public Constructors() {
        }

        protected Constructors(int one) {
        }

        Constructors(double one) {
        }

        private Constructors(boolean one) {
        }
    }

    /**
     * @version 2013/09/04 16:57:17
     */
    @SuppressWarnings("unused")
    private static class ExtendedConstructors extends Constructors {

        public ExtendedConstructors(int one) {
            super(one);
        }

        private ExtendedConstructors(byte one) {
            super(one);
        }
    }

    @Test
    public void getClasses() throws Exception {
        Class[] classes = Classes.class.getClasses();
        assert classes != null;
        assert classes.length == 2;
        assert classes[0] == Classes.PublicNonStatic.class;
        assert classes[1] == Classes.PublicStatic.class;

        classes = ExtendClasses.class.getClasses();
        assert classes != null;
        assert classes.length == 3;
        assert classes[0] == ExtendClasses.ExtendPublicStatic.class;
        assert classes[1] == Classes.PublicNonStatic.class;
        assert classes[2] == Classes.PublicStatic.class;
    }

    @Test
    public void getDeclaredClasses() throws Exception {
        Class[] classes = Classes.class.getDeclaredClasses();
        assert classes != null;
        assert classes.length == 4;
        assert classes[0] == Classes.PirvateStatic.class;
        assert classes[1] == Classes.PrivateNonStatic.class;
        assert classes[2] == Classes.PublicNonStatic.class;
        assert classes[3] == Classes.PublicStatic.class;

        classes = ExtendClasses.class.getDeclaredClasses();
        assert classes != null;
        assert classes.length == 2;
        assert classes[0] == ExtendClasses.ExtendPrivateStatic.class;
        assert classes[1] == ExtendClasses.ExtendPublicStatic.class;
    }

    /**
     * @version 2016/09/25 11:51:32
     */
    private static class Classes {

        public static class PublicStatic {
        }

        public class PublicNonStatic {
        }

        private static class PirvateStatic {
        }

        private class PrivateNonStatic {
        }
    }

    /**
     * @version 2016/09/25 12:33:57
     */
    private static class ExtendClasses extends Classes {

        public static class ExtendPublicStatic {
        }

        private static class ExtendPrivateStatic {
        }
    }

    @Test
    public void asSubclass() throws Exception {
        assert Integer.class.asSubclass(Number.class) == Integer.class;
    }

    @Test(expected = ClassCastException.class)
    public void asSubclassError() throws Exception {
        Integer.class.asSubclass(String.class);
    }

    @Test
    public void isAnnonymousClass() throws Exception {
        assert !Public.class.isAnonymousClass();
        assert !ClassTest.class.isAnonymousClass();
        assert anonymousClass.isAnonymousClass();

        class Local {
        }
        assert !Local.class.isAnonymousClass();
    }

    @Test
    public void isArray() throws Exception {
        assert !Public.class.isArray();
        assert Public[].class.isArray();
        assert !int.class.isArray();
        assert int[].class.isArray();
    }

    @Test
    public void isAssignableFrom() throws Exception {
        assert Object.class.isAssignableFrom(Public.class);
        assert SuperClass.class.isAssignableFrom(ExtendedClass.class);
        assert !SuperClass.class.isAssignableFrom(Public.class);
        assert Interface.class.isAssignableFrom(ExtendedInterface.class);
        assert Interface.class.isAssignableFrom(ImplementdExtendedClass.class);
        assert !Interface.class.isAssignableFrom(Runnable.class);
    }

    @Test
    public void isEnum() throws Exception {
        assert RetentionPolicy.class.isEnum();
        assert !Enum.class.isEnum();
        assert !Public.class.isEnum();
    }

    @Test
    public void isInstance() throws Exception {
        assert SuperClass.class.isInstance(new SuperClass());
        assert SuperClass.class.isInstance(new ExtendedClass());
        assert !SuperClass.class.isInstance(new Public());
        assert Object.class.isInstance(new Public());
        assert String.class.isInstance("test");
    }

    @Test
    public void isInterface() throws Exception {
        assert Runnable.class.isInterface();
        assert Interface.class.isInterface();
        assert !Object.class.isInterface();
        assert !Public.class.isInterface();
    }

    @Test
    public void isLocalClass() throws Exception {
        assert !Public.class.isLocalClass();
        assert !anonymousClass.isLocalClass();

        class Local {
        }
        assert Local.class.isLocalClass();
    }

    @Test
    public void isMemberClass() throws Exception {
        assert Public.class.isMemberClass();
        assert !ClassTest.class.isMemberClass();
        assert !anonymousClass.isMemberClass();

        class Local {
        }
        assert !Local.class.isMemberClass();
    }

    @Test
    public void isPrimitive() throws Exception {
        assert int.class.isPrimitive();
        assert long.class.isPrimitive();
        assert float.class.isPrimitive();
        assert double.class.isPrimitive();
        assert byte.class.isPrimitive();
        assert short.class.isPrimitive();
        assert boolean.class.isPrimitive();
        assert char.class.isPrimitive();
        assert void.class.isPrimitive();

        assert !int[].class.isPrimitive();
        assert !Object.class.isPrimitive();
        assert !Throwable.class.isPrimitive();
        assert !Runnable.class.isPrimitive();
    }

    @Test
    public void cast() throws Exception {
        CharSequence.class.cast("test");
        CharSequence.class.cast(null);
    }

    @Test(expected = ClassCastException.class)
    public void castFail() throws Exception {
        Number.class.cast("as");
    }

    @Test
    public void forName() throws Exception {
        assert Class.forName("java.lang.String") == String.class;
        assert Class.forName("[Ljava.lang.String;") == String[].class;
        assert Class.forName("[[Ljava.lang.String;") == String[][].class;
        assert Class.forName("[I") == int[].class;
        assert Class.forName("[[J") == long[][].class;
        assert Class.forName("[F") == float[].class;
        assert Class.forName("[D") == double[].class;
        assert Class.forName("[Z") == boolean[].class;
        assert Class.forName("[B") == byte[].class;
        assert Class.forName("[S") == short[].class;
        assert Class.forName("[C") == char[].class;
    }

    @Test(expected = ClassNotFoundException.class)
    public void forNamePrimitive() throws Exception {
        Class.forName("int");
    }

    @Test(expected = ClassNotFoundException.class)
    public void forNameUnknown() throws Exception {
        Class.forName("this.class.is.invalid.Class");
    }

    @Test
    public void getName() throws Exception {
        assert String.class.getName().equals("java.lang.String");
        assert Fields.class.getName().equals("js.lang.reflect.ClassTest$Fields");
        assert int.class.getName().equals("int");
        assert boolean.class.getName().equals("boolean");
        assert Map[].class.getName().equals("[Ljava.util.Map;");
        assert long[].class.getName().equals("[J");
        assert char[][].class.getName().equals("[[C");
    }

    @Test
    public void getSimpleName() throws Exception {
        assert String.class.getSimpleName().equals("String");
        assert Fields.class.getSimpleName().equals("Fields");
        assert int.class.getSimpleName().equals("int");
        assert boolean.class.getSimpleName().equals("boolean");
        assert Map[].class.getSimpleName().equals("Map[]");
        assert long[].class.getSimpleName().equals("long[]");
        assert char[][].class.getSimpleName().equals("char[][]");
    }

    @Test
    public void getCanonicalName() throws Exception {
        assert String.class.getCanonicalName().equals("java.lang.String");
        assert Fields.class.getCanonicalName().equals("js.lang.reflect.ClassTest.Fields");
        assert int.class.getCanonicalName().equals("int");
        assert boolean.class.getCanonicalName().equals("boolean");
        assert Map[].class.getCanonicalName().equals("java.util.Map[]");
        assert long[].class.getCanonicalName().equals("long[]");
        assert char[][].class.getCanonicalName().equals("char[][]");
    }

    @Test
    public void getEnclosingClass() throws Exception {
        assert String.class.getEnclosingClass() == null;
        assert Fields.class.getEnclosingClass() == ClassTest.class;
        assert int.class.getEnclosingClass() == null;
        assert Integer[].class.getEnclosingClass() == null;
    }

    @Test
    public void getDeclaringClass() throws Exception {
        assert String.class.getDeclaringClass() == null;
        assert Fields.class.getDeclaringClass() == ClassTest.class;
        assert int.class.getDeclaringClass() == null;
        assert Integer[].class.getDeclaringClass() == null;
    }

    @Test
    public void getEnumConstants() throws Exception {
        RetentionPolicy[] policies = RetentionPolicy.class.getEnumConstants();
        assert policies.length == 3;
        assert policies[0] == RetentionPolicy.SOURCE;
        assert policies[1] == RetentionPolicy.CLASS;
        assert policies[2] == RetentionPolicy.RUNTIME;
    }

    @Test
    public void getPackage() throws Exception {
        java.lang.Package p = String.class.getPackage();
        assert p != null;
        assert p.getName().equals("java.lang");
    }
}
