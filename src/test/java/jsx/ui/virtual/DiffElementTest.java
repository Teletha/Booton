/*
 * Copyright (C) 2014 Nameless Production Committee
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://opensource.org/licenses/mit-license.php
 */
package jsx.ui.virtual;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import js.dom.Node;
import js.dom.NodeComparator;

import org.junit.Test;

import booton.css.CSS;

/**
 * @version 2014/09/05 9:24:53
 */
public class DiffElementTest {

    /** The reusable attribute map. */
    private static final Map<String, String> noAttr = new HashMap();

    @Test
    public void addClass() throws Exception {
        assertDiff(rootClass(), rootClass(A.class), 1);
        assertDiff(rootClass(A.class), rootClass(A.class, B.class), 1);
        assertDiff(rootClass(A.class), rootClass(A.class, B.class, C.class), 2);
    }

    @Test
    public void removeClass() throws Exception {
        assertDiff(rootClass(A.class, B.class, C.class), rootClass(A.class, B.class), 1);
        assertDiff(rootClass(A.class, B.class, C.class), rootClass(B.class), 2);
    }

    @Test
    public void attributeChange() throws Exception {
        assertDiff(rootAttr("a", "A"), rootAttr("b", "B"), 2);
    }

    @Test
    public void attributeChangeValue() throws Exception {
        assertDiff(rootAttr("a", "A"), rootAttr("a", "1"), 1);
        assertDiff(rootAttr("a", "A", "b", "B"), rootAttr("a", "1", "b", "2"), 2);
        assertDiff(rootAttr("a", "A"), rootAttr("a", "A"), 0);
    }

    @Test
    public void attributeAdd() throws Exception {
        assertDiff(rootAttr(), rootAttr("a", "A"), 1);
        assertDiff(rootAttr("a", "A"), rootAttr("a", "A", "b", "B"), 1);
    }

    @Test
    public void attributeRemove() throws Exception {
        assertDiff(rootAttr("a", "A"), rootAttr(), 1);
        assertDiff(rootAttr("a", "A", "b", "B"), rootAttr("b", "B"), 1);
        assertDiff(rootAttr("a", "A", "b", "B"), rootAttr(), 2);
    }

    @Test
    public void childTextAdd() {
        assertDiff(e("root"), e("root", text("text1")), 1);
    }

    @Test
    public void childTextRemove() {
        assertDiff(e("root", text("text1")), e("root"), 1);
    }

    @Test
    public void childTextReplace() {
        assertDiff(e("root", text("text1")), e("root", text("text2")), 1);
    }

    @Test
    public void childInsertFromEmpty() {
        assertDiff(e("root"), e("root", e("child")), 1);
    }

    @Test
    public void childInsertAtTail() {
        assertDiff(root("c1", "c2"), root("c1", "c2", "c3"), 1);
        assertDiff(root("c1", "c2"), root("c1", "c2", "A", "B"), 2);
    }

    @Test
    public void childInsertAtHead() {
        assertDiff(root("c1", "c2"), root("c1", "c2", "c3"), 1);
        assertDiff(root("c1", "c2"), root("A", "B", "c1", "c2"), 2);
    }

    @Test
    public void childInsertAtMiddle() {
        assertDiff(root("c1", "c2"), root("c1", "c2", "c3"), 1);
        assertDiff(root("c1", "c2"), root("c1", "A", "B", "c2"), 2);
    }

    @Test
    public void childRemoveAtTail() {
        assertDiff(root("c1", "c2", "c3"), root("c1", "c2"), 1);
        assertDiff(root("c1", "c2", "c3"), root("c1"), 2);
    }

    @Test
    public void childRemoveAtHead() {
        assertDiff(root("c1", "c2", "c3"), root("c2", "c3"), 1);
        assertDiff(root("c1", "c2", "c3"), root("c3"), 2);
    }

    @Test
    public void childRemoveAtMiddle() {
        assertDiff(root("c1", "c2", "c3"), root("c1", "c3"), 1);
        assertDiff(root("c1", "c2", "c3", "c4"), root("c1", "c4"), 2);
    }

    @Test
    public void childReverse() {
        assertDiff(root("c1", "c2"), root("c2", "c1"), 1);
        assertDiff(root("c1", "c2", "c3"), root("c3", "c2", "c1"), 2);
    }

