/*
 * Copyright (C) 2015 Nameless Production Committee
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://opensource.org/licenses/mit-license.php
 */
package jsx.ui;

import static jsx.ui.VirtualStructureStyle.*;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collection;
import java.util.Deque;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.function.Supplier;

import javafx.beans.value.ObservableValue;

import js.lang.NativeString;
import jsx.style.ContextualizedStyle;
import jsx.style.Style;
import jsx.style.StyleManager;
import jsx.ui.ContextualizedEventListeners.EventListener;

/**
 * @version 2014/09/13 1:52:02
 */
public final class VirtualStructure {

    /**
     * <p>
     * Define stackable container and get the descriptor of the container element.
     * </p>
     * <p>
     * This field is equivalent to the method call <code>sbox(auto-assignment-id)</code>.
     * </p>
     * 
     * @see #sbox(int)
     */
    public final ContainerDescriptor nbox = new ContainerDescriptor("span", NBOX);

    /** The descriptor of properties. */
    public final AttributeDescriptor attr = new AttributeDescriptor();

    /** The latest context line number. */
    protected int latestContextId;

    /** The local id modifier. */
    protected int modifier;

    /** The node route. */
    private final Deque<VirtualElement> parents = new ArrayDeque();

    /** The associated widget. */
    private final Widget widget;

    /** The latest element. */
    public static VirtualElement latest;

    /** The context object. */
    private Object context;

    /**
     * 
     */
    public VirtualStructure() {
        this(null, new VirtualElement(0, "div"));
    }

    /**
     * 
     */
    public VirtualStructure(Widget widget) {
        this(widget, new VirtualElement(0, "div"));
    }

    /**
     * 
     */
    public VirtualStructure(Widget widget, VirtualElement root) {
        parents.add(latest = root);

        this.widget = widget;

        root.contextualized = createSpecifiedListenerDifinitions(WidgetStyle.Root);
    }

    /**
     * @return
     */
    public Path path() {
        return path(LocalId.findContextLineNumber());
    }

    /**
     * <p>
     * For compiler.
     * </p>
     * 
     * @param id An identifier of the current element.
     * @return Chainable API.
     */
    private Path path(int id) {
        return new Path(id);
    }

    /**
     * @return
     */
    public PolyLine polyline() {
        return polyline(LocalId.findContextLineNumber());
    }

    /**
     * <p>
     * For compiler.
     * </p>
     * 
     * @param id An identifier of the current element.
     * @return Chainable API.
     */
    private PolyLine polyline(int id) {
        return new PolyLine(id);
    }

    /**
     * @return
     */
    public Rect rect() {
        return rect(LocalId.findContextLineNumber());
    }

    /**
     * <p>
     * For compiler.
     * </p>
     * 
     * @param id An identifier of the current element.
     * @return Chainable API.
     */
    private Rect rect(int id) {
        return new Rect(id);
    }

    /**
     * <p>
     * Define children of the current processing element.
     * </p>
     * 
     * @param item A list of children items.
     */
    public void 〡(Object... items) {
        for (Object item : items) {
            process(latest, item);
        }
    }

    /**
     * <p>
     * Define the child of the current processing element.
     * </p>
     * 
     * @param item A child item.
     */
    public void 〡(Object item) {
        process(latest, item);
    }

    /**
     * <p>
     * Define stackable container with local id.
     * </p>
     * 
     * @param localId A local id for the container element.
     * @return A descriptor of the container element.
     * @see #sbox
     */
    public final ContainerDescriptor nbox(int localId) {
        nbox.localId = localId;
        return nbox;
    }

    /**
     * <p>
     * Define html element with local id.
     * </p>
     * 
     * @param name A element name.
     * @param localId A local id for the container element.
     * @return A descriptor of the container element.
     */
    public final ContainerDescriptor e(String name, int localId) {
        ContainerDescriptor container = new ContainerDescriptor(name, null);
        container.localId = localId;

        return container;
    }

