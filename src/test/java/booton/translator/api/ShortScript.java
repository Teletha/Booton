/*
 * Copyright (C) 2012 Nameless Production Committee
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://opensource.org/licenses/mit-license.php
 */
package booton.translator.api;

/**
 * @version 2012/11/30 15:33:55
 */
public interface ShortScript {

    /**
     * Script fragment.
     * 
     * @param value A script input.
     * @return A script output.
     */
    short execute(short value);
}
