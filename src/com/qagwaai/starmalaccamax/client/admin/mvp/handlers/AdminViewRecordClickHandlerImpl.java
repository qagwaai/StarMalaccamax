package com.qagwaai.starmalaccamax.client.admin.mvp.handlers;

import com.qagwaai.starmalaccamax.client.admin.mvp.AdminView;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;

/**
 * 
 * @author ehldxae
 *
 */
public final class AdminViewRecordClickHandlerImpl implements RecordClickHandler {
    private final AdminView view;

    /**
     * 
     * @param view the view to notify
     */
    public AdminViewRecordClickHandlerImpl(final AdminView view) {
        this.view = view;
    }

    @Override
    public void onRecordClick(final RecordClickEvent event) {
        view.detailsFormReset();
        view.editSelectedListGridData();
    }
}
