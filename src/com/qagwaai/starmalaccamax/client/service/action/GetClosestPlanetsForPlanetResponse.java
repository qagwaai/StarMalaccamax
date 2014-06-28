/**
 * GetCurrentUserResponse.java
 * Created by pgirard at 3:57:56 PM on Jul 22, 2010
 * in the com.qagwaai.starmalaccamax.shared.services.actions package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.service.action;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.qagwaai.starmalaccamax.shared.model.PlanetDTO;
import com.qagwaai.starmalaccamax.shared.model.PlanetDistanceDTO;

/**
 * @author pgirard
 * 
 */
public final class GetClosestPlanetsForPlanetResponse extends AbstractResponse implements IsSerializable {

    /**
	 * 
	 */
    private ArrayList<PlanetDistanceDTO> planets;

    /**
	 * 
	 */
    private PlanetDTO planet;

    /**
     * @return the planet
     */
    public PlanetDTO getPlanet() {
        return planet;
    }

    /**
     * @return the closest
     */
    public ArrayList<PlanetDistanceDTO> getPlanets() {
        return planets;
    }

    /**
     * @param planet
     *            the planet to set
     */
    public void setPlanet(final PlanetDTO planet) {
        this.planet = planet;
    }

    /**
     * @param planets
     *            the planets to set
     */
    public void setPlanets(final ArrayList<PlanetDistanceDTO> planets) {
        this.planets = planets;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "GetClosestPlanetsForPlanetResponse [planets=" + planets + ", planet=" + planet + "]";
    }

}
