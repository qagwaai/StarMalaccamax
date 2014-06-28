/**
 * Closest.java
 * Created by pgirard at 11:35:48 AM on Dec 3, 2010
 * in the com.qagwaai.starmalaccamax.shared.model package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.shared.model;

/**
 * @author pgirard
 * 
 */
public interface Closest extends Model {
    /**
     * 
     * @return the closest unique identifier
     */
    Long getId();

    /**
     * 
     * @return the order of this entry in the closest calculation
     */
    int getNumberOfJumps();

    /**
     * 
     * @return closest is associated with a specific solar system
     */
    Long getSolarSystemId();

    /**
     * 
     * @return the to system reference
     */
    Long getToSolarSystemId();

    /**
     * 
     * @param id
     *            the closest unique identifier
     */
    void setId(Long id);

    /**
     * 
     * @param order
     *            the order of this entry in the closest calculation
     */
    void setNumberOfJumps(int order);

    /**
     * 
     * @param solarSystemId
     *            closest is associated with a specific solar system
     */
    void setSolarSystemId(Long solarSystemId);

    /**
     * 
     * @param toSolarSystemId
     *            the to system reference
     */
    void setToSolarSystemId(Long toSolarSystemId);
}
