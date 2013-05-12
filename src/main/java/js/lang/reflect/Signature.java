/*
 * Copyright (C) 2013 Nameless Production Committee
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://opensource.org/licenses/mit-license.php
 */
package js.lang.reflect;

/**
 * @version 2013/04/07 2:56:54
 */
public @interface Signature {

    String returnType();

    String[] parameterTypes();

    int modifier();
}
