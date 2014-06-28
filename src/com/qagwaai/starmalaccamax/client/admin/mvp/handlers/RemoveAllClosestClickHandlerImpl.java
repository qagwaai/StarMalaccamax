package com.qagwaai.starmalaccamax.client.admin.mvp.handlers;

import com.qagwaai.starmalaccamax.client.core.mvp.LoginBarViewImpl;
import com.qagwaai.starmalaccamax.client.service.ServiceFactory;
import com.qagwaai.starmalaccamax.client.service.action.RemoveAllClosest;
import com.qagwaai.starmalaccamax.client.service.action.RemoveAllClosestResponse;
import com.qagwaai.starmalaccamax.client.service.helpers.BaseAsyncCallback;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;

/**
 * 
 * @author pgirard
 * 
 */
public final class RemoveAllClosestClickHandlerImpl implements com.smartgwt.client.widgets.menu.events.ClickHandler {
    /**
	 * 
	 */
    private final LoginBarViewImpl view;

    /**
     * 
     * @param view
     *            the associated view
     */
    public RemoveAllClosestClickHandlerImpl(final LoginBarViewImpl view) {
        this.view = view;
    }

    @Override
    public void onClick(final MenuItemClickEvent event) {
        SC.confirm("Proceed with remove ALL closests?", new BooleanCallback() {
            public void execute(final Boolean value) {
                if (value != null && value) {
                    ServiceFactory.getSolarSystemService().execute(new RemoveAllClosest(),
                        new BaseAsyncCallback<RemoveAllClosestResponse>() {

                            @Override
                            public void onFailure(final Throwable caught) {
                                super.onFailure(caught);
                                view.say("Remove All Closest", "Error removing all closest: " + caught.getMessage());

                            }

                            @Override
                            public void onSuccess(final RemoveAllClosestResponse result) {
                                super.onSuccess(result);
                                view.say("Remove All Closest", "Success");

                            }
                        });
                }
            }
        });

    }
}