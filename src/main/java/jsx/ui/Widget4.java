/*
 * Copyright (C) 2015 Nameless Production Committee
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://opensource.org/licenses/mit-license.php
 */
package jsx.ui;

/**
 * @version 2014/08/21 13:31:25
 */
public abstract class Widget4<First, Second, Third, Fourth> extends Widget {

    /** The first model associated with this {@link Widget}. */
    protected final First model1;

    /** The second model associated with this {@link Widget}. */
    protected final Second model2;

    /** The third model associated with this {@link Widget}. */
    protected final Third model3;

    /** The fourth model associated with this {@link Widget}. */
    protected final Fourth model4;

    /**
     * <p>
     * Visible for sub-class.
     * </p>
     */
    protected Widget4() {
        this.model1 = (First) loophole[0];
        this.model2 = (Second) loophole[1];
        this.model3 = (Third) loophole[2];
        this.model4 = (Fourth) loophole[3];
    }
}
