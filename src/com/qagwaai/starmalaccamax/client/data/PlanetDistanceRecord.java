/**
 * PlanetDistanceRecord.java
 * Created by pgirard at 11:13:39 AM on Dec 17, 2010
 * in the com.qagwaai.starmalaccamax.client.data package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.data;

import com.qagwaai.starmalaccamax.shared.model.DistanceDTO;
import com.qagwaai.starmalaccamax.shared.model.PlanetDistanceDTO;

/**
 * @author pgirard
 * 
 */
public class PlanetDistanceRecord extends PlanetRecord {
    /**
	 * 
	 */
    public static final String NUMBER_OF_JUMPS = "numberOfJumps";
    /**
	 * 
	 */
    public static final String DISTANCE_IN_AU = "auDistance";

    /**
	 * 
	 */
    public PlanetDistanceRecord() {

    }

    /**
     * @param planetDistance
     *            the object to hydrate from
     */
    public PlanetDistanceRecord(final PlanetDistanceDTO planetDistance) {
        super(planetDistance.getPlanet());
        setNumberOfJumps(planetDistance.getDistance().getJumps());
        setDistanceInAU(planetDistance.getDistance().getDistanceInAU());
    }

    /**
     * @return the auDistance
     */
    public final Double getDistanceInAU() {
        return getAttributeAsDouble(PlanetDistanceRecord.DISTANCE_IN_AU);
    }

    /**
     * @return the jumps
     */
    public final int getNumberOfJumps() {
        return getAttributeAsInt(PlanetDistanceRecord.NUMBER_OF_JUMPS);
    }

    /**
     * @param auDistance
     *            the auDistance to set
     */
    public final void setDistanceInAU(final Double auDistance) {
        setAttribute(PlanetDistanceRecord.DISTANCE_IN_AU, auDistance);
    }

    /**
     * @param jumps
     *            the jumps to set
     */
    public final void setNumberOfJumps(final int jumps) {
        setAttribute(PlanetDistanceRecord.NUMBER_OF_JUMPS, jumps);
    }

    /**
     * 
     * @return a solar system object from this record
     */
    public final PlanetDistanceDTO toPlanetDistance() {
        PlanetDistanceDTO pd = new PlanetDistanceDTO();
        pd.setPlanet(super.toPlanet());
        DistanceDTO d = new DistanceDTO();
        d.setDistanceInAU(getDistanceInAU());
        d.setJumps(getNumberOfJumps());
        pd.setDistance(d);
        return pd;
    }
}
