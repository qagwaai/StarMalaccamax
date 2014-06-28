/**
 * 
 */
package com.qagwaai.starmalaccamax.client.admin.mvp.handlers;

import com.qagwaai.starmalaccamax.client.admin.mvp.AdminView;
import com.smartgwt.client.widgets.grid.events.DataArrivedEvent;
import com.smartgwt.client.widgets.grid.events.DataArrivedHandler;

/**
 * @author ehldxae
 * 
 */
public class ListGridDataArrivedHandlerImpl implements DataArrivedHandler {

    private final AdminView view;

    public ListGridDataArrivedHandlerImpl(AdminView view) {
        this.view = view;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.smartgwt.client.widgets.grid.events.DataArrivedHandler#onDataArrived
     * (com.smartgwt.client.widgets.grid.events.DataArrivedEvent)
     */
    @Override
    public void onDataArrived(DataArrivedEvent event) {
        view.setListGridLabelMessage("Total Records loaded: " + event.getEndRow() + ", Max Records: "
            + view.getListGridTotalRecords());

    }
}
