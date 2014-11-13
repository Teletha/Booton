/*
 * Copyright (C) 2014 Nameless Production Committee
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://opensource.org/licenses/mit-license.php
 */
package jsx.style;

import static java.lang.Integer.*;

import java.util.List;
import java.util.Map;

import jsx.style.Style;
import jsx.style.StyleName;
import jsx.style.StyleRule;
import jsx.style.StyleRuleDescriptor;
import jsx.style.StyleSheet;
import jsx.style.value.Color;

/**
 * @version 2014/11/13 13:22:44
 */
public class StyleTester extends StyleRuleDescriptor {

    protected ValidatableStyle style(Style style) {
        // empty style sheet
        StyleSheet sheet = new StyleSheet();
        sheet.createRule("$", style);

        // search specified rule
        String name = "." + StyleName.name(style);

        for (StyleRule rule : sheet.rules) {
            if (rule.selector.equals(name)) {
                return new ValidatableStyle(sheet, rule);
            }
        }
        // If this exception will be thrown, it is bug of this program. So we must rethrow the
        // wrapped error in here.
        throw new Error();
    }

    /**
     * @version 2014/11/13 13:31:06
     */
    public static class ValidatableStyle {

        /** The target stylesheet. */
        private final StyleSheet sheet;

        /** The target to validate. */
        private final StyleRule rules;

        /**
         * @param rules
         */
        private ValidatableStyle(StyleSheet sheet, StyleRule rules) {
            this.sheet = sheet;
            this.rules = rules;
        }

        /**
         * @param name
         * @param value
         * @return
         */
        public boolean property(String name, String... values) {
            assert name != null;
            assert values != null;
            assert values.length != 0;

            Map<String, List<String>> properties = rules.holder;
            List<String> list = properties.get(name);
            assert list != null;
            assert list.size() == values.length;

            for (String value : values) {
                if (value.startsWith("rgb(")) {
                    value = convertRGB(value);
                }

                if (value.startsWith("transparent")) {
                    value = "hsla(0,0%,0%,0)";
                }
                assert list.contains(value);
            }
            return true;
        }

        /**
         * <p>
         * Convert color expression.
         * </p>
         */
        private String convertRGB(String value) {
            String[] v = value.substring(4, value.length() - 1).split(",");

            return Color.rgb(parseInt(v[0].trim()), parseInt(v[1].trim()), parseInt(v[2].trim())).toString();
        }

        /**
         * @param selector
         * @return
         */
        public ValidatableStyle sub(String selector) {
            String combinator = rules.selector + ":" + selector;
            String pseudo = rules.selector + "::" + selector;

            for (StyleRule rule : sheet.rules) {
                selector = rule.selector;

                if (selector.equals(combinator) || selector.equals(pseudo)) {
                    return new ValidatableStyle(sheet, rule);
                }
            }
            throw new AssertionError("The rule[" + combinator + "] or [" + pseudo + "] is not found.");
        }

        /**
         * <p>
         * Property has no prefix.
         * </p>
         */
        public boolean noPrefix() {
            return false;
        }
    }
}