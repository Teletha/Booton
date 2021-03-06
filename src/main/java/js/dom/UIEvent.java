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

import booton.translator.JavascriptAPIProvider;
import booton.translator.JavascriptNative;
import booton.translator.JavascriptNativeProperty;

/**
 * @version 2015/10/05 8:11:29
 */
@JavascriptAPIProvider(targetJavaScriptClassName = "Event")
public class UIEvent implements JavascriptNative {

    /** The DOM element that initiated the event. */
    @JavascriptNativeProperty
    @Deprecated
    public Element target;

    /** The current DOM element within the event bubbling phase. */
    @JavascriptNativeProperty
    @Deprecated
    public Element currentTarget;

    /** The other DOM element involved in the event, if any. */
    @JavascriptNativeProperty
    @Deprecated
    public Element relatedTarget;

    /** The element where the currently-called jQuery event handler was attached. */
    @JavascriptNativeProperty
    @Deprecated
    public Element delegateTarget;

    /** The namespace specified when the event was triggered. */
    @JavascriptNativeProperty
    public String namespace;

    /** The DOM element that initiated the event. */
    @JavascriptNativeProperty
    @Deprecated
    public int detail;

    /** The DOM element that initiated the event. */
    @JavascriptNativeProperty
    @Deprecated
    public int deltaX;

    /** The DOM element that initiated the event. */
    @JavascriptNativeProperty
    @Deprecated
    public int deltaY;

    /** The DOM element that initiated the event. */
    @JavascriptNativeProperty
    @Deprecated
    public int deltaZ;

    /** The mouse position relative to the left edge of the document. */
    @JavascriptNativeProperty
    @Deprecated
    public int pageX;

    /** The mouse position relative to the top edge of the document. */
    @JavascriptNativeProperty
    @Deprecated
    public int pageY;

    /**
     * The difference in milliseconds between the time the browser created the event and January 1,
     * 1970.
     */
    @JavascriptNativeProperty
    public long timeStamp;

    /** Describes the nature of the event. */
    @JavascriptNativeProperty
    public String type;

    /**
     * For key or mouse events, this property indicates the specific key or button that was pressed.
     */
    @JavascriptNativeProperty
    @Deprecated
    public int which;

    /**
     * 
     */
    public UIEvent() {
    }

    /**
     * <p>
     * Initialize event.
     * </p>
     * 
     * @param type The type of event.
     * @param bubbles A boolean indicating whether the event should bubble up through the event
     *            chain or not.
     * @param cancelable A boolean indicating whether the event can be canceled.
     */
    public native void initEvent(String type, boolean bubbles, boolean cancelable);

    /**
     * <p>
     * Returns whether event.preventDefault() was ever called on this event object.
     * </p>
     * 
     * @return
     */
    public native boolean isDefaultPrevented();

    /**
     * <p>
     * Returns whether event.stopImmediatePropagation() was ever called on this event object.
     * </p>
     * 
     * @return
     */
    public native boolean isImmediatePropagationStopped();

    /**
     * <p>
     * Returns whether event.stopPropagation() was ever called on this event object.
     * </p>
     * 
     * @return
     */
    public native boolean isPropagationStopped();

    /**
     * <p>
     * If this method is called, the default action of the event will not be triggered.
     * </p>
     */
    public native void preventDefault();

    /**
     * <p>
     * Prevents the event from bubbling up the DOM tree, preventing any parent handlers from being
     * notified of the event.
     * </p>
     */
    public native void stopPropagation();

    /**
     * <p>
     * Keeps the rest of the handlers from being executed and prevents the event from bubbling up
     * the DOM tree.
     * </p>
     */
    public native void stopImmediatePropagation();

    /**
     * <p>
     * Retrieve the value property of the current {@link EventTarget}.
     * </p>
     * 
     * @return A value property.
     */
    public String value() {
        return String.valueOf(target.property("value"));
    }
}
