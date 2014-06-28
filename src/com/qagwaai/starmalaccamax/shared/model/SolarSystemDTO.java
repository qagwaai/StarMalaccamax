/**
 * SolarSystemDTO.java
 * Created by pgirard at 11:40:36 AM on Sep 1, 2010
 * in the com.qagwaai.starmalaccamax.shared.model package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.shared.model;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.googlecode.objectify.annotation.Entity;

/**
 * @author pgirard
 * 
 */
@SuppressWarnings("serial")
@Entity
public final class SolarSystemDTO implements IsSerializable, Serializable, SolarSystem {

    /**
	 * 
	 */
    @com.google.code.twig.annotation.Id
    @com.googlecode.objectify.annotation.Id
    private Long id;
    /**
	 * 
	 */
    private double alpha;
    /**
	 * 
	 */
    private double delta;
    /**
	 * 
	 */
    private int hip;
    /**
	 * 
	 */
    private String name;
    /**
	 * 
	 */
    private int numberOfComponents;
    /**
	 * 
	 */
    private double parallax;
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
	 * 
	 */
    private int maximumOrbits;

    /**
	 * 
	 */
    private int habitableOrbit;

    /**
	 * 
	 */
    private int gasGiants;

    /**
	 * 
	 */
    private int planetoidBelts;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        SolarSystemDTO other = (SolarSystemDTO) obj;
        if (gasGiants != other.gasGiants) {
            return false;
        }
        if (habitableOrbit != other.habitableOrbit) {
            return false;
        }
        if (hip != other.hip) {
            return false;
        }
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        if (maximumOrbits != other.maximumOrbits) {
            return false;
        }
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        if (numberOfComponents != other.numberOfComponents) {
            return false;
        }
        if (planetoidBelts != other.planetoidBelts) {
            return false;
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getAlpha() {
        return alpha;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getDelta() {
        return delta;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public int getHabitableOrbit() {

        return habitableOrbit;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getHIP() {
        return hip;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long getId() {
        return id;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public int getMaximumOrbits() {
        return maximumOrbits;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getNumberOfComponents() {
        return numberOfComponents;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public int getNumberOfGasGiants() {
        return gasGiants;
    }

    /**
     * @return the planetoidBelts
     */
    public int getNumberOfPlanetoidBelts() {
        return planetoidBelts;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getParallax() {
        return parallax;
    }

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
    public double getY() {
        return y;
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
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + gasGiants;
        result = prime * result + habitableOrbit;
        result = prime * result + hip;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + maximumOrbits;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + numberOfComponents;
        result = prime * result + planetoidBelts;
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAlpha(final double alpha) {
        this.alpha = alpha;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDelta(final double delta) {
        this.delta = delta;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void setHabitableOrbit(final int habitableOrbit) {
        this.habitableOrbit = habitableOrbit;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setHIP(final int hip) {
        this.hip = hip;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setId(final Long id) {
        this.id = id;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void setMaximumOrbits(final int maximumOrbits) {
        this.maximumOrbits = maximumOrbits;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setNumberOfComponents(final int numberOfComponents) {
        this.numberOfComponents = numberOfComponents;
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void setNumberOfGasGiants(final int gasGiants) {
        this.gasGiants = gasGiants;
    }

    /**
     * @param planetoidBelts
     *            the planetoidBelts to set
     */
    public void setNumberOfPlanetoidBelts(final int planetoidBelts) {
        this.planetoidBelts = planetoidBelts;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setParallax(final double parallax) {
        this.parallax = parallax;
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
    public void setY(final double y) {
        this.y = y;
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
        StringBuilder builder = new StringBuilder();
        builder.append("SolarSystemDTO [alpha=");
        builder.append(alpha);
        builder.append(", delta=");
        builder.append(delta);
        builder.append(", gasGiants=");
        builder.append(gasGiants);
        builder.append(", habitableOrbit=");
        builder.append(habitableOrbit);
        builder.append(", hip=");
        builder.append(hip);
        builder.append(", id=");
        builder.append(id);
        builder.append(", maximumOrbits=");
        builder.append(maximumOrbits);
        builder.append(", name=");
        builder.append(name);
        builder.append(", numberOfComponents=");
        builder.append(numberOfComponents);
        builder.append(", parallax=");
        builder.append(parallax);
        builder.append(", planetoidBelts=");
        builder.append(planetoidBelts);
        builder.append(", x=");
        builder.append(x);
        builder.append(", y=");
        builder.append(y);
        builder.append(", z=");
        builder.append(z);
        builder.append("]");
        return builder.toString();
    }

}
