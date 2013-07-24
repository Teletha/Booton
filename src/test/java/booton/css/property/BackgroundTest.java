/*
 * Copyright (C) 2013 Nameless Production Committee
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://opensource.org/licenses/mit-license.php
 */
package booton.css.property;

import static booton.css.Vendor.*;
import static booton.css.value.Color.*;

import org.junit.Test;

import booton.css.MyCSS;
import booton.css.value.LinearGradient;

/**
 * @version 2013/07/23 16:49:41
 */
public class BackgroundTest {

    @Test
    public void image() throws Exception {
        MyCSS css = new MyCSS();
        css.background.image("one");

        assert css.has("background-image", "url(\"one\")");
    }

    @Test
    public void images() throws Exception {
        MyCSS css = new MyCSS();
        css.background.image("one").image("two");

        assert css.has("background-image", "url(\"one\"),url(\"two\")");
    }

    @Test
    public void imageGradient() throws Exception {
        MyCSS css = new MyCSS();
        css.background.image(new LinearGradient().color(Black, White));

        assert css.has("background-image", "-webkit-linear-gradient(black,white)");
        assert css.has("background-image", "linear-gradient(black,white)");
        assert css.no(Mozilla, IE);
    }

    @Test
    public void imageGradients() throws Exception {
        MyCSS css = new MyCSS();
        css.background.image(new LinearGradient().color(Black, White)).image(new LinearGradient().color(White, Black));

        assert css.has("background-image", "-webkit-linear-gradient(black,white),-webkit-linear-gradient(white,black)");
        assert css.has("background-image", "linear-gradient(black,white),linear-gradient(white,black)");
        assert css.no(Mozilla, IE);
    }
}
