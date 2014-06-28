/**
 * Shape.java
 * Created by pgirard at 2:16:56 PM on Feb 15, 2011
 * in the com.qagwaai.starmalaccamax.client.game.mvp.draw package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.game.mvp.draw;

/**
 * @author pgirard
 * 
 */
public interface Shape {
    /**
     * 
     * @param p
     *            the point to check
     * @return true if the point is contained within the shape
     */
    boolean contains(Point p);

    /**
     * 
     * @return the depth of the shape
     */
    int getZ();

    /**
     * 
     * @param z
     *            the depth of the shape
     */
    void setZ(int z);
}
