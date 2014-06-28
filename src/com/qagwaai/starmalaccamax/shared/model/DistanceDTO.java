/**
 * DistanceDTO.java
 * Created by pgirard at 9:51:38 AM on Dec 17, 2010
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
public final class DistanceDTO implements Distance, IsSerializable, Serializable {

    /**
	 * 
	 */
    private int jumps;
    /**
	 * 
	 */
    private Double auDistance;

    /**
	 * 
	 */
    private Long kmDistance;

    /**
	 * 
	 */
    public DistanceDTO() {

    }

    /**
     * @param jumps
     *            the number of jumps between one location and another
     * @param auDistance
     *            the distance in AU
     */
    public DistanceDTO(final int jumps, final Double auDistance) {
        this.jumps = jumps;
        this.auDistance = auDistance;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Double getDistanceInAU() {
        return auDistance;
    }

    /**
     * @return the kmDistance
     */
    public Long getDistanceInKM() {
        return kmDistance;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getJumps() {
        return jumps;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDistanceInAU(final Double distance) {
        this.auDistance = distance;
    }

    /**
     * @param kmDistance
     *            the kmDistance to set
     */
    public void setDistanceInKM(final Long kmDistance) {
        this.kmDistance = kmDistance;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setJumps(final int jumps) {
        this.jumps = jumps;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "DistanceDTO [jumps=" + jumps + ", auDistance=" + auDistance + ", kmDistance=" + kmDistance + "]";
    }

}
