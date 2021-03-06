/*
 * Copyright (C) 2016 Nameless Production Committee
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://opensource.org/licenses/mit-license.php
 */
package js.dom;

import booton.translator.JavascriptNative;
import booton.translator.JavascriptNativeProperty;

/**
 * <p>
 * Returns a Location object, which contains information about the URL of the document and provides
 * methods for changing that URL. You can also assign to this property to load another URL.
 * </p>
 * 
 * @version 2016/11/08 16:08:48
 */
public abstract class Location implements JavascriptNative {

    /**
     * <p>
     * the part of the URL that follows the # symbol, including the # symbol. You can listen for the
     * hashchange event to get notified of changes to the hash in supporting browsers.
     * </p>
     */
    @JavascriptNativeProperty
    public String hash;

    /** the host name and port number. */
    @JavascriptNativeProperty
    public String host;

    /** the host name (without the port number or square brackets). */
    @JavascriptNativeProperty
    public String hostname;

    /** the entire URL. */
    @JavascriptNativeProperty
    public String href;

    /** the path (relative to the host). */
    @JavascriptNativeProperty
    public String pathname;

    /** the port number of the URL. */
    @JavascriptNativeProperty
    public int port;

    /** the protocol of the URL. */
    @JavascriptNativeProperty
    public String protocol;

    /** the part of the URL that follows the ? symbol, including the ? symbol. */
    @JavascriptNativeProperty
    public String search;

    /**
     * <p>
     * Load the document at the provided URL.
     * </p>
     * 
     * @param uri
     */
    public native void assign(String uri);

    /**
     * <p>
     * Reload the document from the current URL. forceget is a boolean, which, when it is true,
     * causes the page to always be reloaded from the server. If it is false or not specified, the
     * browser may reload the page from its cache.
     * </p>
     */
    public native void reload(boolean ignoreCache);

    /**
     * <p>
     * Replace the current document with the one at the provided URL. The difference from the
     * assign() method is that after using replace() the current page will not be saved in session
     * history, meaning the user won't be able to use the Back button to navigate to it.
     * </p>
     * 
     * @param uri
     */
    public native void replace(String uri);
}
