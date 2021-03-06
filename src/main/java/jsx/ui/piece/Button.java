/*
 * Copyright (C) 2016 Nameless Production Committee
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://opensource.org/licenses/mit-license.php
 */
package jsx.ui.piece;

import javafx.beans.binding.StringExpression;
import javafx.beans.property.SimpleStringProperty;

import jsx.style.Style;
import jsx.style.value.Color;
import jsx.ui.LowLevelWidget;
import jsx.ui.StructureDSL;
import jsx.ui.piece.Button.Styles;

/**
 * @version 2015/10/21 3:00:41
 */
public class Button extends LowLevelWidget<Styles, Button> {

    /** The label text. */
    public StringExpression label;

    /**
     * @param name
     */
    Button() {
    }

    /**
     * @param string
     * @return
     */
    public Button label(StringExpression text) {
        this.label = text;

        return this;
    }

    /**
     * @param texts
     * @return
     */
    public Button label(Object... texts) {
        StringExpression label = new SimpleStringProperty("");

        for (Object text : texts) {
            label = label.concat(text);
        }
        return label(label);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void virtualize() {
        new StructureDSL() {
            {
                html("button", $.Button, userStyle, WidgetRoot, If(disabled, attr("disabled", "disabled")), () -> {
                    text(label.get());
                });
            }
        };
    }

    /**
     * @version 2016/10/22 10:43:47
     */
    protected static class Styles extends PieceStyle {

        Style Button = SingleLineFormBase.with(() -> {
            background.color(Color.Transparent);
            cursor.pointer();

            hover(() -> {
                background.color(BorderColor.opacify(-0.4));
            });
        });
    }
}
