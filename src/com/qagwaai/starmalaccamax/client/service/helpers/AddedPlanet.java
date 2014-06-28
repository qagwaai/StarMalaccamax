/**
 * GotUser.java
 * Created by pgirard at 2:04:51 PM on Aug 19, 2010
 * in the com.qagwaai.starmalaccamax.client.service package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.service.helpers;

import com.qagwaai.starmalaccamax.client.service.action.AddPlanetResponse;
import com.qagwaai.starmalaccamax.shared.model.PlanetDTO;

/**
 * @author pgirard
 * 
 */
public abstract class AddedPlanet extends BaseAsyncCallback<AddPlanetResponse> {
    /**
     * notify that we got a new Planet
     * 
     * @param planet
     *            the Planet found by the command or null
     */
    public abstract void got(final PlanetDTO planet);

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void onFailure(final Throwable caught) {
        super.onFailure(caught);
        System.out.println("Failed to add planet");
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void onSuccess(final AddPlanetResponse result) {
        super.onSuccess(result);
        got(result.getPlanet());

    }

}
