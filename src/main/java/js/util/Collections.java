/*
 * Copyright (C) 2016 Nameless Production Committee
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://opensource.org/licenses/mit-license.php
 */
package js.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.RandomAccess;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;

/**
 * @version 2016/11/12 13:55:50
 */
// @JavaAPIProvider(java.util.Collections.class)
class Collections {

    /**
     * The empty set (immutable). This set is serializable.
     * 
     * @see #emptySet()
     */
    public static final Set EMPTY_SET = new UnmodifiableSet(new HashSet());

    /**
     * The empty list (immutable). This list is serializable.
     * 
     * @see #emptyList()
     */
    public static final List EMPTY_LIST = new UnmodifiableList(new ArrayList());

    /**
     * The empty map (immutable). This map is serializable.
     * 
     * @see #emptyMap()
     * @since 1.3
     */
    public static final Map EMPTY_MAP = new UnmodifiableMap(new HashMap());

    /** The natural order. */
    private static final Comparator<Comparable> NATURAL_COMPARATOR = new Comparator<Comparable>() {

        /**
         * {@inheritDoc}
         */
        @Override
        public int compare(Comparable o1, Comparable o2) {
            return o1.compareTo(o2);
        }
    };

    /**
     * Adds all of the specified elements to the specified collection. Elements to be added may be
     * specified individually or as an array. The behavior of this convenience method is identical
     * to that of <tt>c.addAll(Arrays.asList(elements))</tt>, but this method is likely to run
     * significantly faster under most implementations.
     * <p>
     * When elements are specified individually, this method provides a convenient way to add a few
     * elements to an existing collection: <pre>
     *     Collections.addAll(flavors, "Peaches 'n Plutonium", "Rocky Racoon");
     * </pre>
     * 
     * @param <T> the class of the elements to add and of the collection
     * @param c the collection into which <tt>elements</tt> are to be inserted
     * @param elements the elements to insert into <tt>c</tt>
     * @return <tt>true</tt> if the collection changed as a result of the call
     * @throws UnsupportedOperationException if <tt>c</tt> does not support the <tt>add</tt>
     *             operation
     * @throws NullPointerException if <tt>elements</tt> contains one or more null values and
     *             <tt>c</tt> does not permit null elements, or if <tt>c</tt> or <tt>elements</tt>
     *             are <tt>null</tt>
     * @throws IllegalArgumentException if some property of a value in <tt>elements</tt> prevents it
     *             from being added to <tt>c</tt>
     * @see Collection#addAll(Collection)
     * @since 1.5
     */
    @SafeVarargs
    public static <T> boolean addAll(Collection<? super T> c, T... elements) {
        boolean result = false;

        for (T element : elements) {
            result |= c.add(element);
        }
        return result;
    }

    /**
     * Returns a dynamically typesafe view of the specified list. Any attempt to insert an element
     * of the wrong type will result in an immediate {@link ClassCastException}. Assuming a list
     * contains no incorrectly typed elements prior to the time a dynamically typesafe view is
     * generated, and that all subsequent access to the list takes place through the view, it is
     * <i>guaranteed</i> that the list cannot contain an incorrectly typed element.
     * <p>
     * A discussion of the use of dynamically typesafe views may be found in the documentation for
     * the {@link #checkedCollection checkedCollection} method.
     * <p>
     * The returned list will be serializable if the specified list is serializable.
     * <p>
     * Since {@code null} is considered to be a value of any reference type, the returned list
     * permits insertion of null elements whenever the backing list does.
     *
     * @param <E> the class of the objects in the list
     * @param list the list for which a dynamically typesafe view is to be returned
     * @param type the type of element that {@code list} is permitted to hold
     * @return a dynamically typesafe view of the specified list
     * @since 1.5
     */
    public static <E> List<E> checkedList(List<E> list, Class<E> type) {
        return list;
    }

    /**
     * Returns an enumeration over the specified collection. This provides interoperability with
     * legacy APIs that require an enumeration as input.
     *
     * @param <T> the class of the objects in the collection
     * @param c the collection for which an enumeration is to be returned.
     * @return an enumeration over the specified collection.
     * @see Enumeration
     */
    public static <T> Enumeration<T> enumeration(Collection<T> c) {
        return new Enumeration<T>() {

            private final Iterator<T> i = c.iterator();

            @Override
            public boolean hasMoreElements() {
                return i.hasNext();
            }

            @Override
            public T nextElement() {
                return i.next();
            }
        };
    }

    /**
     * Returns an enumeration that has no elements. More precisely,
     * <ul>
     * <li>{@link Enumeration#hasMoreElements hasMoreElements} always returns {@code false}.</li>
     * <li>{@link Enumeration#nextElement nextElement} always throws {@link NoSuchElementException}.
     * </li>
     * </ul>
     * <p>
     * Implementations of this method are permitted, but not required, to return the same object
     * from multiple invocations.
     *
     * @param <T> the class of the objects in the enumeration
     * @return an empty enumeration
     * @since 1.7
     */
    public static <T> Enumeration<T> emptyEnumeration() {
        return (Enumeration<T>) EmptyEnumeration.EMPTY_ENUMERATION;
    }

