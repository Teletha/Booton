/*
 * Copyright (C) 2014 Nameless Production Committee
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://opensource.org/licenses/mit-license.php
 */
package booton.soeur;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import net.sourceforge.htmlunit.corejs.javascript.Context;
import net.sourceforge.htmlunit.corejs.javascript.Function;
import net.sourceforge.htmlunit.corejs.javascript.JavaScriptException;
import net.sourceforge.htmlunit.corejs.javascript.Scriptable;
import net.sourceforge.htmlunit.corejs.javascript.ScriptableObject;
import net.sourceforge.htmlunit.corejs.javascript.annotations.JSStaticFunction;
import antibug.Async;

/**
 * <p>
 * {@link Async} implemetation for Javascript runtime which uses {@link Async} class.
 * </p>
 * 
 * @version 2014/01/10 20:37:55
 */
@SuppressWarnings("serial")
public class AsyncJSDefinition extends ScriptableObject {

    /** The task scheduler. */
    private static final ScheduledThreadPoolExecutor scheduler = Async.use();

    /** The task manager. */
    private static final Map<Integer, ScheduledFuture> tasks = new ConcurrentHashMap();

    /**
     * 
     */
    public AsyncJSDefinition() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getClassName() {
        return "Async";
    }

    @JSStaticFunction
    public static int setTimeout(Function function, int delay) {
        FunctionTask task = new FunctionTask(function);

        tasks.put(task.id, scheduler.schedule(task, delay, TimeUnit.MILLISECONDS));

        return task.id;
    }

    @JSStaticFunction
    public static void clearTimeout(int id) {
        ScheduledFuture task = tasks.remove(id);

        if (task != null) {
            task.cancel(true);
        }
    }

    /**
     * <p>
     * Wait script execution.
     * </p>
     * 
     * @param millseconds
     */
    @JSStaticFunction
    public static void wait(int millseconds) {
        Async.wait(millseconds);
    }

    /**
     * <p>
     * Await all background job's execution.
     * </p>
     * 
     * @param timeout
     */
    @JSStaticFunction
    public static void awaitTasks() {
        Async.awaitTasks();
    }

    /**
     * @version 2014/01/10 20:17:18
     */
    private static class FunctionTask implements Runnable {

        /** The id counter. */
        private static int conter = 0;

        /** The actual task. */
        private final Function function;

        /** The task id. */
        private final int id;

        /**
         * @param function
         */
        private FunctionTask(Function function) {
            this.function = function;
            this.id = conter++;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void run() {
            try {
                function.call(Context.enter(), ScriptTester.html.getScriptObject(), (Scriptable) ScriptTester.html.getEnclosingWindow()
                        .getScriptObject(), new Object[0]);
            } catch (JavaScriptException e) {
                // ignore
            } finally {
                Context.exit();
            }
        }
    }
}