    /**
     * <p>
     * Define element with some attributes.
     * </p>
     * 
     * @param name A element name.
     * @param localId A local id for the container element.
     * @return A descriptor of the container element.
     */
    public final ContainerDescriptor e(String name, String... attributes) {
        ContainerDescriptor container = new ContainerDescriptor(name, null);
        VirtualElement element = container.container(0, null);

        for (int i = 0; i < attributes.length; i++) {
            element.attributes.add(attributes[i], attributes[++i]);
        }

        if (name.equals("svg")) {
            element.attributes.add("version", "1.1");
            element.attributes.add("preserveAspectRatio", "xMidYMid meet");
        }

        return container;
    }

    /**
     * <p>
     * Define element with some attributes.
     * </p>
     * 
     * @param name A element name.
     * @param localId A local id for the container element.
     * @return A descriptor of the container element.
     */
    public final ContainerDescriptor e(String name, Style style, Object... attributes) {
        ContainerDescriptor container = new ContainerDescriptor(name, null);
        VirtualElement element = container.container(0, style);

        for (int i = 0; i < attributes.length; i++) {
            if (attributes[i] != null && attributes[i + 1] != null) {
                element.attributes.add(String.valueOf(attributes[i]), String.valueOf(attributes[++i]));
            }
        }

        if (name.equals("svg")) {
            element.attributes.add("version", "1.1");
            element.attributes.add("preserveAspectRatio", "xMidYMid meet");
        }

        return container;
    }

    /**
     * <p>
     * Retrieve the root {@link VirtualElement}.
     * </p>
     * 
     * @return A single root element.
     */
    protected final VirtualElement getRoot() {
        return latest;
    }

    private void process(VirtualElement container, Object child) {
        if (child instanceof Widget) {
            Widget widget = (Widget) child;
            VirtualWidget virtualize = new VirtualWidget(widget.id, widget);
            container.items.push(virtualize);
            widget.assemble(new VirtualStructure(widget, virtualize));
        } else if (child instanceof String && child.equals("\r\n")) {
            container.items.push(new VirtualElement(0, "br"));
        } else {
            container.items.push(new VirtualText(String.valueOf(child)));
        }
    }

    private ContextualizedEventListeners createSpecifiedListenerDifinitions(Style style) {
        if (widget == null) {
            return null;
        }

        List<EventListener> listeners = widget.getEventListenersFor(style.locator());

        if (listeners == null) {
            return null;
        }

        return new ContextualizedEventListeners(style instanceof ContextualizedStyle ? ((ContextualizedStyle) style).context : context, listeners);
    }

    /**
     * @version 2015/01/21 14:20:59
     */
    public class ContainerDescriptor {

        /** The container element name. */
        private final String name;

        /** The built-in style. */
        private final Style builtin;

        /** The container {@link VirtualElement}. */
        private VirtualElement container;

        /** The local id. */
        private int localId;

        /** The latest local id. */
        private int latestLocalId;

        /**
         * <p>
         * DSL to define element.
         * </p>
         * 
         * @param namespace A container element namespace uri.
         * @param name A container element name.
         * @param style A element style.
         */
        private ContainerDescriptor(String name, Style builtin) {
            this.name = name;
            this.builtin = builtin;
        }

        /**
         * <p>
         * Retrieve the current container element.
         * </p>
         * 
         * @return The current container element.
         */
        private final VirtualElement container(int contextId, Style style) {
            // This process is used in java environment only. (because js implementation of
            // LocalId always return 0)
            if (contextId != 0 && latestContextId != contextId) {
                if (localId == latestContextId) {
                    localId = contextId;
                }
                // update context line number
                latestContextId = contextId;
            }

            //
            if (localId != 0 && latestLocalId != localId) {
                latestLocalId = localId;
                container = null;
            }

            if (container == null) {
                int id = localId;

                if (id == 0) {
                    localId = id = LocalId.generate();
                }

                if (modifier != 0) {
                    latestLocalId = id = (31 + id) ^ modifier;
                }

                // built-in container
                container = new VirtualElement(id, name);

                if (builtin != null) {
                    container.classList.push(builtin);
                }
                latest.items.push(container);

                if (style != null) {
                    WidgetLog.Style.start();
                    style.assignTo(container.classList, container.inlines);
                    container.contextualized = createSpecifiedListenerDifinitions(style);

                    StyleManager.add(style);
                    WidgetLog.Style.stop();
                }
            }

            return container;
        }

