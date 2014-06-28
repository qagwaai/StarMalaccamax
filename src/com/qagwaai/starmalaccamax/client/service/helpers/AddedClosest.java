/**
 * GotUser.java
 * Created by pgirard at 2:04:51 PM on Aug 19, 2010
 * in the com.qagwaai.closestmalaccamax.client.service package
 * for the ClosestMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.service.helpers;

import com.qagwaai.starmalaccamax.client.core.mvp.ErrorPresenter;
import com.qagwaai.starmalaccamax.client.service.action.AddClosestResponse;
import com.qagwaai.starmalaccamax.shared.model.ClosestDTO;

/**
 * @author pgirard
 * 
 */
public abstract class AddedClosest extends BaseAsyncCallback<AddClosestResponse> {
    /**
     * notify that we got a new user
     * 
     * @param closest
     *            the Closest found by the command or null
     */
    public abstract void got(final ClosestDTO closest);

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
    public void onSuccess(final AddClosestResponse result) {
        super.onSuccess(result);
        got(result.getClosest());

    }

}
