/*
 * Copyright (C) 2012 Nameless Production Committee
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://opensource.org/licenses/mit-license.php
 */
package booton;

import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import kiss.I;
import kiss.Manageable;
import kiss.Singleton;
import kiss.Table;
import booton.css.CSS;
import booton.translator.Literal;
import booton.util.Font;

/**
 * @version 2012/12/23 17:27:55
 */
@Manageable(lifestyle = Singleton.class)
class StylesheetManager implements Literal<CSS> {

    /** The used web fonts. */
    private final Set<Font> fonts = new HashSet();

    /** The used styles. */
    private final Set<Class<? extends CSS>> used = new LinkedHashSet();

    /**
     * <p>
     * Write css file.
     * </p>
     * 
     * @param file
     */
    public void write(Path file) throws Exception {
        // collect defined styles
        Table<Class, CSS> cascading = new Table();

        for (CSS style : I.find(CSS.class)) {
            Class type = style.getClass();

            while (type != CSS.class) {
                cascading.push(type, style);

                type = type.getSuperclass();
            }
        }

        // collect required styles
        Set<CSS> required = new LinkedHashSet();

        for (Class type : used) {
            required.add(cascading.find(type));
        }

        // collect required fonts
        for (CSS style : required) {
            collectFont(style.getClass(), style);
        }

        StringBuilder builder = new StringBuilder();

        // write font imports
        for (Font font : fonts) {
            builder.append("@import url(" + font.uri + ");\r\n");
        }

        // write require styles
        for (CSS style : required) {
            builder.append(style);
        }

        Files.write(file, builder.toString().getBytes(I.$encoding));
    }

    /**
     * <p>
     * Collect font declarations.
     * </p>
     * 
     * @param clazz
     */
    private void collectFont(Class clazz, CSS style) throws Exception {
        if (clazz != null && clazz != CSS.class) {
            for (Field field : clazz.getDeclaredFields()) {
                if (field.getType() == Font.class) {
                    field.setAccessible(true);
                    fonts.add((Font) field.get(style));
                }
            }

            if (clazz.isMemberClass()) {
                collectFont(clazz.getEnclosingClass(), null);
            }
            collectFont(clazz.getSuperclass(), style);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String write(Class<? extends CSS> clazz) {
        used.add(clazz);

        return '"' + Obfuscator.computeCSSName(clazz) + '"';
    }
}