        /**
         * <p>
         * Define children.
         * </p>
         * 
         * @param children A list of child nodes.
         */
        public final void 〡(Style style, String text) {
            if (text != null && text.length() != 0) {
                〡(style, new Object[] {text});
            }
        }

        public final void 〡(Style style, Widget widget) {
            // store the current context
            VirtualElement container = container(LocalId.findContextLineNumber(), style);
            if (style != null) style.assignTo(container.classList, container.inlines);

            // enter into the child node
            parents.addLast(latest = container);

            // process into child nodes
            // create virtual element for this widget
            VirtualWidget virtualize = new VirtualWidget(widget.id, widget);

            // mount virtual element on virtual structure
            container.items.push(virtualize);

            // process child nodes
            widget.assemble(new VirtualStructure(widget, virtualize));

            // leave from the child node
            parents.pollLast();
            latest = parents.peekLast();
        }

        /**
         * <p>
         * Define children.
         * </p>
         * 
         * @param children A list of child nodes.
         */
        public final void 〡(Style style) {
            // store the current context
            container(LocalId.findContextLineNumber(), style);
        }

        /**
         * <p>
         * Define children.
         * </p>
         * 
         * @param children A list of child nodes.
         */
        public final void 〡(Style style, Object... children) {
            // store the current context
            VirtualElement container = container(LocalId.findContextLineNumber(), style);

            // enter into the child node
            parents.addLast(latest = container);

            // process into child nodes
            for (Object child : children) {
                process(container, child);
            }

            // leave from the child node
            parents.pollLast();
            latest = parents.peekLast();
        }

        /**
         * <p>
         * Define children.
         * </p>
         * 
         * @param children A list of child widget.
         */
        public final <T> void 〡(Style style, Class<? extends Widget1<T>> childType, T child) {
            〡(style, childType, Arrays.asList(child));
        }

        /**
         * <p>
         * Define children.
         * </p>
         * 
         * @param children A list of child widget.
         */
        public final <T> void 〡(Style style, Class<? extends Widget1<T>> childType, T[] children) {
            〡(style, childType, Arrays.asList(children));
        }

        /**
         * <p>
         * Define children.
         * </p>
         * 
         * @param children A list of child widget.
         */
        public final <T> void 〡(Style style, Class<? extends Widget1<T>> childType, Collection<T> children) {
            // store the current context
            VirtualElement container = container(LocalId.findContextLineNumber(), style);

            // enter into the child node
            parents.addLast(latest = container);

            // process into child nodes

            for (T child : children) {
                Widget widget = Widget.of(childType, child);

                // create virtual element for this widget
                VirtualWidget virtualize = new VirtualWidget(widget.id, widget);

                // mount virtual element on virtual structure
                container.items.push(virtualize);

                // process child nodes
                widget.assemble(new VirtualStructure(widget, virtualize));
            }

            // leave from the child node
            parents.pollLast();
            latest = parents.peekLast();
        }

        /**
         * <p>
         * Define children.
         * </p>
         * 
         * @param children A list of child nodes.
         */
        public final void 〡(Runnable children) {
            〡((Style) null, children);
        }

        /**
         * <p>
         * Define children.
         * </p>
         * 
         * @param children A list of child nodes.
         */
        public final void 〡(Style style, Runnable children) {
            // store the current context
            VirtualElement container = container(LocalId.findContextLineNumber(), style);

            // then, clean it for nested invocation
            parents.addLast(latest = container);
            this.container = null;

            // precess into child items
            children.run();

            // restore context environment
            parents.pollLast();
            latest = parents.peekLast();
        }

        /**
         * <p>
         * Define children.
         * </p>
         * 
         * @param children A list of child widget.
         */
        public final <T> void 〡(Style style, T[] items, Consumer<T> child) {
            〡(style, Arrays.asList(items), child);
        }

        /**
         * <p>
         * Define children.
         * </p>
         * 
         * @param children A list of child widget.
         */
        public final <T> void 〡(Style style, Collection<T> items, Consumer<T> child) {
            〡(style, () -> {
                Object last = context;

                for (T item : items) {
                    context = item;
                    modifier = Objects.hashCode(item);
                    child.accept(item);
                }
                context = last;
            });
        }