    @Test
    public void childUp() {
        assertDiff(root("a", "b", "c", "d"), root("c", "d", "a", "b"), 2);
        assertDiff(root("a", "b", "c", "d"), root("b", "d", "a", "c"), 2);
    }

    @Test
    public void childDown() throws Exception {
        assertDiff(root("a", "b", "c", "d"), root("b", "c", "d", "a"), 1);
        assertDiff(root("a", "b", "c", "d"), root("b", "d", "c", "a"), 2);
    }

    @Test
    public void childReplace() {
        assertDiff(root("a"), root("1"), 1);
        assertDiff(root("a", "b", "c"), root("1", "2"), 3);
        assertDiff(root("a", "b"), root("1", "2", "3"), 3);
    }

    @Test
    public void childComplexOperation1() {
        assertDiff(root("a", "b", "c"), root("0", "1", "a", "c"), 3);

    }

    @Test
    public void childComplexOperation2() throws Exception {
        assertDiff(root("a", "b", "c", "d", "e"), root("0", "d", "a"), 5);
    }

    @Test
    public void childComplexOperation3() throws Exception {
        assertDiff(root("a", "b", "c"), root("0", "b", "1", "a", "c"), 4);
    }

    @Test
    public void childComplexOperation4() {
        assertDiff(root("a", "b", "c", "d", "e"), root("0", "d", "e", "a"), 4);
    }

    @Test
    public void childComplexOperation5() {
        assertDiff(root("a", "b", "c", "d", "e"), root("0", "c", "d", "a"), 4);
    }

    @Test
    public void childComplexOperation6() {
        assertDiff(root("a", "b", "c"), root("0", "b", "1", "a", "2", "c"), 5);
    }

    @Test
    public void childReplaceNode() {
        assertDiff(root(e("child")), root(text("child")), 1);
    }

    @Test
    public void nestAttributeAdd() {
        assertDiff(root(e("child")), root(e("child", attr("a", "A"))), 1);
    }

    @Test
    public void nestChildAdd() {
        VirtualElement e1 = root(e("child"));
        VirtualElement e2 = root(e("child", e("grand")));

        assertDiff(e1, e2, 1);
    }

    @Test
    public void nestComplex() {
        VirtualElement e1 = root(e("child", e("grand")));
        VirtualElement e2 = root(e("inserted", e("grand")), e("child", e("changed")));

        assertDiff(e1, e2, 2);
    }

    @Test
    public void applyPatchTwice() throws Exception {
        VirtualElement e1 = root(e("child"));
        VirtualElement e2 = root(e("child", attr("a", "A")));
        VirtualElement e3 = root(e("child", attr("a", "A"), e("grand")));

        assertDiff(e1, e2, 1);
    }

    /**
     * Assert map diff.
     * 
     * @param prev
     * @param next
     */
    private void assertDiff(VirtualElement prev, VirtualElement next, int expectedOperationCount) {
        List<Patch> ops = Diff.diff(prev, next);

        Node prevNode = prev.createNode();
        Node nextNode = next.createNode();

        for (int i = 0; i < ops.size(); i++) {
            try {
                ops.get(i).apply();
            } catch (IndexOutOfBoundsException e) {
                AssertionError error = new AssertionError(message(prevNode, nextNode, ops, i));
                error.addSuppressed(e);

                throw error;
            }
        }

        String message = message(prevNode, nextNode, ops, ops.size());

        assert expectedOperationCount == ops.size() : message;
        NodeComparator.equals(prevNode, nextNode);
    }

    /**
     * Helper to write erro message.
     * 
     * @param prev
     * @param next
     * @param ops
     * @param snapshots
     * @param size
     * @return
     */
    private String message(Node prev, Node next, List<Patch> ops, int size) {
        StringBuilder message = new StringBuilder("\r\n");
        message.append("PREV:\r\n").append(prev).append("\r\n\r\n");
        message.append("NEXT:\r\n").append(next).append("\r\n\r\n");
        message.append("OPERATIONS:\r\n");

        for (int i = 0; i < size; i++) {
            message.append(ops.get(i)).append("  ").append("\r\n");
        }

        if (size != ops.size()) {
            message.append("\r\nNOEXECUTED:\r\n");

            for (int i = size; i < ops.size(); i++) {
                message.append(ops.get(i)).append("\r\n");
            }
        }
        return message.toString();
    }

