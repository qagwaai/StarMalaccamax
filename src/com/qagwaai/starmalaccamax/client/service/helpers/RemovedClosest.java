/**
 * GotUser.java
 * Created by pgirard at 2:04:51 PM on Aug 19, 2010
 * in the com.qagwaai.closestmalaccamax.client.service package
 * for the ClosestMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.service.helpers;

import com.qagwaai.starmalaccamax.client.core.mvp.ErrorPresenter;
import com.qagwaai.starmalaccamax.client.service.action.RemoveClosestResponse;
import com.qagwaai.starmalaccamax.shared.model.ClosestDTO;

/**
 * @author pgirard
 * 
 */
public abstract class RemovedClosest extends BaseAsyncCallback<RemoveClosestResponse> {
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
    public void onSuccess(final RemoveClosestResponse result) {
        super.onSuccess(result);
        removed(result.getClosest());

    }

    /**
     * notify that we got a new closest
     * 
     * @param closest
     *            the closest found by the command or null
     */
    public abstract void removed(final ClosestDTO closest);

}
