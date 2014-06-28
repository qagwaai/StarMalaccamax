/**
 * CaptainSolarSystemDTO.java
 * Created by pgirard at 12:29:26 PM on Jan 19, 2011
 * in the com.qagwaai.starmalaccamax.shared.model package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.shared.model;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * @author pgirard
 * 
 */
@SuppressWarnings("serial")
public final class CaptainSolarSystemDTO implements IsSerializable, Serializable {
    /**
	 * 
	 */
    private CaptainDTO captain;
    /**
	 * 
	 */
    private SolarSystemDTO solarSystem;

    /**
	 * 
	 */
    public CaptainSolarSystemDTO() {

    }

    /**
     * @param captain
     *            the captain
     * @param solarSystem
     *            the solar system
     */
    public CaptainSolarSystemDTO(final CaptainDTO captain, final SolarSystemDTO solarSystem) {
        this.captain = captain;
        this.solarSystem = solarSystem;
    }

    /**
     * @return the captain
     */
    public CaptainDTO getCaptain() {
        return captain;
    }

    /**
     * @return the solarSystem
     */
    public SolarSystemDTO getSolarSystem() {
        return solarSystem;
    }

    /**
     * @param captain
     *            the captain to set
     */
    public void setCaptain(final CaptainDTO captain) {
        this.captain = captain;
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
        return "CaptainSolarSystemDTO [captain=" + captain + ", solarSystem=" + solarSystem + "]";
    }
}
