/**
 * GetAllUsersResponse.java
 * Created by pgirard at 2:07:29 PM on Aug 19, 2010
 * in the com.qagwaai.starmalaccamax.shared.services.action package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.service.action;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.qagwaai.starmalaccamax.shared.model.PlanetDTO;
import com.qagwaai.starmalaccamax.shared.model.SolarSystemDTO;

/**
 * @author pgirard
 * 
 */
public final class GetLocationForNewCaptainResponse extends AbstractResponse implements IsSerializable {

    /**
	 * 
	 */
    private PlanetDTO planet;

    private SolarSystemDTO solarSystem;

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
     * @return the solarSystem
     */
    public SolarSystemDTO getSolarSystem() {
        return solarSystem;
    }

    /**
     * @param solarSystem
     *            the solarSystem to set
     */
    public void setSolarSystem(final SolarSystemDTO solarSystem) {
        this.solarSystem = solarSystem;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "GetLocationForNewCaptainResponse [planet=" + planet + ", solarSystem=" + solarSystem + "]";
    }

}