    /**
     * Returns an iterator that has no elements. More precisely, <ul compact>
     * <li>{@link Iterator#hasNext hasNext} always returns {@code false}.
     * <li>{@link Iterator#next next} always throws {@link NoSuchElementException}.
     * <li>{@link Iterator#remove remove} always throws {@link IllegalStateException}.
     * </ul>
     * <p>
     * Implementations of this method are permitted, but not required, to return the same object
     * from multiple invocations.
     * 
     * @return an empty iterator
     * @since 1.7
     */
    public static <T> Iterator<T> emptyIterator() {
        return EmptyIterator.EMPTY_ITERATOR;
    }

    /**
     * Returns a list iterator that has no elements. More precisely, <ul compact>
     * <li>{@link Iterator#hasNext hasNext} and {@link ListIterator#hasPrevious hasPrevious} always
     * return {@code false}.
     * <li>{@link Iterator#next next} and {@link ListIterator#previous previous} always throw
     * {@link NoSuchElementException}.
     * <li>{@link Iterator#remove remove} and {@link ListIterator#set set} always throw
     * {@link IllegalStateException}.
     * <li>{@link ListIterator#add add} always throws {@link UnsupportedOperationException}.
     * <li>{@link ListIterator#nextIndex nextIndex} always returns {@code 0} .
     * <li>{@link ListIterator#previousIndex previousIndex} always returns {@code -1}.
     * </ul>
     * <p>
     * Implementations of this method are permitted, but not required, to return the same object
     * from multiple invocations.
     * 
     * @return an empty list iterator
     * @since 1.7
     */
    public static <T> ListIterator<T> emptyListIterator() {
        return EmptyListIterator.EMPTY_ITERATOR;
    }

    /**
     * Returns the empty list (immutable). This list is serializable.
     * <p>
     * This example illustrates the type-safe way to obtain an empty list: <pre>
     *     List&lt;String&gt; s = Collections.emptyList();
     * </pre> Implementation note: Implementations of this method need not create a separate
     * <tt>List</tt> object for each call. Using this method is likely to have comparable cost to
     * using the like-named field. (Unlike this method, the field does not provide type safety.)
     * 
     * @see #EMPTY_LIST
     * @since 1.5
     */
    public static <T> List<T> emptyList() {
        return EMPTY_LIST;
    }

    /**
     * Returns the empty set (immutable). This set is serializable. Unlike the like-named field,
     * this method is parameterized.
     * <p>
     * This example illustrates the type-safe way to obtain an empty set: <pre>
     *     Set&lt;String&gt; s = Collections.emptySet();
     * </pre> Implementation note: Implementations of this method need not create a separate
     * <tt>Set</tt> object for each call. Using this method is likely to have comparable cost to
     * using the like-named field. (Unlike this method, the field does not provide type safety.)
     * 
     * @see #EMPTY_SET
     * @since 1.5
     */
    public static <T> Set<T> emptySet() {
        return EMPTY_SET;
    }

    /**
     * Returns the empty map (immutable). This map is serializable.
     * <p>
     * This example illustrates the type-safe way to obtain an empty set: <pre>
     *     Map&lt;String, Date&gt; s = Collections.emptyMap();
     * </pre> Implementation note: Implementations of this method need not create a separate
     * <tt>Map</tt> object for each call. Using this method is likely to have comparable cost to
     * using the like-named field. (Unlike this method, the field does not provide type safety.)
     * 
     * @see #EMPTY_MAP
     * @since 1.5
     */
    public static <K, V> Map<K, V> emptyMap() {
        return EMPTY_MAP;
    }

    /**
     * Returns an array list containing the elements returned by the specified enumeration in the
     * order they are returned by the enumeration. This method provides interoperability between
     * legacy APIs that return enumerations and new APIs that require collections.
     *
     * @param <T> the class of the objects returned by the enumeration
     * @param e enumeration providing elements for the returned array list
     * @return an array list containing the elements returned by the specified enumeration.
     * @since 1.4
     * @see Enumeration
     * @see ArrayList
     */
    public static <T> ArrayList<T> list(Enumeration<T> e) {
        ArrayList<T> list = new ArrayList<>();
        while (e.hasMoreElements()) {
            list.add(e.nextElement());
        }
        return list;
    }

    /**
     * Returns an unmodifiable view of the specified collection. This method allows modules to
     * provide users with "read-only" access to internal collections. Query operations on the
     * returned collection "read through" to the specified collection, and attempts to modify the
     * returned collection, whether direct or via its iterator, result in an
     * <tt>UnsupportedOperationException</tt>.
     * <p>
     * The returned collection does <i>not</i> pass the hashCode and equals operations through to
     * the backing collection, but relies on <tt>Object</tt>'s <tt>equals</tt> and <tt>hashCode</tt>
     * methods. This is necessary to preserve the contracts of these operations in the case that the
     * backing collection is a set or a list.
     * <p>
     * The returned collection will be serializable if the specified collection is serializable.
     *
     * @param <T> the class of the objects in the collection
     * @param collection the collection for which an unmodifiable view is to be returned.
     * @return an unmodifiable view of the specified collection.
     */
    public static <T> Collection<T> unmodifiableCollection(Collection<? extends T> collection) {
        return new UnmodifiableCollection(collection);
    }

