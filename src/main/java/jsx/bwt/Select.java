/*
 * Copyright (C) 2013 Nameless Production Committee
 *
 * Licensed under the MIT License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://opensource.org/licenses/mit-license.php
 */
package jsx.bwt;

import static js.lang.Global.*;
import js.util.jQuery;
import jsx.bwt.FormUIStyle.Focus;
import jsx.bwt.SelectStyle.SelectArrow;
import jsx.bwt.SelectStyle.SelectForm;
import jsx.bwt.SelectStyle.SelectItem;
import jsx.bwt.SelectStyle.SelectItemList;
import jsx.bwt.SelectStyle.SelectedItem;
import jsx.bwt.view.ScrollableListView;
import jsx.bwt.view.ScrollableListView.ItemRenderer;
import jsx.bwt.view.SlidableView;
import jsx.model.SelectableListener;
import jsx.model.SelectableModel;

/**
 * @version 2013/04/08 23:38:34
 */
public class Select<M> extends FormUI<Select> {

    /** The associated model. */
    public final SelectableModel<M> model;

    /** The option list. */
    public final SlidableView options;

    /** The associated view. */
    protected final ScrollableListView view;

    /** The view-model binder. */
    private final Binder binder = new Binder();

    /** The select button. */
    private final Button arrow;

    /**
     * <p>
     * Create select form.
     * </p>
     */
    public Select(M... model) {
        this(new SelectableModel(model));
    }

    /**
     * <p>
     * Create select form.
     * </p>
     */
    public Select(SelectableModel<M> selectable) {
        model = selectable;
        model.bind(binder);

        form.add(SelectForm.class).attr("type", "input").attr("placeholder", "Mastery Set Name");

        view = new ScrollableListView(10, 28).provide(binder);
        view.root.add(SelectItemList.class).bind(binder);

        options = root.child(new SlidableView(view));
        options.bind(binder);

        arrow = root.child(new Button(Icon.BottomArrow, options));
        arrow.root.add(SelectArrow.class);

        if (model.size() == 0) {
            disable();
        }
    }

    @Listen(ui = UIAction.Key_Up)
    private void selectPrevious() {
        model.selectPrevious();
    }

    @Listen(ui = UIAction.Key_Down)
    private void selectNext() {
        model.selectNext();
    }

    @Listen(ui = UIAction.Focus)
    private void startInput() {
        root.add(Focus.class);
    }

    @Listen(ui = UIAction.Blur)
    private void endInput() {
        root.remove(Focus.class);
    }

    /**
     * @version 2013/04/05 10:06:20
     */
    private class Binder implements ItemRenderer, SelectableListener<M> {

        @Listen(ui = UIAction.Click)
        private void selectItem(UIEvent event) {
            model.setSelectionIndex(Integer.parseInt($(event.target).attr("index")));
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public int countItem() {
            return model.size();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void renderItem(int itemIndex, jQuery element) {
            element.add(SelectItem.class).attr("index", itemIndex).text(model.get(itemIndex));

            if (itemIndex == model.getSelectionIndex()) {
                element.add(SelectedItem.class);
            } else {
                element.remove(SelectedItem.class);
            }
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void select(int index, M item) {
            form.val(item.toString());

            view.render(index);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void deselect(int index, M item) {
            view.render(index);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void add(int index, M item) {
            view.provide(this);
            view.render(index);

            enable();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void remove(int index, M item) {
            view.provide(this);

            if (model.size() == 0) {
                disable();
            }
        }
    }
}
