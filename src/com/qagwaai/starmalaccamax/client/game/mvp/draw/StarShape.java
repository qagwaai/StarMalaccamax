/**
 * StarShape.java
 * Created by pgirard at 2:17:40 PM on Feb 15, 2011
 * in the com.qagwaai.starmalaccamax.client.game.mvp.draw package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.client.game.mvp.draw;

import com.qagwaai.starmalaccamax.shared.model.StarDTO;

/**
 * @author pgirard
 * 
 */
public final class StarShape extends AbstractShape {
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
    private String color;
    /**
	 * 
	 */
    private String description;
    /**
	 * 
	 */
    private StarDTO star;

    /**
	 * 
	 */
    public StarShape() {

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
     * @param description
     *            the description of the star
     */
    public StarShape(final int orbit, final float cx, final float cy, final int radius, final String color,
        final String description) {
        this.orbit = orbit;
        this.cx = cx;
        this.cy = cy;
        this.radius = radius;
        this.color = color;
        this.description = description;
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
     * @param description
     *            the description of the star
     */
    public StarShape(final int orbit, final float cx, final float cy, final int radius, final String color,
        final String description, final int z) {
        super(z);
        this.orbit = orbit;
        this.cx = cx;
        this.cy = cy;
        this.radius = radius;
        this.color = color;
        this.description = description;
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
     * @param description
     *            the description of the star
     * @param star
     *            the star associated with this shape
     */
    public StarShape(final int orbit, final float cx, final float cy, final int radius, final String color,
        final String description, final StarDTO star, final int z) {
        super(z);
        this.orbit = orbit;
        this.cx = cx;
        this.cy = cy;
        this.radius = radius;
        this.color = color;
        this.description = description;
        this.star = star;
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
        result = prime * result + ((color == null) ? 0 : color.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + orbit;
        result = prime * result + radius;
        result = prime * result + ((star == null) ? 0 : star.hashCode());
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
        StarShape other = (StarShape) obj;
        if (color == null) {
            if (other.color != null) {
                return false;
            }
        } else if (!color.equals(other.color)) {
            return false;
        }
        if (description == null) {
            if (other.description != null) {
                return false;
            }
        } else if (!description.equals(other.description)) {
            return false;
        }
        if (orbit != other.orbit) {
            return false;
        }
        if (radius != other.radius) {
            return false;
        }
        if (star == null) {
            if (other.star != null) {
                return false;
            }
        } else if (!star.equals(other.star)) {
            return false;
        }
        return true;
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
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return the orbit
     */
    public int getOrbit() {
        return orbit;
    }

    /**
     * @return the radius
     */
    public int getRadius() {
        return radius;
    }

    /**
     * @return the star
     */
    public StarDTO getStar() {
        return star;
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
     * @param description
     *            the description to set
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * @param orbit
     *            the orbit to set
     */
    public void setOrbit(final int orbit) {
        this.orbit = orbit;
    }

    /**
     * @param radius
     *            the radius to set
     */
    public void setRadius(final int radius) {
        this.radius = radius;
    }

    /**
     * @param star
     *            the star to set
     */
    public void setStar(final StarDTO star) {
        this.star = star;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "StarShape [color=" + color + ", cx=" + cx + ", cy=" + cy + ", description=" + description + ", orbit="
            + orbit + ", radius=" + radius + "]";
    }

}