    /**
     * Returns an unmodifiable view of the specified list. This method allows modules to provide
     * users with "read-only" access to internal lists. Query operations on the returned list "read
     * through" to the specified list, and attempts to modify the returned list, whether direct or
     * via its iterator, result in an <tt>UnsupportedOperationException</tt>.
     * <p>
     * The returned list will be serializable if the specified list is serializable. Similarly, the
     * returned list will implement {@link RandomAccess} if the specified list does.
     * 
     * @param list the list for which an unmodifiable view is to be returned.
     * @return an unmodifiable view of the specified list.
     */
    public static <T> List<T> unmodifiableList(List<? extends T> list) {
        return new UnmodifiableList(list);
    }

    /**
     * Returns an unmodifiable view of the specified map. This method allows modules to provide
     * users with "read-only" access to internal maps. Query operations on the returned map "read
     * through" to the specified map, and attempts to modify the returned map, whether direct or via
     * its collection views, result in an <tt>UnsupportedOperationException</tt>.
     * <p>
     * The returned map will be serializable if the specified map is serializable.
     * 
     * @param map the map for which an unmodifiable view is to be returned.
     * @return an unmodifiable view of the specified map.
     */
    public static <K, V> Map<K, V> unmodifiableMap(Map<? extends K, ? extends V> map) {
        return new UnmodifiableMap(map);
    }

    /**
     * Returns an unmodifiable view of the specified set. This method allows modules to provide
     * users with "read-only" access to internal sets. Query operations on the returned set "read
     * through" to the specified set, and attempts to modify the returned set, whether direct or via
     * its iterator, result in an <tt>UnsupportedOperationException</tt>.
     * <p>
     * The returned set will be serializable if the specified set is serializable.
     * 
     * @param set the set for which an unmodifiable view is to be returned.
     * @return an unmodifiable view of the specified set.
     */
    public static <T> Set<T> unmodifiableSet(Set<? extends T> set) {
        return new UnmodifiableSet(set);
    }

    /**
     * Returns an unmodifiable view of the specified sorted set. This method allows modules to
     * provide users with "read-only" access to internal sorted sets. Query operations on the
     * returned sorted set "read through" to the specified sorted set. Attempts to modify the
     * returned sorted set, whether direct, via its iterator, or via its <tt>subSet</tt>,
     * <tt>headSet</tt>, or <tt>tailSet</tt> views, result in an
     * <tt>UnsupportedOperationException</tt>.
     * <p>
     * The returned sorted set will be serializable if the specified sorted set is serializable.
     * 
     * @param set the sorted set for which an unmodifiable view is to be returned.
     * @return an unmodifiable view of the specified sorted set.
     */
    public static <T> SortedSet<T> unmodifiableSortedSet(SortedSet<T> set) {
        return new UnmodifiableSortedSet(set);
    }

    /**
     * Returns an immutable set containing only the specified object. The returned set is
     * serializable.
     * 
     * @param item the sole object to be stored in the returned set.
     * @return an immutable set containing only the specified object.
     */
    public static <T> Set<T> singleton(T item) {
        Set<T> set = new HashSet();
        set.add(item);

        return new UnmodifiableSet(set);
    }

    /**
     * Returns an immutable list containing only the specified object. The returned list is
     * serializable.
     * 
     * @param item the sole object to be stored in the returned list.
     * @return an immutable list containing only the specified object.
     * @since 1.3
     */
    public static <T> List<T> singletonList(T item) {
        List<T> list = new ArrayList();
        list.add(item);

        return new UnmodifiableList(list);
    }

    /**
     * Returns an immutable map, mapping only the specified key to the specified value. The returned
     * map is serializable.
     * 
     * @param key the sole key to be stored in the returned map.
     * @param value the value to which the returned map maps <tt>key</tt>.
     * @return an immutable map containing only the specified key-value mapping.
     * @since 1.3
     */
    public static <K, V> Map<K, V> singletonMap(K key, V value) {
        Map<K, V> map = new HashMap();
        map.put(key, value);

        return new UnmodifiableMap(map);
    }

    /**
     * Returns a synchronized (thread-safe) collection backed by the specified collection. In order
     * to guarantee serial access, it is critical that <strong>all</strong> access to the backing
     * collection is accomplished through the returned collection.
     * <p>
     * It is imperative that the user manually synchronize on the returned collection when iterating
     * over it: <pre>
     *  Collection c = Collections.synchronizedCollection(myCollection);
     *     ...
     *  synchronized (c) {
     *      Iterator i = c.iterator(); // Must be in the synchronized block
     *      while (i.hasNext())
     *         foo(i.next());
     *  }
     * </pre> Failure to follow this advice may result in non-deterministic behavior.
     * <p>
     * The returned collection does <i>not</i> pass the <tt>hashCode</tt> and <tt>equals</tt>
     * operations through to the backing collection, but relies on <tt>Object</tt>'s equals and
     * hashCode methods. This is necessary to preserve the contracts of these operations in the case
     * that the backing collection is a set or a list.
     * <p>
     * The returned collection will be serializable if the specified collection is serializable.
     * 
     * @param collection the collection to be "wrapped" in a synchronized collection.
     * @return a synchronized view of the specified collection.
     */
    public static <T> Collection<T> synchronizedCollection(Collection<T> collection) {
        return collection;
    }

    /**
     * <p>
     * Internal helper.
     * </p>
     * 
     * @param collection
     * @param mutex
     * @return
     */
    static <T> Collection<T> synchronizedCollection(Collection<T> collection, Object mutex) {
        return collection;
    }

