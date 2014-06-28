package com.qagwaai.starmalaccamax.client.admin.mvp.handlers;

import com.qagwaai.starmalaccamax.client.core.mvp.LoginBarViewImpl;
import com.qagwaai.starmalaccamax.client.service.ServiceFactory;
import com.qagwaai.starmalaccamax.client.service.action.RemoveAllGameEvents;
import com.qagwaai.starmalaccamax.client.service.action.RemoveAllGameEventsResponse;
import com.qagwaai.starmalaccamax.client.service.helpers.BaseAsyncCallback;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;

/**
 * 
 * @author pgirard
 * 
 */
public final class RemoveAllGameEventsClickHandlerImpl implements com.smartgwt.client.widgets.menu.events.ClickHandler {
    /**
	 * 
	 */
    private final LoginBarViewImpl view;

    /**
     * 
     * @param view
     *            the associated view
     */
    public RemoveAllGameEventsClickHandlerImpl(final LoginBarViewImpl view) {
        this.view = view;
    }

    @Override
    public void onClick(final MenuItemClickEvent event) {
        SC.confirm("Proceed with remove ALL GameEvents?", new BooleanCallback() {
            public void execute(final Boolean value) {
                if (value != null && value) {
                    ServiceFactory.getGameEventService().execute(new RemoveAllGameEvents(),
                        new BaseAsyncCallback<RemoveAllGameEventsResponse>() {

                            @Override
                            public void onFailure(final Throwable caught) {
                                super.onFailure(caught);
                                view.say("Remove All GameEvents",
                                    "Error removing all GameEvents: " + caught.getMessage());

                            }

                            @Override
                            public void onSuccess(final RemoveAllGameEventsResponse result) {
                                super.onSuccess(result);
                                view.say("Remove All GameEvents", "Success");

                            }
                        });
                }
            }
        });

    }
}