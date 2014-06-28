package com.qagwaai.starmalaccamax.client.admin.mvp.handlers;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.qagwaai.starmalaccamax.client.admin.constants.AdminConstants;
import com.qagwaai.starmalaccamax.client.admin.mvp.AdminView;
import com.qagwaai.starmalaccamax.client.admin.util.PleaseWaitView;
import com.smartgwt.client.data.RecordList;

/**
 * 
 * @author pgirard
 * 
 */
public final class SelectAllClickHandlerImpl implements com.smartgwt.client.widgets.events.ClickHandler {
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
    public SelectAllClickHandlerImpl(final AdminView view) {
        this.view = view;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void onClick(final com.smartgwt.client.widgets.events.ClickEvent event) {
        final PleaseWaitView pwv = new PleaseWaitView(constants.selectingRecords(), constants.selecting());
        pwv.show();
        Scheduler.get().scheduleDeferred(new ScheduledCommand() {

            @Override
            public void execute() {
                RecordList records = view.getListGridRecordList();
                for (int i = 0; i < records.getLength(); i++) {
                    if (records.get(i) != null) {
                        view.selectListGridRecord(records.get(i));
                        pwv.getBar().setPercentDone(Math.round(((float) i / (float) records.getLength()) * 100));
                    }
                }
                pwv.hide();
            }
        });

    }
}
