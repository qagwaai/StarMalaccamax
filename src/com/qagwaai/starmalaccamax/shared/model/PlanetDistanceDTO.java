/**
 * PlanetDistanceDTO.java
 * Created by pgirard at 9:54:16 AM on Dec 17, 2010
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
public class PlanetDistanceDTO implements IsSerializable, Serializable {
    /**
	 * 
	 */
    private PlanetDTO planet;
    /**
	 * 
	 */
    private DistanceDTO distance;

    /**
	 * 
	 */
    public PlanetDistanceDTO() {

    }

    /**
     * @param planet
     *            the planet
     * @param distance
     *            and the distance to that planet
     */
    public PlanetDistanceDTO(final PlanetDTO planet, final DistanceDTO distance) {
        this.planet = planet;
        this.distance = distance;
    }

    /**
     * @return the distance
     */
    public final DistanceDTO getDistance() {
        return distance;
    }

    /**
     * @return the planet
     */
    public final PlanetDTO getPlanet() {
        return planet;
    }

    /**
     * @param distance
     *            the distance to set
     */
    public final void setDistance(final DistanceDTO distance) {
        this.distance = distance;
    }

    /**
     * @param planet
     *            the planet to set
     */
    public final void setPlanet(final PlanetDTO planet) {
        this.planet = planet;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "PlanetDistanceDTO [planet=" + planet + ", distance=" + distance + "]";
    }

}