    /**
     * Returns a synchronized (thread-safe) list backed by the specified list. In order to guarantee
     * serial access, it is critical that <strong>all</strong> access to the backing list is
     * accomplished through the returned list.
     * <p>
     * It is imperative that the user manually synchronize on the returned list when iterating over
     * it: <pre>
     *  List list = Collections.synchronizedList(new ArrayList());
     *      ...
     *  synchronized (list) {
     *      Iterator i = list.iterator(); // Must be in synchronized block
     *      while (i.hasNext())
     *          foo(i.next());
     *  }
     * </pre> Failure to follow this advice may result in non-deterministic behavior.
     * <p>
     * The returned list will be serializable if the specified list is serializable.
     * 
     * @param list the list to be "wrapped" in a synchronized list.
     * @return a synchronized view of the specified list.
     */
    public static <T> List<T> synchronizedList(List<T> list) {
        return list;
    }

    /**
     * <p>
     * Internal helper.
     * </p>
     * 
     * @param list
     * @param mutex
     * @return
     */
    static <T> List<T> synchronizedList(List<T> list, Object mutex) {
        return list;
    }

    /**
     * Returns a synchronized (thread-safe) set backed by the specified set. In order to guarantee
     * serial access, it is critical that <strong>all</strong> access to the backing set is
     * accomplished through the returned set.
     * <p>
     * It is imperative that the user manually synchronize on the returned set when iterating over
     * it: <pre>
     *  Set s = Collections.synchronizedSet(new HashSet());
     *      ...
     *  synchronized (s) {
     *      Iterator i = s.iterator(); // Must be in the synchronized block
     *      while (i.hasNext())
     *          foo(i.next());
     *  }
     * </pre> Failure to follow this advice may result in non-deterministic behavior.
     * <p>
     * The returned set will be serializable if the specified set is serializable.
     * 
     * @param set the set to be "wrapped" in a synchronized set.
     * @return a synchronized view of the specified set.
     */
    public static <T> Set<T> synchronizedSet(Set<T> set) {
        return set;
    }

    /**
     * <p>
     * Internal helper.
     * </p>
     * 
     * @param set
     * @param mutex
     * @return
     */
    static <T> Set<T> synchronizedSet(Set<T> set, Object mutex) {
        return set;
    }

    /**
     * Returns a synchronized (thread-safe) sorted set backed by the specified sorted set. In order
     * to guarantee serial access, it is critical that <strong>all</strong> access to the backing
     * sorted set is accomplished through the returned sorted set (or its views).
     * <p>
     * It is imperative that the user manually synchronize on the returned sorted set when iterating
     * over it or any of its <tt>subSet</tt>, <tt>headSet</tt>, or <tt>tailSet</tt> views. <pre>
     *  SortedSet s = Collections.synchronizedSortedSet(new TreeSet());
     *      ...
     *  synchronized (s) {
     *      Iterator i = s.iterator(); // Must be in the synchronized block
     *      while (i.hasNext())
     *          foo(i.next());
     *  }
     * </pre> or: <pre>
     *  SortedSet s = Collections.synchronizedSortedSet(new TreeSet());
     *  SortedSet s2 = s.headSet(foo);
     *      ...
     *  synchronized (s) {  // Note: s, not s2!!!
     *      Iterator i = s2.iterator(); // Must be in the synchronized block
     *      while (i.hasNext())
     *          foo(i.next());
     *  }
     * </pre> Failure to follow this advice may result in non-deterministic behavior.
     * <p>
     * The returned sorted set will be serializable if the specified sorted set is serializable.
     * 
     * @param set the sorted set to be "wrapped" in a synchronized sorted set.
     * @return a synchronized view of the specified sorted set.
     */
    public static <T> SortedSet<T> synchronizedSortedSet(SortedSet<T> set) {
        return set;
    }

    /**
     * Returns a synchronized (thread-safe) map backed by the specified map. In order to guarantee
     * serial access, it is critical that <strong>all</strong> access to the backing map is
     * accomplished through the returned map.
     * <p>
     * It is imperative that the user manually synchronize on the returned map when iterating over
     * any of its collection views: <pre>
     *  Map m = Collections.synchronizedMap(new HashMap());
     *      ...
     *  Set s = m.keySet();  // Needn't be in synchronized block
     *      ...
     *  synchronized (m) {  // Synchronizing on m, not s!
     *      Iterator i = s.iterator(); // Must be in synchronized block
     *      while (i.hasNext())
     *          foo(i.next());
     *  }
     * </pre> Failure to follow this advice may result in non-deterministic behavior.
     * <p>
     * The returned map will be serializable if the specified map is serializable.
     * 
     * @param map the map to be "wrapped" in a synchronized map.
     * @return a synchronized view of the specified map.
     */
    public static <K, V> Map<K, V> synchronizedMap(Map<K, V> map) {
        return map;
    }

