/*
 * Copyright (C) 2013 Nameless Production Committee
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://opensource.org/licenses/mit-license.php
 */
package js.util;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.PrimitiveIterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.IntConsumer;
import java.util.function.LongConsumer;

import booton.JDKEmulator;
import booton.translator.JavaAPIProvider;

/**
 * @version 2013/10/29 16:19:18
 */
@JavaAPIProvider(java.util.Spliterators.class)
class Spliterators {

    /** reuse */
    private static final Spliterator EMPTY_SPLITERATOR = new Spliterator() {

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean tryAdvance(Consumer action) {
            return false;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Spliterator trySplit() {
            return null;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public long estimateSize() {
            return 0;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public int characteristics() {
            return Spliterator.SIZED | Spliterator.SUBSIZED;
        }
    };

    /** reuse */
    private static final Spliterator.OfInt EMPTY_INT = new Spliterator.OfInt() {

        /**
         * {@inheritDoc}
         */
        @Override
        public void forEachRemaining(IntConsumer action) {
        }

        @Override
        public long estimateSize() {
            return 0;
        }

        @Override
        public int characteristics() {
            return Spliterator.SIZED | Spliterator.SUBSIZED;
        }

        @Override
        public OfInt trySplit() {
            return null;
        }

        @Override
        public boolean tryAdvance(IntConsumer action) {
            return false;
        }
    };

    /** reuse */
    private static final Spliterator.OfLong EMPTY_LONG = new Spliterator.OfLong() {

        /**
         * {@inheritDoc}
         */
        @Override
        public void forEachRemaining(LongConsumer action) {
        }

        @Override
        public long estimateSize() {
            return 0;
        }

        @Override
        public int characteristics() {
            return Spliterator.SIZED | Spliterator.SUBSIZED;
        }

        @Override
        public OfLong trySplit() {
            return null;
        }

        @Override
        public boolean tryAdvance(LongConsumer action) {
            return false;
        }
    };

    /** reuse */
    private static final Spliterator.OfDouble EMPTY_DOUBE = new Spliterator.OfDouble() {

        /**
         * {@inheritDoc}
         */
        @Override
        public void forEachRemaining(DoubleConsumer action) {
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public long estimateSize() {
            return 0;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public int characteristics() {
            return Spliterator.SIZED | Spliterator.SUBSIZED;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public OfDouble trySplit() {
            return null;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean tryAdvance(DoubleConsumer action) {
            return false;
        }
    };

    /**
     * Creates an empty {@code Spliterator}
     * <p>
     * The empty spliterator reports {@link Spliterator#SIZED} and {@link Spliterator#SUBSIZED}.
     * Calls to {@link java.util.Spliterator#trySplit()} always return {@code null}.
     * 
     * @param <T> Type of elements
     * @return An empty spliterator
     */
    @SuppressWarnings("unchecked")
    public static <T> Spliterator<T> emptySpliterator() {
        return (Spliterator<T>) EMPTY_SPLITERATOR;
    }

    /**
     * Creates an empty {@code Spliterator.OfInt}
     * <p>
     * The empty spliterator reports {@link Spliterator#SIZED} and {@link Spliterator#SUBSIZED}.
     * Calls to {@link java.util.Spliterator#trySplit()} always return {@code null}.
     * 
     * @return An empty spliterator
     */
    public static Spliterator.OfInt emptyIntSpliterator() {
        return EMPTY_INT;
    }

    /**
     * Creates an empty {@code Spliterator.OfLong}
     * <p>
     * The empty spliterator reports {@link Spliterator#SIZED} and {@link Spliterator#SUBSIZED}.
     * Calls to {@link java.util.Spliterator#trySplit()} always return {@code null}.
     * 
     * @return An empty spliterator
     */
    public static Spliterator.OfLong emptyLongSpliterator() {
        return EMPTY_LONG;
    }

    /**
     * Creates an empty {@code Spliterator.OfDouble}
     * <p>
     * The empty spliterator reports {@link Spliterator#SIZED} and {@link Spliterator#SUBSIZED}.
     * Calls to {@link java.util.Spliterator#trySplit()} always return {@code null}.
     * 
     * @return An empty spliterator
     */
    public static Spliterator.OfDouble emptyDoubleSpliterator() {
        return EMPTY_DOUBE;
    }

    /**
     * Creates an {@code Iterator} from a {@code Spliterator}.
     * <p>
     * Traversal of elements should be accomplished through the iterator. The behaviour of traversal
     * is undefined if the spliterator is operated after the iterator is returned.
     * 
     * @param <T> Type of elements
     * @param spliterator The spliterator
     * @return An iterator
     * @throws NullPointerException if the given spliterator is {@code null}
     */
    public static <T> Iterator<T> iterator(Spliterator<? extends T> spliterator) {
        Objects.requireNonNull(spliterator);
        class Adapter implements Iterator<T>, Consumer<T> {

            boolean valueReady = false;

            T nextElement;

            @Override
            public void accept(T t) {
                valueReady = true;
                nextElement = t;
            }

            @Override
            public boolean hasNext() {
                if (!valueReady) spliterator.tryAdvance(this);
                return valueReady;
            }

            @Override
            public T next() {
                if (!valueReady && !hasNext())
                    throw new NoSuchElementException();
                else {
                    valueReady = false;
                    return nextElement;
                }
            }
        }

        return new Adapter();
    }

    /**
     * Creates an {@code PrimitiveIterator.OfInt} from a {@code Spliterator.OfInt}.
     * <p>
     * Traversal of elements should be accomplished through the iterator. The behaviour of traversal
     * is undefined if the spliterator is operated after the iterator is returned.
     * 
     * @param spliterator The spliterator
     * @return An iterator
     * @throws NullPointerException if the given spliterator is {@code null}
     */
    public static PrimitiveIterator.OfInt iterator(Spliterator.OfInt spliterator) {
        Objects.requireNonNull(spliterator);
        class Adapter implements PrimitiveIterator.OfInt, IntConsumer {

            boolean valueReady = false;

            int nextElement;

            /**
             * {@inheritDoc}
             */
            @Override
            public void forEachRemaining(Consumer<? super Integer> action) {
            }

            @Override
            public void accept(int t) {
                valueReady = true;
                nextElement = t;
            }

            @Override
            public boolean hasNext() {
                if (!valueReady) spliterator.tryAdvance(this);
                return valueReady;
            }

            @Override
            public int nextInt() {
                if (!valueReady && !hasNext())
                    throw new NoSuchElementException();
                else {
                    valueReady = false;
                    return nextElement;
                }
            }
        }

        return new Adapter();
    }

    /**
     * Creates an {@code PrimitiveIterator.OfLong} from a {@code Spliterator.OfLong}.
     * <p>
     * Traversal of elements should be accomplished through the iterator. The behaviour of traversal
     * is undefined if the spliterator is operated after the iterator is returned.
     * 
     * @param spliterator The spliterator
     * @return An iterator
     * @throws NullPointerException if the given spliterator is {@code null}
     */
    public static PrimitiveIterator.OfLong iterator(Spliterator.OfLong spliterator) {
        Objects.requireNonNull(spliterator);
        class Adapter implements PrimitiveIterator.OfLong, LongConsumer {

            boolean valueReady = false;

            long nextElement;

            /**
             * {@inheritDoc}
             */
            @Override
            public void forEachRemaining(Consumer<? super Long> action) {
            }

            @Override
            public void accept(long t) {
                valueReady = true;
                nextElement = t;
            }

            @Override
            public boolean hasNext() {
                if (!valueReady) spliterator.tryAdvance(this);
                return valueReady;
            }

            @Override
            public long nextLong() {
                if (!valueReady && !hasNext())
                    throw new NoSuchElementException();
                else {
                    valueReady = false;
                    return nextElement;
                }
            }
        }

        return new Adapter();
    }

    /**
     * Creates an {@code PrimitiveIterator.OfDouble} from a {@code Spliterator.OfDouble}.
     * <p>
     * Traversal of elements should be accomplished through the iterator. The behaviour of traversal
     * is undefined if the spliterator is operated after the iterator is returned.
     * 
     * @param spliterator The spliterator
     * @return An iterator
     * @throws NullPointerException if the given spliterator is {@code null}
     */
    public static PrimitiveIterator.OfDouble iterator(Spliterator.OfDouble spliterator) {
        Objects.requireNonNull(spliterator);
        class Adapter implements PrimitiveIterator.OfDouble, DoubleConsumer {

            boolean valueReady = false;

            double nextElement;

            /**
             * {@inheritDoc}
             */
            @Override
            public void forEachRemaining(Consumer<? super Double> action) {
            }

            @Override
            public void accept(double t) {
                valueReady = true;
                nextElement = t;
            }

            @Override
            public boolean hasNext() {
                if (!valueReady) spliterator.tryAdvance(this);
                return valueReady;
            }

            @Override
            public double nextDouble() {
                if (!valueReady && !hasNext())
                    throw new NoSuchElementException();
                else {
                    valueReady = false;
                    return nextElement;
                }
            }
        }

        return new Adapter();
    }

    /**
     * Creates a {@code Spliterator} using the given collection's
     * {@link java.util.Collection#iterator()} as the source of elements, and reporting its
     * {@link java.util.Collection#size()} as its initial size.
     * <p>
     * The spliterator is <em><a href="Spliterator.html#binding">late-binding</a></em>, inherits the
     * <em>fail-fast</em> properties of the collection's iterator, and implements {@code trySplit}
     * to permit limited parallelism.
     * 
     * @param <T> Type of elements
     * @param c The collection
     * @param characteristics Characteristics of this spliterator's source or elements. The
     *            characteristics {@code SIZED} and {@code SUBSIZED} are additionally reported
     *            unless {@code CONCURRENT} is supplied.
     * @return A spliterator from an iterator
     * @throws NullPointerException if the given collection is {@code null}
     */
    public static <T> Spliterator<T> spliterator(Collection<? extends T> c, int characteristics) {
        return new IteratorSpliterator<>(Objects.requireNonNull(c), characteristics);
    }

    /**
     * Creates a {@code Spliterator} using a given {@code Iterator} as the source of elements, and
     * with a given initially reported size.
     * <p>
     * The spliterator is not <em><a href="Spliterator.html#binding">late-binding</a></em>, inherits
     * the <em>fail-fast</em> properties of the iterator, and implements {@code trySplit} to permit
     * limited parallelism.
     * <p>
     * Traversal of elements should be accomplished through the spliterator. The behaviour of
     * splitting and traversal is undefined if the iterator is operated on after the spliterator is
     * returned, or the initially reported size is not equal to the actual number of elements in the
     * source.
     * 
     * @param <T> Type of elements
     * @param iterator The iterator for the source
     * @param size The number of elements in the source, to be reported as initial
     *            {@code estimateSize}
     * @param characteristics Characteristics of this spliterator's source or elements. The
     *            characteristics {@code SIZED} and {@code SUBSIZED} are additionally reported
     *            unless {@code CONCURRENT} is supplied.
     * @return A spliterator from an iterator
     * @throws NullPointerException if the given iterator is {@code null}
     */
    public static <T> Spliterator<T> spliterator(Iterator<? extends T> iterator, long size, int characteristics) {
        return new IteratorSpliterator<>(Objects.requireNonNull(iterator), size, characteristics);
    }

    /**
     * Creates a {@code Spliterator} using a given {@code Iterator} as the source of elements, with
     * no initial size estimate.
     * <p>
     * The spliterator is not <em><a href="Spliterator.html#binding">late-binding</a></em>, inherits
     * the <em>fail-fast</em> properties of the iterator, and implements {@code trySplit} to permit
     * limited parallelism.
     * <p>
     * Traversal of elements should be accomplished through the spliterator. The behaviour of
     * splitting and traversal is undefined if the iterator is operated on after the spliterator is
     * returned.
     * 
     * @param <T> Type of elements
     * @param iterator The iterator for the source
     * @param characteristics Characteristics of this spliterator's source or elements ({@code SIZED}
     *            and {@code SUBSIZED}, if supplied, are ignored and are not reported.)
     * @return A spliterator from an iterator
     * @throws NullPointerException if the given iterator is {@code null}
     */
    public static <T> Spliterator<T> spliteratorUnknownSize(Iterator<? extends T> iterator, int characteristics) {
        return new IteratorSpliterator<>(Objects.requireNonNull(iterator), characteristics);
    }

    /**
     * Creates a {@code Spliterator.OfInt} using a given {@code IntStream.IntIterator} as the source
     * of elements, and with a given initially reported size.
     * <p>
     * The spliterator is not <em><a href="Spliterator.html#binding">late-binding</a></em>, inherits
     * the <em>fail-fast</em> properties of the iterator, and implements {@code trySplit} to permit
     * limited parallelism.
     * <p>
     * Traversal of elements should be accomplished through the spliterator. The behaviour of
     * splitting and traversal is undefined if the iterator is operated on after the spliterator is
     * returned, or the initially reported size is not equal to the actual number of elements in the
     * source.
     * 
     * @param iterator The iterator for the source
     * @param size The number of elements in the source, to be reported as initial
     *            {@code estimateSize}.
     * @param characteristics Characteristics of this spliterator's source or elements. The
     *            characteristics {@code SIZED} and {@code SUBSIZED} are additionally reported
     *            unless {@code CONCURRENT} is supplied.
     * @return A spliterator from an iterator
     * @throws NullPointerException if the given iterator is {@code null}
     */
    public static Spliterator.OfInt spliterator(PrimitiveIterator.OfInt iterator, long size, int characteristics) {
        return new IntIteratorSpliterator(Objects.requireNonNull(iterator), size, characteristics);
    }

    /**
     * Creates a {@code Spliterator.OfInt} using a given {@code IntStream.IntIterator} as the source
     * of elements, with no initial size estimate.
     * <p>
     * The spliterator is not <em><a href="Spliterator.html#binding">late-binding</a></em>, inherits
     * the <em>fail-fast</em> properties of the iterator, and implements {@code trySplit} to permit
     * limited parallelism.
     * <p>
     * Traversal of elements should be accomplished through the spliterator. The behaviour of
     * splitting and traversal is undefined if the iterator is operated on after the spliterator is
     * returned.
     * 
     * @param iterator The iterator for the source
     * @param characteristics Characteristics of this spliterator's source or elements ({@code SIZED}
     *            and {@code SUBSIZED}, if supplied, are ignored and are not reported.)
     * @return A spliterator from an iterator
     * @throws NullPointerException if the given iterator is {@code null}
     */
    public static Spliterator.OfInt spliteratorUnknownSize(PrimitiveIterator.OfInt iterator, int characteristics) {
        return new IntIteratorSpliterator(Objects.requireNonNull(iterator), characteristics);
    }

    /**
     * Creates a {@code Spliterator.OfLong} using a given {@code LongStream.LongIterator} as the
     * source of elements, and with a given initially reported size.
     * <p>
     * The spliterator is not <em><a href="Spliterator.html#binding">late-binding</a></em>, inherits
     * the <em>fail-fast</em> properties of the iterator, and implements {@code trySplit} to permit
     * limited parallelism.
     * <p>
     * Traversal of elements should be accomplished through the spliterator. The behaviour of
     * splitting and traversal is undefined if the iterator is operated on after the spliterator is
     * returned, or the initially reported size is not equal to the actual number of elements in the
     * source.
     * 
     * @param iterator The iterator for the source
     * @param size The number of elements in the source, to be reported as initial
     *            {@code estimateSize}.
     * @param characteristics Characteristics of this spliterator's source or elements. The
     *            characteristics {@code SIZED} and {@code SUBSIZED} are additionally reported
     *            unless {@code CONCURRENT} is supplied.
     * @return A spliterator from an iterator
     * @throws NullPointerException if the given iterator is {@code null}
     */
    public static Spliterator.OfLong spliterator(PrimitiveIterator.OfLong iterator, long size, int characteristics) {
        return new LongIteratorSpliterator(Objects.requireNonNull(iterator), size, characteristics);
    }

    /**
     * Creates a {@code Spliterator.OfLong} using a given {@code LongStream.LongIterator} as the
     * source of elements, with no initial size estimate.
     * <p>
     * The spliterator is not <em><a href="Spliterator.html#binding">late-binding</a></em>, inherits
     * the <em>fail-fast</em> properties of the iterator, and implements {@code trySplit} to permit
     * limited parallelism.
     * <p>
     * Traversal of elements should be accomplished through the spliterator. The behaviour of
     * splitting and traversal is undefined if the iterator is operated on after the spliterator is
     * returned.
     * 
     * @param iterator The iterator for the source
     * @param characteristics Characteristics of this spliterator's source or elements ({@code SIZED}
     *            and {@code SUBSIZED}, if supplied, are ignored and are not reported.)
     * @return A spliterator from an iterator
     * @throws NullPointerException if the given iterator is {@code null}
     */
    public static Spliterator.OfLong spliteratorUnknownSize(PrimitiveIterator.OfLong iterator, int characteristics) {
        return new LongIteratorSpliterator(Objects.requireNonNull(iterator), characteristics);
    }

    /**
     * Creates a {@code Spliterator.OfDouble} using a given {@code DoubleStream.DoubleIterator} as
     * the source of elements, and with a given initially reported size.
     * <p>
     * The spliterator is not <em><a href="Spliterator.html#binding">late-binding</a></em>, inherits
     * the <em>fail-fast</em> properties of the iterator, and implements {@code trySplit} to permit
     * limited parallelism.
     * <p>
     * Traversal of elements should be accomplished through the spliterator. The behaviour of
     * splitting and traversal is undefined if the iterator is operated on after the spliterator is
     * returned, or the initially reported size is not equal to the actual number of elements in the
     * source.
     * 
     * @param iterator The iterator for the source
     * @param size The number of elements in the source, to be reported as initial
     *            {@code estimateSize}
     * @param characteristics Characteristics of this spliterator's source or elements. The
     *            characteristics {@code SIZED} and {@code SUBSIZED} are additionally reported
     *            unless {@code CONCURRENT} is supplied.
     * @return A spliterator from an iterator
     * @throws NullPointerException if the given iterator is {@code null}
     */
    public static Spliterator.OfDouble spliterator(PrimitiveIterator.OfDouble iterator, long size, int characteristics) {
        return new DoubleIteratorSpliterator(Objects.requireNonNull(iterator), size, characteristics);
    }

    /**
     * Creates a {@code Spliterator.OfDouble} using a given {@code DoubleStream.DoubleIterator} as
     * the source of elements, with no initial size estimate.
     * <p>
     * The spliterator is not <em><a href="Spliterator.html#binding">late-binding</a></em>, inherits
     * the <em>fail-fast</em> properties of the iterator, and implements {@code trySplit} to permit
     * limited parallelism.
     * <p>
     * Traversal of elements should be accomplished through the spliterator. The behaviour of
     * splitting and traversal is undefined if the iterator is operated on after the spliterator is
     * returned.
     * 
     * @param iterator The iterator for the source
     * @param characteristics Characteristics of this spliterator's source or elements ({@code SIZED}
     *            and {@code SUBSIZED}, if supplied, are ignored and are not reported.)
     * @return A spliterator from an iterator
     * @throws NullPointerException if the given iterator is {@code null}
     */
    public static Spliterator.OfDouble spliteratorUnknownSize(PrimitiveIterator.OfDouble iterator, int characteristics) {
        return new DoubleIteratorSpliterator(Objects.requireNonNull(iterator), characteristics);
    }

    /**
     * A Spliterator designed for use by sources that traverse and split elements maintained in an
     * unmodifiable {@code Object[]} array.
     */
    @JavaAPIProvider(JDKEmulator.class)
    static final class ArraySpliterator<T> implements Spliterator<T> {

        /**
         * The array, explicitly typed as Object[]. Unlike in some other classes (see for example CR
         * 6260652), we do not need to screen arguments to ensure they are exactly of type Object[]
         * so long as no methods write into the array or serialize it, which we ensure here by
         * defining this class as final.
         */
        private final Object[] array;

        private int index; // current index, modified on advance/split

        private final int fence; // one past last index

        private final int characteristics;

        /**
         * Creates a spliterator covering all of the given array.
         * 
         * @param array the array, assumed to be unmodified during use
         * @param additionalCharacteristics Additional spliterator characteristics of this
         *            spliterator's source or elements beyond {@code SIZED} and {@code SUBSIZED}
         *            which are are always reported
         */
        public ArraySpliterator(Object[] array, int additionalCharacteristics) {
            this(array, 0, array.length, additionalCharacteristics);
        }

        /**
         * Creates a spliterator covering the given array and range
         * 
         * @param array the array, assumed to be unmodified during use
         * @param origin the least index (inclusive) to cover
         * @param fence one past the greatest index to cover
         * @param additionalCharacteristics Additional spliterator characteristics of this
         *            spliterator's source or elements beyond {@code SIZED} and {@code SUBSIZED}
         *            which are are always reported
         */
        public ArraySpliterator(Object[] array, int origin, int fence, int additionalCharacteristics) {
            this.array = array;
            this.index = origin;
            this.fence = fence;
            this.characteristics = additionalCharacteristics | Spliterator.SIZED | Spliterator.SUBSIZED;
        }

        @Override
        public Spliterator<T> trySplit() {
            int lo = index, mid = (lo + fence) >>> 1;
            return (lo >= mid) ? null : new ArraySpliterator(array, lo, index = mid, characteristics);
        }

        @SuppressWarnings("unchecked")
        @Override
        public void forEachRemaining(Consumer<? super T> action) {
            Object[] a;
            int i, hi; // hoist accesses and checks from loop
            if (action == null) throw new NullPointerException();
            if ((a = array).length >= (hi = fence) && (i = index) >= 0 && i < (index = hi)) {
                do {
                    action.accept((T) a[i]);
                } while (++i < hi);
            }
        }

        @Override
        public boolean tryAdvance(Consumer<? super T> action) {
            if (action == null) throw new NullPointerException();
            if (index >= 0 && index < fence) {
                @SuppressWarnings("unchecked")
                T e = (T) array[index++];
                action.accept(e);
                return true;
            }
            return false;
        }

        @Override
        public long estimateSize() {
            return (long) (fence - index);
        }

        @Override
        public int characteristics() {
            return characteristics;
        }

        @Override
        public Comparator<? super T> getComparator() {
            if (hasCharacteristics(Spliterator.SORTED)) return null;
            throw new IllegalStateException();
        }
    }

    /**
     * A Spliterator.OfInt designed for use by sources that traverse and split elements maintained
     * in an unmodifiable {@code int[]} array.
     */
    // @JavaAPIProvider(Emulator.class)
    static final class IntArraySpliterator implements Spliterator.OfInt {

        private final int[] array;

        private int index; // current index, modified on advance/split

        private final int fence; // one past last index

        private final int characteristics;

        /**
         * Creates a spliterator covering all of the given array.
         * 
         * @param array the array, assumed to be unmodified during use
         * @param additionalCharacteristics Additional spliterator characteristics of this
         *            spliterator's source or elements beyond {@code SIZED} and {@code SUBSIZED}
         *            which are are always reported
         */
        public IntArraySpliterator(int[] array, int additionalCharacteristics) {
            this(array, 0, array.length, additionalCharacteristics);
        }

        /**
         * Creates a spliterator covering the given array and range
         * 
         * @param array the array, assumed to be unmodified during use
         * @param origin the least index (inclusive) to cover
         * @param fence one past the greatest index to cover
         * @param additionalCharacteristics Additional spliterator characteristics of this
         *            spliterator's source or elements beyond {@code SIZED} and {@code SUBSIZED}
         *            which are are always reported
         */
        public IntArraySpliterator(int[] array, int origin, int fence, int additionalCharacteristics) {
            this.array = array;
            this.index = origin;
            this.fence = fence;
            this.characteristics = additionalCharacteristics | Spliterator.SIZED | Spliterator.SUBSIZED;
        }

        @Override
        public OfInt trySplit() {
            int lo = index, mid = (lo + fence) >>> 1;
            return (lo >= mid) ? null : new IntArraySpliterator(array, lo, index = mid, characteristics);
        }

        @Override
        public void forEachRemaining(IntConsumer action) {
            int[] a;
            int i, hi; // hoist accesses and checks from loop
            if (action == null) throw new NullPointerException();
            if ((a = array).length >= (hi = fence) && (i = index) >= 0 && i < (index = hi)) {
                do {
                    action.accept(a[i]);
                } while (++i < hi);
            }
        }

        @Override
        public boolean tryAdvance(IntConsumer action) {
            if (action == null) throw new NullPointerException();
            if (index >= 0 && index < fence) {
                action.accept(array[index++]);
                return true;
            }
            return false;
        }

        @Override
        public long estimateSize() {
            return (long) (fence - index);
        }

        @Override
        public int characteristics() {
            return characteristics;
        }

        @Override
        public Comparator<? super Integer> getComparator() {
            if (hasCharacteristics(Spliterator.SORTED)) return null;
            throw new IllegalStateException();
        }
    }

    /**
     * A Spliterator.OfLong designed for use by sources that traverse and split elements maintained
     * in an unmodifiable {@code int[]} array.
     */
    static final class LongArraySpliterator implements Spliterator.OfLong {

        private final long[] array;

        private int index; // current index, modified on advance/split

        private final int fence; // one past last index

        private final int characteristics;

        /**
         * Creates a spliterator covering all of the given array.
         * 
         * @param array the array, assumed to be unmodified during use
         * @param additionalCharacteristics Additional spliterator characteristics of this
         *            spliterator's source or elements beyond {@code SIZED} and {@code SUBSIZED}
         *            which are are always reported
         */
        public LongArraySpliterator(long[] array, int additionalCharacteristics) {
            this(array, 0, array.length, additionalCharacteristics);
        }

        /**
         * Creates a spliterator covering the given array and range
         * 
         * @param array the array, assumed to be unmodified during use
         * @param origin the least index (inclusive) to cover
         * @param fence one past the greatest index to cover
         * @param additionalCharacteristics Additional spliterator characteristics of this
         *            spliterator's source or elements beyond {@code SIZED} and {@code SUBSIZED}
         *            which are are always reported
         */
        public LongArraySpliterator(long[] array, int origin, int fence, int additionalCharacteristics) {
            this.array = array;
            this.index = origin;
            this.fence = fence;
            this.characteristics = additionalCharacteristics | Spliterator.SIZED | Spliterator.SUBSIZED;
        }

        @Override
        public OfLong trySplit() {
            int lo = index, mid = (lo + fence) >>> 1;
            return (lo >= mid) ? null : new LongArraySpliterator(array, lo, index = mid, characteristics);
        }

        @Override
        public void forEachRemaining(LongConsumer action) {
            long[] a;
            int i, hi; // hoist accesses and checks from loop
            if (action == null) throw new NullPointerException();
            if ((a = array).length >= (hi = fence) && (i = index) >= 0 && i < (index = hi)) {
                do {
                    action.accept(a[i]);
                } while (++i < hi);
            }
        }

        @Override
        public boolean tryAdvance(LongConsumer action) {
            if (action == null) throw new NullPointerException();
            if (index >= 0 && index < fence) {
                action.accept(array[index++]);
                return true;
            }
            return false;
        }

        @Override
        public long estimateSize() {
            return (long) (fence - index);
        }

        @Override
        public int characteristics() {
            return characteristics;
        }

        @Override
        public Comparator<? super Long> getComparator() {
            if (hasCharacteristics(Spliterator.SORTED)) return null;
            throw new IllegalStateException();
        }
    }

    /**
     * A Spliterator.OfDouble designed for use by sources that traverse and split elements
     * maintained in an unmodifiable {@code int[]} array.
     */
    static final class DoubleArraySpliterator implements Spliterator.OfDouble {

        private final double[] array;

        private int index; // current index, modified on advance/split

        private final int fence; // one past last index

        private final int characteristics;

        /**
         * Creates a spliterator covering all of the given array.
         * 
         * @param array the array, assumed to be unmodified during use
         * @param additionalCharacteristics Additional spliterator characteristics of this
         *            spliterator's source or elements beyond {@code SIZED} and {@code SUBSIZED}
         *            which are are always reported
         */
        public DoubleArraySpliterator(double[] array, int additionalCharacteristics) {
            this(array, 0, array.length, additionalCharacteristics);
        }

        /**
         * Creates a spliterator covering the given array and range
         * 
         * @param array the array, assumed to be unmodified during use
         * @param origin the least index (inclusive) to cover
         * @param fence one past the greatest index to cover
         * @param additionalCharacteristics Additional spliterator characteristics of this
         *            spliterator's source or elements beyond {@code SIZED} and {@code SUBSIZED}
         *            which are are always reported
         */
        public DoubleArraySpliterator(double[] array, int origin, int fence, int additionalCharacteristics) {
            this.array = array;
            this.index = origin;
            this.fence = fence;
            this.characteristics = additionalCharacteristics | Spliterator.SIZED | Spliterator.SUBSIZED;
        }

        @Override
        public OfDouble trySplit() {
            int lo = index, mid = (lo + fence) >>> 1;
            return (lo >= mid) ? null : new DoubleArraySpliterator(array, lo, index = mid, characteristics);
        }

        @Override
        public void forEachRemaining(DoubleConsumer action) {
            double[] a;
            int i, hi; // hoist accesses and checks from loop
            if (action == null) throw new NullPointerException();
            if ((a = array).length >= (hi = fence) && (i = index) >= 0 && i < (index = hi)) {
                do {
                    action.accept(a[i]);
                } while (++i < hi);
            }
        }

        @Override
        public boolean tryAdvance(DoubleConsumer action) {
            if (action == null) throw new NullPointerException();
            if (index >= 0 && index < fence) {
                action.accept(array[index++]);
                return true;
            }
            return false;
        }

        @Override
        public long estimateSize() {
            return (long) (fence - index);
        }

        @Override
        public int characteristics() {
            return characteristics;
        }

        @Override
        public Comparator<? super Double> getComparator() {
            if (hasCharacteristics(Spliterator.SORTED)) return null;
            throw new IllegalStateException();
        }
    }

    /**
     * A Spliterator using a given Iterator for element operations. The spliterator implements
     * {@code trySplit} to permit limited parallelism.
     */
    static class IteratorSpliterator<T> implements Spliterator<T> {

        static final int BATCH_UNIT = 1 << 10; // batch array size increment

        static final int MAX_BATCH = 1 << 25; // max batch array size;

        private final Collection<? extends T> collection; // null OK

        private Iterator<? extends T> it;

        private final int characteristics;

        private long est; // size estimate

        private int batch; // batch size for splits

/**
         * Creates a spliterator using the given given
         * collection's {@link java.util.Collection#iterator()) for traversal,
         * and reporting its {@link java.util.Collection#size()) as its initial
         * size.
         *
         * @param c the collection
         * @param characteristics properties of this spliterator's
         *        source or elements.
         */
        public IteratorSpliterator(Collection<? extends T> collection, int characteristics) {
            this.collection = collection;
            this.it = null;
            this.characteristics = (characteristics & Spliterator.CONCURRENT) == 0 ? characteristics | Spliterator.SIZED | Spliterator.SUBSIZED
                    : characteristics;
        }

        /**
         * Creates a spliterator using the given iterator for traversal, and reporting the given
         * initial size and characteristics.
         * 
         * @param iterator the iterator for the source
         * @param size the number of elements in the source
         * @param characteristics properties of this spliterator's source or elements.
         */
        public IteratorSpliterator(Iterator<? extends T> iterator, long size, int characteristics) {
            this.collection = null;
            this.it = iterator;
            this.est = size;
            this.characteristics = (characteristics & Spliterator.CONCURRENT) == 0 ? characteristics | Spliterator.SIZED | Spliterator.SUBSIZED
                    : characteristics;
        }

        /**
         * Creates a spliterator using the given iterator for traversal, and reporting the given
         * initial size and characteristics.
         * 
         * @param iterator the iterator for the source
         * @param characteristics properties of this spliterator's source or elements.
         */
        public IteratorSpliterator(Iterator<? extends T> iterator, int characteristics) {
            this.collection = null;
            this.it = iterator;
            this.est = Long.MAX_VALUE;
            this.characteristics = characteristics & ~(Spliterator.SIZED | Spliterator.SUBSIZED);
        }

        @Override
        public Spliterator<T> trySplit() {
            /*
             * Split into arrays of arithmetically increasing batch sizes. This will only improve
             * parallel performance if per-element Consumer actions are more costly than
             * transferring them into an array. The use of an arithmetic progression in split sizes
             * provides overhead vs parallelism bounds that do not particularly favor or penalize
             * cases of lightweight vs heavyweight element operations, across combinations of
             * #elements vs #cores, whether or not either are known. We generate O(sqrt(#elements))
             * splits, allowing O(sqrt(#cores)) potential speedup.
             */
            Iterator<? extends T> i;
            long s;
            if ((i = it) == null) {
                i = it = collection.iterator();
                s = est = (long) collection.size();
            } else
                s = est;
            if (s > 1 && i.hasNext()) {
                int n = batch + BATCH_UNIT;
                if (n > s) n = (int) s;
                if (n > MAX_BATCH) n = MAX_BATCH;
                Object[] a = new Object[n];
                int j = 0;
                do {
                    a[j] = i.next();
                } while (++j < n && i.hasNext());
                batch = j;
                if (est != Long.MAX_VALUE) est -= j;
                return new ArraySpliterator<>(a, 0, j, characteristics);
            }
            return null;
        }

        @Override
        public void forEachRemaining(Consumer<? super T> action) {
            if (action == null) throw new NullPointerException();
            Iterator<? extends T> i;
            if ((i = it) == null) {
                i = it = collection.iterator();
                est = (long) collection.size();
            }
            i.forEachRemaining(action);
        }

        @Override
        public boolean tryAdvance(Consumer<? super T> action) {
            if (action == null) throw new NullPointerException();
            if (it == null) {
                it = collection.iterator();
                est = (long) collection.size();
            }
            if (it.hasNext()) {
                action.accept(it.next());
                return true;
            }
            return false;
        }

        @Override
        public long estimateSize() {
            if (it == null) {
                it = collection.iterator();
                return est = (long) collection.size();
            }
            return est;
        }

        @Override
        public int characteristics() {
            return characteristics;
        }

        @Override
        public Comparator<? super T> getComparator() {
            if (hasCharacteristics(Spliterator.SORTED)) return null;
            throw new IllegalStateException();
        }
    }

    /**
     * A Spliterator.OfInt using a given IntStream.IntIterator for element operations. The
     * spliterator implements {@code trySplit} to permit limited parallelism.
     */
    static final class IntIteratorSpliterator implements Spliterator.OfInt {

        static final int BATCH_UNIT = IteratorSpliterator.BATCH_UNIT;

        static final int MAX_BATCH = IteratorSpliterator.MAX_BATCH;

        private PrimitiveIterator.OfInt it;

        private final int characteristics;

        private long est; // size estimate

        private int batch; // batch size for splits

        /**
         * Creates a spliterator using the given iterator for traversal, and reporting the given
         * initial size and characteristics.
         * 
         * @param iterator the iterator for the source
         * @param size the number of elements in the source
         * @param characteristics properties of this spliterator's source or elements.
         */
        public IntIteratorSpliterator(PrimitiveIterator.OfInt iterator, long size, int characteristics) {
            this.it = iterator;
            this.est = size;
            this.characteristics = (characteristics & Spliterator.CONCURRENT) == 0 ? characteristics | Spliterator.SIZED | Spliterator.SUBSIZED
                    : characteristics;
        }

        /**
         * Creates a spliterator using the given iterator for a source of unknown size, reporting
         * the given characteristics.
         * 
         * @param iterator the iterator for the source
         * @param characteristics properties of this spliterator's source or elements.
         */
        public IntIteratorSpliterator(PrimitiveIterator.OfInt iterator, int characteristics) {
            this.it = iterator;
            this.est = Long.MAX_VALUE;
            this.characteristics = characteristics & ~(Spliterator.SIZED | Spliterator.SUBSIZED);
        }

        @Override
        public OfInt trySplit() {
            PrimitiveIterator.OfInt i = it;
            long s = est;
            if (s > 1 && i.hasNext()) {
                int n = batch + BATCH_UNIT;
                if (n > s) n = (int) s;
                if (n > MAX_BATCH) n = MAX_BATCH;
                int[] a = new int[n];
                int j = 0;
                do {
                    a[j] = i.nextInt();
                } while (++j < n && i.hasNext());
                batch = j;
                if (est != Long.MAX_VALUE) est -= j;
                return new IntArraySpliterator(a, 0, j, characteristics);
            }
            return null;
        }

        @Override
        public void forEachRemaining(IntConsumer action) {
            if (action == null) throw new NullPointerException();
            it.forEachRemaining(action);
        }

        @Override
        public boolean tryAdvance(IntConsumer action) {
            if (action == null) throw new NullPointerException();
            if (it.hasNext()) {
                action.accept(it.nextInt());
                return true;
            }
            return false;
        }

        @Override
        public long estimateSize() {
            return est;
        }

        @Override
        public int characteristics() {
            return characteristics;
        }

        @Override
        public Comparator<? super Integer> getComparator() {
            if (hasCharacteristics(Spliterator.SORTED)) return null;
            throw new IllegalStateException();
        }
    }

    static final class LongIteratorSpliterator implements Spliterator.OfLong {

        static final int BATCH_UNIT = IteratorSpliterator.BATCH_UNIT;

        static final int MAX_BATCH = IteratorSpliterator.MAX_BATCH;

        private PrimitiveIterator.OfLong it;

        private final int characteristics;

        private long est; // size estimate

        private int batch; // batch size for splits

        /**
         * Creates a spliterator using the given iterator for traversal, and reporting the given
         * initial size and characteristics.
         * 
         * @param iterator the iterator for the source
         * @param size the number of elements in the source
         * @param characteristics properties of this spliterator's source or elements.
         */
        public LongIteratorSpliterator(PrimitiveIterator.OfLong iterator, long size, int characteristics) {
            this.it = iterator;
            this.est = size;
            this.characteristics = (characteristics & Spliterator.CONCURRENT) == 0 ? characteristics | Spliterator.SIZED | Spliterator.SUBSIZED
                    : characteristics;
        }

        /**
         * Creates a spliterator using the given iterator for a source of unknown size, reporting
         * the given characteristics.
         * 
         * @param iterator the iterator for the source
         * @param characteristics properties of this spliterator's source or elements.
         */
        public LongIteratorSpliterator(PrimitiveIterator.OfLong iterator, int characteristics) {
            this.it = iterator;
            this.est = Long.MAX_VALUE;
            this.characteristics = characteristics & ~(Spliterator.SIZED | Spliterator.SUBSIZED);
        }

        @Override
        public OfLong trySplit() {
            PrimitiveIterator.OfLong i = it;
            long s = est;
            if (s > 1 && i.hasNext()) {
                int n = batch + BATCH_UNIT;
                if (n > s) n = (int) s;
                if (n > MAX_BATCH) n = MAX_BATCH;
                long[] a = new long[n];
                int j = 0;
                do {
                    a[j] = i.nextLong();
                } while (++j < n && i.hasNext());
                batch = j;
                if (est != Long.MAX_VALUE) est -= j;
                return new LongArraySpliterator(a, 0, j, characteristics);
            }
            return null;
        }

        @Override
        public void forEachRemaining(LongConsumer action) {
            if (action == null) throw new NullPointerException();
            it.forEachRemaining(action);
        }

        @Override
        public boolean tryAdvance(LongConsumer action) {
            if (action == null) throw new NullPointerException();
            if (it.hasNext()) {
                action.accept(it.nextLong());
                return true;
            }
            return false;
        }

        @Override
        public long estimateSize() {
            return est;
        }

        @Override
        public int characteristics() {
            return characteristics;
        }

        @Override
        public Comparator<? super Long> getComparator() {
            if (hasCharacteristics(Spliterator.SORTED)) return null;
            throw new IllegalStateException();
        }
    }

    static final class DoubleIteratorSpliterator implements Spliterator.OfDouble {

        static final int BATCH_UNIT = IteratorSpliterator.BATCH_UNIT;

        static final int MAX_BATCH = IteratorSpliterator.MAX_BATCH;

        private PrimitiveIterator.OfDouble it;

        private final int characteristics;

        private long est; // size estimate

        private int batch; // batch size for splits

        /**
         * Creates a spliterator using the given iterator for traversal, and reporting the given
         * initial size and characteristics.
         * 
         * @param iterator the iterator for the source
         * @param size the number of elements in the source
         * @param characteristics properties of this spliterator's source or elements.
         */
        public DoubleIteratorSpliterator(PrimitiveIterator.OfDouble iterator, long size, int characteristics) {
            this.it = iterator;
            this.est = size;
            this.characteristics = (characteristics & Spliterator.CONCURRENT) == 0 ? characteristics | Spliterator.SIZED | Spliterator.SUBSIZED
                    : characteristics;
        }

        /**
         * Creates a spliterator using the given iterator for a source of unknown size, reporting
         * the given characteristics.
         * 
         * @param iterator the iterator for the source
         * @param characteristics properties of this spliterator's source or elements.
         */
        public DoubleIteratorSpliterator(PrimitiveIterator.OfDouble iterator, int characteristics) {
            this.it = iterator;
            this.est = Long.MAX_VALUE;
            this.characteristics = characteristics & ~(Spliterator.SIZED | Spliterator.SUBSIZED);
        }

        @Override
        public OfDouble trySplit() {
            PrimitiveIterator.OfDouble i = it;
            long s = est;
            if (s > 1 && i.hasNext()) {
                int n = batch + BATCH_UNIT;
                if (n > s) n = (int) s;
                if (n > MAX_BATCH) n = MAX_BATCH;
                double[] a = new double[n];
                int j = 0;
                do {
                    a[j] = i.nextDouble();
                } while (++j < n && i.hasNext());
                batch = j;
                if (est != Long.MAX_VALUE) est -= j;
                return new DoubleArraySpliterator(a, 0, j, characteristics);
            }
            return null;
        }

        @Override
        public void forEachRemaining(DoubleConsumer action) {
            if (action == null) throw new NullPointerException();
            it.forEachRemaining(action);
        }

        @Override
        public boolean tryAdvance(DoubleConsumer action) {
            if (action == null) throw new NullPointerException();
            if (it.hasNext()) {
                action.accept(it.nextDouble());
                return true;
            }
            return false;
        }

        @Override
        public long estimateSize() {
            return est;
        }

        @Override
        public int characteristics() {
            return characteristics;
        }

        @Override
        public Comparator<? super Double> getComparator() {
            if (hasCharacteristics(Spliterator.SORTED)) return null;
            throw new IllegalStateException();
        }
    }
}
