/**
 * GetCurrentUser.java
 * Created by pgirard at 3:57:10 PM on Jul 22, 2010
 * in the com.qagwaai.starmalaccamax.shared.services.actions package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.service.action;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.qagwaai.starmalaccamax.shared.model.PlanetDTO;

/**
 * @author pgirard
 * 
 */
public final class GetMarketForPlanet implements IsSerializable, Action<GetMarketForPlanetResponse> {
    /**
	 * 
	 */
    private PlanetDTO planet;

    /**
	 * 
	 */
    public GetMarketForPlanet() {

    }

    /**
     * 
     * @param planet
     *            the market's planet
     */
    public GetMarketForPlanet(final PlanetDTO planet) {
        this.planet = planet;
    }

    /**
     * @return the planet
     */
    public PlanetDTO getPlanet() {
        return planet;
    }

    /**
     * @param planet
     *            the planet to set
     */
    public void setPlanet(final PlanetDTO planet) {
        this.planet = planet;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "GetMarketForPlanet [planet=" + planet + "]";
    }

}
