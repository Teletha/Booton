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
 * @version 2013/10/26 10:54:36
 */
@JavaAPIProvider(ClassLoader.class)
class JSClassLoader {

    /** The system class loader. */
    private static JSClassLoader system = new JSClassLoader();

    /**
     * Loads the class with the specified <a href="#name">binary name</a>. This method searches for
     * classes in the same manner as the {@link #loadClass(String, boolean)} method. It is invoked
     * by the Java virtual machine to resolve class references. Invoking this method is equivalent
     * to invoking {@link #loadClass(String, boolean) <tt>loadClass(name,
     * false)</tt>}.
     *
     * @param name The <a href="#name">binary name</a> of the class
     * @return The resulting <tt>Class</tt> object
     * @throws ClassNotFoundException If the class was not found
     */
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        return loadClass(name, false);
    }

    /**
     * Loads the class with the specified <a href="#name">binary name</a>. The default
     * implementation of this method searches for classes in the following order:
     * <ol>
     * <li>
     * <p>
     * Invoke {@link #findLoadedClass(String)} to check if the class has already been loaded.
     * </p>
     * </li>
     * <li>
     * <p>
     * Invoke the {@link #loadClass(String) <tt>loadClass</tt>} method on the parent class loader.
     * If the parent is <tt>null</tt> the class loader built-in to the virtual machine is used,
     * instead.
     * </p>
     * </li>
     * <li>
     * <p>
     * Invoke the {@link #findClass(String)} method to find the class.
     * </p>
     * </li>
     * </ol>
     * <p>
     * If the class was found using the above steps, and the <tt>resolve</tt> flag is true, this
     * method will then invoke the {@link #resolveClass(Class)} method on the resulting
     * <tt>Class</tt> object.
     * <p>
     * Subclasses of <tt>ClassLoader</tt> are encouraged to override {@link #findClass(String)},
     * rather than this method.
     * </p>
     * <p>
     * Unless overridden, this method synchronizes on the result of {@link #getClassLoadingLock
     * <tt>getClassLoadingLock</tt>} method during the entire class loading process.
     *
     * @param name The <a href="#name">binary name</a> of the class
     * @param resolve If <tt>true</tt> then resolve the class
     * @return The resulting <tt>Class</tt> object
     * @throws ClassNotFoundException If the class could not be found
     */
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        // If this exception will be thrown, it is bug of this program. So we must rethrow the
        // wrapped error in here.
        throw new Error();
    }

    /**
     * Returns the system class loader for delegation. This is the default delegation parent for new
     * <tt>ClassLoader</tt> instances, and is typically the class loader used to start the
     * application.
     * <p>
     * This method is first invoked early in the runtime's startup sequence, at which point it
     * creates the system class loader and sets it as the context class loader of the invoking
     * <tt>Thread</tt>.
     * <p>
     * The default system class loader is an implementation-dependent instance of this class.
     * <p>
     * If the system property "<tt>java.system.class.loader</tt>" is defined when this method is
     * first invoked then the value of that property is taken to be the name of a class that will be
     * returned as the system class loader. The class is loaded using the default system class
     * loader and must define a public constructor that takes a single parameter of type
     * <tt>ClassLoader</tt> which is used as the delegation parent. An instance is then created
     * using this constructor with the default system class loader as the parameter. The resulting
     * class loader is defined to be the system class loader.
     * <p>
     * If a security manager is present, and the invoker's class loader is not <tt>null</tt> and the
     * invoker's class loader is not the same as or an ancestor of the system class loader, then
     * this method invokes the security manager's
     * {@link SecurityManager#checkPermission(java.security.Permission) <tt>checkPermission</tt>}
     * method with a {@link RuntimePermission#RuntimePermission(String)
     * <tt>RuntimePermission("getClassLoader")</tt>} permission to verify access to the system class
     * loader. If not, a <tt>SecurityException</tt> will be thrown.
     * </p>
     * 
     * @return The system <tt>ClassLoader</tt> for delegation, or <tt>null</tt> if none
     * @throws SecurityException If a security manager exists and its <tt>checkPermission</tt>
     *             method doesn't allow access to the system class loader.
     * @throws IllegalStateException If invoked recursively during the construction of the class
     *             loader specified by the "<tt>java.system.class.loader</tt>" property.
     * @throws Error If the system property "<tt>java.system.class.loader</tt>" is defined but the
     *             named class could not be loaded, the provider class does not define the required
     *             constructor, or an exception is thrown by that constructor when it is invoked.
     *             The underlying cause of the error can be retrieved via the
     *             {@link Throwable#getCause()} method.
     * @revised 1.4
     */
    public static ClassLoader getSystemClassLoader() {
        return (ClassLoader) (Object) system;
    }
}
