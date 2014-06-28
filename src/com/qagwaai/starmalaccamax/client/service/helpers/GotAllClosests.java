/**
 * GotUser.java
 * Created by pgirard at 2:04:51 PM on Aug 19, 2010
 * in the com.qagwaai.starmalaccamax.client.service package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.service.helpers;

import java.util.ArrayList;

import com.qagwaai.starmalaccamax.client.core.mvp.ErrorPresenter;
import com.qagwaai.starmalaccamax.client.service.action.GetAllClosestsResponse;
import com.qagwaai.starmalaccamax.shared.model.ClosestDTO;

/**
 * @author pgirard
 * 
 */
public abstract class GotAllClosests extends BaseAsyncCallback<GetAllClosestsResponse> {
    /**
     * notify that we got a list of Closests
     * 
     * @param closests
     *            the Closests found by the command or null
     */
    public abstract void got(final ArrayList<ClosestDTO> closests);

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void onFailure(final Throwable caught) {
        super.onFailure(caught);
        ErrorPresenter.present(caught);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void onSuccess(final GetAllClosestsResponse result) {
        super.onSuccess(result);
        got(result.getClosests());

    }

}
