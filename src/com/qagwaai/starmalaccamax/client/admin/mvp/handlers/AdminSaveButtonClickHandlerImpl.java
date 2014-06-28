package com.qagwaai.starmalaccamax.client.admin.mvp.handlers;

import com.qagwaai.starmalaccamax.client.admin.mvp.AdminView;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;

/**
 * 
 * @author ehldxae
 *
 */
public final class AdminSaveButtonClickHandlerImpl implements ClickHandler {
    private final AdminView view;

    /**
     * 
     * @param view the view to notify
     */
    public AdminSaveButtonClickHandlerImpl(final AdminView view) {
        this.view = view;
    }

    @Override
    public void onClick(final ClickEvent event) {
        view.detailsFormSave();
    }
}