    /**
     * Returns a synchronized (thread-safe) sorted map backed by the specified sorted map. In order
     * to guarantee serial access, it is critical that <strong>all</strong> access to the backing
     * sorted map is accomplished through the returned sorted map (or its views).
     * <p>
     * It is imperative that the user manually synchronize on the returned sorted map when iterating
     * over any of its collection views, or the collections views of any of its <tt>subMap</tt>,
     * <tt>headMap</tt> or <tt>tailMap</tt> views. <pre>
     *  SortedMap m = Collections.synchronizedSortedMap(new TreeMap());
     *      ...
     *  Set s = m.keySet();  // Needn't be in synchronized block
     *      ...
     *  synchronized (m) {  // Synchronizing on m, not s!
     *      Iterator i = s.iterator(); // Must be in synchronized block
     *      while (i.hasNext())
     *          foo(i.next());
     *  }
     * </pre> or: <pre>
     *  SortedMap m = Collections.synchronizedSortedMap(new TreeMap());
     *  SortedMap m2 = m.subMap(foo, bar);
     *      ...
     *  Set s2 = m2.keySet();  // Needn't be in synchronized block
     *      ...
     *  synchronized (m) {  // Synchronizing on m, not m2 or s2!
     *      Iterator i = s.iterator(); // Must be in synchronized block
     *      while (i.hasNext())
     *          foo(i.next());
     *  }
     * </pre> Failure to follow this advice may result in non-deterministic behavior.
     * <p>
     * The returned sorted map will be serializable if the specified sorted map is serializable.
     * 
     * @param map the sorted map to be "wrapped" in a synchronized sorted map.
     * @return a synchronized view of the specified sorted map.
     */
    public static <K, V> SortedMap<K, V> synchronizedSortedMap(SortedMap<K, V> map) {
        return map;
    }

    /**
     * Sorts the specified list into ascending order, according to the {@linkplain Comparable
     * natural ordering} of its elements. All elements in the list must implement the
     * {@link Comparable} interface. Furthermore, all elements in the list must be <i>mutually
     * comparable</i> (that is, {@code e1.compareTo(e2)} must not throw a {@code ClassCastException}
     * for any elements {@code e1} and {@code e2} in the list).
     * <p>
     * This sort is guaranteed to be <i>stable</i>: equal elements will not be reordered as a result
     * of the sort.
     * <p>
     * The specified list must be modifiable, but need not be resizable.
     * <p>
     * Implementation note: This implementation is a stable, adaptive, iterative mergesort that
     * requires far fewer than n lg(n) comparisons when the input array is partially sorted, while
     * offering the performance of a traditional mergesort when the input array is randomly ordered.
     * If the input array is nearly sorted, the implementation requires approximately n comparisons.
     * Temporary storage requirements vary from a small constant for nearly sorted input arrays to
     * n/2 object references for randomly ordered input arrays.
     * <p>
     * The implementation takes equal advantage of ascending and descending order in its input
     * array, and can take advantage of ascending and descending order in different parts of the
     * same input array. It is well-suited to merging two or more sorted arrays: simply concatenate
     * the arrays and sort the resulting array.
     * <p>
     * The implementation was adapted from Tim Peters's list sort for Python (
     * <a href="http://svn.python.org/projects/python/trunk/Objects/listsort.txt"> TimSort</a>). It
     * uses techiques from Peter McIlroy's "Optimistic Sorting and Information Theoretic
     * Complexity", in Proceedings of the Fourth Annual ACM-SIAM Symposium on Discrete Algorithms,
     * pp 467-474, January 1993.
     * <p>
     * This implementation dumps the specified list into an array, sorts the array, and iterates
     * over the list resetting each element from the corresponding position in the array. This
     * avoids the n<sup>2</sup> log(n) performance that would result from attempting to sort a
     * linked list in place.
     * 
     * @param list the list to be sorted.
     * @throws ClassCastException if the list contains elements that are not <i>mutually
     *             comparable</i> (for example, strings and integers).
     * @throws UnsupportedOperationException if the specified list's list-iterator does not support
     *             the {@code set} operation.
     * @throws IllegalArgumentException (optional) if the implementation detects that the natural
     *             ordering of the list elements is found to violate the {@link Comparable} contract
     */
    public static <T extends Comparable<? super T>> void sort(List<T> list) {
        Object[] values = list.toArray();

        Arrays.sort(values);

        ListIterator<T> iterator = list.listIterator();

        for (int i = 0; i < values.length; i++) {
            iterator.next();
            iterator.set((T) values[i]);
        }
    }

    /**
     * Sorts the specified list according to the order induced by the specified comparator. All
     * elements in the list must be <i>mutually comparable</i> using the specified comparator (that
     * is, {@code c.compare(e1, e2)} must not throw a {@code ClassCastException} for any elements
     * {@code e1} and {@code e2} in the list).
     * <p>
     * This sort is guaranteed to be <i>stable</i>: equal elements will not be reordered as a result
     * of the sort.
     * <p>
     * The specified list must be modifiable, but need not be resizable.
     * <p>
     * Implementation note: This implementation is a stable, adaptive, iterative mergesort that
     * requires far fewer than n lg(n) comparisons when the input array is partially sorted, while
     * offering the performance of a traditional mergesort when the input array is randomly ordered.
     * If the input array is nearly sorted, the implementation requires approximately n comparisons.
     * Temporary storage requirements vary from a small constant for nearly sorted input arrays to
     * n/2 object references for randomly ordered input arrays.
     * <p>
     * The implementation takes equal advantage of ascending and descending order in its input
     * array, and can take advantage of ascending and descending order in different parts of the
     * same input array. It is well-suited to merging two or more sorted arrays: simply concatenate
     * the arrays and sort the resulting array.
     * <p>
     * The implementation was adapted from Tim Peters's list sort for Python (
     * <a href="http://svn.python.org/projects/python/trunk/Objects/listsort.txt"> TimSort</a>). It
     * uses techiques from Peter McIlroy's "Optimistic Sorting and Information Theoretic
     * Complexity", in Proceedings of the Fourth Annual ACM-SIAM Symposium on Discrete Algorithms,
     * pp 467-474, January 1993.
     * <p>
     * This implementation dumps the specified list into an array, sorts the array, and iterates
     * over the list resetting each element from the corresponding position in the array. This
     * avoids the n<sup>2</sup> log(n) performance that would result from attempting to sort a
     * linked list in place.
     * 
     * @param list the list to be sorted.
     * @param comparator the comparator to determine the order of the list. A {@code null} value
     *            indicates that the elements' <i>natural ordering</i> should be used.
     * @throws ClassCastException if the list contains elements that are not <i>mutually
     *             comparable</i> using the specified comparator.
     * @throws UnsupportedOperationException if the specified list's list-iterator does not support
     *             the {@code set} operation.
     * @throws IllegalArgumentException (optional) if the comparator is found to violate the
     *             {@link Comparator} contract
     */
    public static <T> void sort(List<T> list, Comparator<? super T> comparator) {
        T[] values = (T[]) list.toArray();
        Arrays.sort(values, comparator);

        ListIterator iterator = list.listIterator();

        for (int i = 0; i < values.length; i++) {
            iterator.next();
            iterator.set(values[i]);
        }
    }

