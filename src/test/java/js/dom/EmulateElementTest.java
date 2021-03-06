/*
 * Copyright (C) 2016 Nameless Production Committee
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://opensource.org/licenses/mit-license.php
 */
package js.dom;

import static js.lang.Global.*;

import org.junit.Test;

/**
 * @version 2013/06/30 12:23:59
 */
public class EmulateElementTest {

    @Test
    public void tagName() throws Exception {
        Element element = new EmulateElement("test");
        assert element.tagName().equals("TEST");
    }

    @Test
    public void apendChild() throws Exception {
        Element element = new EmulateElement();
        Element child1 = new EmulateElement();

        assert element.children().size() == 0;

        Node node = element.appendChild(child1);
        assert element.children().size() == 1;
        assert element.children().get(0) == child1;
        assert node == child1;
    }

    @Test(expected = EmulateDOMError.class)
    public void apendChildNull() throws Exception {
        Element element = new EmulateElement();
        element.appendChild(null);
    }

    @Test
    public void removeChild() throws Exception {
        Element element = new EmulateElement();
        Element child = new EmulateElement();
        element.appendChild(child);

        assert element.children().size() == 1;
        assert element.children().get(0) == child;

        Node removed = element.removeChild(child);
        assert element.children().size() == 0;
        assert removed == child;
    }

    @Test(expected = EmulateDOMError.class)
    public void removeChildNull() throws Exception {
        Element element = new EmulateElement();
        element.appendChild(null);
    }

    @Test(expected = EmulateDOMError.class)
    public void removeNonChild() throws Exception {
        Element element = new EmulateElement();
        Element child = new EmulateElement();
        element.removeChild(child);
    }

    @Test
    public void insertBefore() throws Exception {
        Element element = new EmulateElement();
        Element child1 = new EmulateElement();
        Element child2 = new EmulateElement();

        assert element.children().size() == 0;

        element.append(child1).insertBefore(child2, child1);
        assert element.children().size() == 2;
        assert element.children().get(0) == child2;
        assert element.children().get(1) == child1;
    }

    @Test
    public void insertBeforeNull() throws Exception {
        Element element = new EmulateElement();
        Element child1 = new EmulateElement();
        Element child2 = new EmulateElement();

        assert element.children().size() == 0;

        element.append(child1).insertBefore(child2, null);
        assert element.children().size() == 2;
        assert element.children().get(0) == child1;
        assert element.children().get(1) == child2;
    }

    @Test(expected = EmulateDOMError.class)
    public void insertBeforeNotChildReference() throws Exception {
        Element element = new EmulateElement();
        Element child1 = new EmulateElement();
        Element child2 = new EmulateElement();

        element.insertBefore(child2, child1);
    }

    @Test
    public void textContent() throws Exception {
        Element element = new EmulateElement();
        assert element.textContent().equals("");

        element.appendChild(document.createTextNode("c"));
        element.appendChild(document.createTextNode("a"));
        element.appendChild(document.createTextNode("t"));
        assert element.textContent().equals("cat");
    }

    @Test
    public void textContentWithElement() throws Exception {
        Element element = new EmulateElement();
        assert element.textContent().equals("");

        element.appendChild(document.createTextNode("c"));
        Element child = new EmulateElement();
        child.appendChild(document.createTextNode("a"));
        element.appendChild(child);
        element.appendChild(document.createTextNode("t"));
        assert element.textContent().equals("cat");
    }

    @Test
    public void childElements() throws Exception {
        Element element = new EmulateElement();
        Element child1 = new EmulateElement();
        Element child2 = new EmulateElement();
        HTMLCollection collection = element.childElements();

        assert collection.length() == 0;

        element.append(child1).append(child2);

        assert collection.length() == 2;
        assert collection.item(0) == child1;
        assert collection.item(1) == child2;

        element.removeChild(child1);

        assert collection.length() == 1;
        assert collection.item(0) == child2;
    }

    @Test
    public void setAttribute() throws Exception {
        Element element = new EmulateElement();
        assert element.getAttribute("key") == null;

        element.setAttribute("key", "value");
        assert element.getAttribute("key").equals("value");
    }

