package com.qagwaai.starmalaccamax.client.admin.mvp.handlers;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.qagwaai.starmalaccamax.client.admin.constants.AdminConstants;
import com.qagwaai.starmalaccamax.client.admin.mvp.AdminView;
import com.qagwaai.starmalaccamax.client.admin.util.PleaseWaitView;
import com.smartgwt.client.widgets.grid.ListGridRecord;

/**
 * 
 * @author pgirard
 * 
 */
public final class DeleteClickHandlerImpl implements com.smartgwt.client.widgets.events.ClickHandler {
    /**
	 * 
	 */
    private final AdminView view;
    private static AdminConstants constants = GWT.create(AdminConstants.class);

    /**
     * 
     * @param view
     *            the view
     */
    public DeleteClickHandlerImpl(final AdminView view) {
        this.view = view;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void onClick(final com.smartgwt.client.widgets.events.ClickEvent event) {
        final PleaseWaitView pwv = new PleaseWaitView(constants.deletingRecords(), constants.deleting());
        pwv.show();
        Scheduler.get().scheduleDeferred(new ScheduledCommand() {

            @Override
            public void execute() {
                ListGridRecord[] selected = view.getListGridSelectedRecords();
                int total = selected.length;
                int current = 0;
                for (ListGridRecord record : selected) {
                    view.removeListGridData(record);
                    current++;
                    pwv.getBar().setPercentDone(Math.round(((float) current / (float) total) * 100));
                }
                pwv.hide();
            }
        });
    }
}