    /**
     * Returns a comparator that imposes the reverse of the <em>natural ordering</em> on a
     * collection of objects that implement the {@code Comparable} interface. (The natural ordering
     * is the ordering imposed by the objects' own {@code compareTo} method.) This enables a simple
     * idiom for sorting (or maintaining) collections (or arrays) of objects that implement the
     * {@code Comparable} interface in reverse-natural-order. For example, suppose {@code a} is an
     * array of strings. Then: <pre>
     *          Arrays.sort(a, Collections.reverseOrder());
     * </pre> sorts the array in reverse-lexicographic (alphabetical) order.
     * <p>
     * The returned comparator is serializable.
     * 
     * @return A comparator that imposes the reverse of the <i>natural ordering</i> on a collection
     *         of objects that implement the <tt>Comparable</tt> interface.
     * @see Comparable
     */
    public static <T> Comparator<T> reverseOrder() {
        return reverseOrder(null);
    }

    /**
     * Returns a comparator that imposes the reverse ordering of the specified comparator. If the
     * specified comparator is {@code null}, this method is equivalent to {@link #reverseOrder()}
     * (in other words, it returns a comparator that imposes the reverse of the <em>natural
     * ordering</em> on a collection of objects that implement the Comparable interface).
     * <p>
     * The returned comparator is serializable (assuming the specified comparator is also
     * serializable or {@code null}).
     * 
     * @param comparator a comparator who's ordering is to be reversed by the returned comparator or
     *            {@code null}
     * @return A comparator that imposes the reverse ordering of the specified comparator.
     * @since 1.5
     */
    public static <T> Comparator<T> reverseOrder(Comparator<T> comparator) {
        if (comparator == null) {
            comparator = (Comparator<T>) NATURAL_COMPARATOR;
        }

        if (comparator instanceof ReverseComparator) {
            return ((ReverseComparator<T>) comparator).comparator;
        }
        return new ReverseComparator<>(comparator);
    }

    /**
     * @version 2013/08/09 1:14:11
     */
    private static class ReverseComparator<T> implements Comparator<T> {

        /**
         * The comparator specified in the static factory. This will never be null, as the static
         * factory returns a ReverseComparator instance if its argument is null.
         */
        private final Comparator<T> comparator;

