/*
 * Copyright (C) 2016 Nameless Production Committee
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://opensource.org/licenses/mit-license.php
 */
package jsx.application;

import static js.lang.Global.*;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import js.dom.DocumentFragment;
import js.dom.UIEvent;
import js.lang.NativeArray;
import js.lang.NativeFunction;
import jsx.ui.User;
import jsx.ui.Widget;
import kiss.Decoder;
import kiss.Encoder;
import kiss.I;
import kiss.Interceptor;
import kiss.Manageable;
import kiss.Singleton;
import kiss.model.Model;

/**
 * @version 2015/08/18 10:27:23
 */
@Manageable(lifestyle = Singleton.class)
public abstract class Application<R extends ApplicationRouter> {

    public R router;

    /** The path router. */
    private final NativeArray<PageRoute> paths = new NativeArray();

    /**
     * Initialize application.
     */
    protected Application() {
        Type[] types = Model.collectParameters(getClass(), Application.class);

        if (types.length != 1) {
            throw new IllegalArgumentException("Application must have single router interface parameter.");
        }

        Class routerInterface = (Class) types[0];

        if (routerInterface.isInterface() == false) {
            throw new IllegalArgumentException("Application must have single router interface parameter");
        }

        root: for (Method method : routerInterface.getMethods()) {
            if (method.isAnnotationPresent(Route.class)) {
                // Return type must be Widget
                if (!Widget.class.isAssignableFrom(method.getReturnType())) {
                    continue;
                }

                StringBuilder pattern = new StringBuilder(method.getName());

                // All parameters must be decodable.
                Class[] parameTypes = method.getParameterTypes();
                Decoder[] decoders = new Decoder[parameTypes.length];

                for (int i = 0; i < decoders.length; i++) {
                    Decoder decoder = I.find(Decoder.class, parameTypes[i]);

                    if (decoder == null) {
                        continue root;
                    }
                    decoders[i] = decoder;
                    pattern.append("/").append("([^/].+)");
                }

                paths.push(new PageRoute(pattern.toString(), decoders, method));
            }
        }

        router = (R) Proxy.newProxyInstance(null, new Class[] {routerInterface}, new Router());

        // Activate router system.
        window.addEventListener(User.HashChange.name, new NativeFunction<UIEvent>(event -> {
            dispatch(location.hash);
        }));
    }

    /**
     * <p>
     * Dispatch page by the specified path.
     * </p>
     * 
     * @param path
     */
    void dispatch(String path) {
        if (path.length() != 0 && path.startsWith("#")) {
            path = path.substring(1);
        }

        if (path.length() == 0) {
            show(path, null);
            return;
        }

        for (int i = 0; i < paths.length(); i++) {
            Widget widget = paths.get(i).dispatch(path);

            if (widget != null) {
                return;
            }
        }
        show(path, null);
    }

    /**
     * <p>
     * Show the specified {@link Widget}.
     * </p>
     * 
     * @param path A client specified path.
     * @param widget An associated widget of the specified path.
     */
    private void show(String path, Widget widget) {
        if (widget == null) {
            widget = findDefault();
        }

        // create element cradle
        DocumentFragment cradle = document.createDocumentFragment();

        // build page element
        widget.renderIn(cradle.child("div"));

        // clear old page and append new page
        document.contentElement().empty().append(cradle);
    }

    /**
     * <p>
     * Helper method to retrieve the default widget.
     * </p>
     * 
     * @return
     */
    private Widget findDefault() {
        Supplier<Widget> supplier = router.defaultWidget();

        if (supplier != null) {
            Widget widget = supplier.get();

            if (widget != null) {
                return widget;
            }
        }

        throw new IllegalStateException("Default widget is not found.");
    }

    /**
     * <p>
     * Initialize application.
     * </p>
     * 
     * @param applicationClass An application class to start.
     * @param configurator You can configure your application.
     */
    protected static <A extends Application> void initialize(Class<A> applicationClass) {
        A application = I.make(applicationClass);

        // View initial page by URL.
        application.dispatch(location.hash);
    }

    /**
     * @version 2015/08/18 14:47:12
     */
    private class PageRoute {

        /** The path pattern. */
        private final Pattern pattern;

        /** The parameter pattern. */
        private final Decoder[] decoders;

        /** The dispatch method. */
        private final Method method;

        /**
         * @param pattern
         * @param decoders
         * @param method
         */
        private PageRoute(String pattern, Decoder[] decoders, Method method) {
            this.pattern = Pattern.compile(pattern);
            this.decoders = decoders;
            this.method = method;
        }

        /**
         * <p>
         * Dispatch by the specified path pattern.
         * </p>
         */
        private Widget dispatch(String path) {
            Matcher matcher = pattern.matcher(path);

            if (!matcher.matches() || matcher.groupCount() != decoders.length) {
                return null;
            }

            Object[] parameters = new Object[decoders.length];

            for (int i = 0; i < decoders.length; i++) {
                parameters[i] = decoders[i].decode(matcher.group(i + 1));
            }

            try {
                return (Widget) method.invoke(router, parameters);
            } catch (Exception e) {
                throw I.quiet(e);
            }
        }
    }

    /** The cache for root widget. */
    private final static Map<String, Object> cache = new HashMap();

    /**
     * @version 2015/08/18 16:48:41
     */
    @Manageable(lifestyle = Singleton.class)
    private class Router extends Interceptor<Route>implements InvocationHandler {

        /**
         * {@inheritDoc}
         */
        @Override
        protected Object invoke(Object... params) {
            // rebuild path
            StringBuilder builder = new StringBuilder("#").append(name);

            for (Object param : params) {
                builder.append("/").append(I.find(Encoder.class, param.getClass()).encode(param));
            }

            // update history if needed
            String path = builder.toString();

            // create widget for this path
            Object widget;

            if (annotation.cache()) {
                widget = cache.computeIfAbsent(path, p -> super.invoke(params));
            } else {
                widget = super.invoke(params);
            }

            // validation
            if (that instanceof Application && widget instanceof Widget) {
                if (!location.hash.equals(path)) {
                    history.pushState("", "", path);
                }

                // render widget
                ((Application) that).show(path, (Widget) widget);
            }
            return widget;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Object invoke(Object proxy, Method method, Object[] params) throws Throwable {
            // rebuild path
            StringBuilder builder = new StringBuilder("#").append(method.getName());

            for (Object param : params) {
                builder.append("/").append(I.find(Encoder.class, param.getClass()).encode(param));
            }

            // update history if needed
            String path = builder.toString();
            System.out.println(path);
            // create widget for this path
            Object widget;

            Route route = method.getAnnotation(Route.class);

            if (route != null && route.cache()) {
                widget = cache.computeIfAbsent(path, p -> call(proxy, method, params));
            } else {
                widget = call(proxy, method, params);
            }

            // validation
            if (widget instanceof Widget) {
                if (!location.hash.equals(path)) {
                    history.pushState("", "", path);
                }

                // render widget
                show(path, (Widget) widget);
            }
            return widget;
        }

        private Object call(Object proxy, Method method, Object[] args) {
            try {
                if (method.isDefault()) {
                    Class target = method.getDeclaringClass();
                    return MethodHandles.lookup().in(target).unreflectSpecial(method, target).bindTo(proxy).invokeWithArguments(args);
                } else {
                    return method.invoke(proxy, args);
                }
            } catch (Throwable e) {
                throw I.quiet(e);
            }
        }
    }
}
