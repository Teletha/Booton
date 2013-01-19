/*
 * Copyright (C) 2012 Nameless Production Committee
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://opensource.org/licenses/mit-license.php
 */
package js.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import js.lang.NativeArray;


/**
 * @version 2012/12/09 22:57:44
 */
public class ArrayList<E> extends AbstractCollection<E> implements List<E> {

    /** The actual container. */
    private NativeArray<E> array = new NativeArray();

    /**
     * {@inheritDoc}
     */
    @Override
    public int size() {
        return array.length();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean contains(Object item) {
        return array.indexOf(item) != -1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterator<E> iterator() {
        return new View();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean add(E item) {
        array.push(item);

        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean remove(Object item) {
        int index = array.indexOf(item);

        if (index == -1) {
            return false;
        } else {
            array.remove(index);

            return true;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        array = new NativeArray();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public E get(int index) {
        return array.get(index);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public E set(int index, E element) {
        return array.set(index, element);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(int index, E element) {
        array.add(index, element);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public E remove(int index) {
        return array.remove(index);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int indexOf(Object item) {
        return array.indexOf(item);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int lastIndexOf(Object item) {
        return array.lastIndexOf(item);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ListIterator<E> listIterator() {
        // If this exception will be thrown, it is bug of this program. So we must rethrow the
        // wrapped error in here.
        throw new Error();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ListIterator<E> listIterator(int index) {
        // If this exception will be thrown, it is bug of this program. So we must rethrow the
        // wrapped error in here.
        throw new Error();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        // If this exception will be thrown, it is bug of this program. So we must rethrow the
        // wrapped error in here.
        throw new Error();
    }

    /**
     * <p>
     * Helper method to check index range,
     * </p>
     * 
     * @param index
     */
    private void checkRange(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Negative index is unacceptable. Size: " + size() + "  Index: " + index);
        }

        if (size() <= index) {
            throw new IndexOutOfBoundsException("Index is overflowed. Size: " + size() + "  Index: " + index);
        }
    }

    /**
     * @version 2012/12/19 13:51:53
     */
    private class View implements Iterator<E> {

        /** The curren position. */
        private int index = 0;

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean hasNext() {
            return index < array.length();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public E next() {
            return array.get(index++);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void remove() {
            if (0 < index) {
                array.remove(index - 1);
            }
        }
    }
}
