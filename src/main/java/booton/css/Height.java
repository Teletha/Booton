/*
 * Copyright (C) 2012 Nameless Production Committee
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://opensource.org/licenses/mit-license.php
 */
package booton.css;

/**
 * @version 2012/12/11 16:28:33
 */
public final class Height extends CSSLength {

    /**
     * @param css
     */
    protected Height(CSS css) {
        super(css);
    }

    /**
     * The browser will calculate and select a height for the specified element.
     */
    public void auto() {
        value = "auto";
    }
}
