package com.qagwaai.starmalaccamax.client.game.mvp.handlers;

import java.util.HashMap;
import java.util.Map;

import com.qagwaai.starmalaccamax.client.game.mvp.NewCaptainView;
import com.qagwaai.starmalaccamax.client.service.ServiceFactory;
import com.qagwaai.starmalaccamax.client.service.action.CaptainNameCheck;
import com.qagwaai.starmalaccamax.client.service.action.CaptainNameCheckResponse;
import com.qagwaai.starmalaccamax.client.service.helpers.BaseAsyncCallback;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;




/**
 * @author pgirard
 * 
 */
public final class CaptainNameChangedHandlerImplementation implements ChangedHandler {
    /**
	 * 
	 */
    private final NewCaptainView view;

    /**
     * @param view the view associated with this handler
     */
    public CaptainNameChangedHandlerImplementation(final NewCaptainView view) {
        this.view = view;
    }

    @Override
    public void onChanged(ChangedEvent event) {
        String name = (String) event.getValue();

        @SuppressWarnings("serial") final Map<String, String> errors = new HashMap<String, String>() {
            {
                put("captainName", "Must be more than 4 characters and unique");
            }
        };
        if (name != null) {
            if (!name.isEmpty()) {
                if (name.length() > 4) {
                    ServiceFactory.getCaptainService().execute(new CaptainNameCheck(name),
                        new BaseAsyncCallback<CaptainNameCheckResponse>() {

                            @Override
                            public void onFailure(Throwable caught) {
                                super.onFailure(caught);
                                errors.put("captainName", "Could not validate: " + caught.getMessage());
                            }

                            @Override
                            public void onSuccess(CaptainNameCheckResponse result) {
                                super.onSuccess(result);
                                if (result.exists()) {
                                    errors.put("captainName", "Name is taken");
                                    view.getNameForm().setErrors(errors, true);
                                } else {
                                    errors.remove("captainName");
                                    view.getNameForm().setErrors(errors, true);
                                }
                            }
                        });
                } else {
                    view.getNameForm().setErrors(errors, true);
                }
            }
        }
    }
}