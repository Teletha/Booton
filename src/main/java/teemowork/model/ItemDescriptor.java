/*
 * Copyright (C) 2013 Nameless Production Committee
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://opensource.org/licenses/mit-license.php
 */
package teemowork.model;

import static teemowork.model.Status.*;

import java.util.Arrays;

/**
 * @version 2013/01/29 1:55:25
 */
public class ItemDescriptor extends Descriptor<ItemDescriptor> {

    /** The value store. */
    private double[] values = new double[Status.values().length];

    /** The item build. */
    private Item[] build = new Item[0];

    /** The ability list. */
    private Ability[] abilities = new Ability[0];

    /** The item status. */
    private boolean deprecated = false;

    /**
     * @param name
     */
    ItemDescriptor(Item item, ItemDescriptor previous) {
        super(item, previous);

        if (previous != null) {
            values = Arrays.copyOf(previous.values, previous.values.length);
            build = previous.build;
            abilities = previous.abilities;
        }
    }

    /**
     * <p>
     * Retrieve status value.
     * </p>
     * 
     * @param status A target status.
     * @return A result.
     */
    public double get(Status status) {
        return values[status.ordinal()];
    }

    /**
     * <p>
     * Retrieve status value.
     * </p>
     * 
     * @param status A target status.
     * @return Chainable API.
     */
    ItemDescriptor set(Status status, double value) {
        values[status.ordinal()] = value;

        return this;
    }

    /**
     * <p>
     * Retrieve status value.
     * </p>
     * 
     * @param status A target status.
     * @return Chainable API.
     */
    ItemDescriptor cost(double base) {
        set(Cost, base);

        return this;
    }

    /**
     * <p>
     * Get build items.
     * </p>
     * 
     * @return items.
     */
    public Item[] getBuild() {
        return build;
    }

    /**
     * <p>
     * Set build items.
     * </p>
     * 
     * @param items
     * @return
     */
    ItemDescriptor build(Item... items) {
        this.build = items;

        return this;
    }

    /**
     * <p>
     * Get special abilities.
     * </p>
     * 
     * @return Abilities.
     */
    public Ability[] getAbilities() {
        return abilities;
    }

    /**
     * <p>
     * Set special abilities.
     * </p>
     * 
     * @param Ability list.
     */
    ItemDescriptor abilities(Ability... abilities) {
        this.abilities = abilities;

        return this;
    }

    /**
     * <p>
     * Make this item deprecated.
     * </p>
     * 
     * @return
     */
    ItemDescriptor deprecated() {
        this.deprecated = true;

        return this;
    }

    /**
     * <p>
     * Check whether this item is deprecated or not.
     * </p>
     * 
     * @return A result.
     */
    public boolean isDeprecated() {
        return deprecated;
    }
}
