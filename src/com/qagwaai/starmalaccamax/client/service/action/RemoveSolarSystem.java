/**
 * GetUser.java
 * Created by pgirard at 1:39:28 PM on Aug 19, 2010
 * in the com.qagwaai.starmalaccamax.shared.services.action package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.service.action;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.qagwaai.starmalaccamax.shared.model.SolarSystemDTO;

/**
 * @author pgirard
 * 
 */
public final class RemoveSolarSystem implements IsSerializable, Action<RemoveSolarSystemResponse> {

    /**
	 * 
	 */
    private SolarSystemDTO solarSystem;

    /**
	 * 
	 */
    public RemoveSolarSystem() {

    }

    /**
     * @param solarSystem
     *            the SolarSystem to remove
     */
    public RemoveSolarSystem(final SolarSystemDTO solarSystem) {
        this.solarSystem = solarSystem;
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
        return "RemoveSolarSystem [solarSystem=" + solarSystem + "]";
    }

}
