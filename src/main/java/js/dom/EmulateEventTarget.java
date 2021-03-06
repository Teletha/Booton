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

import js.lang.NativeFunction;
import kiss.Table;

/**
 * @version 2013/10/05 10:07:28
 */
class EmulateEventTarget<T extends EmulateEventTarget<T>> extends EventTarget<T> {

    /** The listener table. */
    private Table<String, NativeFunction> listeners = new Table();

    /**
     * {@inheritDoc}
     */
    @Override
    public void addEventListener(String type, NativeFunction listener) {
        if (type != null && listener != null) {
            listeners.push(type, listener);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeEventListener(String type, NativeFunction listener) {
        if (type != null && listener != null) {
            listeners.pull(type, listener);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void dispatchEvent(UIEvent event) {
        if (event != null) {
            for (NativeFunction listener : listeners.get(event.type)) {
                listener.apply(null, event);
            }
        }
    }
}
