/*
 * Copyright (C) 2016 Nameless Production Committee
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://opensource.org/licenses/mit-license.php
 */
package booton.soeur;

import java.util.ArrayList;
import java.util.List;

import booton.live.Source;
import net.sourceforge.htmlunit.corejs.javascript.EcmaError;
import net.sourceforge.htmlunit.corejs.javascript.ScriptStackElement;

/**
 * @version 2013/08/25 16:36:01
 */
@SuppressWarnings("serial")
public class ScriptRuntimeError extends Error {

    /** The actual message. */
    private final String message;

    /**
     * <p>
     * Build error infomation.
     * </p>
     * 
     * @param error
     * @return
     */
    ScriptRuntimeError(Source source, EcmaError error) {
        this.message = error.getErrorMessage();

        ScriptStackElement[] elements = error.getScriptStack();
        List<StackTraceElement> list = new ArrayList();

        for (int i = 0; i < elements.length; i++) {
            ScriptStackElement element = elements[i];
            int line = element.lineNumber;

            if (1 < line) {
                if (element.fileName.endsWith(".js")) {
                    list.add(new StackTraceElement(element.fileName, "", element.fileName, element.lineNumber));
                } else {
                    list.add(source.search(elements[i].lineNumber));
                }
            }
        }
        setStackTrace(list.toArray(new StackTraceElement[list.size()]));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getMessage() {
        return message;
    }
}
