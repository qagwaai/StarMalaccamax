/**
 * JumpGate.java
 * Created by pgirard at 1:36:18 PM on Oct 10, 2010
 * in the com.qagwaai.starmalaccamax.shared.model package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.shared.model;

/**
 * @author pgirard
 * 
 */
public interface JumpGate extends Model {
    /**
     * @return the id
     */
    Long getId();

    /**
     * 
     * @return the first solar system attached to the gate
     */
    LocationDTO getLocation1();

    /**
     * 
     * @return the second solar system attached to the gate
     */
    LocationDTO getLocation2();

    /**
     * 
     * @return the id of the "owner" solar system
     */
    Long getSolarSystem1Id();

    /**
     * 
     * @return the id of the the "to" solar system
     */
    Long getSolarSystem2Id();

    /**
     * @param id
     *            the id to set
     */
    void setId(final Long id);

    /**
     * 
     * @param location
     *            the first solar system attached to the gate
     */
    void setLocation1(LocationDTO location);

    /**
     * 
     * @param location
     *            the second solar system attached to the gate
     */
    void setLocation2(LocationDTO location);

    /**
     * 
     * @param id
     *            the id of the "owner" solar system
     */
    void setSolarSystem1Id(final Long id);

    /**
     * 
     * @param id
     *            the id of the "to" solar system
     */
    void setSolarSystem2Id(final Long id);

    /**
     * 
     * @return the amount of time that this gate will fail
     */
    int getFailurePct();

    /**
     * 
     * @param pct
     *            the amount of time that this gate will fail
     */
    void setFailurePct(int pct);
}
