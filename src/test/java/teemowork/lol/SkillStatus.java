/*
 * Copyright (C) 2013 Nameless Production Committee
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://opensource.org/licenses/mit-license.php
 */
package teemowork.lol;

import static teemowork.lol.Status.*;

import java.util.List;

import js.lang.NativeArray;
import js.util.ArrayList;

/**
 * @version 2013/01/27 20:32:01
 */
public class SkillStatus {

    /** The associated skill. */
    public final Skill skill;

    /** The value store. */
    private NativeArray<Double> values;

    /** The value store. */
    private NativeArray<SkillVariable> variables;

    /** The skill cost type. */
    private Status cost;

    /** The toggle flag. */
    private boolean isToggle;

    /** The skill description. */
    public final List passive;

    /** The skill description. */
    public final List active;

    /**
     * @param name
     */
    SkillStatus(Skill skill, SkillStatus previous) {
        this.skill = skill;

        if (previous != null) {
            values = previous.values.copy();
            variables = previous.variables;
            passive = previous.passive;
            active = previous.active;
            isToggle = previous.isToggle;
            cost = previous.cost;
        } else {
            values = new NativeArray();
            variables = new NativeArray();
            passive = new ArrayList();
            active = new ArrayList();
            isToggle = false;
            cost = Mana;
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
        Double value = values.get(status.ordinal());

        return value == null ? 0 : value;
    }

    /**
     * <p>
     * Retrieve status value.
     * </p>
     * 
     * @param status A target status.
     * @return Chainable API.
     */
    SkillStatus set(Status status, double value) {
        values.set(status.ordinal(), value);

        return this;
    }

    /**
     * <p>
     * Set skill passive ability.
     * </p>
     */
    SkillStatus passive(String text) {
        return parse(passive, text);
    }

    /**
     * <p>
     * Set skill active ability.
     * </p>
     * 
     * @param text
     * @return
     */
    SkillStatus active(String text) {
        return parse(active, text);
    }

    /**
     * <p>
     * Parse skill text.
     * </p>
     * 
     * @param tokens A list of text tokens.
     * @param text A skill text.
     */
    private SkillStatus parse(List tokens, String text) {
        // clear previous version
        tokens.clear();

        for (String token : text.split("[\\{\\}]")) {
            if (token.length() != 1 || !Character.isDigit(token.charAt(0))) {
                tokens.add(token);
            } else {
                int id = Integer.parseInt(token);
                SkillVariable variable = variables.get(id);

                if (variable == null) {
                    variable = new SkillVariable();
                    variables.set(id, variable);
                }
                tokens.add(variable);
            }
        }
        return this;
    }

    /**
     * <p>
     * Set new variable.
     * </p>
     * 
     * @param id A variable identifier.
     * @param status A variable type.
     * @param base A base value.
     * @return A chainable API.
     */
    SkillStatus variable(int id, Status status, double base) {
        return variable(id, status, base, 0);
    }

    /**
     * <p>
     * Set new variable.
     * </p>
     * 
     * @param id A variable identifier.
     * @param status A variable type.
     * @param base A base value.
     * @param diff A diff value.
     * @return A chainable API.
     */
    SkillStatus variable(int id, Status status, double base, double diff) {
        return variable(id, status, base, diff, null, null);
    }

    /**
     * <p>
     * Set new variable.
     * </p>
     * 
     * @param id A variable identifier.
     * @param status A variable type.
     * @param resolver A resolver.
     * @return Chainable API.
     */
    SkillStatus variable(int id, Status status, SkillVariableResolver resolver) {
        return variable(id, status, resolver, null, null);
    }

    /**
     * <p>
     * Set new variable.
     * </p>
     * 
     * @param id A variable identifier.
     * @param status A variable type.
     * @param base A base value.
     * @param diff A diff value.
     * @param amplifier A first amplifier.
     * @return Chainable API.
     */
    SkillStatus variable(int id, Status status, double base, double diff, SkillVariable amplifier) {
        return variable(id, status, base, diff, amplifier, null);
    }

    /**
     * <p>
     * Set new variable.
     * </p>
     * 
     * @param id A variable identifier.
     * @param status A variable type.
     * @param base A base value.
     * @param diff A diff value.
     * @param first A first amplifier.
     * @param second A second amplifier.
     * @return Chainable API.
     */
    SkillStatus variable(int id, Status status, double base, double diff, SkillVariable first, SkillVariable second) {
        return variable(id, status, new SimpleValues(skill.getMaxLevel(), base, diff), first, second);
    }

    /**
     * <p>
     * Set new variable.
     * </p>
     * 
     * @param id A variable identifier.
     * @param status A variable type.
     * @param resolver A resolver.
     * @param first A first amplifier.
     * @param second A second amplifier.
     * @return Chainable API.
     */
    private SkillStatus variable(int id, Status status, SkillVariableResolver resolver, SkillVariable first, SkillVariable second) {
        SkillVariable variable = variables.get(id);
        variable.setStatus(status);
        variable.setResolver(resolver);

        if (first != null) {
            variable.amplifiers.add(first);
        }

        if (second != null) {
            variable.amplifiers.add(second);
        }
        return this;
    }

    /**
     * <p>
     * Set cooldown time.
     * </p>
     * 
     * @param base A base time.
     * @param diff A diff time.
     */
    SkillStatus cd(double base) {
        return cd(base, 0);
    }

    /**
     * <p>
     * Set cooldown time.
     * </p>
     * 
     * @param base A base time.
     * @param diff A diff time.
     */
    SkillStatus cd(double base, double diff) {
        values.set(CD.ordinal(), base);
        values.set(CDPerLv.ordinal(), diff);

        return this;
    }

    /**
     * <p>
     * Set skill cost.
     * </p>
     * 
     * @param base A base cost.
     * @param diff A diff cost.
     */
    SkillStatus mana(double base) {
        return cost(Mana, base, 0);
    }

    /**
     * <p>
     * Set skill cost.
     * </p>
     * 
     * @param base A base cost.
     * @param diff A diff cost.
     */
    SkillStatus mana(double base, double diff) {
        return cost(Mana, base, diff);
    }

    /**
     * <p>
     * Set skill cost.
     * </p>
     * 
     * @param base A base cost.
     * @param diff A diff cost.
     */
    SkillStatus cost(Status type, double base, double diff) {
        values.set(Cost.ordinal(), base);
        values.set(CostPerLv.ordinal(), diff);
        cost = type;

        return this;
    }

    /**
     * <p>
     * Retrieve skill cost type.
     * </p>
     * 
     * @return
     */
    public Status getCostType() {
        return cost;
    }

    /**
     * <p>
     * Set skill range.
     * </p>
     * 
     * @param range
     */
    SkillStatus range(double range) {
        return range(range, 0);
    }

    /**
     * <p>
     * Set skill range.
     * </p>
     * 
     * @param range
     */
    SkillStatus range(double range, double diff) {
        values.set(Range.ordinal(), range);
        values.set(RangePerLv.ordinal(), diff);

        return this;
    }

    /**
     * <p>
     * Retrieve this skill is toggle type or not.
     * </p>
     * 
     * @return A result.
     */
    public boolean isToggle() {
        return isToggle;
    }

    /**
     * <p>
     * Set this skill is togglable.
     * </p>
     * 
     * @return
     */
    SkillStatus toggle() {
        isToggle = true;

        return this;
    }

    /**
     * @version 2013/02/12 10:41:24
     */
    public static class SimpleValues extends SkillVariableResolver {

        /** The skill size. */
        private final int size;

        /** The base value. */
        private final double base;

        /** The value of diff for each stack. */
        private final double diff;

        /**
         * @param size A size of skill level.
         * @param base A base value.
         * @param diff A diff value.
         */
        public SimpleValues(int size, double base, double diff) {
            this.size = size;
            this.base = base;
            this.diff = diff;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public double compute(int skillLevel) {
            return base + diff * (skillLevel - 1);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public int computeSize(int hint) {
            return base == 0 ? 0 : diff == 0 ? 1 : size;
        }
    }
}