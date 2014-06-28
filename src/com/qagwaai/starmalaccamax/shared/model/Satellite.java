/**
 * Satellite.java
 * Created by pgirard at 10:00:11 AM on Sep 27, 2010
 * in the com.qagwaai.starmalaccamax.shared.model package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.shared.model;

/**
 * @author pgirard
 * 
 */
public interface Satellite extends Model {

    /**
     * @return the id
     */
    Long getId();

    /**
     * 
     * @return the main planet id
     */
    Long getMainPlanetId();

    /**
     * 
     * @return the satellite id
     */
    Long getSatellitePlanetId();

    /**
     * @param id
     *            the id to set
     */
    void setId(final Long id);

    /**
     * 
     * @param id
     *            the main planet id
     */
    void setMainPlanetId(final Long id);

    /**
     * 
     * @param id
     *            the satellite id
     */
    void setSatellitePlanetId(final Long id);
}
