/**
 * GotUser.java
 * Created by pgirard at 2:04:51 PM on Aug 19, 2010
 * in the com.qagwaai.starmalaccamax.client.service package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.service.helpers;

import com.qagwaai.starmalaccamax.client.service.action.RemovePlanetResponse;
import com.qagwaai.starmalaccamax.shared.model.PlanetDTO;

/**
 * @author pgirard
 * 
 */
public abstract class RemovedPlanet extends BaseAsyncCallback<RemovePlanetResponse> {
    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void onFailure(final Throwable caught) {
        super.onFailure(caught);
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void onSuccess(final RemovePlanetResponse result) {
        super.onSuccess(result);
        removed(result.getPlanet());

    }

    /**
     * notify that we got a new planet
     * 
     * @param planet
     *            the planet found by the command or null
     */
    public abstract void removed(final PlanetDTO planet);

}