        /**
         * <p>
         * Define children.
         * </p>
         * 
         * @param children A list of child widget.
         */
        public final <T> void 〡(Style style, int size, IntConsumer child) {
            〡(style, () -> {
                for (int i = 0; i < size; i++) {
                    modifier = (i + 117 + latestContextId) * 31;
                    child.accept(i);
                }
            });
        }
    }

    /**
     * @version 2015/01/21 13:18:17
     */
    public class AttributeDescriptor {

        /**
         * <p>
         * Define attribute of the latest element.
         * </p>
         * 
         * @param name An attribute name.
         * @param value An attribute value.
         * @return A descriptor for property.
         */
        public AttributeDescriptor 〡(String name, String value) {
            VirtualElement e = parents.peekLast();
            e.attributes.add(name, value);

            return this;
        }

        /**
         * <p>
         * Define attribute of the latest element.
         * </p>
         * 
         * @param name An attribute name.
         * @param value An attribute value.
         * @return A descriptor for property.
         */
        public AttributeDescriptor 〡(String name, Supplier value) {
            if (value == null) {
                return this;
            } else {
                return 〡(name, String.valueOf(value.get()));
            }
        }

        /**
         * <p>
         * Define attribute of the latest element.
         * </p>
         * 
         * @param name An attribute name.
         * @param value An attribute value.
         * @return A descriptor for property.
         */
        public AttributeDescriptor 〡(String name, ObservableValue value) {
            if (value == null) {
                return this;
            } else {
                return 〡(name, String.valueOf(value.getValue()));
            }
        }

        /**
         * <p>
         * Add {@link Style} if the given attribute is valid. < /p>
         * 
         * @param name An attribute name.
         * @param value An attribute value.
         * @param style A target style to apply.
         */
        public AttributeDescriptor 〡(String name, String value, Style style) {
            if (name != null && name.length() != 0 && value != null && value.length() != 0) {
                style.assignTo(latest.classList, latest.inlines);
                latest.attributes.add(name, value);
            }
            return this;
        }
    }

    /**
     * @version 2015/03/23 15:58:26
     */
    public class DescriptableElement<E> extends VirtualElement {

        /**
         * @param id
         * @param name
         */
        private DescriptableElement(int id, String name) {
            super(id, name);

            latest.items.push(this);
        }

        public E style(Style style) {
            style.assignTo(classList, inlines);

            return (E) this;
        }

        /**
         * <p>
         * Define children.
         * </p>
         * 
         * @param children A list of child widget.
         */
        public final <T> void 〡(Class<? extends Widget1<T>> childType, T child) {
            〡(childType, Arrays.asList(child));
        }

        /**
         * <p>
         * Define children.
         * </p>
         * 
         * @param children A list of child widget.
         */
        public final <T> void 〡(Class<? extends Widget1<T>> childType, T[] children) {
            〡(childType, Arrays.asList(children));
        }

        /**
         * <p>
         * Define children.
         * </p>
         * 
         * @param children A list of child widget.
         */
        public final <T> void 〡(Class<? extends Widget1<T>> childType, Collection<T> children) {
            〡(() -> {
                for (T child : children) {
                    Widget widget = Widget.of(childType, child);

                    // create virtual element for this widget
                    VirtualWidget virtualize = new VirtualWidget(widget.id, widget);

                    // mount virtual element on virtual structure
                    latest.items.push(virtualize);

                    // process child nodes
                    widget.assemble(new VirtualStructure(widget, virtualize));
                }
            });
        }

        /**
         * <p>
         * Define children.
         * </p>
         * 
         * @param children A list of child widget.
         */
        public final <T> void 〡(T[] items, Consumer<T> child) {
            〡(Arrays.asList(items), child);
        }

        public void 〡(Runnable children) {
            parents.add(latest = this);

            children.run();

            // restore context environment
            parents.pollLast();
            latest = parents.peekLast();
        }

        /**
         * <p>
         * Define children.
         * </p>
         * 
         * @param children A list of child widget.
         */
        public final <T> void 〡(Collection<T> items, Consumer<T> child) {
            〡(() -> {
                for (T item : items) {
                    modifier = item.hashCode();
                    child.accept(item);
                }
            });
        }

