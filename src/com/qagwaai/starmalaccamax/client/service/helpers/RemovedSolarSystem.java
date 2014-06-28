/**
 * GotUser.java
 * Created by pgirard at 2:04:51 PM on Aug 19, 2010
 * in the com.qagwaai.starmalaccamax.client.service package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.service.helpers;

import com.qagwaai.starmalaccamax.client.core.mvp.ErrorPresenter;
import com.qagwaai.starmalaccamax.client.service.action.RemoveSolarSystemResponse;
import com.qagwaai.starmalaccamax.shared.model.SolarSystemDTO;

/**
 * @author pgirard
 * 
 */
public abstract class RemovedSolarSystem extends BaseAsyncCallback<RemoveSolarSystemResponse> {
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
    public void onSuccess(final RemoveSolarSystemResponse result) {
        super.onSuccess(result);
        removed(result.getSolarSystem());

    }

    /**
     * notify that we got a new solarSystem
     * 
     * @param solarSystem
     *            the solarSystem found by the command or null
     */
    public abstract void removed(final SolarSystemDTO solarSystem);

}
