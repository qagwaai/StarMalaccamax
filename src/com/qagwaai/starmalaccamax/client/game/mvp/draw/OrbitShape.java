/**
 * OrbitShape.java
 * Created by pgirard at 2:17:24 PM on Feb 15, 2011
 * in the com.qagwaai.starmalaccamax.client.game.mvp.draw package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.game.mvp.draw;

import com.qagwaai.starmalaccamax.shared.model.PlanetDTO;

/**
 * @author pgirard
 * 
 */
public final class OrbitShape extends AbstractShape {
    /**
	 * 
	 */
    private int orbit;
    /**
	 * 
	 */
    private float cx;
    /**
	 * 
	 */
    private float cy;
    /**
	 * 
	 */
    private int radius;
    /**
	 * 
	 */
    private PlanetDTO planet;

    /**
	 * 
	 */
    public OrbitShape() {

    }

    /**
     * @param orbit
     *            the orbit number
     * @param cx
     *            center x position
     * @param cy
     *            center y position
     * @param radius
     *            the radius
     */
    public OrbitShape(final int orbit, final float cx, final float cy, final int radius) {
        this.orbit = orbit;
        this.cx = cx;
        this.cy = cy;
        this.radius = radius;
    }

    /**
     * @param z
     *            the depth to draw the shape at
     * @param orbit
     *            the orbit number
     * @param cx
     *            center x position
     * @param cy
     *            center y position
     * @param radius
     *            the radius
     */
    public OrbitShape(final int orbit, final float cx, final float cy, final int radius, final int z) {
        super(z);
        this.orbit = orbit;
        this.cx = cx;
        this.cy = cy;
        this.radius = radius;
    }

    /**
     * @param z
     *            the depth to draw the shape at
     * @param orbit
     *            the orbit number
     * @param cx
     *            center x position
     * @param cy
     *            center y position
     * @param radius
     *            the radius
     * @param planet
     *            the planet associated with this shape
     */
    public OrbitShape(final int orbit, final float cx, final float cy, final int radius, final PlanetDTO planet,
        final int z) {
        super(z);
        this.orbit = orbit;
        this.cx = cx;
        this.cy = cy;
        this.radius = radius;
        this.planet = planet;
    }

    /**
     * 
     * {@inheritDoc}
     */
    public boolean contains(final Point p) {
        return p.distanceTo(new Point(cx, cy)) <= radius;
    }



    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + orbit;
        result = prime * result + ((planet == null) ? 0 : planet.hashCode());
        result = prime * result + radius;
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
        OrbitShape other = (OrbitShape) obj;
        if (orbit != other.orbit) {
            return false;
        }
        if (planet == null) {
            if (other.planet != null) {
                return false;
            }
        } else if (!planet.equals(other.planet)) {
            return false;
        }
        if (radius != other.radius) {
            return false;
        }
        return true;
    }

    /**
     * @return the cx
     */
    public float getCx() {
        return cx;
    }

    /**
     * @return the cy
     */
    public float getCy() {
        return cy;
    }

    /**
     * @return the orbit
     */
    public int getOrbit() {
        return orbit;
    }

    /**
     * @return the planet
     */
    public PlanetDTO getPlanet() {
        return planet;
    }

    /**
     * @return the radius
     */
    public int getRadius() {
        return radius;
    }

    /**
     * @param cx
     *            the cx to set
     */
    public void setCx(final float cx) {
        this.cx = cx;
    }

    /**
     * @param cy
     *            the cy to set
     */
    public void setCy(final float cy) {
        this.cy = cy;
    }

    /**
     * @param orbit
     *            the orbit to set
     */
    public void setOrbit(final int orbit) {
        this.orbit = orbit;
    }

    /**
     * @param planet
     *            the planet to set
     */
    public void setPlanet(final PlanetDTO planet) {
        this.planet = planet;
    }

    /**
     * @param radius
     *            the radius to set
     */
    public void setRadius(final int radius) {
        this.radius = radius;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "OrbitShape [cx=" + cx + ", cy=" + cy + ", orbit=" + orbit + ", radius=" + radius + ", getZ()=" + getZ()
            + "]";
    }

}
