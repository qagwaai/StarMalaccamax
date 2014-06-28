/**
 * GetAllUsersResponse.java
 * Created by pgirard at 2:07:29 PM on Aug 19, 2010
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
public final class GetAllPlanetsResponse extends AbstractResponse implements IsSerializable {

    /**
	 * 
	 */
    private ArrayList<PlanetDTO> planets;
    /**
	 * 
	 */
    private int totalPlanets;

    /**
     * @return the users
     */
    public ArrayList<PlanetDTO> getPlanets() {
        return planets;
    }

    /**
     * @return the totalPlanets
     */
    public int getTotalPlanets() {
        return totalPlanets;
    }

    /**
     * @param planets
     *            the users to set
     */
    public void setPlanets(final ArrayList<PlanetDTO> planets) {
        this.planets = planets;
    }

    /**
     * @param totalPlanets
     *            the totalPlanets to set
     */
    public void setTotalPlanets(final int totalPlanets) {
        this.totalPlanets = totalPlanets;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "GetAllPlanetsResponse [planets=" + planets + ", totalPlanets=" + totalPlanets + "]";
    }

}