        /**
         * <p>
         * Define children.
         * </p>
         * 
         * @param children A list of child widget.
         */
        public final <T> void 〡(int size, IntConsumer child) {
            〡(() -> {
                for (int i = 0; i < size; i++) {
                    modifier = (i + 117 + latestContextId) * 31;
                    child.accept(i);
                }
            });
        }

        /**
         * <p>
         * Helper method to append attribute value.
         * </p>
         * 
         * @param name An attribute name.
         * @param value An attribute value to append.
         * @return Chainable API.
         */
        protected final E edit(String name, NativeString value) {
            int index = attributes.key(name);
            if (index == -1) {
                attributes.add(name, value.toString().trim());
            } else {
                attributes.values().set(index, new NativeString(attributes.value(index)).concat(value).toString());
            }
            return (E) this;
        }
    }

    /**
     * @version 2015/03/23 16:31:44
     */
    public class SVG extends DescriptableElement<SVG> {

        /**
         * 
         */
        private SVG(int id) {
            super(id, "s:svg");

            attributes.set("preserveAspectRatio", "xMidYMid meet");
        }

        /**
         * <p>
         * The viewBox attribute allows to specify that a given set of graphics stretch to fit a
         * particular container element.
         * </p>
         * <p>
         * The value of the viewBox attribute is a list of four numbers min-x, min-y, width and
         * height, separated by whitespace and/or a comma, which specify a rectangle in user space
         * which should be mapped to the bounds of the viewport established by the given element,
         * taking into account attribute preserveAspectRatio.
         * </p>
         * <p>
         * Negative values for width or height are not permitted and a value of zero disables
         * rendering of the element.
         * </p>
         * 
         * @param minX
         * @param minY
         * @param width
         * @param height
         */
        public SVG viewBox(int minX, int minY, int width, int height) {
            attributes.set("viewBox", new NativeString(minX).concat(" ")
                    .concat(minY)
                    .concat(" ")
                    .concat(width)
                    .concat(" ")
                    .concat(height)
                    .toString());

            return this;
        }
    }

    /**
     * @version 2015/03/28 2:46:12
     */
    public class Rect extends DescriptableElement<Rect> {

        /**
         * @param id
         * @param name
         */
        private Rect(int id) {
            super(id, "s:rect");
        }

        public Rect position(double x, double y) {
            attributes.set("x", String.valueOf(x));
            attributes.set("y", String.valueOf(y));

            return this;
        }

        public Rect size(double width, double height) {
            attributes.set("width", String.valueOf(width));
            attributes.set("height", String.valueOf(height));

            return this;
        }
    }

    /**
     * @version 2015/03/28 2:21:56
     */
    public class PolyLine extends DescriptableElement<PolyLine> {

        /**
         * @param id
         * @param name
         */
        private PolyLine(int id) {
            super(id, "s:polyline");
        }

        public PolyLine points(double... points) {
            StringJoiner joiner = new StringJoiner(" ");

            for (int i = 0; i < points.length;) {
                joiner.add(String.valueOf(points[i++])).add(",").add(String.valueOf(points[i++]));
            }
            attributes.set("points", joiner.toString());

            return this;
        }
    }

    /**
     * @version 2015/03/23 15:53:01
     */
    public class Path extends DescriptableElement<Path> {

        /** The current draw mode. */
        private boolean relativeMode = false;

        /**
         * @param id
         * @param name
         */
        private Path(int id) {
            super(id, "s:path");
        }

        /**
         * <p>
         * Set the start position to draw.
         * </p>
         * 
         * @param x A horizontal position.
         * @param y A vertical position.
         * @return Chainable API.
         */
        public Path moveTo(double x, double y) {
            return edit("d", command("M").concat(x).concat(" ").concat(y));
        }

        /**
         * <p>
         * Draw line to the specified position.
         * </p>
         * 
         * @param x A horizontal position.
         * @param y A vertical position.
         * @return Chainable API.
         */
        public Path lineTo(double x, double y) {
            return edit("d", command("L").concat(x).concat(" ").concat(y));
        }

