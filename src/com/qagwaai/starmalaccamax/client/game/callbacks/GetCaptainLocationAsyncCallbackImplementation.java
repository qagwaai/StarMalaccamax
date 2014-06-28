package com.qagwaai.starmalaccamax.client.game.callbacks;

import com.qagwaai.starmalaccamax.client.game.mvp.NewCaptainViewImpl;
import com.qagwaai.starmalaccamax.client.service.action.GetLocationForNewCaptainResponse;
import com.qagwaai.starmalaccamax.client.service.helpers.BaseAsyncCallback;


/**
 * @author pgirard
 * 
 */
public final class GetCaptainLocationAsyncCallbackImplementation extends
    BaseAsyncCallback<GetLocationForNewCaptainResponse> {
    /**
	 * 
	 */
    private final NewCaptainViewImpl view;

    /**
     * @param view the view associated with this handler
     */
    public GetCaptainLocationAsyncCallbackImplementation(final NewCaptainViewImpl view) {
        super();
        this.view = view;
    }

    @Override
    public void onFailure(Throwable caught) {
        super.onFailure(caught);
        view.alert("Could not get location for new captain: " + caught.getMessage());
    }

    @Override
    public void onSuccess(GetLocationForNewCaptainResponse result) {
        super.onSuccess(result);
        view.insertLocation(result);

    }
}
