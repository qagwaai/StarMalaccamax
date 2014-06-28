package com.qagwaai.starmalaccamax.client.admin.mvp.handlers;

import com.qagwaai.starmalaccamax.client.core.mvp.LoginBarViewImpl;
import com.qagwaai.starmalaccamax.client.service.ServiceFactory;
import com.qagwaai.starmalaccamax.client.service.action.RemoveAllShipTypes;
import com.qagwaai.starmalaccamax.client.service.action.RemoveAllShipTypesResponse;
import com.qagwaai.starmalaccamax.client.service.helpers.BaseAsyncCallback;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;

/**
 * 
 * @author pgirard
 * 
 */

public final class RemoveAllShipTypesClickHandlerImpl implements com.smartgwt.client.widgets.menu.events.ClickHandler {
    /**
	 * 
	 */
    private final LoginBarViewImpl view;

    /**
     * 
     * @param view
     *            the associated view
     */
    public RemoveAllShipTypesClickHandlerImpl(final LoginBarViewImpl view) {
        this.view = view;
    }

    @Override
    public void onClick(final MenuItemClickEvent event) {
        SC.confirm("Proceed with remove ALL ShipTypes?", new BooleanCallback() {
            public void execute(final Boolean value) {
                if (value != null && value) {
                    ServiceFactory.getShipService().execute(new RemoveAllShipTypes(),
                        new BaseAsyncCallback<RemoveAllShipTypesResponse>() {

                            @Override
                            public void onFailure(final Throwable caught) {
                                super.onFailure(caught);
                                view.say("Remove All ShipTypes", "Error removing all ShipTypes: " + caught.getMessage());

                            }

                            @Override
                            public void onSuccess(final RemoveAllShipTypesResponse result) {
                                super.onSuccess(result);
                                view.say("Remove All ShipTypes", "Success");

                            }
                        });
                }
            }
        });

    }
}