/**
 * LocationDTO.java
 * Created by pgirard at 12:55:52 PM on Nov 19, 2010
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

public final class LocationDTO implements Location, IsSerializable, Serializable {
    
    public static final String SOLAR_SYSTEM_ID = "solarSystemId";
    public static final String PLANET_ID = "planetID";
    public static final String X = "x";
    public static final String Y = "y";
    public static final String Z = "z";

    /**
	 * 
	 */
    private static final long serialVersionUID = 7301696119170900781L;
    /**
	 * 
	 */
    private long solarSystemId = 0L;
    /**
	 * 
	 */
    private long planetId = 0L;
    /**
	 * 
	 */
    private long x = 0L;
    /**
	 * 
	 */
    private long y = 0L;
    /**
	 * 
	 */
    private long z = 0L;

    /**
     * {@inheritDoc}
     */
    @Override
    public long getPlanetId() {
        return planetId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long getSolarSystemId() {
        return solarSystemId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long getX() {
        return x;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long getY() {
        return y;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long getZ() {
        return z;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPlanetId(final long planetId) {
        this.planetId = planetId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSolarSystemId(final long solarSystemId) {
        this.solarSystemId = solarSystemId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setX(final long x) {
        this.x = x;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setY(final long y) {
        this.y = y;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setZ(final long z) {
        this.z = z;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("LocationDTO [pId=");
        builder.append(planetId);
        builder.append(", ssId=");
        builder.append(solarSystemId);
        builder.append(", x=");
        builder.append(x);
        builder.append(", y=");
        builder.append(y);
        builder.append(", z=");
        builder.append(z);
        builder.append("]");
        return builder.toString();
    }

    /**
     * @param solarSystemId
     *            the solar system id
     * @param planetId
     *            the planet id
     * @param x
     *            the x coordinate
     * @param y
     *            the y coordinate
     * @param z
     *            the z coordinate
     */
    public LocationDTO(final long solarSystemId, final long planetId, final long x, final long y, final long z) {
        super();
        this.solarSystemId = solarSystemId;
        this.planetId = planetId;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
	 * 
	 */
    public LocationDTO() {
        super();
    }

}
