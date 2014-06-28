/**
 * Point.java
 * Created by pgirard at 2:26:32 PM on Feb 15, 2011
 * in the com.qagwaai.starmalaccamax.client.game.mvp.draw package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.game.mvp.draw;

/**
 * @author pgirard
 * 
 */
public final class Point {
    /**
	 * 
	 */
    private float x;
    /**
	 * 
	 */
    private float y;

    /**
	 * 
	 */
    public Point() {

    }

    /**
     * @param x
     *            x
     * @param y
     *            y
     */
    public Point(final float x, final float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * 
     * @param that
     *            the point to calculate the distance to
     * @return the distance
     */
    public double distanceTo(final Point that) {
        double dx = this.x - that.x;
        double dy = this.y - that.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

 

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Point other = (Point) obj;
        return true;
    }

    /**
     * @return the x
     */
    public float getX() {
        return x;
    }

    /**
     * @return the y
     */
    public float getY() {
        return y;
    }

    /**
     * @param x
     *            the x to set
     */
    public void setX(final float x) {
        this.x = x;
    }

    /**
     * @param y
     *            the y to set
     */
    public void setY(final float y) {
        this.y = y;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Point [x=" + x + ", y=" + y + "]";
    }
}
