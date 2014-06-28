package com.qagwaai.starmalaccamax.client.admin.mvp;

import com.smartgwt.client.widgets.grid.events.RowContextClickEvent;

public interface SolarSystemAdminView extends AdminView {

    /**
     * 
     * @param event
     *            the event captured on the row click
     */
    void showContextMenu(final RowContextClickEvent event);

}