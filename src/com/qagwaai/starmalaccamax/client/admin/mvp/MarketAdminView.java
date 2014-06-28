package com.qagwaai.starmalaccamax.client.admin.mvp;

import com.smartgwt.client.widgets.grid.events.RowContextClickEvent;

public interface MarketAdminView extends AdminView {

    /**
     * 
     * @param event
     *            the event captured on the row
     */
    void showContextMenu(final RowContextClickEvent event);

}