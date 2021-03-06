/*
 * Copyright (C) 2016 Nameless Production Committee
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://opensource.org/licenses/mit-license.php
 */
package js.dom;

import booton.translator.JavascriptAPIProvider;
import booton.translator.JavascriptNative;

/**
 * @version 2013/07/27 15:57:10
 */
@JavascriptAPIProvider(targetJavaScriptClassName = "HTMLCollection")
public abstract class HTMLCollection extends NodeList<Element> implements JavascriptNative {
}
