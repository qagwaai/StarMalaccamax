/**
 * TravelingVectorDTO.java
 * com.qagwaai.starmalaccamax.shared.model
 * StarMalaccamax
 * Created Mar 14, 2011 at 2:18:23 PM
 */
package com.qagwaai.starmalaccamax.shared.model;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * @author pgirard
 * 
 */
@SuppressWarnings("serial")
public final class TravelVectorDTO implements IsSerializable, Serializable, TravelVector {

    /**
	 * 
	 */
    private double x;
    /**
	 * 
	 */
    private double y;
    /**
	 * 
	 */
    private double z;

    /**
     * {@inheritDoc}
     */
    @Override
    public double getX() {
        return x;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setX(final double x) {
        this.x = x;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getY() {
        return y;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setY(final double y) {
        this.y = y;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getZ() {
        return z;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setZ(final double z) {
        this.z = z;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "TravelVectorDTO [x=" + x + ", y=" + y + ", z=" + z + "]";
    }

    /**
     * @param x
     *            x vector
     * @param y
     *            y vector
     * @param z
     *            z vector
     */
    public TravelVectorDTO(final double x, final double y, final double z) {
        super();
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
	 * 
	 */
    public TravelVectorDTO() {
        super();
    }

}
