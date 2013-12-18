/*
 * Copyright (C) 2013 Nameless Production Committee
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://opensource.org/licenses/mit-license.php
 */
package js.dom.event;

import jsx.bwt.UIEvent;

/**
 * @version 2013/12/18 14:26:59
 */
public abstract class AbstractUIEvent extends UIEvent {

    protected UIEvent delegator;

    public void set(UIEvent nativeEvent) {
        this.currentTarget = nativeEvent.currentTarget;
        this.delegateTarget = nativeEvent.delegateTarget;
        this.namespace = nativeEvent.namespace;
        this.pageX = nativeEvent.pageX;
        this.pageY = nativeEvent.pageY;
        this.relatedTarget = nativeEvent.relatedTarget;
        this.target = nativeEvent.target;
        this.timeStamp = nativeEvent.timeStamp;
        this.type = nativeEvent.type;
        this.which = nativeEvent.which;
        this.delegator = nativeEvent;
    }

    /**
     * {@inheritDoc}
     */
    public int hashCode() {
        return delegator.hashCode();
    }

    /**
     * {@inheritDoc}
     */
    public void initEvent(String type, boolean bubbles, boolean cancelable) {
        delegator.initEvent(type, bubbles, cancelable);
    }

    /**
     * {@inheritDoc}
     */
    public boolean isDefaultPrevented() {
        return delegator.isDefaultPrevented();
    }

    /**
     * {@inheritDoc}
     */
    public boolean isImmediatePropagationStopped() {
        return delegator.isImmediatePropagationStopped();
    }

    /**
     * {@inheritDoc}
     */
    public boolean isPropagationStopped() {
        return delegator.isPropagationStopped();
    }

    /**
     * {@inheritDoc}
     */
    public void preventDefault() {
        delegator.preventDefault();
    }

    /**
     * {@inheritDoc}
     */
    public void stopPropagation() {
        delegator.stopPropagation();
    }

    /**
     * {@inheritDoc}
     */
    public void stopImmediatePropagation() {
        delegator.stopImmediatePropagation();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void dispose() {
        stopPropagation();
        preventDefault();
    }
}