        /**
         * @param comparator
         */
        private ReverseComparator(Comparator<T> comparator) {
            this.comparator = comparator;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public int compare(T object1, T object2) {
            return comparator.compare(object2, object1);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean equals(Object object) {
            return object == this || (object instanceof ReverseComparator && comparator.equals(((ReverseComparator) object).comparator));
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public int hashCode() {
            return comparator.hashCode() ^ Integer.MIN_VALUE;
        }
    }

    /**
     * @version 2014/02/07 9:31:41
     */
    private static class EmptyEnumeration<E> implements Enumeration<E> {

        /** The singleton. */
        private static final EmptyEnumeration<Object> EMPTY_ENUMERATION = new EmptyEnumeration();

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean hasMoreElements() {
            return false;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public E nextElement() {
            throw new NoSuchElementException();
        }
    }

    /**
     * @version 2013/08/02 15:45:44
     */
    private static class EmptyIterator<E> implements Iterator<E> {

        /** The singleton. */
        private static final EmptyIterator EMPTY_ITERATOR = new EmptyIterator();

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean hasNext() {
            return false;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public E next() {
            throw new NoSuchElementException();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void remove() {
            throw new IllegalStateException();
        }
    }

    /**
     * @version 2013/08/02 15:46:25
     */
    private static class EmptyListIterator<E> extends EmptyIterator<E> implements ListIterator<E> {

        /** The singleton. */
        private static final EmptyListIterator EMPTY_ITERATOR = new EmptyListIterator();

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean hasPrevious() {
            return false;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public E previous() {
            throw new NoSuchElementException();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public int nextIndex() {
            return 0;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public int previousIndex() {
            return -1;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void set(E e) {
            throw new IllegalStateException();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void add(E e) {
            throw new UnsupportedOperationException();
        }
    }

    /**
     * @version 2013/08/04 11:35:14
     */
    private static class UnmodifiableCollection<E, C extends Collection<E>> implements Collection<E> {

        /** The delegator. */
        protected final C collection;

        /**
         * <p>
         * Create delegator.
         * </p>
         * 
         * @param collection
         */
        private UnmodifiableCollection(C collection) {
            if (collection == null) {
                throw new NullPointerException();
            }
            this.collection = collection;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public int size() {
            return collection.size();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean isEmpty() {
            return collection.isEmpty();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean contains(Object o) {
            return collection.contains(o);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Object[] toArray() {
            return collection.toArray();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public <T> T[] toArray(T[] a) {
            return collection.toArray(a);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String toString() {
            return collection.toString();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Iterator<E> iterator() {
            return new UnmodifiableIterator(collection.iterator());
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean add(E item) {
            throw new UnsupportedOperationException();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean remove(Object object) {
            throw new UnsupportedOperationException();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean containsAll(Collection<?> colletion) {
            return collection.containsAll(colletion);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean addAll(Collection<? extends E> colletion) {
            throw new UnsupportedOperationException();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean removeAll(Collection<?> colletion) {
            throw new UnsupportedOperationException();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean retainAll(Collection<?> collection) {
            throw new UnsupportedOperationException();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void clear() {
            throw new UnsupportedOperationException();
        }
    }

    /**
     * @version 2013/08/04 11:38:47
     */
    private static class UnmodifiableIterator<E, I extends Iterator<E>> implements Iterator<E> {

        /** The delegator. */
        protected final I iterator;

        /**
         * <p>
         * Create delegator.
         * </p>
         * 
         * @param iterator
         */
        protected UnmodifiableIterator(I iterator) {
            this.iterator = iterator;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public E next() {
            return iterator.next();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    /**
     * @version 2013/08/05 9:05:23
     */
    private static class UnmodifiableListIterator<E> extends UnmodifiableIterator<E, ListIterator<E>> implements ListIterator<E> {

        /**
         * <p>
         * Create delegator.
         * </p>
         * 
         * @param iterator
         */
        private UnmodifiableListIterator(ListIterator<E> iterator) {
            super(iterator);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean hasPrevious() {
            return iterator.hasPrevious();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public E previous() {
            return iterator.previous();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public int nextIndex() {
            return iterator.nextIndex();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public int previousIndex() {
            return iterator.previousIndex();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void set(E e) {
            throw new UnsupportedOperationException();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void add(E e) {
            throw new UnsupportedOperationException();
        }
    }

    /**
     * @version 2013/08/04 11:41:29
     */
    private static class UnmodifiableSet<E, S extends Set<E>> extends UnmodifiableCollection<E, S> implements Set<E> {

        /**
         * <p>
         * Create delegator.
         * </p>
         * 
         * @param set
         */
        private UnmodifiableSet(S set) {
            super(set);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean equals(Object object) {
            return object == this || collection.equals(object);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public int hashCode() {
            return collection.hashCode();
        }
    }

    /**
     * @version 2013/08/09 0:52:29
     */
    private static class UnmodifiableSortedSet<E> extends UnmodifiableSet<E, SortedSet<E>> implements SortedSet<E> {

        /**
         * <p>
         * Create delegator.
         * </p>
         * 
         * @param set
         */
        private UnmodifiableSortedSet(SortedSet<E> set) {
            super(set);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Comparator<? super E> comparator() {
            return collection.comparator();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public SortedSet<E> subSet(E fromElement, E toElement) {
            return new UnmodifiableSortedSet<>(collection.subSet(fromElement, toElement));
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public SortedSet<E> headSet(E toElement) {
            return new UnmodifiableSortedSet<>(collection.headSet(toElement));
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public SortedSet<E> tailSet(E fromElement) {
            return new UnmodifiableSortedSet<>(collection.tailSet(fromElement));
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public E first() {
            return collection.first();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public E last() {
            return collection.last();
        }
    }

    /**
     * @version 2013/08/04 11:34:48
     */
    private static class UnmodifiableList<E> extends UnmodifiableCollection<E, List<E>> implements List<E> {

        /**
         * <p>
         * Create delegator.
         * </p>
         * 
         * @param list
         */
        protected UnmodifiableList(List<E> list) {
            super(list);

        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean equals(Object object) {
            return object == this || collection.equals(object);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public int hashCode() {
            return collection.hashCode();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public E get(int index) {
            return collection.get(index);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public E set(int index, E element) {
            throw new UnsupportedOperationException();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void add(int index, E element) {
            throw new UnsupportedOperationException();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public E remove(int index) {
            throw new UnsupportedOperationException();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public int indexOf(Object item) {
            return collection.indexOf(item);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public int lastIndexOf(Object item) {
            return collection.lastIndexOf(item);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean addAll(int index, Collection<? extends E> collection) {
            throw new UnsupportedOperationException();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public ListIterator<E> listIterator() {
            return listIterator(0);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public ListIterator<E> listIterator(int index) {
            return new UnmodifiableListIterator(collection.listIterator(index));
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public List<E> subList(int fromIndex, int toIndex) {
            return new UnmodifiableList(collection.subList(fromIndex, toIndex));
        }
    }

    /**
     * @version 2013/08/04 12:10:19
     */
    private static class UnmodifiableMap<K, V> implements Map<K, V> {

        /** The delegator. */
        private final Map<K, V> map;

        /** cache. */
        private transient Set<K> keySet = null;

        /** cache. */
        private transient Set<Entry<K, V>> entrySet = null;

        /** cache. */
        private transient Collection<V> values = null;

        /**
         * <p>
         * Create delegator.
         * </p>
         * 
         * @param map
         */
        private UnmodifiableMap(Map<K, V> map) {
            if (map == null) {
                throw new NullPointerException();
            }
            this.map = map;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public int size() {
            return map.size();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean isEmpty() {
            return map.isEmpty();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean containsKey(Object key) {
            return map.containsKey(key);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean containsValue(Object val) {
            return map.containsValue(val);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public V get(Object key) {
            return map.get(key);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public V put(K key, V value) {
            throw new UnsupportedOperationException();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public V remove(Object key) {
            throw new UnsupportedOperationException();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void putAll(Map<? extends K, ? extends V> map) {
            throw new UnsupportedOperationException();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void clear() {
            throw new UnsupportedOperationException();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Set<K> keySet() {
            if (keySet == null) {
                keySet = new UnmodifiableSet(map.keySet());
            }
            return keySet;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Set<Entry<K, V>> entrySet() {
            if (entrySet == null) {
                entrySet = new UnmodifiableEntrySet(map.entrySet());
            }
            return entrySet;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Collection<V> values() {
            if (values == null) {
                values = new UnmodifiableCollection(map.values());
            }
            return values;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean equals(Object object) {
            return object == this || map.equals(object);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public int hashCode() {
            return map.hashCode();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String toString() {
            return map.toString();
        }
    }

    /**
     * @version 2013/08/04 16:38:01
     */
    private static class UnmodifiableEntry<K, V> implements Entry<K, V> {

        /** The delegator. */
        private final Entry<K, V> entry;

        /**
         * <p>
         * Create delegator.
         * </p>
         * 
         * @param entry
         */
        private UnmodifiableEntry(Entry<K, V> entry) {
            this.entry = entry;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public K getKey() {
            return entry.getKey();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public V getValue() {
            return entry.getValue();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public V setValue(V value) {
            throw new UnsupportedOperationException();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public int hashCode() {
            return entry.hashCode();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean equals(Object object) {
            if (this == object) {
                return true;
            }

            if (!(object instanceof Entry)) {
                return false;
            }
            Entry candidate = (Entry) object;
            return Objects.equals(entry.getKey(), candidate.getKey()) && Objects.equals(entry.getValue(), candidate.getValue());
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String toString() {
            return entry.toString();
        }
    }

    /**
     * We need this class in addition to UnmodifiableSet as Map.Entries themselves permit
     * modification of the backing Map via their setValue operation. This class is subtle: there are
     * many possible attacks that must be thwarted.
     * 
     * @serial include
     */
    private static class UnmodifiableEntrySet<K, V> extends UnmodifiableSet<Entry<K, V>, Set<Entry<K, V>>> {

        /**
         * <p>
         * Create delegator.
         * </p>
         * 
         * @param set
         */
        private UnmodifiableEntrySet(Set<Entry<K, V>> set) {
            super(set);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Iterator<Entry<K, V>> iterator() {
            return new UnmodifiableIterator<Entry<K, V>, Iterator<Entry<K, V>>>(collection.iterator()) {

                /**
                 * {@inheritDoc}
                 */
                @Override
                public Entry<K, V> next() {
                    return new UnmodifiableEntry(iterator.next());
                }
            };
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Object[] toArray() {
            Object[] array = collection.toArray();

            for (int i = 0; i < array.length; i++) {
                array[i] = new UnmodifiableEntry((Entry<K, V>) array[i]);
            }
            return array;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public <T> T[] toArray(T[] dest) {
            // We don't pass a to c.toArray, to avoid window of
            // vulnerability wherein an unscrupulous multithreaded client
            // could get his hands on raw (unwrapped) Entries from c.
            Object[] array = collection.toArray(dest.length == 0 ? dest : Arrays.copyOf(dest, 0));

            for (int i = 0; i < array.length; i++) {
                array[i] = new UnmodifiableEntry((Entry<K, V>) array[i]);
            }

            if (array.length > dest.length) {
                return (T[]) array;
            }

            System.arraycopy(array, 0, dest, 0, array.length);

            if (dest.length > array.length) {
                dest[array.length] = null;
            }
            return dest;
        }

        /**
         * This method is overridden to protect the backing set against an object with a nefarious
         * equals function that senses that the equality-candidate is Entry and calls its setValue
         * method.
         */
        @Override
        public boolean contains(Object object) {
            if (object instanceof Entry) {
                return collection.contains(new UnmodifiableEntry((Entry) object));
            }
            return false;
        }

        /**
         * This method is overridden to protect against an unscrupulous List whose contains(Object
         * o) method senses when o is a Entry, and calls o.setValue.
         */
        @Override
        public boolean containsAll(Collection collection) {
            for (Object object : collection) {
                if (!contains(object)) {
                    return false;
                }
            }
            return true;
        }

        /**
         * This method is overridden to protect against an unscrupulous List whose contains(Object
         * o) method senses when o is a Entry, and calls o.setValue.
         */
        @Override
        public boolean equals(Object object) {
            if (object == this) {
                return true;
            }

            if (object instanceof Set) {
                Set set = (Set) object;

                if (set.size() == collection.size()) {
                    return containsAll(set);
                }
            }
            return false;
        }
    }
}
