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

import org.junit.Test;

/**
 * @version 2014/11/15 12:58:36
 */
public class PseudoClassTest extends StyleTester {

    @Test
    public void interaction() {
        ValidatableStyle parsed = style(() -> {
            hover(() -> {
                font.size(1, px);
            });

            focus(() -> {
                font.size(2, px);
            });

            active(() -> {
                font.size(3, px);
            });
        });
        assert parsed.sub("hover").property("font-size", "1px");
        assert parsed.sub("focus").property("font-size", "2px");
        assert parsed.sub("active").property("font-size", "3px");
    }

    @Test
    public void link() {
        ValidatableStyle parsed = style(() -> {
            link(() -> {
                font.size(1, px);
            });

            visited(() -> {
                font.size(2, px);
            });
        });
        assert parsed.sub("link").property("font-size", "1px");
        assert parsed.sub("visited").property("font-size", "2px");
    }

    @Test
    public void form() throws Exception {
        ValidatableStyle parsed = style(() -> {
            enabled(() -> {
                font.size(1, px);
            });

            disabled(() -> {
                font.size(2, px);
            });

            checked(() -> {
                font.size(3, px);
            });

            indeterminate(() -> {
                font.size(4, px);
            });

            optional(() -> {
                font.size(5, px);
            });

            required(() -> {
                font.size(6, px);
            });

            valid(() -> {
                font.size(7, px);
            });

            invalid(() -> {
                font.size(8, px);
            });
        });

        assert parsed.sub("enabled").property("font-size", "1px");
        assert parsed.sub("disabled").property("font-size", "2px");
        assert parsed.sub("checked").property("font-size", "3px");
        assert parsed.sub("indeterminate").property("font-size", "4px");
        assert parsed.sub("optional").property("font-size", "5px");
        assert parsed.sub("required").property("font-size", "6px");
        assert parsed.sub("valid").property("font-size", "7px");
        assert parsed.sub("invalid").property("font-size", "8px");
    }

    @Test
    public void firstChild() throws Exception {
        ValidatableStyle parsed = style(() -> {
            firstChild(() -> {
                font.size(1, px);
            });
        });
        assert parsed.sub("first-child").property("font-size", "1px");
    }

    @Test
    public void lastChild() throws Exception {
        ValidatableStyle parsed = style(() -> {
            lastChild(() -> {
                font.size(1, px);
            });
        });
        assert parsed.sub("last-child").property("font-size", "1px");
    }

    @Test
    public void nthChild() throws Exception {
        ValidatableStyle parsed = style(() -> {
            nthChild("1", () -> {
                font.size(1, px);
            });

            nthChild("2n", () -> {
                font.size(1, px);
            });

            nthChild("odd", () -> {
                font.size(1, px);
            });
        });

        assert parsed.sub("nth-child(1)").property("font-size", "1px");
        assert parsed.sub("nth-child(2n)").property("font-size", "1px");
        assert parsed.sub("nth-child(odd)").property("font-size", "1px");
    }

    @Test
    public void nthLastChild() throws Exception {
        ValidatableStyle parsed = style(() -> {
            nthLastChild("1", () -> {
                font.size(1, px);
            });

            nthLastChild("2n", () -> {
                font.size(1, px);
            });

            nthLastChild("odd", () -> {
                font.size(1, px);
            });
        });

        assert parsed.sub("nth-last-child(1)").property("font-size", "1px");
        assert parsed.sub("nth-last-child(2n)").property("font-size", "1px");
        assert parsed.sub("nth-last-child(odd)").property("font-size", "1px");
    }

    @Test
    public void firstType() throws Exception {
        ValidatableStyle parsed = style(() -> {
            firstOfType(() -> {
                font.size(1, px);
            });
        });

        assert parsed.sub("first-of-type").property("font-size", "1px");
    }

    @Test
    public void lastType() throws Exception {
        ValidatableStyle parsed = style(() -> {
            lastOfType(() -> {
                font.size(1, px);
            });
        });

        assert parsed.sub("last-of-type").property("font-size", "1px");
    }

    @Test
    public void nthType() throws Exception {
        ValidatableStyle parsed = style(() -> {
            nthOfType("1", () -> {
                font.size(1, px);
            });

            nthOfType("2n", () -> {
                font.size(1, px);
            });

            nthOfType("odd", () -> {
                font.size(1, px);
            });
        });

        assert parsed.sub("nth-of-type(1)").property("font-size", "1px");
        assert parsed.sub("nth-of-type(2n)").property("font-size", "1px");
        assert parsed.sub("nth-of-type(odd)").property("font-size", "1px");
    }

    @Test
    public void nthLastType() throws Exception {
        ValidatableStyle parsed = style(() -> {
            nthLastOfType("1", () -> {
                font.size(1, px);
            });

            nthLastOfType("2n", () -> {
                font.size(1, px);
            });

            nthLastOfType("odd", () -> {
                font.size(1, px);
            });
        });

        assert parsed.sub("nth-last-of-type(1)").property("font-size", "1px");
        assert parsed.sub("nth-last-of-type(2n)").property("font-size", "1px");
        assert parsed.sub("nth-last-of-type(odd)").property("font-size", "1px");
    }

    @Test
    public void onlyChild() throws Exception {
        ValidatableStyle parsed = style(() -> {
            onlyChild(() -> {
                font.size(1, px);
            });
        });
        assert parsed.sub("only-child").property("font-size", "1px");
    }

    @Test
    public void onlyType() throws Exception {
        ValidatableStyle parsed = style(() -> {
            onlyOfType(() -> {
                font.size(1, px);
            });
        });
        assert parsed.sub("only-of-type").property("font-size", "1px");
    }

    @Test
    public void empty() throws Exception {
        ValidatableStyle parsed = style(() -> {
            empty(() -> {
                font.size(1, px);
            });
        });
        assert parsed.sub("empty").property("font-size", "1px");
    }

    private static Style E = () -> {
    };

    @Test
    public void not() throws Exception {
        ValidatableStyle parsed = style(() -> {
            not(E, () -> {
                font.size(1, px);
            });
        });
        assert parsed.sub("not(.jsx_style_PseudoClassTest___E)").property("font-size", "1px");
    }

    @Test
    public void nest() throws Exception {
        ValidatableStyle parsed = style(() -> {
            active(() -> {
                invalid(() -> {
                    font.size(1, px);
                });
            });
        });
        assert parsed.sub("active:invalid").property("font-size", "1px");
    }
}