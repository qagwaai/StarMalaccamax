package com.qagwaai.starmalaccamax.client.admin.mvp.handlers;

import com.qagwaai.starmalaccamax.client.core.mvp.LoginBarViewImpl;
import com.qagwaai.starmalaccamax.client.service.ServiceFactory;
import com.qagwaai.starmalaccamax.client.service.action.RemoveAllJumpGates;
import com.qagwaai.starmalaccamax.client.service.action.RemoveAllJumpGatesResponse;
import com.qagwaai.starmalaccamax.client.service.helpers.BaseAsyncCallback;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;

/**
 * 
 * @author pgirard
 * 
 */
public final class RemoveAllJumpGatesClickHandlerImpl implements com.smartgwt.client.widgets.menu.events.ClickHandler {
    /**
	 * 
	 */
    private final LoginBarViewImpl view;

    /**
     * 
     * @param view
     *            the associated view
     */
    public RemoveAllJumpGatesClickHandlerImpl(final LoginBarViewImpl view) {
        this.view = view;
    }

    @Override
    public void onClick(final MenuItemClickEvent event) {
        SC.confirm("Proceed with remove ALL jump gates?", new BooleanCallback() {
            public void execute(final Boolean value) {
                if (value != null && value) {
                    ServiceFactory.getJumpGateService().execute(new RemoveAllJumpGates(),
                        new BaseAsyncCallback<RemoveAllJumpGatesResponse>() {

                            @Override
                            public void onFailure(final Throwable caught) {
                                super.onFailure(caught);
                                view.say("Remove All Jump Gates",
                                    "Error removing all jump gates: " + caught.getMessage());

                            }

                            @Override
                            public void onSuccess(final RemoveAllJumpGatesResponse result) {
                                super.onSuccess(result);
                                view.say("Remove All Jump Gates", "Success");

                            }
                        });
                }
            }
        });

    }
}
