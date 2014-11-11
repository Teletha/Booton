/*
 * Copyright (C) 2013 Nameless Production Committee
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://opensource.org/licenses/mit-license.php
 */
package jsx.bwt;

import static jsx.bwt.FormUIStyle.*;

import java.util.function.Consumer;

import js.dom.UIAction;
import js.dom.UIEvent;

/**
 * @version 2013/04/17 16:09:04
 */
public class Button extends FormUI<Button> {

    /** The current label text. */
    private String label;

    /**
     * <p>
     * Create labeled button with the specified action.
     * </p>
     * 
     * @param label
     * @param action
     */
    public Button(String label, Runnable action) {
        this(label, event -> {
            action.run();
        });
    }

    /**
     * @param label
     */
    public Button(String label, Consumer<UIEvent> action) {
        super("span");

        form.add(ButtonForm).text(label).observe(UIAction.Click).to(action);

        this.label = label;
    }

    /**
     * @param label
     */
    public Button(Icon icon, Consumer<UIEvent> action) {
        this("", action);

        form.add(Icons).attr("icon", icon.code);
    }

    public void label(String label) {
        form.text(label);
    }
}
