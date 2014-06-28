/**
 * GetUser.java
 * Created by pgirard at 1:39:28 PM on Aug 19, 2010
 * in the com.qagwaai.starmalaccamax.shared.services.action package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.service.action;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.qagwaai.starmalaccamax.shared.model.PlanetDTO;

/**
 * @author pgirard
 * 
 */
public final class RemovePlanet implements IsSerializable, Action<RemovePlanetResponse> {

    /**
	 * 
	 */
    private PlanetDTO planet;

    /**
	 * 
	 */
    public RemovePlanet() {

    }

    /**
     * @param planet
     *            the Planet to remove
     */
    public RemovePlanet(final PlanetDTO planet) {
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
        return "RemovePlanet [planet=" + planet + "]";
    }

}