        /**
         * <p>
         * Draw horizontal line to the specified position.
         * </p>
         * 
         * @param x A horizontal position.
         * @return Chainable API.
         */
        public Path hlineTo(double x) {
            return edit("d", command("H").concat(x));
        }

        /**
         * <p>
         * Draw vertical line to the specified position.
         * </p>
         * 
         * @param y A vertical position.
         * @return Chainable API.
         */
        public Path vlineTo(double y) {
            return edit("d", command("V").concat(y));
        }

        /**
         * <p>
         * Close the current path.
         * </p>
         * 
         * @return Chainable API.
         */
        public Path end() {
            return edit("d", command("Z"));
        }

        /**
         * <p>
         * Draw cubic Bézier curve to the path. It requires three points. The first two points are
         * control points and the third one is the end point. The starting point is the last point
         * in the current path, which can be changed using moveTo() before creating the Bézier
         * curve.
         * </p>
         * 
         * @param cp1x The x axis of the coordinate for the first control point.
         * @param cp1y The y axis of the coordinate for first control point.
         * @param cp2x The x axis of the coordinate for the second control point.
         * @param cp2y The y axis of the coordinate for the second control point.
         * @param x The x axis of the coordinate for the end point.
         * @param y The y axis of the coordinate for the end point.
         * @return Chainable API.
         */
        public Path curveTo(double cp1x, double cp1y, double cp2x, double cp2y, double x, double y) {
            return edit("d", command("C").concat(cp1x)
                    .concat(" ")
                    .concat(cp1y)
                    .concat(" ")
                    .concat(cp2x)
                    .concat(" ")
                    .concat(cp2y)
                    .concat(" ")
                    .concat(x)
                    .concat(" ")
                    .concat(y));
        }

        /**
         * <p>
         * Draw cubic Bézier curve to the path. It requires three points. The first two points are
         * control points and the third one is the end point. The starting point is the last point
         * in the current path, which can be changed using moveTo() before creating the Bézier
         * curve.
         * </p>
         * 
         * @param cp1x The x axis of the coordinate for the first control point.
         * @param cp1y The y axis of the coordinate for first control point.
         * @param cp2x The x axis of the coordinate for the second control point.
         * @param cp2y The y axis of the coordinate for the second control point.
         * @param x The x axis of the coordinate for the end point.
         * @param y The y axis of the coordinate for the end point.
         * @return Chainable API.
         */
        public Path curveRelativeTo(double cp1x, double cp1y, double cp2x, double cp2y, double x, double y) {
            return edit("d", command("C").concat(cp1x)
                    .concat(" ")
                    .concat(cp1y)
                    .concat(" ")
                    .concat(cp2x)
                    .concat(" ")
                    .concat(cp2y)
                    .concat(" ")
                    .concat(x)
                    .concat(" ")
                    .concat(y));
        }

        /**
         * <p>
         * Make drawing context relative.
         * </p>
         * 
         * @return
         */
        public final Path relatively() {
            relativeMode = true;

            return this;
        }

        /**
         * <p>
         * Make drawing context relative.
         * </p>
         * 
         * @return
         */
        public final Path absolutely() {
            relativeMode = false;

            return this;
        }

        /**
         * <p>
         * Make command expression.
         * </p>
         * 
         * @param command
         * @return
         */
        private final NativeString command(String command) {
            return new NativeString(" ").concat(relativeMode ? command.toLowerCase() : command).concat(" ");
        }
    }

    /**
     * @version 2015/09/14 16:51:36
     */
    public static class Declarables {

        public static void box(Declarable... declarables) {
            element("span", declarables);
        }

        /**
         * <p>
         * Define children.
         * </p>
         * 
         * @param children A list of child widget.
         */
        public static <T> void box(Declarable style, Class<? extends Widget1<T>> childType, List<T> children) {
            element(0, "span", new Declarable[] {style}, () -> {
                for (T child : children) {
                    Widget.of(childType, child).declare();
                }
            });
        }

        public static void svg(Declarable... declarables) {
            element("s:svg", declarables);
        }

        public static void rect(Declarable... declarables) {
            element("s:rect", declarables);
        }

        public static void path(Declarable... declarables) {
            element("s:path", declarables);
        }

