/*
 * Copyright (C) 2012 Nameless Production Committee
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://opensource.org/licenses/mit-license.php
 */
package booton.css.property;

import js.util.Color;
import booton.css.CSSProperty;
import booton.css.CSSWriter;
import booton.css.ColorValue;
import booton.css.Colorable;
import booton.css.GradientValue;
import booton.css.Unit;

/**
 * @version 2012/12/12 0:00:13
 */
public class Background extends CSSProperty<Background> implements Colorable<Background> {

    public final ColorValue<Background> color = new ColorValue("background-color", this);

    private String repeat;

    private String verticalPosition;

    private String horizontalPosition;

    private String size;

    private String image;

    private GradientValue gradient;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void write(CSSWriter writer) {
        super.write(writer);

        writer.property("background-repeat", repeat);
        writer.property("background-position", horizontalPosition, verticalPosition);
        writer.property("background-size", size);
        writer.property("background-image", image);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Background color(int red, int green, int blue) {
        return color.color(red, green, blue);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Background color(int red, int green, int blue, double alpha) {
        return color.color(red, green, blue, alpha);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Background color(Color color) {
        return this.color.color(color);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Color color() {
        Color backgroundColor = color.color();

        if (backgroundColor == null) {
            if (gradient == null) {
                backgroundColor = Color.Transparent;
            } else {
                backgroundColor = gradient.end;
            }
        }
        return backgroundColor;
    }

    /**
     * <p>
     * The CSS background-image property sets the background images for an element. The images are
     * drawn on successive stacking context layers, with the first specified being drawn as if it is
     * the closest to the user. The borders of the element are then drawn on top of them, and the
     * background-color is drawn beneath them.
     * </p>
     * 
     * @param uri
     * @return
     */
    public Background imageNone() {
        image = "none";

        return chain();
    }

    /**
     * <p>
     * The CSS background-image property sets the background images for an element. The images are
     * drawn on successive stacking context layers, with the first specified being drawn as if it is
     * the closest to the user. The borders of the element are then drawn on top of them, and the
     * background-color is drawn beneath them.
     * </p>
     * 
     * @param uri
     * @return
     */
    public Background image(String uri) {
        image = url(uri);

        return chain();
    }

    /**
     * <p>
     * The CSS background-image property sets the background images for an element. The images are
     * drawn on successive stacking context layers, with the first specified being drawn as if it is
     * the closest to the user. The borders of the element are then drawn on top of them, and the
     * background-color is drawn beneath them.
     * </p>
     * 
     * @param uri
     * @return
     */
    public Background image(GradientValue gradient) {
        this.gradient = gradient;
        this.image = gradient.toString();

        return chain();
    }

    /**
     * <p>
     * The background-repeat CSS property defines how background images are repeated. A background
     * image can be repeated along the horizontal axis, the vertical axis, both, or not repeated at
     * all. When the repetition of the image tiles doesn't let them exactly cover the background,
     * the way adjustments are done can be controlled by the author: by default, the last image is
     * clipped, but the different tiles can instead be re-sized, or space can be inserted between
     * the tiles.
     * </p>
     * 
     * @return
     */
    public Background repeatX() {
        repeat = "repeat-x";

        // Chainable API
        return chain();
    }

    /**
     * <p>
     * The background-repeat CSS property defines how background images are repeated. A background
     * image can be repeated along the horizontal axis, the vertical axis, both, or not repeated at
     * all. When the repetition of the image tiles doesn't let them exactly cover the background,
     * the way adjustments are done can be controlled by the author: by default, the last image is
     * clipped, but the different tiles can instead be re-sized, or space can be inserted between
     * the tiles.
     * </p>
     * 
     * @return
     */
    public Background repeatY() {
        repeat = "repeat-y";

        // Chainable API
        return chain();
    }

    /**
     * <p>
     * The background-repeat CSS property defines how background images are repeated. A background
     * image can be repeated along the horizontal axis, the vertical axis, both, or not repeated at
     * all. When the repetition of the image tiles doesn't let them exactly cover the background,
     * the way adjustments are done can be controlled by the author: by default, the last image is
     * clipped, but the different tiles can instead be re-sized, or space can be inserted between
     * the tiles.
     * </p>
     * 
     * @return
     */
    public Background repeat() {
        repeat = "repeat";

        // Chainable API
        return chain();
    }

    /**
     * <p>
     * The background-repeat CSS property defines how background images are repeated. A background
     * image can be repeated along the horizontal axis, the vertical axis, both, or not repeated at
     * all. When the repetition of the image tiles doesn't let them exactly cover the background,
     * the way adjustments are done can be controlled by the author: by default, the last image is
     * clipped, but the different tiles can instead be re-sized, or space can be inserted between
     * the tiles.
     * </p>
     * 
     * @return
     */
    public Background space() {
        repeat = "space";

        // Chainable API
        return chain();
    }

    /**
     * <p>
     * The background-repeat CSS property defines how background images are repeated. A background
     * image can be repeated along the horizontal axis, the vertical axis, both, or not repeated at
     * all. When the repetition of the image tiles doesn't let them exactly cover the background,
     * the way adjustments are done can be controlled by the author: by default, the last image is
     * clipped, but the different tiles can instead be re-sized, or space can be inserted between
     * the tiles.
     * </p>
     * 
     * @return
     */
    public Background round() {
        repeat = "round";

        // Chainable API
        return chain();
    }

    /**
     * <p>
     * The background-repeat CSS property defines how background images are repeated. A background
     * image can be repeated along the horizontal axis, the vertical axis, both, or not repeated at
     * all. When the repetition of the image tiles doesn't let them exactly cover the background,
     * the way adjustments are done can be controlled by the author: by default, the last image is
     * clipped, but the different tiles can instead be re-sized, or space can be inserted between
     * the tiles.
     * </p>
     * 
     * @return
     */
    public Background noRepeat() {
        repeat = "no-repeat";

        // Chainable API
        return chain();
    }

    /**
     * <p>
     * The background-position CSS property sets the initial position, relative to the background
     * position layer defined by background-origin for each defined background image.
     * </p>
     * 
     * @return
     */
    public Background top() {
        verticalPosition = "top";

        // Chainable API
        return chain();
    }

    /**
     * <p>
     * The background-position CSS property sets the initial position, relative to the background
     * position layer defined by background-origin for each defined background image.
     * </p>
     * 
     * @return
     */
    public Background bottom() {
        verticalPosition = "bottom";

        // Chainable API
        return chain();
    }

    /**
     * <p>
     * The background-position CSS property sets the initial position, relative to the background
     * position layer defined by background-origin for each defined background image.
     * </p>
     * 
     * @return
     */
    public Background left() {
        horizontalPosition = "left";

        // Chainable API
        return chain();
    }

    /**
     * <p>
     * The background-position CSS property sets the initial position, relative to the background
     * position layer defined by background-origin for each defined background image.
     * </p>
     * 
     * @return
     */
    public Background right() {
        horizontalPosition = "right";

        // Chainable API
        return chain();
    }

    /**
     * <p>
     * The background-position CSS property sets the initial position, relative to the background
     * position layer defined by background-origin for each defined background image.
     * </p>
     * 
     * @return
     */
    public Background vertical(double size, Unit unit) {
        horizontalPosition = compute(size, unit);

        // Chainable API
        return chain();
    }

    /**
     * <p>
     * The background-position CSS property sets the initial position, relative to the background
     * position layer defined by background-origin for each defined background image.
     * </p>
     * 
     * @return
     */
    public Background horizontal(double size, Unit unit) {
        horizontalPosition = compute(size, unit);

        // Chainable API
        return chain();
    }

    /**
     * <p>
     * The background-size CSS property specifies the size of the background images. The size of the
     * image can be fully constrained or only partially in order to preserve its intrinsic ratio.
     * </p>
     * <p>
     * A value that scales the background image to the specified length in the corresponding
     * dimension. Negative lengths are not allowed.
     * </p>
     * 
     * @return
     */
    public Background size(double size, Unit unit) {
        this.size = compute(size, unit);

        // Chainable API
        return chain();
    }

    /**
     * <p>
     * The background-size CSS property specifies the size of the background images. The size of the
     * image can be fully constrained or only partially in order to preserve its intrinsic ratio.
     * </p>
     * <p>
     * This keyword specifies that the background image should be scaled to be as small as possible
     * while ensuring both its dimensions are greater than or equal to the corresponding dimensions
     * of the background positioning area.
     * </p>
     * 
     * @return
     */
    public Background cover() {
        size = "cover";

        // Chainable API
        return chain();
    }

    /**
     * <p>
     * The background-size CSS property specifies the size of the background images. The size of the
     * image can be fully constrained or only partially in order to preserve its intrinsic ratio.
     * </p>
     * <p>
     * This keyword specifies that the background image should be scaled to be as large as possible
     * while ensuring both its dimensions are less than or equal to the corresponding dimensions of
     * the background positioning area.
     * </p>
     * 
     * @return
     */
    public Background contain() {
        size = "contain";

        // Chainable API
        return chain();
    }
}
