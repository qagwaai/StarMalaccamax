package com.qagwaai.starmalaccamax.client.admin.mvp.handlers;

import com.qagwaai.starmalaccamax.client.core.mvp.LoginBarViewImpl;
import com.qagwaai.starmalaccamax.client.service.ServiceFactory;
import com.qagwaai.starmalaccamax.client.service.action.RemoveAllJobs;
import com.qagwaai.starmalaccamax.client.service.action.RemoveAllJobsResponse;
import com.qagwaai.starmalaccamax.client.service.helpers.BaseAsyncCallback;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;

/**
 * 
 * @author pgirard
 * 
 */
public final class RemoveAllJobsClickHandlerImpl implements com.smartgwt.client.widgets.menu.events.ClickHandler {
    /**
	 * 
	 */
    private final LoginBarViewImpl view;

    /**
     * 
     * @param view
     *            the associated view
     */
    public RemoveAllJobsClickHandlerImpl(final LoginBarViewImpl view) {
        this.view = view;
    }

    @Override
    public void onClick(final MenuItemClickEvent event) {
        SC.confirm("Proceed with remove ALL Jobs?", new BooleanCallback() {
            public void execute(final Boolean value) {
                if (value != null && value) {
                    ServiceFactory.getJobService().execute(new RemoveAllJobs(),
                        new BaseAsyncCallback<RemoveAllJobsResponse>() {

                            @Override
                            public void onFailure(final Throwable caught) {
                                super.onFailure(caught);
                                view.say("Remove All Jobs", "Error removing all Jobs: " + caught.getMessage());

                            }

                            @Override
                            public void onSuccess(final RemoveAllJobsResponse result) {
                                super.onSuccess(result);
                                view.say("Remove All Jobs", "Success");

                            }
                        });
                }
            }
        });

    }
}