    @Test
    public void setAttributeDuplicated() throws Exception {
        Element element = new EmulateElement();
        assert element.getAttribute("key") == null;

        element.setAttribute("key", "value");
        assert element.getAttribute("key").equals("value");

        element.setAttribute("key", "override");
        assert element.getAttribute("key").equals("override");
    }

    @Test
    public void setAttributeNull() throws Exception {
        Element element = new EmulateElement();
        assert element.getAttribute(null) == null;

        element.setAttribute(null, null);
        assert element.getAttribute(null).equals("null");
    }

    @Test
    public void setAttributeCaseIgnore() throws Exception {
        Element element = new EmulateElement();
        element.setAttribute("key", "value");
        assert element.getAttribute("key").equals("value");

        element.setAttribute("KEY", "override");
        assert element.getAttribute("key").equals("override");
        assert element.getAttribute("KEY").equals("override");
    }

    @Test
    public void setClassAttribute() throws Exception {
        Element element = new EmulateElement();

        element.setAttribute("class", "value");
        assert element.getAttribute("class").equals("value");
        assert element.classList().contains("value");
        assert element.classList().length() == 1;

        element.setAttribute("class", "override");
        assert element.getAttribute("class").equals("override");
        assert element.classList().contains("override");
        assert element.classList().length() == 1;
    }

    @Test
    public void setMultiClassAttribute() throws Exception {
        Element element = new EmulateElement();

        element.setAttribute("class", "multi value");
        assert element.getAttribute("class").equals("multi value");
        assert element.classList().contains("value");
        assert element.classList().contains("multi");
        assert element.classList().length() == 2;
    }

    @Test
    public void removeAttribute() throws Exception {
        Element element = new EmulateElement();
        assert element.getAttribute("key") == null;

        element.setAttribute("key", "value");
        assert element.hasAttribute("key");

        element.removeAttribute("key");
        assert !element.hasAttribute("key");
    }

    @Test
    public void removeAttributeCaseIgnore() throws Exception {
        Element element = new EmulateElement();
        assert element.getAttribute("key") == null;

        element.setAttribute("key", "value");
        assert element.hasAttribute("key");

        element.removeAttribute("KEY");
        assert !element.hasAttribute("key");
    }

    @Test
    public void removeClassAttribute() throws Exception {
        Element element = new EmulateElement();

        element.setAttribute("class", "multi value");
        assert element.classList().length() == 2;

        element.removeAttribute("class");
        assert element.classList().length() == 0;
    }

    @Test
    public void replaceNode() throws Exception {
        Element element = new EmulateElement();
        Element child1 = new EmulateElement();
        Element child2 = new EmulateElement();
        element.append(child1).append(child2);
        assert element.children().get(0) == child1;
        assert element.children().get(1) == child2;

        Element child3 = new EmulateElement();
        assert element.children().contains(child3) == false;

        Node replaced = element.replaceChild(child3, child1);
        assert replaced == child1;
        assert element.children().get(0) == child3;
        assert element.children().get(1) == child2;
    }

    @Test
    public void querySelector() throws Exception {
        Element root = createTree();
        Element query = root.querySelector("child1");

        assert query == root.firstChild();
    }

    @Test
    public void querySelectorAll() throws Exception {
        Element root = createTree();
        NodeList<Element> query = root.querySelectorAll(".child");

        assert query.length() == 2;
        assert query.item(0) == root.firstChild();
        assert query.item(1) == root.lastChild();
    }

    @Test
    public void matches() throws Exception {
        Element root = createTree();
        Element child = root.firstElementChild();

        assert child.matches(".child");
        assert child.matches("child1");
        assert !child.matches("child2");
        assert root.querySelector("sub11").matches("child1 sub11");
    }

    /**
     * 
     */
    private Element createTree() {
        Element root = new EmulateElement("root");
        Element child1 = new EmulateElement("child1").attr("class", "child");
        Element child2 = new EmulateElement("child2").attr("class", "child");
        Element sub11 = new EmulateElement("sub11").attr("class", "sub");

        root.append(child1).append(child2);
        child1.append(sub11);

        return root;
    }
}