        public static void con(Object... contents) {
            for (Object content : contents) {
                if (content instanceof Declarable) {
                    ((Declarable) content).declare();
                } else if (content instanceof String && content.equals("\r\n")) {
                    latest.items.push(new VirtualElement(0, "br"));
                } else {
                    latest.items.push(new VirtualText(String.valueOf(content)));
                }
            }
        }

        public static void element(String name, Declarable... declarables) {
            element(0, name, declarables, null);
        }

        private static void element(int id, String name, Declarable[] declarables, Runnable process) {
            VirtualElement element = new VirtualElement(id, name);

            // enter into the child node (store context)
            VirtualElement parent = latest;
            latest = element;

            for (int i = 0, length = declarables.length; i < length; i++) {
                if (declarables[i] != null) declarables[i].define();
            }

            if (process != null) {
                process.run();
            }

            // leave from the child node (revert context)
            latest = parent;

            if (latest != null) {
                latest.items.push(element);
            }
        }

        /**
         * <p>
         * The viewBox attribute allows to specify that a given set of graphics stretch to fit a
         * particular container element.
         * </p>
         * <p>
         * The value of the viewBox attribute is a list of four numbers min-x, min-y, width and
         * height, separated by whitespace and/or a comma, which specify a rectangle in user space
         * which should be mapped to the bounds of the viewport established by the given element,
         * taking into account attribute preserveAspectRatio.
         * </p>
         * <p>
         * Negative values for width or height are not permitted and a value of zero disables
         * rendering of the element.
         * </p>
         * 
         * @param minX
         * @param minY
         * @param width
         * @param height
         */
        public static Declarable viewBox(int minX, int minY, int width, int height) {
            return attr("viewBox", new NativeString(minX).concat(" ")
                    .concat(minY)
                    .concat(" ")
                    .concat(width)
                    .concat(" ")
                    .concat(height)
                    .toString());
        }

        public static Declarable position(double x, double y) {
            return () -> {
                latest.attributes.add("x", String.valueOf(x));
                latest.attributes.add("y", String.valueOf(y));
            };
        }

        public static Declarable size(double width, double height) {
            return () -> {
                latest.attributes.add("width", String.valueOf(width));
                latest.attributes.add("height", String.valueOf(height));
            };
        }

        public static SVGPath d() {
            return new SVGPath();
        }

        /**
         * <p>
         * Declare "placeholder" attribute with the specified value.
         * </p>
         * 
         * @param type A value of "placeholder" attribute.
         * @return An attribute declaration.
         */
        public static Declarable placeholder(String placeholder) {
            return attr("placeholder", placeholder);
        }

        /**
         * <p>
         * Declare "type" attribute with the specified value.
         * </p>
         * 
         * @param type A value of "type" attribute.
         * @return An attribute declaration.
         */
        public static Declarable type(String type) {
            return attr("type", type);
        }

        /**
         * <p>
         * Declare "value" attribute with the specified value.
         * </p>
         * 
         * @param value A value of "value" attribute.
         * @return An attribute declaration.
         */
        public static Declarable value(String value) {
            return attr("value", value);
        }

        /**
         * <p>
         * Declare "id" attribute with the specified value.
         * </p>
         * 
         * @param id A value of "id" attribute.
         * @return An attribute declaration.
         */
        public static Declarable id(String id) {
            return attr("id", id);
        }

        /**
         * <p>
         * Declare "xlink:href" attribute with the specified value.
         * </p>
         * 
         * @param id A value of "xlink:href" attribute.
         * @return An attribute declaration.
         */
        public static Declarable xlink(String href) {
            return attr("xlink:href", href);
        }

        /**
         * <p>
         * General attribute definition method.
         * </p>
         * 
         * @param name An attribute name.
         * @param value An attribute value.
         * @return
         */
        public static Declarable attr(String name, String value) {
            return name == null || name.length() == 0 || value == null || value.length() == 0 ? null : () -> {
                latest.attributes.add(name, value);
            };
        }
    }

    /**
     * @version 2015/09/15 11:18:03
     */
    public static class SVGPath implements Declarable {

        /** The current draw mode. */
        private boolean relativeMode = false;

        /** The buffer. */
        private StringBuilder builder = new StringBuilder();

