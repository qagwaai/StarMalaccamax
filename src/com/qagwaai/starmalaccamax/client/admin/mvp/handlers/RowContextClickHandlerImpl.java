package com.qagwaai.starmalaccamax.client.admin.mvp.handlers;

import com.qagwaai.starmalaccamax.client.admin.mvp.AdminView;
import com.smartgwt.client.widgets.grid.events.RowContextClickEvent;
import com.smartgwt.client.widgets.grid.events.RowContextClickHandler;

/**
 * 
 * @author pgirard
 * 
 */
public final class RowContextClickHandlerImpl implements RowContextClickHandler {
    /**
	 * 
	 */
    private final AdminView view;

    /**
     * 
     * @param view
     *            the view
     */
    public RowContextClickHandlerImpl(final AdminView view) {
        this.view = view;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void onRowContextClick(final RowContextClickEvent event) {
        view.showContextMenu(event);

    }
}