    /**
     * <p>
     * Helper method to create element.
     * </p>
     * 
     * @param attributes
     * @return
     */
    private static VirtualElement root(String... children) {
        VirtualElement[] elements = new VirtualElement[children.length];

        for (int i = 0; i < children.length; i++) {
            elements[i] = e(children[i]);
        }
        return e("root", elements);
    }

    /**
     * <p>
     * Helper method to create element.
     * </p>
     * 
     * @param attributes
     * @return
     */
    private static VirtualElement rootAttr(String... attributes) {
        return e("root", attr(attributes));
    }

    /**
     * <p>
     * Helper method to create element.
     * </p>
     * 
     * @param attributes
     * @return
     */
    private static VirtualElement rootClass(Class<? extends CSS>... classes) {
        return e("root", clazz(classes));
    }

    /**
     * <p>
     * Helper method to create element.
     * </p>
     * 
     * @param attributes
     * @return
     */
    private static VirtualElement root(VirtualNode... children) {
        return e("root", children);
    }

    /**
     * <p>
     * Helper method to create element.
     * </p>
     * 
     * @param attributes
     * @return
     */
    private static VirtualElement e(String key, VirtualNode... children) {
        return e(key, noAttr, children);
    }

    /**
     * <p>
     * Helper method to create element.
     * </p>
     * 
     * @param attributes
     * @return
     */
    private static VirtualElement e(String key, List<Class<? extends CSS>> classes, VirtualNode... children) {
        return e(key, noAttr, classes, children);
    }

    /**
     * <p>
     * Helper method to create element.
     * </p>
     * 
     * @param attributes
     * @return
     */
    private static VirtualElement e(String key, Map<String, String> attributes, VirtualNode... children) {
        VirtualElement e = new VirtualElement(key.hashCode(), key);

        for (Entry<String, String> attribute : attributes.entrySet()) {
            e.attributes.set(attribute.getKey(), attribute.getValue());
        }

        for (VirtualNode child : children) {
            e.children.items.push(child);
        }

        return e;
    }

    /**
     * <p>
     * Helper method to create element.
     * </p>
     * 
     * @param attributes
     * @return
     */
    private static VirtualElement e(String key, Map<String, String> attributes, List<Class<? extends CSS>> classes, VirtualNode... children) {
        VirtualElement e = new VirtualElement(key.hashCode(), key);

        for (Entry<String, String> attribute : attributes.entrySet()) {
            e.attributes.set(attribute.getKey(), attribute.getValue());
        }

        for (Class<? extends CSS> clazz : classes) {
            e.classList.push(clazz);
        }

        for (VirtualNode child : children) {
            e.children.items.push(child);
        }

        return e;
    }

    /**
     * <p>
     * Helper method to create attribute map.
     * </p>
     * 
     * @param attributes
     * @return
     */
    private static Map<String, String> attr(String... attributes) {
        Map<String, String> map = new HashMap();

        for (int i = 0; i < attributes.length; i += 2) {
            map.put(attributes[i], attributes[i + 1]);
        }
        return map;
    }

    /**
     * <p>
     * Helper method to create class.
     * </p>
     * 
     * @param classes
     * @return
     */
    private static List<Class<? extends CSS>> clazz(Class... classes) {
        List<Class<? extends CSS>> list = new ArrayList();

        for (int i = 0; i < classes.length; i++) {
            list.add(classes[i]);
        }
        return list;
    }

    /**
     * <p>
     * Helper method to create attribute map.
     * </p>
     * 
     * @param attributes
     * @return
     */
    private static VirtualText text(String value) {
        return new VirtualText(value.hashCode() ^ 31, value);
    }

    /**
     * @version 2014/09/12 11:17:24
     */
    private static class A extends CSS {
    }

    /**
     * @version 2014/09/12 11:17:24
     */
    private static class B extends CSS {
    }

    /**
     * @version 2014/09/12 11:17:24
     */
    private static class C extends CSS {
    }
}