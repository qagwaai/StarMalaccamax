/**
 * StarShape.java
 * Created by pgirard at 2:17:40 PM on Feb 15, 2011
 * in the com.qagwaai.starmalaccamax.client.game.mvp.draw package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.game.mvp.draw;

import com.qagwaai.starmalaccamax.shared.model.PlanetDTO;

/**
 * @author pgirard
 * 
 */
public final class SatelliteShape extends AbstractShape {
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
    private float radius;
    /**
	 * 
	 */
    private String color;
    /**
	 * 
	 */
    private PlanetDTO planet;

    /**
	 * 
	 */
    public SatelliteShape() {

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
     * @param color
     *            the fill color
     */
    public SatelliteShape(final int orbit, final float cx, final float cy, final float radius, final String color) {
        this.orbit = orbit;
        this.cx = cx;
        this.cy = cy;
        this.radius = radius;
        this.color = color;
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
     * @param color
     *            the fill color
     */
    public SatelliteShape(final int orbit, final float cx, final float cy, final float radius, final String color,
        final int z) {
        super(z);
        this.orbit = orbit;
        this.cx = cx;
        this.cy = cy;
        this.radius = radius;
        this.color = color;
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
     * @param color
     *            the fill color
     * @param planet
     *            the planet associated with this shape
     */
    public SatelliteShape(final int orbit, final float cx, final float cy, final float radius, final String color,
        final PlanetDTO planet, final int z) {
        super(z);
        this.orbit = orbit;
        this.cx = cx;
        this.cy = cy;
        this.radius = radius;
        this.color = color;
        this.planet = planet;
    }

    /**
     * 
     * {@inheritDoc}
     */
    public boolean contains(final Point p) {
        return p.distanceTo(new Point(cx, cy)) <= radius;
    }

    /**
     * @return the color
     */
    public String getColor() {
        return color;
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
    public float getRadius() {
        return radius;
    }

    /**
     * @param color
     *            the color to set
     */
    public void setColor(final String color) {
        this.color = color;
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
    public void setRadius(final float radius) {
        this.radius = radius;
    }

}
