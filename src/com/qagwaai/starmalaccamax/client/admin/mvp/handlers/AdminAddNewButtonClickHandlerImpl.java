package com.qagwaai.starmalaccamax.client.admin.mvp.handlers;

import com.qagwaai.starmalaccamax.client.admin.mvp.AdminView;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;

/**
 * 
 * @author ehldxae
 *
 */
public final class AdminAddNewButtonClickHandlerImpl implements ClickHandler {
    private final AdminView view;

    /**
     * 
     * @param view the view to notify
     */
    public AdminAddNewButtonClickHandlerImpl(final AdminView view) {
        this.view = view;
    }

    @Override
    public void onClick(final ClickEvent event) {
        view.detailsFormEditNewRecord();
    }
}