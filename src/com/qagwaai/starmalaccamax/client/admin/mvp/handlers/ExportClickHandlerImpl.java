package com.qagwaai.starmalaccamax.client.admin.mvp.handlers;

import com.qagwaai.starmalaccamax.client.admin.mvp.AdminView;

/**
 * 
 * @author pgirard
 * 
 */
public final class ExportClickHandlerImpl implements com.smartgwt.client.widgets.events.ClickHandler {
    /**
	 * 
	 */
    private final AdminView view;

    /**
     * 
     * @param view
     *            the view
     */
    public ExportClickHandlerImpl(final AdminView view) {
        this.view = view;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void onClick(final com.smartgwt.client.widgets.events.ClickEvent event) {
        view.exportListGridData();
    }
}
