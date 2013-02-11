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

import org.junit.Test;

/**
 * @version 2013/01/27 20:34:23
 */
public class SkillStatusTest {

    @Test
    public void GetAndSet() throws Exception {
        SkillStatus skill = new SkillStatus(null);
        assert skill.get(Range) == 0;

        skill.set(Range, 100);
        assert skill.get(Range) == 100;
    }

    @Test
    public void Delegate() throws Exception {
        SkillStatus previous = new SkillStatus(null);
        previous.set(Range, 100);
        assert previous.get(Range) == 100;

        SkillStatus skill = new SkillStatus(previous);
        assert skill.get(Range) == 100;
    }

    @Test
    public void Override() throws Exception {
        SkillStatus previous = new SkillStatus(null);
        previous.set(Range, 100);
        assert previous.get(Range) == 100;

        SkillStatus skill = new SkillStatus(previous);
        skill.set(Range, 200);
        assert skill.get(Range) == 200;
        assert previous.get(Range) == 100;
    }

    @Test
    public void description() throws Exception {
        SkillStatus skill = new SkillStatus(null);
        skill.description("Test");

        List tokens = skill.getDescriptionTokens();
        assert tokens.size() == 1;
        assert tokens.get(0).equals("Test");
    }

    @Test
    public void variable() throws Exception {
        SkillStatus skill = new SkillStatus(null);
        skill.description("Test{1}");
        skill.variable(1, AD, 10, 10);

        List tokens = skill.getDescriptionTokens();
        assert tokens.size() == 2;
        assert tokens.get(0).equals("Test");

        Object token = tokens.get(1);
        assert token instanceof SkillVariable;

        SkillVariable variable = (SkillVariable) token;
        assert variable.id == 1;
        assert variable.status == AD;
        assert variable.base == 10;
        assert variable.diff == 10;
        assert variable.amplifiers.size() == 0;
    }

    @Test
    public void variable2() throws Exception {
        SkillStatus skill = new SkillStatus(null);
        skill.description("Test{1}");
        skill.variable(1, SV, 10, 10);

        List tokens = skill.getDescriptionTokens();
        assert tokens.size() == 2;
        assert tokens.get(0).equals("Test");

        Object token = tokens.get(1);
        assert token instanceof SkillVariable;

        SkillVariable variable = (SkillVariable) token;
        assert variable.id == 1;
        assert variable.status == SV;
        assert variable.base == 10;
        assert variable.diff == 10;
        assert variable.amplifiers.size() == 0;
    }
}