        /**
         * 
         */
        private SVGPath() {
            super();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void declare() {
            latest.attributes.add("d", builder.toString());
        }

        /**
         * <p>
         * Set the start position to draw.
         * </p>
         * 
         * @param x A horizontal position.
         * @param y A vertical position.
         * @return Chainable API.
         */
        public SVGPath moveTo(double x, double y) {
            command("M").append(x).append(" ").append(y);
            return this;
        }

        /**
         * <p>
         * Draw line to the specified position.
         * </p>
         * 
         * @param x A horizontal position.
         * @param y A vertical position.
         * @return Chainable API.
         */
        public SVGPath lineTo(double x, double y) {
            command("L").append(x).append(" ").append(y);
            return this;
        }

        /**
         * <p>
         * Draw horizontal line to the specified position.
         * </p>
         * 
         * @param x A horizontal position.
         * @return Chainable API.
         */
        public SVGPath hlineTo(double x) {
            command("H").append(x);
            return this;
        }

        /**
         * <p>
         * Draw vertical line to the specified position.
         * </p>
         * 
         * @param y A vertical position.
         * @return Chainable API.
         */
        public SVGPath vlineTo(double y) {
            command("V").append(y);
            return this;
        }

        /**
         * <p>
         * Close the current path.
         * </p>
         * 
         * @return Chainable API.
         */
        public SVGPath end() {
            command("Z");
            return this;
        }

        /**
         * <p>
         * Draw cubic Bézier curve to the path. It requires three points. The first two points are
         * control points and the third one is the end point. The starting point is the last point
         * in the current path, which can be changed using moveTo() before creating the Bézier
         * curve.
         * </p>
         * 
         * @param cp1x The x axis of the coordinate for the first control point.
         * @param cp1y The y axis of the coordinate for first control point.
         * @param cp2x The x axis of the coordinate for the second control point.
         * @param cp2y The y axis of the coordinate for the second control point.
         * @param x The x axis of the coordinate for the end point.
         * @param y The y axis of the coordinate for the end point.
         * @return Chainable API.
         */
        public SVGPath curveTo(double cp1x, double cp1y, double cp2x, double cp2y, double x, double y) {
            command("C").append(cp1x)
                    .append(" ")
                    .append(cp1y)
                    .append(" ")
                    .append(cp2x)
                    .append(" ")
                    .append(cp2y)
                    .append(" ")
                    .append(x)
                    .append(" ")
                    .append(y);
            return this;
        }

        /**
         * <p>
         * Draw cubic Bézier curve to the path. It requires three points. The first two points are
         * control points and the third one is the end point. The starting point is the last point
         * in the current path, which can be changed using moveTo() before creating the Bézier
         * curve.
         * </p>
         * 
         * @param cp1x The x axis of the coordinate for the first control point.
         * @param cp1y The y axis of the coordinate for first control point.
         * @param cp2x The x axis of the coordinate for the second control point.
         * @param cp2y The y axis of the coordinate for the second control point.
         * @param x The x axis of the coordinate for the end point.
         * @param y The y axis of the coordinate for the end point.
         * @return Chainable API.
         */
        public SVGPath curveRelativeTo(double cp1x, double cp1y, double cp2x, double cp2y, double x, double y) {
            command("C").append(cp1x)
                    .append(" ")
                    .append(cp1y)
                    .append(" ")
                    .append(cp2x)
                    .append(" ")
                    .append(cp2y)
                    .append(" ")
                    .append(x)
                    .append(" ")
                    .append(y);
            return this;
        }

        /**
         * <p>
         * Make drawing context relative.
         * </p>
         * 
         * @return
         */
        public final SVGPath relatively() {
            relativeMode = true;

            return this;
        }

        /**
         * <p>
         * Make drawing context relative.
         * </p>
         * 
         * @return
         */
        public final SVGPath absolutely() {
            relativeMode = false;

            return this;
        }

        /**
         * <p>
         * Make command expression.
         * </p>
         * 
         * @param command
         * @return
         */
        private final StringBuilder command(String command) {
            builder.append(" ").append(relativeMode ? command.toLowerCase() : command).append(" ");
            return builder;
        }
    }
}
