/**
 * SolarSystem.java
 * Created by pgirard at 3:47:13 PM on Aug 31, 2010
 * in the com.qagwaai.starmalaccamax.shared.model package
 * for the StarMalaccamax project
 */
package com.qagwaai.starmalaccamax.shared.model;

/**
 * @author pgirard
 * 
 */
public interface SolarSystem extends Model {
    /**
     * 
     * @return the alpha
     */
    double getAlpha();

    /**
     * 
     * @return the delta
     */
    double getDelta();

    /**
     * 
     * @return the habitable orbit
     */
    int getHabitableOrbit();

    /**
     * 
     * @return the HIP
     */
    int getHIP();

    /**
     * @return the id
     */
    Long getId();

    /**
     * 
     * @return the maximum orbits
     */
    int getMaximumOrbits();

    /**
     * 
     * @return the name
     */
    String getName();

    /**
     * 
     * @return the number of components
     */
    int getNumberOfComponents();

    /**
     * 
     * @return the number of gas giants
     */
    int getNumberOfGasGiants();

    /**
     * 
     * @return the number of planetoid belts
     */
    int getNumberOfPlanetoidBelts();

    /**
     * 
     * @return the parallax
     */
    double getParallax();

    /**
     * 
     * @return the x coordinate
     */
    double getX();

    /**
     * 
     * @return the y coordinate
     */
    double getY();

    /**
     * 
     * @return the z coordinate
     */
    double getZ();

    /**
     * 
     * @param alpha
     *            the alpha
     */
    void setAlpha(double alpha);

    /**
     * 
     * @param delta
     *            the delta
     */
    void setDelta(double delta);

    /**
     * 
     * @param habitableOrbit
     *            the habitable orbit
     */
    void setHabitableOrbit(int habitableOrbit);

    /**
     * 
     * @param hip
     *            the HIP
     */
    void setHIP(int hip);

    /**
     * @param id
     *            the id to set
     */
    void setId(final Long id);

    /**
     * 
     * @param maximumOrbits
     *            the maximum orbits
     */
    void setMaximumOrbits(int maximumOrbits);

    /**
     * 
     * @param name
     *            the name
     */
    void setName(String name);

    /**
     * 
     * @param numberOfComponents
     *            the number of components
     */
    void setNumberOfComponents(int numberOfComponents);

    /**
     * 
     * @param gasGiants
     *            the number of gas giants
     */
    void setNumberOfGasGiants(int gasGiants);

    /**
     * 
     * @param planetoidBelts
     *            the number of planetoid belts
     */
    void setNumberOfPlanetoidBelts(int planetoidBelts);

    /**
     * 
     * @param parallax
     *            the parallax
     */
    void setParallax(double parallax);

    /**
     * 
     * @param x
     *            the x coordinate
     */
    void setX(double x);

    /**
     * 
     * @param y
     *            the y coordinate
     */
    void setY(double y);

    /**
     * 
     * @param z
     *            the z coordinate
     */
    void setZ(double z);
}
