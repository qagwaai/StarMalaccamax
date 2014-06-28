/**
 * GotUser.java
 * Created by pgirard at 2:04:51 PM on Aug 19, 2010
 * in the com.qagwaai.starmalaccamax.client.service package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.service.helpers;

import com.qagwaai.starmalaccamax.client.core.mvp.ErrorPresenter;
import com.qagwaai.starmalaccamax.client.service.action.AddSolarSystemResponse;
import com.qagwaai.starmalaccamax.shared.model.SolarSystemDTO;

/**
 * @author pgirard
 * 
 */
public abstract class AddedSolarSystem extends BaseAsyncCallback<AddSolarSystemResponse> {
    /**
     * notify that we got a new SolarSystem
     * 
     * @param solarSystem
     *            the SolarSystem found by the command or null
     */
    public abstract void got(final SolarSystemDTO solarSystem);

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
    public void onSuccess(final AddSolarSystemResponse result) {
        super.onSuccess(result);
        got(result.getSolarSystem());

    }

}
