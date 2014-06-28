package com.qagwaai.starmalaccamax.client.admin.mvp.handlers;

import com.qagwaai.starmalaccamax.client.core.mvp.LoginBarViewImpl;
import com.qagwaai.starmalaccamax.client.service.ServiceFactory;
import com.qagwaai.starmalaccamax.client.service.action.RemoveAllShips;
import com.qagwaai.starmalaccamax.client.service.action.RemoveAllShipsResponse;
import com.qagwaai.starmalaccamax.client.service.helpers.BaseAsyncCallback;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;

/**
 * 
 * @author pgirard
 * 
 */

public final class RemoveAllShipsClickHandlerImpl implements com.smartgwt.client.widgets.menu.events.ClickHandler {
    /**
	 * 
	 */
    private final LoginBarViewImpl view;

    /**
     * 
     * @param view
     *            the associated view
     */
    public RemoveAllShipsClickHandlerImpl(final LoginBarViewImpl view) {
        this.view = view;
    }

    @Override
    public void onClick(final MenuItemClickEvent event) {
        SC.confirm("Proceed with remove ALL Ships?", new BooleanCallback() {
            public void execute(final Boolean value) {
                if (value != null && value) {
                    ServiceFactory.getShipService().execute(new RemoveAllShips(),
                        new BaseAsyncCallback<RemoveAllShipsResponse>() {

                            @Override
                            public void onFailure(final Throwable caught) {
                                super.onFailure(caught);
                                view.say("Remove All Ships", "Error removing all ships: " + caught.getMessage());

                            }

                            @Override
                            public void onSuccess(final RemoveAllShipsResponse result) {
                                super.onSuccess(result);
                                view.say("Remove All Ships", "Success");

                            }
                        });
                }
            }
        });

    }
}
