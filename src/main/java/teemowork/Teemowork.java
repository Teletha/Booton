/*
 * Copyright (C) 2012 Nameless Production Committee
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://opensource.org/licenses/mit-license.php
 */
package teemowork;

import static booton.translator.web.WebSupport.*;

import java.util.Collection;

import js.application.Application;
import js.application.Header;
import js.application.Header.Menu;
import js.ui.ImageGrid;
import teemowork.model.Champion;
import booton.translator.web.jQuery;

/**
 * @version 2012/12/11 14:23:57
 */
public class Teemowork extends Application {

    /** The champion viewer. */
    private ImageGrid champions = new ImageGrid<Champion>() {

        @Override
        protected Collection<Champion> sources() {
            return Champion.getAll();
        }

        @Override
        protected String getTitle(Champion source) {
            return source.name;
        }

        @Override
        protected String getImageURI(Champion source) {
            return "src/main/resources/teemowork/icon/" + source.getSystemName() + ".png";
        }
    };

    /**
     * {@inheritDoc}
     */
    @Override
    protected void jsmain() {
        // create navigation
        // body.append("<div class='navbar'><div class='navbar-inner'><a class='brand' href='#'>Title</a> <ul class='nav'><li class='active'><a href='#''>Home</a></li><li><a href='#''>Link</a></li> <li><a href='#''>Link</a></li> </ul></div></div>");

        $("body").css("padding", "150px");

        jQuery root = $("#Content");

        Header nav = new Header();
        nav.add("< ^ v ^ > Teemowork", "test.html");
        nav.add("Patch", "#");

        Menu sub = nav.add("Data", "#");
        sub.add("Champion", "#");
        sub.add("Item", "#");
        sub.add("Mastery", "#");
        sub.add("Rune", "#");

        nav.add("Builder", "#");
        nav.add("About", "#");
        nav.add("Contact", "#");

        champions.compose(root);
    }
}
