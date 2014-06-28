/**
 * TravelingVector.java
 * com.qagwaai.starmalaccamax.shared.model
 * StarMalaccamax
 * Created Mar 14, 2011 at 2:17:00 PM
 */
package com.qagwaai.starmalaccamax.shared.model;

/**
 * @author pgirard
 * 
 */
public interface TravelVector {
    /**
     * 
     * @return the x velocity
     */
    double getX();

    /**
     * 
     * @param x
     *            the x velocity
     */
    void setX(double x);

    /**
     * 
     * @return the y velocity
     */
    double getY();

    /**
     * 
     * @param y
     *            the y velocity
     */
    void setY(double y);

    /**
     * 
     * @return the z velocity
     */
    double getZ();

    /**
     * 
     * @param z
     *            the z velocity
     */
    void setZ(double z);
}
