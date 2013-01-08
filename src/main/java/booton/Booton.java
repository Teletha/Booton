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

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;

import js.Application;
import js.application.ApplicationTheme;
import kiss.I;
import kiss.XML;
import teemowork.Teemowork;
import booton.live.LiveCoding;
import booton.translator.Javascript;
import booton.util.HTMLWriter;

/**
 * @version 2012/12/10 23:06:05
 */
public class Booton {

    /** The application class. */
    private final Class<? extends Application> application;

    /** The application design. */
    private final Class<? extends ApplicationTheme> design;

    /**
     * <p>
     * Booton web application builder.
     * </p>
     */
    public Booton(Class<? extends Application> application) {
        this(application, null);
    }

    /**
     * <p>
     * Booton web application builder.
     * </p>
     */
    public Booton(Class<? extends Application> application, Class<? extends ApplicationTheme> design) {
        this.application = application;
        this.design = design == null ? ApplicationTheme.class : design;
    }

    /**
     * <p>
     * Build application.
     * </p>
     */
    public void build() {
        build(null);
    }

    /**
     * <p>
     * Build application.
     * </p>
     * 
     * @param output An output location
     */
    public void build(Path output) {
        // normalize output directory
        if (output == null) {
            output = I.locate("");
        }

        if (Files.isRegularFile(output)) {
            output = output.getParent();
        }

        Path mutex = output.resolve("publishing");

        try {
            // load booton extensions
            I.load(Booton.class, true);

            // load built-in library
            I.load(Application.class, true);

            // load application extensions
            I.load(application, true);

            Files.createFile(mutex);

            buildHTML(output.resolve("test.html"));

            // build js file
            Javascript.getScript(application).writeTo(output.resolve("test.js"));
            Javascript.getScript(LiveCoding.class).writeTo(output.resolve("live.js"));

            // build css file
            I.make(StylesheetManager.class).write(output.resolve("test.css"));
        } catch (Exception e) {
            e.printStackTrace(System.out);
        } finally {
            I.delete(mutex);
        }
    }

    /**
     * <p>
     * Build html file.
     * </p>
     * 
     * @param file
     */
    private void buildHTML(Path file) throws Exception {
        long now = new Date().getTime();

        XML html = I.xml("html");
        XML head = html.child("head");
        head.child("meta").attr("charset", "utf-8");
        head.child("link").attr("type", "text/css").attr("rel", "stylesheet").attr("href", "normalize.css?" + now);
        head.child("link").attr("type", "text/css").attr("rel", "stylesheet").attr("href", "test.css?" + now);

        XML body = html.child("body");
        body.child("header").attr("id", "Header");
        body.child("div").attr("id", "Content");
        body.child("footer").attr("id", "Footer");

        body.child("script").attr("type", "text/javascript").attr("src", "jquery.js?" + now);
        body.child("script").attr("type", "text/javascript").attr("src", "boot.js?" + now);
        body.child("script").attr("type", "text/javascript").attr("src", "test.js?" + now);
        body.child("script").attr("type", "text/javascript").attr("src", "live.js?" + now);

        html.to(new HTMLWriter(Files.newBufferedWriter(file, I.$encoding)));
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        Booton booton = new Booton(Teemowork.class);
        booton.build();
    }
}
