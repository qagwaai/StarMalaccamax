/**
 * GetPlanetResponse.java
 * Created by pgirard at 1:40:09 PM on Aug 19, 2010
 * in the com.qagwaai.starmalaccamax.shared.services.action package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.service.action;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.qagwaai.starmalaccamax.shared.MimeType;
import com.qagwaai.starmalaccamax.shared.model.PlanetDTO;

/**
 * @author pgirard
 * 
 */
public final class GetPlanetsForSolarSystemResponseInRPC extends AbstractPolyResponse implements IsSerializable, GetPlanetsForSolarSystemResponse {
    /**
	 * 
	 */
    private ArrayList<PlanetDTO> planets;

    /**
     * @return the planets
     */
    public ArrayList<PlanetDTO> getPlanets() {
        return planets;
    }

    /**
     * @param planets
     *            the planets to set
     */
    public void setPlanets(final ArrayList<PlanetDTO> planets) {
        this.planets = planets;
    }

    @Override
    public String toString() {
	final int maxLen = 10;
	return "GetPlanetsForSolarSystemResponseInRPC [planets=" + (planets != null ? planets.subList(0, Math.min(planets.size(), maxLen)) : null) + ", toString()="
		+ super.toString() + "]";
    }

    public GetPlanetsForSolarSystemResponseInRPC() {
	super();
	super.setMimeType(MimeType.rpc);
    }



}
