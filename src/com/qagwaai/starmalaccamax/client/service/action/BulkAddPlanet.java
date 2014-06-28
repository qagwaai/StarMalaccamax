/**
 * GetUser.java
 * Created by pgirard at 1:39:28 PM on Aug 19, 2010
 * in the com.qagwaai.starmalaccamax.shared.services.action package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.service.action;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.qagwaai.starmalaccamax.shared.model.PlanetDTO;

/**
 * @author pgirard
 * 
 */
public final class BulkAddPlanet implements IsSerializable, Action<BulkAddPlanetResponse> {

    /**
	 * 
	 */
    private ArrayList<PlanetDTO> planets;

    /**
	 * 
	 */
    public BulkAddPlanet() {

    }

    /**
     * @param planets
     *            the Planets to add
     */
    public BulkAddPlanet(final ArrayList<PlanetDTO> planets) {
        this.planets = planets;
    }

    /**
     * @return the planets
     */
    public ArrayList<PlanetDTO> getPlanets() {
        return planets;
    }

    /**
     * @param planets
     *            the Planets to set
     */
    public void setPlanets(final ArrayList<PlanetDTO> planets) {
        this.planets = planets;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        final int maxLen = 10;
        return "BulkAddPlanet [planets="
            + (planets != null ? planets.subList(0, Math.min(planets.size(), maxLen)) : null) + "]";
    }

}
