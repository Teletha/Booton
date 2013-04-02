/*
 * Copyright (C) 2013 Nameless Production Committee
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://opensource.org/licenses/mit-license.php
 */
package js.ui;

/**
 * @version 2013/04/02 3:04:15
 */
public interface ModelProvider<M> {

    int size();

    M item(int index);

    String name(M model);
}