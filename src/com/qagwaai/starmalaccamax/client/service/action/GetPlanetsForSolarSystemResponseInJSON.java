/**
 * GetPlanetResponse.java
 * Created by pgirard at 1:40:09 PM on Aug 19, 2010
 * in the com.qagwaai.starmalaccamax.shared.services.action package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.service.action;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.qagwaai.starmalaccamax.shared.MimeType;

/**
 * @author pgirard
 * 
 */
public final class GetPlanetsForSolarSystemResponseInJSON extends AbstractPolyResponse implements IsSerializable, GetPlanetsForSolarSystemResponse {
    /**
	 * 
	 */
    private String planets;

    /**
     * @return the planets
     */
    public String getPlanets() {
        return planets;
    }

    /**
     * @param planets
     *            the planets to set
     */
    public void setPlanets(final String planets) {
        this.planets = planets;
    }

    @Override
    public String toString() {
	return "GetPlanetsForSolarSystemResponseInJSON [planets=" + planets + ", toString()=" + super.toString() + "]";
    }

    public GetPlanetsForSolarSystemResponseInJSON() {
	super();
	super.setMimeType(MimeType.js);
    }

}
