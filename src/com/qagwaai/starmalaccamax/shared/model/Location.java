/**
 * Location.java
 * Created by pgirard at 2:29:33 PM on Nov 18, 2010
 * in the com.qagwaai.starmalaccamax.shared.model package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.shared.model;

/**
 * @author pgirard
 * 
 */
public interface Location {
    /**
     * 
     * @return the planet part of the location, if any
     */
    long getPlanetId();

    /**
     * 
     * @return the solarsystem part of the location
     */
    long getSolarSystemId();

    /**
     * 
     * @return the x cartesian coordinate within the solar system
     */
    long getX();

    /**
     * 
     * @return the y cartesian coordinate within the solar system
     */
    long getY();

    /**
     * 
     * @return the z cartesian coordinate within the solar system
     */
    long getZ();

    /**
     * 
     * @param planetId
     *            the planet part of the location, if any
     */
    void setPlanetId(long planetId);

    /**
     * 
     * @param solarSystemId
     *            the solarsystem part of the location
     */
    void setSolarSystemId(long solarSystemId);

    /**
     * 
     * @param x
     *            the x cartesian coordinate within the solar system
     */
    void setX(long x);

    /**
     * 
     * @param y
     *            the y cartesian coordinate within the solar system
     */
    void setY(long y);

    /**
     * 
     * @param z
     *            the z cartesian coordinate within the solar system
     */
    void setZ(long z);
}
