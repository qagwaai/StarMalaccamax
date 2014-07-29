package com.qagwaai.starmalaccamax.client.admin.mvp.handlers;

import com.qagwaai.starmalaccamax.client.core.mvp.LoginBarViewImpl;
import com.qagwaai.starmalaccamax.client.service.ServiceFactory;
import com.qagwaai.starmalaccamax.client.service.action.RemoveAllCaptains;
import com.qagwaai.starmalaccamax.client.service.action.RemoveAllCaptainsResponse;
import com.qagwaai.starmalaccamax.client.service.helpers.BaseAsyncCallback;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;

/**
 * 
 * @author pgirard
 * 
 */
public final class RemoveAllCaptainsClickHandlerImpl implements com.smartgwt.client.widgets.menu.events.ClickHandler {
    /**
	 * 
	 */
    private final LoginBarViewImpl view;

    /**
     * 
     * @param view
     *            the associated view
     */
    public RemoveAllCaptainsClickHandlerImpl(final LoginBarViewImpl view) {
        this.view = view;
    }

    @Override
    public void onClick(final MenuItemClickEvent event) {
        SC.confirm("Proceed with remove ALL Captains?", new BooleanCallback() {
            public void execute(final Boolean value) {
                if (value != null && value) {
                    ServiceFactory.getCaptainService().execute(new RemoveAllCaptains(),
                        new BaseAsyncCallback<RemoveAllCaptainsResponse>() {

                            @Override
                            public void onFailure(final Throwable caught) {
                                super.onFailure(caught);
                                view.say("Remove All Captains", "Error removing all Captains: " + caught.getMessage());

                            }

                            @Override
                            public void onSuccess(final RemoveAllCaptainsResponse result) {
                                super.onSuccess(result);
                                view.say("Remove All Captains", "Success");

                            }
                        });
                }
            }
        });

    }
}