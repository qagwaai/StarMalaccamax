/**
 * AbstractShape.java
 * Created by pgirard at 2:21:31 PM on Feb 15, 2011
 * in the com.qagwaai.starmalaccamax.client.game.mvp.draw package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.game.mvp.draw;

/**
 * @author pgirard
 * 
 */
public abstract class AbstractShape implements Shape {
    /**
	 * 
	 */
    private int z;

    /**
	 * 
	 */
    public AbstractShape() {

    }

    /**
     * @param z
     *            the depth
     */
    public AbstractShape(final int z) {
        this.z = z;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getZ() {
        return z;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setZ(final int z) {
        this.z = z;

    }

}
